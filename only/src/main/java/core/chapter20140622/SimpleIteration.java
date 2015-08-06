package core.chapter20140622;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import typeinfo.pets.Cat;
import typeinfo.pets.Dog;
import typeinfo.pets.EgyptianMau;
import typeinfo.pets.Mouse;
import typeinfo.pets.Mutt;
import typeinfo.pets.Pet;
import typeinfo.pets.Pets;
import typeinfo.pets.Pug;
import typeinfo.pets.Rodent;

public class SimpleIteration {
	public static void main(String[] args) {
		List<Pet> pets = new ArrayList<Pet>();

		pets.add(new Dog());
		pets.add(new Mouse());
		pets.add(new Cat());
		pets.add(new Rodent());
		pets.add(new Mutt());
		pets.add(new Pug());
		pets.add(new EgyptianMau());
		Iterator<Pet> it = pets.iterator();
		while (it.hasNext()) {
			Pet p = it.next();
			System.out.print(p.id() + ":" + p + " ");
		}
		System.out.println();
		// A simpler approach, when possible:
		for (Pet p : pets)
			System.out.print(p.id() + ":" + p + " ");
		System.out.println();
		// An Iterator can also remove elements:
		it = pets.iterator();
		for (int i = 0; i < 6; i++) {
			it.next();
			it.remove();
		}
		System.out.println(pets);
	}
}
