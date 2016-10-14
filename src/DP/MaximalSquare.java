package a.DP;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
 * containing all 1's and return its area.
 * 
 * http://www.lintcode.com/en/problem/maximal-square/
 * 
 */
public class MaximalSquare {

	public static void main(String[] args) {

	}

	public int maxSquare(int[][] matrix) {
		if (matrix == null) {
			return 0;
		}

		int n = matrix.length;
		int m;
		if (n != 0) {
			m = matrix[0].length;
		} else {
			return 0;
		}
		// 正方形边长
		int result = 0;

		// dp[i][j] 表示以i,j为点，往左上角延伸的正方形的边长
		int[][] dp = new int[n][m];

		for (int i = 0; i < n; i++) {
			// 初始化第一列
			dp[i][0] = matrix[i][0];
			result = Math.max(result, matrix[i][0]);
			// first column has been inited, here, starting from second column
			for (int j = 1; j < m; j++) {
				if (i > 0) {
					if (matrix[i][j] > 0) {
						// 只能取最短的边长， 否则达不成正方形
						dp[i][j] = Math.min(dp[i - 1][j],
								Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
					} else {
						dp[i][j] = 0;
					}
				} else {
					dp[i][j] = matrix[i][j];
				}
				result = Math.max(result, dp[i][j]);
			}
		}
		return result * result;
	}

	/**
	 * 列上面不能做优化，只能在行上面做mod 2优化， 当前行的结果只决定于上面一行,
	 * 列上面不能做优化,因为你在算下一行某个点的值得时候，还需要正上方行数的列，如果是滚动列，这些信息就已经丢了
	 */

	public int maxSquare1(int[][] matrix) {
		if (matrix == null) {
			return 0;
		}

		int n = matrix.length;
		int m;
		if (n != 0) {
			m = matrix[0].length;
		} else {
			return 0;
		}
		// 正方形边长
		int result = 0;

		// dp[i][j] 表示以i,j为点，往左上角延伸的正方形的边长
		int[][] dp = new int[2][m];

		for (int i = 0; i < n; i++) {
			// 初始化第一列
			dp[i % 2][0] = matrix[i][0];
			result = Math.max(result, matrix[i][0]);
			// first column has been inited, here, starting from second column
			for (int j = 1; j < m; j++) {
				if (i > 0) {
					if (matrix[i][j] > 0) {
						dp[i % 2][j] = Math.min(dp[(i - 1) % 2][j], Math.min(
								dp[i % 2][j - 1], dp[(i - 1) % 2][j - 1])) + 1;
					} else {
						dp[i % 2][j] = 0;
					}
				} else {
					dp[i % 2][j] = matrix[i][j];
				}
				result = Math.max(result, dp[i % 2][j]);
			}
		}
		return result * result;
	}

}
