package com.wm.gc.l2;

import java.nio.ByteBuffer;

import com.wm.gc.l2.nio.RequestHandler;

public class L2Worker implements Runnable {
	private RequestHandler handler;
	

	public L2Worker(RequestHandler handler) {
		this.handler = handler;
	}

	public void run() {
		ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
		
		//read data from local disk or db 
		//drive handler write response
		
		responseBuffer= ByteBuffer.wrap("asfqwerlsdfasdf".getBytes());
		
		handler.setReply(responseBuffer);
		
	}
}
