package core.chapter02;

public class This1 {
	int i = 0;

	This1 increment() {
		i++;
		return this;
	}

	void print() {
		System.out.println("i = " + i);
	}

	public static void main(String[] args) {
		This1 x = new This1();
		x.increment().increment().increment().print();
	}
}
