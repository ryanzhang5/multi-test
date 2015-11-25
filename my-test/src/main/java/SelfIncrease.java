public class SelfIncrease {
    public static void t1() {
	int i = 0;
	++i;
	System.out.println(i); // returns 1

    }

    public static void t2() {
	int i = 0;
	i = i++;
	System.out.println(i); // returns 0
    }
    
    public static void main(String[] args) {
	t1();t2();
    }
}
