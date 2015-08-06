package core.chapter03;

class BaseClass {
	private String s = "BaseClass";

	public void append(String a) {
		s += a;
	}

	public void methodA() {
		append(" methodA()");
	}

	public void methodB() {
		append(" methodB()");
	}

	public void methodC() {
		append(" methodC()");
	}

	public String toString() {
		return s;
	}

	public static void main(String[] args) {
		BaseClass x = new BaseClass();
		x.methodA();
		x.methodB();
		x.methodC();
		System.out.println(x);
	}
}

public class SubClass extends BaseClass {
	// Change a method:
	public void methodC() {
		append(" SubClass.methodC()");
		super.methodC(); // Call base-class version
	}

	// Add methods to the interface:
	public void methodD() {
		append(" methodD()");
	}

	// Test the new class:
	public static void main(String[] args) {
		SubClass x = new SubClass();
		x.methodA();
		x.methodB();
		x.methodC();
		x.methodD();
		System.out.println(x);
		System.out.println("Testing base class:");
		BaseClass.main(args);
	}
}
