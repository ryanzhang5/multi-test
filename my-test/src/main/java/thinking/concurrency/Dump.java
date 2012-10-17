package thinking.concurrency;

import java.util.concurrent.TimeUnit;

public class Dump {

	
	public static void main(String[] args) {
		
		Thread thread = new Thread(new Runnable() {
			int index =0;
			public void run() {
				while(true){
					System.out.println(index++);
					try {
						//TimeUnit.SECONDS.sleep(1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}

}
