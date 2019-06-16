package sorting;

public class SelectionSort {

	public int[] doSort(int[] array) {
		for (int a = 0; a < array.length - 1; a++) {
			int min = a;
			for (int b = a; b < array.length; b++) {
				min = (array[b] < array[min]) ? b : min;
			}

			int temp = array[a];
			array[a] = array[min];
			array[min] = temp;
		}

		return array;
	}

}
