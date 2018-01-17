package misc;
/**
 * 
 */

/**
 * @author t2590pk
 *
 */
public class MergeSort {

	public void merge(Integer[] array, int start, int middle, int end) {
		// Create and initialize 2 temporary arrays for left and right side.
		Integer[] leftArray = new Integer[middle - start];
		Integer[] rightArray = new Integer[end - middle];

		for (int i = 0; i < middle; i++) {
			leftArray[i] = array[i];
		}

		for (int i = middle, j = 0; i < end; i++, j++) {
			rightArray[j] = array[i];
		}

		// Sort the arrays.
		int i = 0, j = 0, k = 0;
		for (; i < leftArray.length && j < rightArray.length;) {
			if (leftArray[i] <= rightArray[j]) {
				array[k++] = leftArray[i];
				i++;
			} else {
				array[k++] = rightArray[j];
				j++;
			}
		}

		// Add remaining items.
		for (; i < leftArray.length;) {
			array[k++] = leftArray[i++];
		}

		for (; j < rightArray.length;) {
			array[k++] = rightArray[j++];
		}
	}

	public void sort(Integer[] array, int start, int end) {
		if (start < end) {
			int middle = (start + end) / 2;

			sort(array, start, middle - 1);
			sort(array, middle, end);

			merge(array, start, middle, end);
		}
	}

	public static void main(String[] a) {
		MergeSort msort = new MergeSort();
		Integer[] array = new Integer[] { 1, 4, 12, -2, 0, 1, 7 };

		msort.print(array);
		msort.sort(array, 0, array.length);
		msort.print(array);
	}

	public void print(Object[] array) {
		System.out.println("-- Printing --");

		for (Object o : array) {
			System.out.print(" " + o + " ");
		}

		System.out.println("\n");
	}
}
