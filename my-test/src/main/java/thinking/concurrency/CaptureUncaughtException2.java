package thinking.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;



public class CaptureUncaughtException2 {
	public static void main(String[] args) {
		ExecutorService exec = Executors
				.newCachedThreadPool(new ThreadFactory() {
					
					public Thread newThread(Runnable r) {
						Thread t = new Thread(r);
						t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
							
							public void uncaughtException(Thread t, Throwable e) {
								System.out.println(t+"------->");
								e.printStackTrace();
								
							}
						});
						return t;
					}
				});
		exec.execute(new ExceptionThread2());
	}
}
