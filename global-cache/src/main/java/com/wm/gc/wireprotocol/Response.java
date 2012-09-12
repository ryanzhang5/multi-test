package com.wm.gc.wireprotocol;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

import com.wm.gc.l1.CacheEntry;

public class Response {
	public static CacheEntry readResponse(InputStream in){
		
		byte[]  bytes = null;
		//TODO bytes shoud be unpack from inputstream
		ByteArrayInputStream input = new ByteArrayInputStream(bytes);
		DataInputStream dataInputStream = new DataInputStream(input);
		
		
		
		
		return CacheEntry.readCacheEntry(dataInputStream);
	}
}
