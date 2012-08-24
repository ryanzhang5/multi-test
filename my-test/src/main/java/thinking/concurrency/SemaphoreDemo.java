package thinking.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class Fat {
	private volatile double d; // Prevent optimization
	private static int counter = 0;
	private final int id = counter++;

	public Fat() {
		// Expensive, interruptible operation:
		for (int i = 1; i < 10000; i++) {
			d += (Math.PI + Math.E) / (double) i;
		}
	}

	public void operation() {
		System.out.println(this);
	}

	public String toString() {
		return "Fat id: " + id;
	}
}

class CheckoutTask<T> implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private Pool<T> pool;

	public CheckoutTask(Pool<T> pool) {
		this.pool = pool;
	}

	public void run() {
		try {
			T item = pool.checkOut();
			System.out.println(this + "checked out " + item);
			TimeUnit.SECONDS.sleep(1);
			System.out.println(this + "checking in " + item);
			pool.checkIn(item);
		} catch (InterruptedException e) {
			// Acceptable way to terminate
		}
	}

	public String toString() {
		return "CheckoutTask " + id + " ";
	}
}

public class SemaphoreDemo {
	final static int SIZE = 3;

	public static void main(String[] args) throws Exception {
		final Pool<Fat> pool = new Pool<Fat>(Fat.class, SIZE);
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < SIZE; i++)
			exec.execute(new CheckoutTask<Fat>(pool));
		System.out.println("All CheckoutTasks created");
		List<Fat> list = new ArrayList<Fat>();
		for (int i = 0; i < SIZE; i++) {
			Fat f = pool.checkOut();
			System.out.println(i + ": main() thread checked out ");
			f.operation();
			list.add(f);
		}
		Future<?> blocked = exec.submit(new Runnable() {
			public void run() {
				try {
					// Semaphore prevents additional checkout,
					// so call is blocked:
					pool.checkOut();
				} catch (InterruptedException e) {
					System.out.println("checkOut() Interrupted");
				}
			}
		});
		TimeUnit.SECONDS.sleep(2);
		blocked.cancel(true); // Break out of blocked call
		System.out.println("Checking in objects in " + list);
		for (Fat f : list)
			pool.checkIn(f);
		for (Fat f : list)
			pool.checkIn(f); // Second checkIn ignored
		exec.shutdown();
	}
}
