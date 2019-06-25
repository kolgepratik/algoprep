package pre2019.misc;
/**
 * 
 */

/**
 * @author t2590pk
 *
 */
public class InsertionSort {
	public void sort(Integer[] array) {
		for (int a = 1; a < array.length; a++) {
			int current = array[a];
			int b = a - 1;

			while (b >= 0 && array[b] > current) {
				array[b + 1] = array[b];
				b--;
			}

			array[b + 1] = current;
		}
	}

	public static void main(String[] a) {
		InsertionSort isort = new InsertionSort();
		Integer[] array = new Integer[] { 9, 4, -12, -2, 14, 1, 7 };

		isort.print(array);
		isort.sort(array);
		isort.print(array);
	}

	public void print(Object[] array) {
		System.out.println("-- Printing --");

		for (Object o : array) {
			System.out.print(" " + o + " ");
		}

		System.out.println("\n");
	}
}
