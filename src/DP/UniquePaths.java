package DP;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
 * the diagram below).
 * 
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (marked 'Finish' in
 * the diagram below).
 * 
 * How many possible unique paths are there?
 *
 */
public class UniquePaths {

	public int uniquePaths(int m, int n) {
		if (m == 0 || n == 0) {
			return 0;
		}
		int[][] sum = new int[m][n];
		// first column
		for (int i = 0; i < m; i++) {
			sum[i][0] = 1;
		}
		// alone with first row
		for (int i = 0; i < n; i++) {
			sum[0][i] = 1;
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
			}
		}

		return sum[m - 1][n - 1];
	}
}
