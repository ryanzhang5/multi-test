package thinking.generic;

class Amphibian {
}

class Vehicle {
}

class TwoTuple<A, B> {
	public final A a;
	public final B b;

	public TwoTuple(A a, B b) {
		this.a = a;
		this.b = b;
	}
}

class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
	public final C c;

	public ThreeTuple(A a, B b, C c) {
		super(a, b);
		this.c = c;
	}
}

class FourTuple<A1, B1, C1, D1> extends ThreeTuple<A1, B1, C1> {
	public final D1 d;

	public FourTuple(A1 a, B1 b, C1 c, D1 d) {
		super(a, b, c);
		this.d = d;
	}
}

public class TupleTest {
	static TwoTuple<String, Integer> f() {
		// Autoboxing converts the int to Integer:
		return new TwoTuple<String, Integer>("hi", 47);
	}

	static ThreeTuple<Amphibian, String, Integer> g() {
		return new ThreeTuple<Amphibian, String, Integer>(new Amphibian(),
				"hi", 47);
	}

	static FourTuple<Vehicle, Amphibian, String, Integer> h() {
		return new FourTuple<Vehicle, Amphibian, String, Integer>(
				new Vehicle(), new Amphibian(), "hi", 47);
	}

	
	
	public static void main(String[] args) {
		TwoTuple<String, Integer> ttsi = f();
		System.out.println(ttsi);
		// ttsi.first = "there"; // Compile error: final
		System.out.println(g());
		System.out.println(h());
	}
}
