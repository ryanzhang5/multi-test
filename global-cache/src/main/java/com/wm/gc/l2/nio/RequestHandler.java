package com.wm.gc.l2.nio;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wm.gc.l1.CacheEntry;
import com.wm.gc.l2.Acceptor;
import com.wm.gc.l2.ChannelIO;
import com.wm.gc.l2.DispatcherPool;
import com.wm.gc.l2.L2Worker;
import com.wm.gc.l2.ThreadPoolManger;
import com.wm.gc.query.RequestHeader;
import com.wm.gc.query.WMQLQuery;
import com.wm.gc.util.GCUtil;
import com.wm.gc.wireprotocol.Request;

public class RequestHandler implements Handler {
	private long startTime = System.currentTimeMillis();
	private ChannelIO channelIO;
	private SelectionKey selectionKey;
	private boolean requestCompleted = false;
	private RequestHeader requestHeader = new RequestHeader();
	private WMQLQuery query = null;
	private boolean responseReady = false;
	private ByteBuffer requestByteBuffer;
	private ByteBuffer responseByteBuffer;
	static Logger logger = Logger.getLogger(RequestHandler.class);
	private static final ConcurrentMap<RequestHandler, Long> runningProcessMap = new ConcurrentHashMap<RequestHandler, Long>();
	private static final DispatcherPool writeDispatcherPool = new DispatcherPool(
			10, "write Disaptcher");

	public RequestHandler(ChannelIO channelIO) {
		this.channelIO = channelIO;
		runningProcessMap.put(this, System.currentTimeMillis());
	}

	public void handle(SelectionKey selectionKey) {
		if (!requestCompleted) {
			try {
				// if (channelIO.read() != -1 || !channelIO.checkComplete()) {
				if (channelIO.read() != -1) {
					return;
				} else {
					requestCompleted = true;
					this.requestByteBuffer = channelIO.getRequestByteBuffer();
					requestByteBuffer.flip();

					process(selectionKey);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (responseReady) {
			send();
			resetAll(selectionKey);
		}

	}

	private void process(SelectionKey selectionKey) {

		try {

			selectionKey.attach(null);
			selectionKey.cancel();
			byte[] bytes = requestByteBuffer.array();
			InputStream is = new ByteArrayInputStream(bytes);
			
			try {
				query = Request.readRquest(is,requestHeader);
				
				if(requestHeader.getQueryType() == WMQLQuery.WMQL_QUERY_TYPE_DATABASE && requestHeader.getMode() == GCUtil.WMQL_REQUEST_TYPE_GET){
					//TODO get cache item from db
				}
				
				TimeUnit.MICROSECONDS.sleep(1000);
				
				ThreadPoolManger.getThreadPoolExecutor()
				.execute(new L2Worker(this));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void register(SelectionKey selectionKey) {
		this.selectionKey = selectionKey;
	}

	public void setReply(ByteBuffer responseBuffer) {
		this.responseByteBuffer = responseBuffer;
		this.responseReady = true;
		writeDispatcherPool.nextDispatcher().register(
				channelIO.getSocketChannel(), selectionKey.OP_WRITE, this);
	}

	private void resetAll(SelectionKey selectionKey2) {
		
		printBench();
		selectionKey2.cancel();
		selectionKey2.attach(null);
		channelIO.close();
		requestByteBuffer.clear();
		responseByteBuffer.clear();
	    runningProcessMap.remove(this);
	    Acceptor.decrementRequestCount();
	}

	private void printBench(){
	logger.info("------------------------------------------millis - "+ (System.currentTimeMillis() - startTime));
	}
	private void send() {
		channelIO.write(responseByteBuffer);
	}

	public static int getActiveRequestCount() {
		return runningProcessMap.size();
	}

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public WMQLQuery getQuery() {
		return query;
	}
	
	
}
