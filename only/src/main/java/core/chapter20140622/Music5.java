package core.chapter20140622;

interface Instrument2 {
	// Compile-time constant:
	int VALUE = 5; // static & final

	// Cannot have method definitions:
	void play(String n); // Automatically public

	void adjust();
}

class Wind2 implements Instrument2 {
	public void play(String n) {
		System.out.println(this + ".play() " + n);
	}

	public String toString() {
		return "Wind";
	}

	public void adjust() {
		System.out.println(this + ".adjust()");
	}
}

class Percussion2 implements Instrument2 {
	public void play(String n) {
		System.out.println(this + ".play() " + n);
	}

	public String toString() {
		return "Percussion";
	}

	public void adjust() {
		System.out.println(this + ".adjust()");
	}
}

public class Music5 {
	// Doesn’t care about type, so new types
	// added to the system still work right:
	static void tune(Instrument2 i) {
		// ...
		i.play("123");
	}

	static void tuneAll(Instrument2[] e) {
		for (Instrument2 i : e)
			tune(i);
	}

	public static void main(String[] args) {
		// Upcasting during addition to the array:
		Instrument2[] orchestra = { new Wind2(), new Percussion2() };
		tuneAll(orchestra);
	}
}