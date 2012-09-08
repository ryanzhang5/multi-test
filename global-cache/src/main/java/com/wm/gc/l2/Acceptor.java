package com.wm.gc.l2;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.wm.gc.l2.nio.RequestHandler;

public class Acceptor implements Runnable {
	private ServerSocketChannel serverSocketChannel = null;
	private static final DispatcherPool readDispatcherPool = new DispatcherPool(
			10, "Read Disaptcher");
	static Logger logger = Logger.getLogger(Acceptor.class);
	private static final LinkedBlockingQueue<Object> requestCountQueue = new LinkedBlockingQueue<Object>();
	public Acceptor(ServerSocketChannel ssc) {
		this.serverSocketChannel = ssc;
	}

	public void run() {

		try {
			while (true) {
				if (RequestHandler.getActiveRequestCount() >= 10) {
						logger.debug("Max Allowed Request " + 10
								+ ", requets under processing "
								+ RequestHandler.getActiveRequestCount());
						try {
							requestCountQueue.take();
							logger.debug(Thread.currentThread().getName() + " ---regriggered");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					
				} else {
					SocketChannel socketChannel = null;
					socketChannel = serverSocketChannel.accept();
					ChannelIO channelIO = ChannelIO.getInstance(socketChannel,
							false);
					RequestHandler requestHandler = new RequestHandler(
							channelIO);
					readDispatcherPool.nextDispatcher().register(socketChannel,
							SelectionKey.OP_READ, requestHandler);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void decrementRequestCount(){
		if(requestCountQueue.size() == 0){
			requestCountQueue.offer(new Object());
			logger.debug(Thread.currentThread().getName() + " ---decrementRequestCount to trigger acceptor");
		}
	}
}
