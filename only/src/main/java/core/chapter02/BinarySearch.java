package core.chapter02;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinarySearch {
	public static int BinarySearch(int[] a, int low, int high, int searchValue) {
	/*	int mid;
		List<String> a = new ArrayList<>()
		if (high <= low)
			return -1;
		mid = low + ((high - low) / 2);
		if (a[mid] > searchValue) {
			return BinarySearch(a, low, mid, searchValue);
		} else if (a[mid] < searchValue) {
			return BinarySearch(a, mid + 1, high, searchValue);
		} else // when a[mid] is the search value..
		{
			return mid;
		}*/
		return 0;
	}

	public static void main(String[] args) {
		//int a[] = { 1, 2, 3, 4, 5, 6,7,8,9,10,11,12,13 };
	//System.out.println(BinarySearch(a, 0, 13, 8));
		List<String> a = new ArrayList<String>();
		a.add("a");
		a.add("b");
		a.add("c");
		for (String string : a) {
			if(string.equals("a")){
				a.remove(string);
			}
		}
	}
}
