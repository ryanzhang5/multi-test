package core.chapter02;

public class ReturnBreakContinue {
	static int test(int testval, int target) {
		if (testval > target)
			return +1;
		else if (testval < target)
			return -1;
		else
			return 0; // Match
	}

	static void break1() {
		for (int i = 0; i < 10; i++) {
			System.out.println("in break1 " + i);
			if (i == 5) {
				break;
			}
		}
	}

	static void continue1() {
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				continue;
			}
			System.out.println("in continue1 " + i);
		}
	}

	public static void main(String[] args) {
		System.out.println(test(10, 5));
		break1();
		continue1();
	}
}
