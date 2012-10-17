package thinking.generic;

public class Holder<T> {
	private T value;

	public Holder() {
	}

	public Holder(T val) {
		value = val;
	}

	public void set(T val) {
		value = val;
	}

	public T get() {
		return value;
	}

	public boolean equals(Object obj) {
		return value.equals(obj);
	}

	public static void main(String[] args) {
		Holder<Apple> holder = new Holder<Apple>(new Apple());
		Apple d = holder.get();
		holder.set(d);
		// Holder<Fruit> Fruit = Apple; // Cannot upcast
		Holder<? extends Fruit> fruit = holder; // OK
		Fruit p = fruit.get();
		d = (Apple) fruit.get(); // Returns ‘Object’
		try {
			Orange c = (Orange) fruit.get(); // No warning
		} catch (Exception e) {
			System.out.println(e);
		}
		// fruit.set(new Apple()); // Cannot call set()
		// fruit.set(new Fruit()); // Cannot call set()
		System.out.println(fruit.equals(d)); // OK
	}
}
