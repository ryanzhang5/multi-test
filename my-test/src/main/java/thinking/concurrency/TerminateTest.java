package thinking.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Walker implements Runnable {

	public void run() {
		int i = 0;
		while (!Thread.currentThread().isInterrupted()) {
			System.out.println(i++);
		}
	}

}

public class TerminateTest {

	public static void main(String[] args) throws Exception{
		ExecutorService es = Executors.newCachedThreadPool();
		
		es.execute(new Walker());
		TimeUnit.SECONDS.sleep(2);
		es.shutdownNow();
	}

}
