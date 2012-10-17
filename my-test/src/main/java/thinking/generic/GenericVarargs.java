package thinking.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericVarargs {
	public static <T> List<T> makeList(T... args) {
		List<T> result = new ArrayList<T>();
		for (T item : args)
			result.add(item);
		return result;
	}

	public static void main(String[] args) {
		List<String> ls = GenericVarargs.<String>makeList("A");
		System.out.println(ls);
		ls = GenericVarargs.<String>makeList("A", "B", "C");
		System.out.println(ls);
		ls = GenericVarargs.<String>makeList("ABCDEFFHIJKLMNOPQRSTUVWXYZ".split(""));
		System.out.println(ls);
	}
}
