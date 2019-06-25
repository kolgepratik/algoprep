package com.kp.study.algorithms.old.sorting;

public class BubbleSort {

	public int[] doSort(int[] array) {
		for (int a = array.length - 1; a >= 0; a--) {
			for (int b = 1; b <= a; b++) {
				if (array[b] > array[b - 1]) {
					int temp = array[b];
					array[b] = array[b - 1];
					array[b - 1] = temp;
				}
			}
		}

		return array;
	}

}
