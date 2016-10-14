package matrix;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix,
 * return the occurrence of it.
 * 
 * This matrix has the following properties:
 * 
 * Integers in each row are sorted from left to right. Integers in each column
 * are sorted from up to bottom. No duplicate integers in each row or column.
 * 
 *
 */
public class Search2DMatrix2 {

	public static void main(String[] args) {
		int[][] matrix = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 },
				{ 3, 6, 9, 16, 22 }, { 10, 13, 14, 17, 24 },
				{ 18, 21, 23, 26, 30 } };

		System.out.println(searchMatrix(matrix, 3));
	}

	// better method
	// http://www.programcreek.com/2013/01/leetcode-search-a-2d-matrix-java/
	// Time Complexity: O(m + n)
	public static boolean searchMatrix(int[][] matrix, int target) {
		int m = matrix.length - 1;
		int n = matrix[0].length - 1;

		int row = m;
		int col = 0;

		// binary search, you can consider mid as matrix[m][0];
		while (row >= 0 && col <= n) {
			if (target < matrix[row][col]) {
				row--;
			} else if (target > matrix[row][col]) {
				col++;
			} else {
				return true;
			}
		}

		return false;
	}

	/**
	 * follow up question in jiuzhang: find occurance of the target
	 * 
	 * http://www.jiuzhang.com/solutions/search-a-2d-matrix-ii/
	 * 
	 * http://www.lintcode.com/en/problem/search-a-2d-matrix-ii/
	 */
	public int searchMatrix1(int[][] matrix, int target) {

		if (matrix == null || matrix.length == 0) {
			return 0;
		}

		if (matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}

		int m = matrix.length - 1;
		int n = matrix[0].length - 1;

		int i = m;
		int j = 0;
		int count = 0;
		while (i >= 0 && j <= n) {
			if (matrix[i][j] == target) {
				count++;
				i--;
				j++;
			} else if (matrix[i][j] > target) {
				i--;
			} else if (matrix[i][j] < target) {
				j++;
			}
		}
		return count;
	}

}
