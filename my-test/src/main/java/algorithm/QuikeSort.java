package algorithm;

import java.util.Arrays;

public class QuikeSort {

	public static void quickSort(int[] pData, int left, int right) {
		//this is test
		int i, j;
		int middle, temp;
		i = left;
		j = right;
		middle = pData[left];
		while (true) {
			while ((i++) < right - 1 && pData[i] < middle)
				;
			while ((j--) > left && pData[j] > middle)
				;
			if (i >= j)
				break;
			temp = pData[i];
			pData[i] = pData[j];
			pData[j] = temp;

		}
		pData[left] = pData[j];
		pData[j] = middle;
		System.out.println(j);

		if (left < j)
			quickSort(pData, left, j);

		if (right > i)
			quickSort(pData, i, right);

	}

	public static void main(String[] args) {
		// int[] pData = new int[] { 49, 38, 65, 55, 76, 13, 27 };
		int[] pData = new int[] { 1, 2, 3, 4, 5, 6 };
		quickSort(pData, 0, pData.length);
		System.out.println(Arrays.toString(pData));

	}

}
