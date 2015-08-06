package core.chapter02;

public class SwitchTest {
	public static void main(String[] args) {
		char[] array = new char[] { 'a', 'b', 'c', 'd', 'e' };
		for (char c : array) {
			switch (c) {
			case 'a':
			case 'b':
			case 'c':
				System.out.println("it is a or b or c ");
				break;
			case 'd':
				System.out.println("it is d ");
				break;
			default:
				System.out.println("it is e ");
				break;
			}
		}
	}
}
