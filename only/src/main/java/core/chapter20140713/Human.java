package core.chapter20140713;

class BaseException extends Exception {
}

class SubException extends BaseException {
}

public class Human {
	public static void main(String[] args) {
		// Catch the exact type:
		try {
			throw new SubException();
		} catch (SubException s) {
			System.out.println("Caught SubException");
		} catch (BaseException a) {
			System.out.println("Caught BaseException");
		}
		// Catch the base type:
		try {
			throw new SubException();
		} catch (BaseException a) {
			System.out.println("Caught BaseException");
		}
	}
}
