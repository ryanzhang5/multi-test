package core.chapter02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Iteration {
	public void doWhile() {
		//Collections.synchronizedMap(m)
		//LinkedList<E>
		int i = 10;
		do {
			System.out.println(i);
			i++;
		} while (i == 15);
	}

	public void while1() {
		int i = 10;
		while (i <= 22) {
			System.out.println("in while1" + i);
			i++;
		}
	}

	public void for1() {
		for (int i = 0; i < 10; i++) {
			System.out.println("for1 " + i);
		}
	}

	public void forEach() {
		float f[] = new float[10];
		for (int i = 0; i < 10; i++)
			f[i] = i;
		for (float x : f) {
			System.out.println(x);
		}
	}

	
	
	public void testList(){
		  List<String> list1 = new ArrayList<String>();  
	         List<String> list2 = new ArrayList<String>();  
	         list1.add("abc");  list2.add("abc");  
	         list1.add("123");  list2.add("123");  
	         list1.add("ABC");  
	         list2.add("XYZ");  
	         list1.retainAll(list2);  
	         System.out.println(list1);  
	}
	
	public static void main(String[] args) {
		Iteration iteration = new Iteration();


		//iteration.doWhile();
	//	iteration.while1();
		//iteration.for1();
		//iteration.forEach();
		iteration.testList();
	}
}
