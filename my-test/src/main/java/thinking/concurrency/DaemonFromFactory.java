package thinking.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class DaemonFromFactory implements Runnable {
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.print(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException e) {
			System.out.print("Interrupted");
		}
	}

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors
				.newCachedThreadPool(new ThreadFactory() {

					public Thread newThread(Runnable r) {
						Thread thread = new Thread();
						thread.setDaemon(true);
						return thread;
					}
				});
		for (int i = 0; i < 10; i++)
			exec.execute(new DaemonFromFactory());
		System.out.print("All daemons started");
		TimeUnit.MILLISECONDS.sleep(500); // Run for a while
	}
}
