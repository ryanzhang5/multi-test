package core.chapter02;

public class StaticBlock {
	static int i;
	static {
		System.out.println("this is static block");
	}

	public static void main(String[] args) {
		new StaticBlock();
	}
}
