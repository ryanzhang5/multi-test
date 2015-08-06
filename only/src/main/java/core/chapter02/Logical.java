package core.chapter02;

public class Logical {
	public void test() {
		int a = 10;
		int b = 20;

		System.out.println(a == 10 && b == 20);
		System.out.println(a == 10 && b == 21);

		System.out.println(a == 10 || b == 21);
		System.out.println(a == 10 || b == 20);
	}

	public static void main(String[] args) {
		Logical logical = new Logical();
		logical.test();
	}
}
