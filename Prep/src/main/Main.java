package main;

import sorting.BubbleSort;
import sorting.SelectionSort;

public class Main {

	private BubbleSort bbsort = new BubbleSort();
	private SelectionSort selsort = new SelectionSort();

	private void print(int[] array) {
		System.out.println("-- Contents --");
		for (int a = 0; a < array.length; a++) {
			System.out.print(" " + array[a] + " ");
		}
		System.out.println("\n");
	}

	private void execute() {
		int[] arrayA = { -1, -4, 7, 1, -2, 3, 0, -15 };
		int[] arrayB = { -1, -4, 7, 1, -2, 3, 0, -15 };

		System.out.println("Bubble Sort");
		print(arrayA);
		arrayA = bbsort.doSort(arrayA);
		print(arrayA);

		System.out.println("\nSelection Sort");
		print(arrayB);
		arrayB = selsort.doSort(arrayB);
		print(arrayB);

	}

	public static void main(String[] args) {
		Main mainObject = new Main();
		mainObject.execute();
	}

}
