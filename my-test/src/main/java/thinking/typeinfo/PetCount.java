package thinking.typeinfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

class Individual implements Comparable<Individual> {
	private static long counter = 0;
	private final long id = counter++;
	private String name;

	public Individual(String name) {
		this.name = name;
	}

	// ‘name’ is optional:
	public Individual() {
	}

	public String toString() {
		return getClass().getSimpleName() + (name == null ? "" : " " + name);
	}

	public long id() {
		return id;
	}

	public boolean equals(Object o) {
		return o instanceof Individual && id == ((Individual) o).id;
	}

	public int hashCode() {
		int result = 17;
		if (name != null)
			result = 37 * result + name.hashCode();
		result = 37 * result + (int) id;
		return result;
	}

	public int compareTo(Individual arg) {
		// Compare by class name first:
		String first = getClass().getSimpleName();
		String argFirst = arg.getClass().getSimpleName();
		int firstCompare = first.compareTo(argFirst);
		if (firstCompare != 0)
			return firstCompare;
		if (name != null && arg.name != null) {
			int secondCompare = name.compareTo(arg.name);
			if (secondCompare != 0)
				return secondCompare;
		}
		return (arg.id < id ? -1 : (arg.id == id ? 0 : 1));
	}
}

class Person extends Individual {
	public Person(String name) {
		super(name);
	}
} // /:~

class Pet extends Individual {
	public Pet(String name) {
		super(name);
	}

	public Pet() {
		super();
	}
} // /:~

class Dog extends Pet {
	public Dog(String name) {
		super(name);
	}

	public Dog() {
		super();
	}
} // /:~

class Mutt extends Dog {
	public Mutt(String name) {
		super(name);
	}

	public Mutt() {
		super();
	}
} // /:~



abstract class PetCreator {
	private Random rand = new Random(47);

	// The List of the different types of Pet to create:
	public abstract List<Class<? extends Pet>> types();

	public Pet randomPet() { // Create one random Pet
		int n = rand.nextInt(types().size());
		try {
			return types().get(n).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public Pet[] createArray(int size) {
		Pet[] result = new Pet[size];
		for (int i = 0; i < size; i++)
			result[i] = randomPet();
		return result;
	}

	public ArrayList<Pet> arrayList(int size) {
		ArrayList<Pet> result = new ArrayList<Pet>();
		Collections.addAll(result, createArray(size));
		return result;
	}
}

class ForNameCreator extends PetCreator {
	private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();
	// Types that you want to be randomly created:
	private static String[] typeNames = { "thinking.typeinfo.Mutt"};

	@SuppressWarnings("unchecked")
	private static void loader() {
		try {
			for (String name : typeNames)
				types.add((Class<? extends Pet>) Class.forName(name));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	static {
		loader();
	}

	public List<Class<? extends Pet>> types() {
		return types;
	}
}

class PetCount {
	static class PetCounter extends HashMap<String, Integer> {
		public void count(String type) {
			Integer quantity = get(type);
			if (quantity == null)
				put(type, 1);
			else
				put(type, quantity + 1);
		}
	}

	public static void countPets(PetCreator creator) {
		PetCounter counter = new PetCounter();
		for (Pet pet : creator.createArray(20)) {
			// List each individual pet:
			System.out.println(pet.getClass().getSimpleName() + " ");
			if (pet instanceof Pet)
				counter.count("Pet");
			if (pet instanceof Dog)
				counter.count("Dog");
			if (pet instanceof Mutt)
				counter.count("Mutt");
		
		}
		// Show the counts:
		System.out.println();
		System.out.println(counter);
	}

	public static void main(String[] args) {
		countPets(new ForNameCreator());
	}
}
