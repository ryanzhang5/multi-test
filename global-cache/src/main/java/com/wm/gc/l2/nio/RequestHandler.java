package com.wm.gc.l2.nio;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.wm.gc.l2.Acceptor;
import com.wm.gc.l2.CacheEntry;
import com.wm.gc.l2.ChannelIO;
import com.wm.gc.l2.DispatcherPool;
import com.wm.gc.l2.L2Worker;
import com.wm.gc.l2.ThreadPoolManger;

public class RequestHandler implements Handler {
	private long startTime = System.currentTimeMillis();
	private ChannelIO channelIO;
	private SelectionKey selectionKey;
	private boolean requestCompleted = false;
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

			byte[] byteabc = new byte[requestByteBuffer.limit()];
			requestByteBuffer.get(byteabc);

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
					byteabc);

			DataInputStream dataInputStream = new DataInputStream(
					byteArrayInputStream);
			System.out.println();
			logger.debug(dataInputStream.readInt());
			ObjectInputStream objectInputStream = new ObjectInputStream(
					byteArrayInputStream);
			List<CacheEntry> list = (List<CacheEntry>) objectInputStream
					.readObject();

			for (CacheEntry cacheEntry : list) {
				logger.debug(cacheEntry);
			}

			int t = byteArrayInputStream.read();
			while (t != -1) {
				System.out.println((char) t);
				t = byteArrayInputStream.read();
			}

			ThreadPoolManger.getThreadPoolExecutor()
					.execute(new L2Worker(this));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
	logger.debug("------------------------------------------millis - "+ (System.currentTimeMillis() - startTime));
	}
	private void send() {
		channelIO.write(responseByteBuffer);
	}

	public static int getActiveRequestCount() {
		return runningProcessMap.size();
	}
}
