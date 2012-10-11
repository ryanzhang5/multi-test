package com.wm.gc.l1;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.SoftReference;

import org.apache.log4j.Logger;

import com.wm.gc.util.GCUtil;

public class CacheEntry implements CacheEntryStat {
	public static Logger logger = Logger.getLogger(CacheEntry.class);

	public long getCreateTime() {
		return createTime;
	}

	private static final byte nullID = 0;
	private static final byte byteID = 1;
	private static final byte objID = 2;

	private SoftReference data = null;

	private long accessCount = 0;
	private long size = 0;
	private long createTime = 0;
	private long accessTime = 0;
	private long expireTime = 0;

	private CacheEntry() {
		createTime = accessTime = expireTime = System.currentTimeMillis();
		accessCount = 1;
	}

	private static CacheEntry getInstance() {
		return new CacheEntry();
	}

	public static CacheEntry getInstance(long ttlMillis, Object data) {
		CacheEntry entry = new CacheEntry();
		entry.setData(data);
		entry.setExpireTime(ttlMillis);

		return entry;
	}

	public static CacheEntry readCacheEntry(DataInputStream dataInputStream)
			throws IOException, ClassNotFoundException {
		CacheEntry cacheEntry = CacheEntry.getInstance();
		// TODO use datainputstream initialize cacheentry
		cacheEntry.read(dataInputStream);
		return cacheEntry;
	}

	public void read(DataInputStream dataInputStream) throws IOException,
			ClassNotFoundException {
		accessCount = dataInputStream.readLong();
		size = dataInputStream.readLong();
		createTime = dataInputStream.readLong();
		accessTime = dataInputStream.readLong();
		expireTime = dataInputStream.readLong();
		logger.debug("----------------------------------" + accessCount
				+ "        " + createTime);
		byte b = dataInputStream.readByte();
		switch (b) {
		case byteID:
			logger.debug("++++++++++++++++read as byte[]");
			data = new SoftReference(readStrand(dataInputStream));
			break;
		case objID:
			logger.debug("++++++++++++++++read as object");
			data = new SoftReference(GCUtil.readObject(dataInputStream));
			break;
		default:
			break;
		}

		// data = new SoftReference(GCUtil.readObject(dataInputStream));
	}

	public static void writeCacheEntry(CacheEntry entry,
			DataOutputStream dataOutputStream) throws IOException,
			ClassNotFoundException {
		entry.write(dataOutputStream);
	}

	public void write(DataOutputStream dataOutputStream) throws IOException {
		dataOutputStream.writeLong(accessCount);
		dataOutputStream.writeLong(size);
		dataOutputStream.writeLong(createTime);
		dataOutputStream.writeLong(accessTime);
		dataOutputStream.writeLong(expireTime);

		Object data2 = data.get();

		if (data2 instanceof byte[]) {
			logger.debug("++++++++++++++++write as byte[]");
			dataOutputStream.writeByte(byteID);
			/*
			 * byte[] bytes = (byte[]) data2;
			 * dataOutputStream.writeInt(bytes.length);
			 * dataOutputStream.write(bytes);
			 */
			GCUtil.writeBytes((byte[]) data2, dataOutputStream);
			return;
		} else if (data2 instanceof Object) {
			logger.debug("++++++++++++++++write as object");
			dataOutputStream.writeByte(objID);
			GCUtil.writeObject(data2, dataOutputStream);
		}

	}

	public byte[] readStrand(DataInputStream in) throws IOException {
		return GCUtil.readBytes(in);
	}

	protected void setTTL(long ttl) {
		expireTime = createTime + ttl;
	}

	public void eventGet() {
		synchronized (this) {
			accessCount++;
			accessTime = System.currentTimeMillis();
		}
	}

	public void eventPut() {

	}

	public void eventRemove() {

	}

	public void setData(Object data) {
		this.data = new SoftReference(data);
		if (data instanceof byte[]) {
			this.size = ((byte[]) data).length;
		} else if (data instanceof Serializable) {

			try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos);
				oos.writeObject(data);
				oos.flush();
				this.size = bos.size();

				oos.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public Object getData() {
		return data.get();
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public long getAccessCount() {
		return accessCount;
	}

	public long getSize() {
		return size;
	}

	public long getAccessTime() {
		return accessTime;
	}

	public long getTTLMillis() {
		return expireTime - createTime;
	}

	public boolean isExpired() {
		return isExpired(System.currentTimeMillis());
	}

	public boolean isExpired(long now) {
		return now > expireTime;
	}

	public CacheEntryStat getCacheEntryStat() {
		return this;
	}

}
