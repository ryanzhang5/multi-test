package com.wm.gc.l1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.SoftReference;

import com.wm.gc.util.GCUtil;

public class CacheEntry implements Serializable {

	private static final byte nullID = 0;
	private static final byte byteID = 1;
	private static final byte objID = 2;

	private SoftReference data = null;

	private long accessCount = 0;
	private long size = 0;
	private long createTime = 0;
	private long accessTime = 0;
	private long expireTime = 0;

	private static CacheEntry getInstance() {
		return new CacheEntry();
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

		//TODO  how to cast data
		data = new SoftReference(GCUtil.readObject(dataInputStream));
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
		
		GCUtil.writeObject(data.get(),dataOutputStream);
		
	}
}
