package DP;

/**
 * There are n coins in a line. Two players take turns to take a coin from one
 * of the ends of the line until there are no more coins left. The player with
 * the larger amount of money wins.
 * 
 * Could you please decide the first player will win or lose?
 * 
 * http://www.lintcode.com/en/problem/coins-in-a-line-iii/
 *
 *
 * Example Given array A = [3,2,2], return true.
 * 
 * Given array A = [1,2,4], return true.
 * 
 * Given array A = [1,20,4], return false.
 */

public class CoininaLineIII {

	public static void main(String[] args) {
		int[] nums = { 3, 2, 2 };
		firstWillWin(nums);
	}

	/**
	 * State: dp[i][j] 表示从第i到第j的硬币,现在当前取硬币的人最后最多取硬币价值
	 * 
	 * Function: sum[i][j]第i到第j的硬币价值总和
	 * 
	 * dp[i+1][j] 表示从左边取了一个， dp[i][j-1]表示从右边取了一个
	 * 
	 * dp[i][j] = sum[i][j] - min(dp[i+1][j], dp[i][j-1]);
	 * 
	 * Intialize: dp[i][i] = coin[i],
	 * 
	 * Answer: dp[0][n-1]
	 * 
	 * O(n^2)
	 * 
	 */

	public static boolean firstWillWin(int[] values) {
		// write your code here
		int n = values.length;
		int[][] dp = new int[n + 1][n + 1];
		boolean[][] flag = new boolean[n + 1][n + 1];
		int[][] sum = new int[n + 1][n + 1];
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				sum[i][j] = i == j ? values[j] : sum[i][j - 1] + values[j];
			}
		}
		int allsum = 0;
		for (int now : values)
			allsum += now;
		// 区间类DP， 从 下标 0 到 values.length - 1
		return allsum / 2 < MemorySearch(0, values.length - 1, dp, flag,
				values, sum);
	}

	static int MemorySearch(int left, int right, int[][] dp, boolean[][] flag,
			int[] values, int[][] sum) {
		if (flag[left][right])
			return dp[left][right];

		flag[left][right] = true;
		if (left > right) {
			dp[left][right] = 0;
		} else if (left == right) {
			dp[left][right] = values[left];
			// 由于每次只能取一个硬币，当两个相邻时，取大的那个
		} else if (left + 1 == right) {
			dp[left][right] = Math.max(values[left], values[right]);
		} else {
			dp[left][right] = sum[left][right]
					- Math.min(
							MemorySearch(left + 1, right, dp, flag, values, sum),
							MemorySearch(left, right - 1, dp, flag, values, sum));
		}
		return dp[left][right];
	}

}
