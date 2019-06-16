package misc;
public class NumbersMain {

	private static NumbersMain getInstance() {
		return new NumbersMain();
	}

	public static void main(String[] args) {
		NumbersMain nm = getInstance();

		int number = 4;
		int count = nm.countOccurences(new int[] { 1, 2, 2, 2, 2, 4, 13 },
				number);
		if (count == -1) {
			System.err.println("Not found.");
		} else {
			System.out.println(number + " occurs " + count + " times.");
		}
	}

	private int countOccurences(int[] array, int number) {
		int first = findFirst(array, 0, array.length - 1, number);
		int last = findLast(array, 0, array.length - 1, number);

		if (last == -1 && first == -1) {
			return 0;
		} else {
			return (last - first + 1);
		}
	}

	private int findFirst(int[] array, int low, int high, int number) {
		if (high >= low) {
			int mid = (low + high) / 2;

			if (mid == 0) {
				if (array[mid] == number) {
					return mid;
				} else {
					return -1;
				}
			} else {
				if (array[mid] == number && array[mid - 1] < number) {
					return mid;
				} else if (number <= array[mid]) {
					return findFirst(array, low, mid - 1, number);
				} else if (number > array[mid]) {
					return findFirst(array, mid + 1, high, number);
				}
			}
		}
		return -1;
	}

	private int findLast(int[] array, int low, int high, int number) {
		if (high >= low) {
			int mid = (low + high) / 2;

			if (mid == array.length - 1) {
				if (array[mid] == number) {
					return mid;
				} else {
					return -1;
				}
			} else {
				if (array[mid] == number && array[mid + 1] > number) {
					return mid;
				} else if (number < array[mid]) {
					return findLast(array, low, mid - 1, number);
				} else if (number >= array[mid]) {
					return findLast(array, mid + 1, high, number);
				}
			}
		}
		return -1;
	}
}
