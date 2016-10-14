package a.DP;

/**
 * There is a fence with n posts, each post can be painted with one of the k
 * colors. You have to paint all the posts such that no more than two adjacent
 * fence posts have the same color. Return the total number of ways you can
 * paint the fence.
 * 
 * Notice
 * 
 * n and k are non-negative integers.
 *
 */
public class PaintFence {

	public static void main(String[] args) {

	}

	/**
	 * dp[n], 是前面n个柱子所有涂色的可能性
	 * 
	 * dp[1] = k, dp[2] = k * k, dp[3] = (k-1) * dp[1] + (k-1) * dp[2];
	 * 
	 * https://segmentfault.com/a/1190000003790650
	 * 
	 * 根据题意，不能有超过连续两根柱子是一个颜色，
	 * 也就意味着第三根柱子要么根第一个柱子不是一个颜色，要么跟第二根柱子不是一个颜色。如果不是同一个颜色，计算可能性的时候就要去掉之前的颜色
	 * ，也就是k-1 种可能性。
	 * 
	 * 假设dp[1]是第一根柱子及之前涂色的可能性数量，dp[2]是第二根柱子及之前涂色的可能性数量，则
	 * 
	 * dp[3]=(k-1)*dp[1] + (k-1)*dp[2]。
	 * 
	 * 递推式有了，下面再讨论下base情况，所有柱子中第一根涂色的方式有k中，第二根涂色的方式则是k*k，因为第二根柱子可以和第一根一样。
	 */
	public int numWays(int n, int k) {
		// 当n=0时返回0, n = 1, return k, n=2, return k*k
		int dp[] = { 0, k, k * k, 0 };
		if (n <= 2) {
			return dp[n];
		}
		//为什么 n-2, 假设n=3带入计算，因为n=3,就需要执行一次，
		for (int i = 0; i < n -2; i++) {
			// 递推式：第三根柱子要么根第一个柱子不是一个颜色，要么跟第二根柱子不是一个颜色
			dp[3] = (k - 1) * (dp[1] + dp[2]);
			dp[1] = dp[2];
			dp[2] = dp[3];
		}
		return dp[3];
	}

}
