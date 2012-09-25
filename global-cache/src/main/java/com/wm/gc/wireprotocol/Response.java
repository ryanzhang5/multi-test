package com.wm.gc.wireprotocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.wm.gc.l1.CacheEntry;
import com.wm.gc.util.GCUtil;

public class Response {
	public static CacheEntry readResponse(InputStream in) throws IOException{
		
		byte[]  bytes = Wire.unpack(in);
		ByteArrayInputStream input = new ByteArrayInputStream(bytes);
		DataInputStream dataInputStream = new DataInputStream(input);
		CacheEntry entry = null;
		try {
			entry  =  CacheEntry.readCacheEntry(dataInputStream);
		} catch (Exception e) {
		}
		
		
		return entry;
	}

	public static void writeResponse(OutputStream bos,
		CacheEntry cacheEntry) throws ClassNotFoundException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		
		CacheEntry.writeCacheEntry(cacheEntry, dos);
		byte[] bytes = baos.toByteArray();
		dos.close();
		GCUtil.writeBytes(bytes, new DataOutputStream(bos));
	}
}
