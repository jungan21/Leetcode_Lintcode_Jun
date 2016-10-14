package matrix;

/**
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 * 
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle
 * defined by its upper left corner (row1, col1) and lower right corner
 * 
 * Note: You may assume that the matrix does not change. There are many calls to
 * sumRegion function. You may assume that row1 ≤ row2 and col1 ≤ col2.
 *
 */
// Your NumMatrix object will be instantiated and called as such:
// RangeSumQuery2DImmutable numMatrix = new RangeSumQuery2DImmutable(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);

public class RangeSumQuery2DImmutable {

	public static void main(String[] args) {
		int[][] matrix = { { 7, 7, 0 }, { -4, -7, 7 }, { -4, 0, -2 },
				{ -8, -5, 6 } };
		RangeSumQuery2DImmutable test = new RangeSumQuery2DImmutable(matrix);
		System.out.println(test.sumRegion(1, 0, 2, 2));
	}

	int[][] sums;

	public RangeSumQuery2DImmutable(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			sums = new int[0][0];
		}
		int m = matrix.length;
		int n = matrix[0].length;
		sums = new int[m][n];
		// 注意
		sums[0][0] = matrix[0][0];
		// first row
		for (int i = 1; i < n; i++) {
			sums[0][i] = sums[0][i - 1] + matrix[0][i];
		}
		// first col
		for (int i = 1; i < m; i++) {
			sums[i][0] = sums[i - 1][0] + matrix[i][0];
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				sums[i][j] = matrix[i][j] + sums[i - 1][j] + sums[i][j - 1]
						- sums[i - 1][j - 1];
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(sums[i][j] + " ");
			}
			System.out.println();
		}

	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if (sums == null || sums.length == 0) {
			return 0;
		}
		if (row1 == 0 && col1 == 0) {
			return sums[row2][col2];
		} else if (row1 == 0) {
			return sums[row2][col2] - sums[row2][col1 - 1];
		} else if (col1 == 0) {
			return sums[row2][col2] - sums[row1 - 1][col2];
		}
		return sums[row2][col2] - sums[row2][col1 - 1] - sums[row1 - 1][col2]
				+ sums[row1 - 1][col1 - 1];
	}

}
