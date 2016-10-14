package DP;

/**
 * 
 * 单次选择+最大价值
 * 
 * Given n items with size Ai and value Vi, and a backpack with size m. What's
 * the maximum value can you put into the backpack?
 * 
 * Notice
 * 
 * You cannot divide item into small pieces and the total size of items you
 * choose should smaller or equal to m.
 * 
 * Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack
 * with size 10. The maximum value is 9.
 * 
 * https://segmentfault.com/a/1190000006325321
 */
public class BackpackII {

	public static void main(String[] args) {

	}

	/**
	 * 2D, jiuzhang template
	 */
	public int backPackII(int m, int[] A, int V[]) {
		if (m == 0 || V == null || V.length == 0) {
			return 0;
		}

		// d[i][j]前i件物品恰放入一个容量为j的背包可以获得的最大价值
		int[][] dp = new int[A.length + 1][m + 1];

		dp[0][0] = 0;
		for (int i = 1; i < A.length + 1; i++) {
			dp[i][0] = 0;
		}

		for (int i = 1; i < m + 1; i++) {
			dp[0][i] = 0;
		}

		for (int i = 1; i < A.length + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				// 不取当前物品
				dp[i][j] = dp[i - 1][j];

				// 取当前物品
				if (j - A[i - 1] >= 0) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]]
							+ V[i - 1]);
				}
			}
		}
		return dp[A.length][m];
	}

	/**
	 * 1D： Best: https://segmentfault.com/a/1190000006325321
	 * 
	 * dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-A[i] + V[j]);
	 * 
	 * 与 Backpack.java的区别一行
	 * 
	 * dp[j] = Math.max(dp[j], dp[j - A[i - 1]] + A[i - 1]);
	 */
	public int backPackII1D(int m, int[] A, int V[]) {
		if (m == 0 || V == null || V.length == 0) {
			return 0;
		}
		int[] dp = new int[m + 1];

		dp[0] = 0;

		for (int i = 1; i < A.length + 1; i++) {
			for (int j = m; j >= 0; j--) {
				if (j - A[i - 1] >= 0) {
					dp[j] = Math.max(dp[j], dp[j - A[i - 1]] + V[i - 1]);
				}
			}
		}
		return dp[m];
	}

}
