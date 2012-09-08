package com.wm.gc.l2;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DispatcherPool {
	private List<Dispatcher> dispatchers = new ArrayList<Dispatcher>();
	private int loopCount = 0;
	private int totalDispatcher;
	static Logger logger = Logger.getLogger(DispatcherPool.class);

	public DispatcherPool(int dispatcherCount, String poolName) {
		for (int i = 0; i < dispatcherCount; i++) {
			Dispatcher dispatcher = new Dispatcher();
			dispatchers.add(dispatcher);
			Thread thread = new Thread(dispatcher);
			thread.setName(poolName + "-thread-" + i);
			thread.start();
		}
		this.totalDispatcher = dispatcherCount;

	}

	public Dispatcher nextDispatcher() {
		synchronized (this) {
			if (loopCount == totalDispatcher) {
				loopCount = 0;
			}
			logger.debug("get dispatcher from pool  " + loopCount);
			return dispatchers.get(loopCount++);
		}

	}
}
