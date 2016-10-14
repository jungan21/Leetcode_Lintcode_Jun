package DP;

/**
 * 01背包单次选择+装满可能性总数 计数背包
 * 
 * 
 * Given n items with size nums[i] which an integer array and all positive
 * numbers. An integer target denotes the size of a backpack. Find the number of
 * possible fill the backpack.
 * 
 * Each item may only be used once
 * 
 * 
 *
 */
public class Backpack5 {

	public static void main(String[] args) {

	}

	/**
	 * jiuzhzhang
	 */
	public int backPackVJiuzhang(int[] nums, int target) {

		int[][] dp = new int[nums.length + 1][target + 1];
		//
		dp[0][0] = 1;

		for (int i = 1; i <= nums.length; i++) {
			dp[i][0] = 1;
		}
		for (int i = 1; i <= target; i++) {
			dp[0][i] = 0;
		}

		for (int i = 1; i <= nums.length; i++) {
			for (int j = 1; j <= target; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j - nums[i - 1] >= 0) {
					// 由于是01背包，所以是 dp[i-1][j - nums[i - 1]]
					// 如果是无限背包： dp[i][j - nums[i - 1]]
					dp[i][j] = dp[i][j] + dp[i - 1][j - nums[i - 1]];
				}
			}
		}
		return dp[nums.length][target];
	}

	public int backPackV(int[] nums, int target) {
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int i = 1; i <= nums.length; i++) {
			for (int j = target; j >= 0; j--) {
				if (j >= nums[i - 1])
					dp[j] += dp[j - nums[i - 1]];
			}
		}
		return dp[target];
	}

}
