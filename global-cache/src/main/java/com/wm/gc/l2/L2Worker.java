package com.wm.gc.l2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

import com.wm.gc.l1.CacheEntry;
import com.wm.gc.l2.drivers.WMQLDriver;
import com.wm.gc.l2.nio.RequestHandler;
import com.wm.gc.query.RequestHeader;
import com.wm.gc.query.WMQLQuery;
import com.wm.gc.wireprotocol.Response;

public class L2Worker implements Runnable {
	private RequestHandler handler;
	

	public L2Worker(RequestHandler handler) {
		this.handler = handler;
	}

	public void run() {
		ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
		
		CacheEntry cacheEntry = null;
		
		RequestHeader header = handler.getRequestHeader();
		WMQLQuery query = handler.getQuery();
		
		WMQLDriver driver = WMQLDriver.getInstance(header.getQueryType());
		
		cacheEntry = driver.execute(header,query);
		
		//read data from local disk or db 
		//drive handler write response
	
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			Response.writeResponse(bos,cacheEntry);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		responseBuffer= ByteBuffer.wrap(bos.toByteArray());
		
		handler.setReply(responseBuffer);
		
	}
}
