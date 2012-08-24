package thinking.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

class Producer implements Runnable {
	BlockingQueue<String> queue = null;

	Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			int i = 0;
			while (!Thread.interrupted()) {
				System.out.println("--------------------------in put");

				String put = "now, putting " + (i++);

				TimeUnit.SECONDS.sleep(i);

				queue.put(put);
			}
		} catch (InterruptedException e) {
			System.out.println("Waking from take()");
		}

	}

}

class Consumer implements Runnable {
	BlockingQueue<String> queue = null;

	Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("in take");
				String result = queue.take();
				System.out.println("now take out a " + result);
			}
		} catch (InterruptedException e) {
			System.out.println("Waking from take()");
		}

	}

}

public class BlockerTest {

	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		BlockingQueue<String> bq = new SynchronousQueue<String>();
		es.execute(new Producer(bq));
		es.execute(new Consumer(bq));
		
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
