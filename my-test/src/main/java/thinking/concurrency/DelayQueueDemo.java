package thinking.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.*;


class DelayedTask implements Runnable, Delayed {
	private static int counter = 0;
	private final int id = counter++;
	private final int delta;
	private final long trigger;
	protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

	public DelayedTask(int delayInMilliseconds) {
		delta = delayInMilliseconds;
		trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
		sequence.add(this);
	}

	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
	}

	public int compareTo(Delayed arg) {
		DelayedTask that = (DelayedTask) arg;
		if (trigger < that.trigger)
			return -1;
		if (trigger > that.trigger)
			return 1;
		return 0;
	}

	public void run() {
		System.out.println(this + " ");
	}

	public String toString() {
		return String.format("[%1$-4d]", delta) + " Task " + id;
	}

	public String summary() {
		return "(" + id + ":" + delta + ")";
	}

	public static class EndSentinel extends DelayedTask {
		private ExecutorService exec;

		public EndSentinel(int delay, ExecutorService e) {
			super(delay);
			exec = e;
		}

		public void run() {
			for (DelayedTask pt : sequence) {
				System.out.println(pt.summary() + " ");
			}
			System.out.print(this + " Calling shutdownNow()");
			exec.shutdownNow();
		}
	}
}

class DelayedTaskConsumer implements Runnable {
	private DelayQueue<DelayedTask> q;

	public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
		this.q = q;
	}

	public void run() {
		try {
			while (!Thread.interrupted())
				q.take().run(); // Run task with the current thread
		} catch (InterruptedException e) {
			// Acceptable way to exit
		}
		System.out.print("Finished DelayedTaskConsumer");
	}
}

public class DelayQueueDemo {
	public static void main(String[] args) {
		Random rand = new Random(47);
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
		// Fill with tasks that have random delays:
		for (int i = 0; i < 20; i++)
			queue.put(new DelayedTask(rand.nextInt(5000)));
		// Set the stopping point
		queue.add(new DelayedTask.EndSentinel(5000, exec));
		exec.execute(new DelayedTaskConsumer(queue));
	}
}
