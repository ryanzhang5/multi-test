package thinking.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class ToTest {
	Semaphore available = new Semaphore(3, true);

	public void go() throws Exception {
		available.acquire();

		System.out.println(Thread.currentThread().getName());
		TimeUnit.SECONDS.sleep(3);
		available.release();
	}
}

class MyRunner implements Runnable{
	 private ToTest toTest = null;
	public MyRunner(ToTest test) {
		this.toTest = test;
	}
	public void run() {
		try {
			toTest.go();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}

public class SemaphoreDemo2 {
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		ToTest test = new ToTest();
		for(int  i=0;i<5;i++){
			es.execute(new MyRunner(test));
		}
	}
}
