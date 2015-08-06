package core.chapter20140622;

abstract class Instrument {
	private int i; // Storage allocated for each

	public abstract void play(String n);

	public String what() {
		return "Instrument";
	}

	public abstract void adjust();
}

class Wind extends Instrument {
	public void play(String n) {
		System.out.println("Wind.play() " + n);
	}

	public String what() {
		return "Wind";
	}

	public void adjust() {
	}
}

class Percussion extends Instrument {
	public void play(String n) {
		System.out.println("Percussion.play() " + n);
	}

	public String what() {
		return "Percussion";
	}

	public void adjust() {
	}
}

class Stringed extends Instrument {
	public void play(String n) {
		System.out.println("Stringed.play() " + n);
	}

	public String what() {
		return "Stringed";
	}

	public void adjust() {
	}
}

public class Music4 {
	static void tune(Instrument i) {
		// ...
		i.play("sssss");
	}

	static void tuneAll(Instrument[] e) {
		for (Instrument i : e)
			tune(i);
	}

	public static void main(String[] args) {
		// Upcasting during addition to the array:
		Instrument[] orchestra = { new Wind(), new Percussion(), new Stringed() };
		tuneAll(orchestra);
	}
}