package com.wm.gc.l2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WMQLRequestBroker implements Runnable {
	ServerSocketChannel ssc = null;

	WMQLRequestBroker() {
		try {
			ssc = ServerSocketChannel.open();
			ssc.socket().setReuseAddress(true);
			ssc.socket().bind(new InetSocketAddress("localhost", 9765), 100);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		Acceptor acceptor = new Acceptor(ssc);
		Thread thread = new Thread(acceptor);
		thread.setName("acceptor-thread-0");
		thread.start();
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new WMQLRequestBroker());

	}
}
