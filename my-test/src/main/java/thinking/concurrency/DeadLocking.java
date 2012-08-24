package thinking.concurrency;

class ChopStick {
	private boolean taken = false;

	public synchronized void take() throws InterruptedException {
		while (taken == true) {
			wait();
			taken = true;
		}
	}

	public synchronized void dropOff() {
		taken = false;
		notifyAll();
	}
}

class Philosopher implements Runnable {

	ChopStick left, right;
	
	int id = 0;

	public Philosopher(ChopStick left, ChopStick right,int id) {
		this.left = left;
		this.right = right;
		this.id = id;
	}

	public void run() {
		try {
			left.take();
			right.take();
			System.out.println("ok,i have both left and righ, now "+id);
			left.dropOff();
			right.dropOff();
		} catch (InterruptedException e) {
			System.out.println("interrupted by exception");
		}

	}

}

public class DeadLocking {

	public static void main(String[] args) {

	}

}
