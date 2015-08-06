package core.chapter03;

class BaseClass2 {
	private String name;

	protected void set(String nm) {
		name = nm;
	}

	public BaseClass2(String name) {
		this.name = name;
	}

	public String toString() {
		return "I’m a BaseClass2 and my name is " + name;
	}
}

public class SubClass2 extends BaseClass2 {
	private int SubClass2Number;

	public SubClass2(String name, int SubClass2Number) {
		super(name);
		this.SubClass2Number = SubClass2Number;
	}

	public void change(String name, int SubClass2Number) {
		set(name); // Available because it’s protected
		this.SubClass2Number = SubClass2Number;
	}

	public String toString() {
		return "SubClass2 " + SubClass2Number + ": " + super.toString();
	}

	public static void main(String[] args) {
		SubClass2 subClass2 = new SubClass2("Limburger", 12);
		System.out.println(subClass2);
		subClass2.change("Bob", 19);
		System.out.println(subClass2);
	}
}