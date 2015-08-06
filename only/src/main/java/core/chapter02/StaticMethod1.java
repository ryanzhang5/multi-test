package core.chapter02;

public class StaticMethod1 {
	public static void method() {
		System.out.println("this is method");
	}

	public void method2() {
		System.out.println("this is method2");
		method();
	}

	public static void main(String[] args) {
		method();
		System.out.println("---------------------------------------------");
		StaticMethod1.method();
		System.out.println("---------------------------------------------");
		StaticMethod1 staticMethod1 = new StaticMethod1();
		staticMethod1.method2();
	}
}
