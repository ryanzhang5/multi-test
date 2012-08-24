package thinking.concurrency;

class DualSynch {
	private Object syncObject = new Object();

	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.out.print("f()");
			Thread.yield();
		}
	}

	public void g() {
		synchronized (syncObject) {
			for (int i = 0; i < 50; i++) {
				System.out.print("g()");
				Thread.yield();
			}
		}
	}
}

public class SyncObject {
	public static void main(String[] args) {
		final DualSynch ds = new DualSynch();
		new Thread() {
			public void run() {
				ds.f();
			}
		}.start();
		ds.g();
	}
}
