package thinking.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Resour {

	void blan() {
		System.out.println(Thread.currentThread() + "--this is blan");
	}

	synchronized void syn() {
		System.out.println(Thread.currentThread() + "---in syn");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		blan();
	}
}

class A implements Runnable {
	Resour resour = null;

	public A(Resour resour) {
		this.resour = resour;
	}

	public void run() {
		resour.syn();
	}

}

class B implements Runnable {
	Resour resour = null;

	public B(Resour resour) {
		this.resour = resour;
	}

	public void run() {
		resour.blan();
	}

}

class C implements Runnable {
	Resour resour = null;

	public C(Resour resour) {
		this.resour = resour;
	}

	public void run() {
		resour.syn();
	}

}

public class SynchroTest {

	public static void main(String[] args) {
		ExecutorService executors = Executors.newCachedThreadPool();
		Resour resour = new Resour();
		executors.execute(new A(resour));
		executors.execute(new B(resour));
		executors.execute(new C(resour));
		
		executors.shutdown();
	}

}
