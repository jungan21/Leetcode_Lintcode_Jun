package DP;

/**
 * 有多少种方法可以把背包装满， 就是把最大max那个地方改为加号
 * 
 * combination 问题 求方案总数
 * 
 * 重复选择+唯一排列+装满可能性总数
 *
 * given n items with size nums[i] which an integer array and all positive
 * numbers, no duplicates. An integer target denotes the size of a backpack.
 * Find the number of possible fill the backpack.
 * 
 * Each item may be chosen unlimited number of times
 */
public class BackpackIIII {

	public static void main(String[] args) {

	}

	/**
	 * jiuzhang 无限背包， 计数背包，当有求max背包，转化为计数背包(方案个数)， 只要把max 改变为加号(+)即可
	 */
	public int backPackIVJiuzhang(int[] nums, int target) {

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
					// 由于是无限背包，所以是 dp[i][j - nums[i - 1]] 而不是 dp[i-1][j - nums[i
					// - 1]]
					dp[i][j] = dp[i][j] + dp[i][j - nums[i - 1]];
				}
			}
		}
		return dp[nums.length][target];
	}

	/**
	 * https://segmentfault.com/a/1190000006325321
	 */
	public static int backPackIV(int[] nums, int target) {
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int num : nums) {
			for (int j = 1; j <= target; j++) {
				if (j >= num) {
					dp[j] = dp[j] + dp[j - num];
				}
			}
		}
		return dp[target];
	}
}
