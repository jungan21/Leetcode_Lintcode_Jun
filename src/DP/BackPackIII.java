package DP;

/**
 * backpackii 是 0，1背包， 这题是无限背包
 * 
 * 重复选择+最大价值
 * 
 * Given n kind of items with size Ai and value Vi( each item has an infinite
 * number available) and a backpack with size m. What's the maximum value can
 * you put into the backpack?
 * 
 * Notice
 * 
 * You cannot divide item into small pieces and the total size of items you
 * choose should smaller or equal to m.
 * 
 * https://segmentfault.com/a/1190000006325321
 */
public class BackPackIII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * https://segmentfault.com/a/1190000006325321
	 */
	public int backPackIII(int[] A, int[] V, int m) {
		int n = A.length;
		int[] dp = new int[m + 1];
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				if (j >= A[i - 1]) {
					dp[j] = Math.max(dp[j], dp[j - A[i - 1]] + V[i - 1]);
				}
			}
		}
		return dp[m];
	}

	/**
	 * 2D
	 */
	public int backPackIII2D(int[] A, int[] V, int m) {
		int n = A.length;
		int[][] dp = new int[n + 1][m + 1];
		dp[0][0] = 0;
		for (int i = 1; i <= m; i++) {
			dp[0][i] = 0;
		}
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 0;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				// 不放
				dp[i][j] = dp[i - 1][j];
				if (j - A[i - 1] >= 0) {
					// 因为有无限个，所以dp[i][j-A[i-1]],而不是i-1因为有无限个
					// 这是与 BackpackII唯一的区别
					dp[i][j] = Math.max(dp[i][j], dp[i][j - A[i - 1]]
							+ V[i - 1]);
				}
			}
		}
		return dp[n][m];
	}
}
