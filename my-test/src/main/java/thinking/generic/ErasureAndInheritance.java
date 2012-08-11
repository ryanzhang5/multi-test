package thinking.generic;

class GenericBase<T> {
	private T element;

	public void set(T arg) {
		arg = element;
	}

	public T get() {
		return element;
	}
}

class Derived1<T> extends GenericBase<T> {
}

class Derived2<T> extends GenericBase<T> {
} // No warning
// class Derived3 extends GenericBase<?> {}
// Strange error:
//

public class ErasureAndInheritance {
	public static void main(String[] args) {
		Derived2 d2 = new Derived2();
		Object obj = d2.get();
		d2.set(obj); // Warning here!
	}
}
