package a.DP;

import java.util.Arrays;

/**
 * This time, all houses at this place are arranged in a circle. That means the
 * first house is the neighbor of the last one. Meanwhile, the security system
 * for these houses remain the same as for those in the previous street.
 * 
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight without
 * alerting the police.
 * 
 * nums = [3,6,4], return 6
 *
 */
public class HouseRobberII {

	public static void main(String[] args) {
	}

	/**
	 * jiuzhang
	 */
	public int houseRobber2(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		
		if (nums.length == 1) {
			return nums[0];
		}
		// 从第一户偷到倒数第二户
		// 从第二户偷到最后一户
		return Math.max(helper(nums, 0, nums.length - 2),
				helper(nums, 1, nums.length - 1));
	}

	public int helper(int[] nums, int st, int ed) {
		int[] res = new int[2];
		if (st == ed)
			return nums[ed];
		// 说明就两个house连着
		if (st + 1 == ed)
			return Math.max(nums[st], nums[ed]);
		res[st % 2] = nums[st];
		res[(st + 1) % 2] = Math.max(nums[st], nums[st + 1]);

		for (int i = st + 2; i <= ed; i++) {
			res[i % 2] = Math.max(res[(i - 1) % 2], res[(i - 2) % 2] + nums[i]);

		}
		return res[ed % 2];
	}

	/**
	 * http://www.programcreek.com/2014/05/leetcode-house-robber-ii-java/
	 */
	public int rob(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		if (nums.length == 1)
			return nums[0];

		int max1 = robHelper(nums, 0, nums.length - 2);
		int max2 = robHelper(nums, 1, nums.length - 1);

		return Math.max(max1, max2);
	}

	public int robHelper(int[] nums, int i, int j) {
		if (i == j) {
			return nums[i];
		}

		int[] dp = new int[nums.length];
		dp[i] = nums[i];
		dp[i + 1] = Math.max(nums[i + 1], dp[i]);

		for (int k = i + 2; k <= j; k++) {
			dp[k] = Math.max(dp[k - 1], dp[k - 2] + nums[k]);
		}

		return dp[j];
	}

	/**
	 * Jun's own
	 */
	public int houseRobber2Jun(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		if (A.length == 1) {
			return A[0];
		}
		// 0, 1 means staring point
		return Math.max(robberHelper(A, 0), robberHelper(A, 1));
	}

	public int robberHelper(int[] A, int start) {
		// 必须是long,否则通不过测试
		long[] dp = new long[A.length + 1];

		dp[0] = 0;
		// start ==0, 表示从第一个house开始,由于连成圈，最后一个house就不能取
		// start==1 表示从第二户人家开始， 这样，最后一个house就可以取
		dp[1] = (start == 0) ? A[0] : 0;

		for (int i = 2; i <= A.length; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + A[i - 1]);
		}

		return (int) ((start == 0) ? dp[A.length - 1] : dp[A.length]);
	}
	
	/**
	 * anothe method
	 * 
	 * 	return (int) Math.max(HouseRobber.houseRobber(Arrays.copyOfRange(nums, 0, nums.length-1)),HouseRobber.houseRobber(Arrays.copyOfRange(nums, 1, nums.length)));
	 * 
	 */

	
}
