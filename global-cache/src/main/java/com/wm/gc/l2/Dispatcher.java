package com.wm.gc.l2;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.wm.gc.l2.nio.Handler;

public class Dispatcher implements Runnable {
	private Handler handler;
	private Selector selector;
	static Logger logger = Logger.getLogger(Dispatcher.class);

	Dispatcher() {
		try {
			selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	Dispatcher(Handler handler) {
		this.handler = handler;
	}

	public void run() {
		while (true) {
			dispatch();
		}
	}

	public void dispatch() {
		try {
			Iterator<SelectionKey> iterator = null;
			
				selector.select();
				iterator = selector.selectedKeys().iterator();
			
			while (iterator.hasNext()) {
				SelectionKey selectionKey = (SelectionKey) iterator.next();
				iterator.remove();
				Handler handler = (Handler) selectionKey.attachment();
				handler.handle(selectionKey);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		synchronized (this) {}
	}

	public void register(SelectableChannel selectableChannel, int ops,
			Handler handler) {
		SelectionKey selectionKey;
		try {
			synchronized (this) {

				selector.wakeup();
				Thread.yield();
				selectionKey = selectableChannel.register(selector, ops,
						handler);
				handler.register(selectionKey);
			}

		} catch (ClosedChannelException e) {
			e.printStackTrace();

		}
	}
}
