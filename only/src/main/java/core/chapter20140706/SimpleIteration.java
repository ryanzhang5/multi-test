package core.chapter20140706;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SimpleIteration {
	public static void main(String[] args) {
		String[] array = {"aa","bb","cc","dd"};
		List<String> pets = new ArrayList<String>(Arrays.asList(array));
		Iterator<String> it = pets.iterator();
		while(it.hasNext()) {
			String p = it.next();
		System.out.print(p);
		}
		System.out.println();
		// A simpler approach, when possible:
		for(String p : pets)
		System.out.print(p);
		System.out.println();
		// An Iterator can also remove elements:
		it = pets.iterator();
		for(int i = 0; i < 3; i++) {
		it.next();
		it.remove();
		}
		System.out.println(pets);
		}
}
