package core.chapter20140727;


public class EvenChecker implements Runnable {
	private IntGenerator generator;
	private final int id;

	public EvenChecker(IntGenerator g, int ident) {
		generator = g;
		id = ident;
	}

	public void run() {
		while (!generator.isCanceled()) {
			int val = generator.next();
			if (val % 2 != 0) {
				System.out.println(val + " not even!");
				generator.cancel(); // Cancels all EvenCheckers
			}
		}
	}

	public static void test(IntGenerator gp) {
		for (int i = 0; i < 10; i++) {
			new Thread(new EvenChecker(gp, i)).start();
		}
	}
}
