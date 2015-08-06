package core.chapter20140622;

import java.util.LinkedList;
import java.util.List;

import typeinfo.pets.Cat;
import typeinfo.pets.Dog;
import typeinfo.pets.EgyptianMau;
import typeinfo.pets.Hamster;
import typeinfo.pets.Mouse;
import typeinfo.pets.Mutt;
import typeinfo.pets.Pet;
import typeinfo.pets.Pets;
import typeinfo.pets.Pug;
import typeinfo.pets.Rat;
import typeinfo.pets.Rodent;

public class LinkedListFeatures {
	public static void main(String[] args) {
		LinkedList<Pet> pets = new LinkedList<Pet>();

		pets.add(new Dog());
		pets.add(new Mouse());
		pets.add(new Cat());
		pets.add(new Rodent());
		pets.add(new Mutt());
		pets.add(new Pug());
		pets.add(new EgyptianMau());
		System.out.println(pets);
		// Identical:
		System.out.println("pets.getFirst(): " + pets.getFirst());
		System.out.println("pets.element(): " + pets.element());
		// Only differs in empty-list behavior:
		System.out.println("pets.peek(): " + pets.peek());
		// Identical; remove and return the first element:
		System.out.println("pets.remove(): " + pets.remove());
		System.out.println("pets.removeFirst(): " + pets.removeFirst());
		// Only differs in empty-list behavior:
		System.out.println("pets.poll(): " + pets.poll());
		System.out.println(pets);
		pets.addFirst(new Rat());
		System.out.println("After addFirst(): " + pets);
		pets.offer(new Pug());
		System.out.println("After offer(): " + pets);
		pets.add(new Mutt());
		System.out.println("After add(): " + pets);
		pets.addLast(new Hamster());
		System.out.println("After addLast(): " + pets);
		System.out.println("pets.removeLast(): " + pets.removeLast());
	}
}
