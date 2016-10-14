package matrix;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 
 * Integers in each row are sorted from left to right. The first integer of each
 * row is greater than the last integer of the previous row.
 * 
 *http://www.jiuzhang.com/solutions/search-a-2d-matrix/
 */
public class Search2DMatrix {

	public static void main(String[] args) {
		int[][] matrix = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 },
				{ 23, 30, 34, 50 } };

		System.out.println(searchMatrix(matrix, 3));
	}

	// by Jun
	public static boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) {
			return false;
		}
		if (matrix[0] == null || matrix[0].length == 0) {
			return false;
		}

		int row = matrix.length, column = matrix[0].length;
		int start = 0, end = row * column - 1;

		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			// Note: mid / column 
			int number = matrix[mid / column][mid % column];
			if (number == target) {
				return true;
			} else if (number < target) {
				start = mid;
			} else {
				end = mid;
			}
		}

		if (matrix[start / column][start % column] == target) {
			return true;
		} else if (matrix[end / column][end % column] == target) {
			return true;
		}

		return false;
	}

}
