package thinking.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

class Data2 implements Serializable {
	private int n;

	public Data2(int n) {
		this.n = n;
	}

	public String toString() {
		return Integer.toString(n);
	}
}

class MyType {
	int go = 10;

	@Override
	public String toString() {
		return go + "";
	}
}

public class Worm2 implements Serializable {
	private static Random rand = new Random(47);
	private Data2[] d = { new Data2(rand.nextInt(10)),
			new Data2(rand.nextInt(10)), new Data2(rand.nextInt(10)) };
	private MyType myType = new MyType();

	public Worm2() {
		System.out.println("Default constructor");
	}

	public String toString() {
		StringBuilder result = new StringBuilder(":");
		result.append("(");
		for (Data2 dat : d)
			result.append(dat);
		result.append(")").append(myType);
		return result.toString();
	}

	public static void main(String[] args) throws ClassNotFoundException,
			IOException {
		Worm2 w = new Worm2();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				"worm2.out"));
		out.writeObject("Worm storage\n");
		out.writeObject(w);
		out.close(); // Also flushes output
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				"worm2.out"));
		String s = (String) in.readObject();
		Worm2 w2 = (Worm2) in.readObject();
		System.out.println(s + "w2 = " + w2);
	}

}