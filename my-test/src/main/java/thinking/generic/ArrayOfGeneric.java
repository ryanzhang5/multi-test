package thinking.generic;

class Generic<T> {
}

public class ArrayOfGeneric {
	static final int SIZE = 100;
	static Generic<Integer>[] gia;

	public static void main(String[] args) {
		// Compiles; produces ClassCastException:
		 gia = (Generic<Integer>[])new Object[SIZE];
		// Runtime type is the raw (erased) type:
		gia = (Generic<Integer>[]) new Generic[SIZE];
		System.out.println(gia.getClass().getSimpleName());
		gia[0] = new Generic<Integer>();
		// gia[1] = new Object(); // Compile-time error
		// Discovers type mismatch at compile time:
		// ! gia[2] = new Generic<Double>();
	}
}
