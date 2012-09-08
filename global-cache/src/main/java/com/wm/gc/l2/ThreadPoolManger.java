package com.wm.gc.l2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManger {
	private static ExecutorService eService = Executors.newFixedThreadPool(20);

	public static ExecutorService getThreadPoolExecutor() {
		return eService;
	}
}
