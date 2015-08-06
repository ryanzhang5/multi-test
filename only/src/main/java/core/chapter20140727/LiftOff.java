package core.chapter20140727;

public class LiftOff implements Runnable {
	protected int countDown = 10; // Default
	private static int taskCount = 0;
	private final int id = taskCount++;

	public LiftOff() {
	}

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!")
				+ "), ";
	}

	public void run() {
		while (countDown-- > 0) {
			System.out.println(status());
			Thread.yield();

			// The call to the static method Thread.yield( ) inside run( ) is a
			// suggestion to the thread scheduler (the part of the Java
			// threading mechanism that moves the CPU from one thread to the
			// next) that says,
			// "I’ve done the important parts of my cycle and this would be a good time to switch to another task for a while."
		}

	}

	// the task’s run( ) is not driven by a separate thread; it is simply called
	// directly in main( ) (actually, this is using a thread: the one that is
	// always allocated for main( ))
	public static void main(String[] args) {
		LiftOff launch = new LiftOff();
		launch.run();
	}
}