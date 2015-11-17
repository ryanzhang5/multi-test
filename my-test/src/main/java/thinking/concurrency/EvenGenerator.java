package thinking.concurrency;

public class EvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;

	public  int next() {
	//	public synchronized int next() {
		++currentEvenValue; // Danger point here!
		//Thread.currentThread().yield();
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}
}
