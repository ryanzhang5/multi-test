package core.chapter20140727;

import java.util.concurrent.TimeUnit;

public class SleepingTask extends LiftOff {
	
	public void run() {
		try {
			while (countDown-- > 0) {
				System.out.println(status());
				TimeUnit.SECONDS.sleep(2);
			}
		} catch (InterruptedException e) {
			System.err.println("Interrupted");
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++){
			new Thread(new SleepingTask()).start();
		}
			
	}
}