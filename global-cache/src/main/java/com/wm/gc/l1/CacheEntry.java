package com.wm.gc.l1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.SoftReference;

import org.apache.log4j.Logger;

import com.wm.gc.util.GCUtil;

public class CacheEntry implements Serializable {
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
	
	private CacheEntry(){
		createTime = accessTime = expireTime = System.currentTimeMillis();
		accessCount =1;
	}

	private static CacheEntry getInstance() {
		return new CacheEntry();
	}

	public static CacheEntry getInstance(long ttlMillis,Object data) {
		CacheEntry  entry = new CacheEntry();
		entry.setData(data);
		entry.setExpireTime(ttlMillis);
		
		return entry;
	}
	public static CacheEntry readCacheEntry(DataInputStream dataInputStream)throws IOException,ClassNotFoundException {
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
		logger.debug("----------------------------------" + accessCount + "        " + createTime);
		byte b = dataInputStream.readByte();
		switch (b) {
		case byteID:
			logger.debug("++++++++++++++++read as byte[]" );
			data = new SoftReference(readStrand(dataInputStream));
			break;
		case objID:
			logger.debug("++++++++++++++++read as object" );
			data = new SoftReference(GCUtil.readObject(dataInputStream));
			break;
		default:
			break;
		}
		
		//data = new SoftReference(GCUtil.readObject(dataInputStream));
	}

	public static void writeCacheEntry(CacheEntry entry,DataOutputStream dataOutputStream)throws IOException,ClassNotFoundException {
		entry.write(dataOutputStream);
	}


	public void write(DataOutputStream dataOutputStream)throws IOException{
		dataOutputStream.writeLong(accessCount);
		dataOutputStream.writeLong(size);
		dataOutputStream.writeLong(createTime);
		dataOutputStream.writeLong(accessTime);
		dataOutputStream.writeLong(expireTime);
		
		Object  data2 =data.get();
		
		if(data2 instanceof byte[]){
			logger.debug("++++++++++++++++write as byte[]" );
			dataOutputStream.writeByte(byteID);
			/*byte[] bytes = (byte[]) data2;
			dataOutputStream.writeInt(bytes.length);
			dataOutputStream.write(bytes);*/
			GCUtil.writeBytes((byte[]) data2, dataOutputStream);
			return ;
		}else if (data2 instanceof Object) {
			logger.debug("++++++++++++++++write as object" );
			dataOutputStream.writeByte(objID);
			GCUtil.writeObject(data2,dataOutputStream);
		}
		
	}

	public byte[] readStrand(DataInputStream in) throws IOException{
		return  GCUtil.readBytes(in);
	}
	public void setData(Object data) {
		this.data = new SoftReference(data);
	}

	public Object getData(){
		return data.get();
	}
	
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public long getExpireTime() {
		return expireTime;
	}
	
	
}
