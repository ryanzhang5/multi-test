package core.chapter20140727;

public class BasicThreads2 {
	public static void main(String[] args) {
		Thread t = new LiftOff2();
		t.start();
		System.out.println("Waiting for LiftOff");
	}
}
