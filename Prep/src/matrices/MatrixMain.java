package matrices;

public class MatrixMain {

	private static MatrixMain getInstance() {
		return new MatrixMain();
	}

	public static void main(String[] args) {
		MatrixMain mm = getInstance();

		int rows = 5;
		int columns = 5;
		// mm.printSpiral(mm.generateMatrix(rows, columns, 1, 1), rows,
		// columns);

		mm.printMatrix(mm.generateSpiral(rows), rows, rows);
	}

	private int[][] generateMatrix(int rows, int columns, int startValue,
			int increment) {
		int value = startValue;

		int[][] matrix = new int[rows][columns];

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				matrix[r][c] = value;
				value += increment;
			}
		}

		return matrix;
	}

	private void printMatrix(int[][] matrix, int rows, int columns) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				System.out.print(" " + matrix[r][c]);
			}
			System.out.println("");
		}
	}

	private void printSpiral(int[][] matrix, int rows, int columns) {
		int left, right, top, bottom, i;
		top = 0;
		bottom = rows - 1;
		left = 0;
		right = columns - 1;

		while (left <= right && top <= bottom) {
			if (top <= bottom) {
				// print top row.
				for (i = left; i <= right; i++) {
					System.out.print(" " + matrix[top][i]);
				}
				top++;
			}

			if (left <= right) {
				// print right column.
				for (i = top; i <= bottom; i++) {
					System.out.print(" " + matrix[i][right]);
				}
				right--;
			}

			if (top <= bottom) {
				// print bottom row.
				for (i = right; i >= left; i--) {
					System.out.print(" " + matrix[bottom][i]);
				}
				bottom--;
			}

			if (left <= right) {
				// print left column.
				for (i = bottom; i >= top; i--) {
					System.out.print(" " + matrix[i][left]);
				}
				left++;
			}
		}

		System.out.println("\nleft: " + left + " right: " + right + " top: "
				+ top + " bottom: " + bottom + "\n");
	}

	private int[][] generateSpiral(int n) {
		int[][] matrix = new int[n][n];
		int left, right, top, bottom, i, value;
		top = 0;
		bottom = n - 1;
		left = 0;
		right = n - 1;
		value = 1;

		while (left <= right && top <= bottom) {
			if (top <= bottom) {
				// print top row.
				for (i = left; i <= right; i++) {
					matrix[top][i] = value++;
				}
				top++;
			}

			if (left <= right) {
				// print right column.
				for (i = top; i <= bottom; i++) {
					matrix[i][right] = value++;
				}
				right--;
			}

			if (top <= bottom) {
				// print bottom row.
				for (i = right; i >= left; i--) {
					matrix[bottom][i] = value++;
				}
				bottom--;
			}

			if (left <= right) {
				// print left column.
				for (i = bottom; i >= top; i--) {
					matrix[i][left] = value++;
				}
				left++;
			}
		}

		return matrix;
	}
}
