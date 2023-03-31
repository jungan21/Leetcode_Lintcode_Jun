package longest_minimum;

import java.util.Arrays;

// dynamic programming

/**
 * For example, Given [10, 9, 2, 5, 3, 7, 101, 18], The longest increasing
 * subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may
 * be more than one LIS combination, it is only necessary for you to return the
 * length.
 * 
 * Your algorithm should run in O(n2) complexity.
 * 
 * Follow up: Could you improve it to O(n log n) time complexity?
 * 
 *
 */
public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		// int[] nums = { 1, 1, 1, 1, 1, 1, 1 };
		int[] nums = { 4, 2, 4, 5, 3, 7 };
		System.out.println(longestIncreasingSubsequence(nums));
	}

	public int lengthOfLISBruteforceMethod(int[] nums) {
		if(nums==null || nums.length==0)
			return 0;

		int[] max = new int[nums.length];
		Arrays.fill(max, 1);

		int result = 1;
		for(int i=0; i<nums.length; i++){
			for(int j=0; j<i; j++){
				if(nums[i]>nums[j]){
					max[i]= Math.max(max[i], max[j]+1);

				}
			}
			result = Math.max(max[i], result);
		}

		return result;
	}

	// http://www.programcreek.com/2014/04/leetcode-longest-increasing-subsequence-java/
	// Dynamic programmming Time: O(n^2 )
	/**
	 * Let dp[i] represent the length of the longest increasing subsequence so
	 * far. If any element before i is smaller than nums[i], then dp[i] =
	 * max(dp[i], dp[j]+1).
	 */
	public static int lengthOfLIS(int[] nums) {

		if (nums == null || nums.length == 0) {
			return 0;
		}

		int[] dp = new int[nums.length];
		Arrays.fill(dp, 1);
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j =0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			// Note:不能return 数组最后一个值，因为这题目的终点不是固定的，任意点结束，都可能是最大值
			result = Math.max(result, dp[i]);
		}
		return result;

	}

	/**
	 * 前1个数的LIS长度d(1)=1(序列：5) 前2个数的LIS长度d(2)=1(序列：3；3前面没有比3小的)
	 * 前3个数的LIS长度d(3)=2(序列：3，4；4前面有个比它小的3，所以d(3)=d(2)+1)
	 * 前4个数的LIS长度d(4)=3(序列：3，4，8；8前面比它小的有3个数，所以 d(4)=max{d(1),d(2),d(3)}+1=3)
	 * 
	 */

	/**
	 * Recommended!!!
	 * 
	 * better method: O(nlog(n)) with binary search
	 * 
	 * http://www.jiuzhang.com/solutions/longest-increasing-subsequence/
	 */
	public static int longestIncreasingSubsequence(int[] nums) {
		// extra array to help us
		int[] minArray = new int[nums.length];
		Arrays.fill(minArray, Integer.MAX_VALUE);

		for (int i = 0; i < nums.length; i++) {
			int index = binarySearch(minArray, nums[i]);
			minArray[index] = nums[i];
		}

		for (int i = minArray.length - 1; i >= 0; i--) {
			if (minArray[i] != Integer.MAX_VALUE) {
				/**
				 * minArray:
				 * 
				 * 1, 2, 3, MAX, MAX
				 * 
				 * i is the index, we need the length so we return i + 1:
				 */
				return i + 1;
			}
		}
		return 0;
	}

	public static int longestIncreasingSubsequence1(int[] nums) {
		int[] minLast = new int[nums.length + 1];
		minLast[0] = -1;
		for (int i = 1; i <= nums.length; i++) {
			// 必须设置成max, 这样，才能找到first number >= target
			minLast[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < nums.length; i++) {
			// find the first number in minLast >= nums[i]
			int index = binarySearch(minLast, nums[i]);
			minLast[index] = nums[i];
		}

		for (int i = nums.length; i >= 1; i--) {
			if (minLast[i] != Integer.MAX_VALUE) {
				return i;
			}
		}

		return 0;
	}

	// find the first number >= num
	private static int binarySearch(int[] minLast, int num) {
		int start = 0, end = minLast.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (minLast[mid] >= num) {
				end = mid;
			} else {
				start = mid;
			}
		}

		if (minLast[start] >= num) {
			return start;
		}
		return end;
	}

}
