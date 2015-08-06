package core.chapter03;

class BaseClass5 {
	private int i = 9;
	protected int j;

	BaseClass5() {
		System.out.println("i = " + i + ", j = " + j);
		j = 39;
	}

	private static int x1 = printInit("static BaseClass5.x1 initialized");

	static int printInit(String s) {
		System.out.println(s);
		return 47;
	}
}

public class SubClass5 extends BaseClass5 {
	private int k = printInit("SubClass5.k initialized");

	public SubClass5() {
		System.out.println("k = " + k);
		System.out.println("j = " + j);
	}

	private static int x2 = printInit("static SubClass5.x2 initialized");

	public static void main(String[] args) {
		System.out.println("SubClass5 constructor");
		SubClass5 b = new SubClass5();
	}
}