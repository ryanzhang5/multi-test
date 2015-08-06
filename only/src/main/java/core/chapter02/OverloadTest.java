package core.chapter02;

public class OverloadTest {

	public void info() {
		System.out.println("in info()");
	}

	public void info(String s) {
		System.out.println(s + ": in info(String s)");
	}

	public static void main(String[] args) {
		OverloadTest test = new OverloadTest();
		test.info();
		test.info("");
	}
}