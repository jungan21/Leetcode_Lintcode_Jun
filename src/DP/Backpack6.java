package DP;

/**
 * 重复选择+不同排列+装满可能性总数
 * 
 * Leetcode: Combination Sum IV
 * 
 * 无重复的正整数数组，计算得到一个目标正整数的所有可能组合方式的个数。
 * 
 * CoinChangeCC150.java 一模一样
 * 
 * Given an integer array nums with all positive numbers and no duplicates, find
 * the number of possible combinations that add up to a positive integer target.
 * 
 * Notice
 * 
 * The different sequences are counted as different combinations.
 *
 */
public class Backpack6 {

	public static void main(String[] args) {

	}

	/**
	 * 解题思想有点像之前爬梯子的那道题Climbing
	 * Stairs，我们需要一个一维数组dp，其中dp[i]表示目标数为i的解的个数，然后我们从1遍历到target
	 * ，对于每一个数i，遍历nums数组，如果i>=x, dp[i] += dp[i - x]。这个也很好理解，比如说对于[1,2,3]
	 * 4，这个例子，当我们在计算dp
	 * [3]的时候，3可以拆分为1+x，而x即为dp[2]，3也可以拆分为2+x，此时x为dp[1]，3同样可以拆为3+x，
	 * 此时x为dp[0]，我们把所有的情况加起来就是组成3的所有情况了，参见代码如下：
	 * 
	 * http://www.cnblogs.com/grandyang/p/5705750.html
	 * 
	 * http://www.jianshu.com/p/bd95012a6009
	 * 
	 * f(target) = f(target - nums[0]) + f(target - nums[1]) + ... + f(target -
	 * nums.back())
	 * 
	 * dp，dp[i]表示当target为i 时，有多少种组合。
	 * 
	 * 状态转移方程：dp[i]=Σdp[i-nums[k]] 0<=k<=nums.length
	 * 
	 * 当然，需要考虑当i-nums[k]为0时，表示数组中有target，则此时dp[i]为1,
	 */
	public int combinationSum4(int[] nums, int target) {
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int i = 1; i <= target; i++) {
			for (int num : nums) {
				if (num <= i)
					dp[i] += dp[i - num];
			}
		}
		return dp[target];
	}

	// http://blog.csdn.net/yeqiuzs/article/details/52029034
	public int combinationSum4New(int[] nums, int target) {
		int dp[] = new int[target + 1];
		dp[0] = 1;
		for (int i = 0; i <= target; i++) {
			for (int k = 0; k < nums.length; k++) {
				if (i - nums[k] > 0) {
					dp[i] += dp[i - nums[k]];
				} else if (i - nums[k] == 0) {
					dp[i] += 1;
				}

			}
		}
		return dp[target];
	}
}
