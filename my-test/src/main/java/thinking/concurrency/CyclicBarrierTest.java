package thinking.concurrency;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class BlackHorse implements Runnable{
	private CyclicBarrier barrier; 	
	public static int count =0;
	int id = count++;
	private int sleepTime;
	
	BlackHorse(CyclicBarrier barrier,int sleepTime){
		this.barrier = barrier;
		this.sleepTime=sleepTime;
	}
	
	public void run(){
			while(true){
				try {
					System.out.println("horse "+id);
					TimeUnit.SECONDS.sleep(sleepTime);
					barrier.await();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
}

public class CyclicBarrierTest {

	
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(3,new Runnable() {
			
			public void run() {
			System.out.println("--------------------------");
				
			}
		});
		ExecutorService eService = Executors.newCachedThreadPool();
		eService.execute(new BlackHorse(barrier, 2));
		eService.execute(new BlackHorse(barrier, 3));
		eService.execute(new BlackHorse(barrier, 5));
	}

}
