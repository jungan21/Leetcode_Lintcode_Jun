package subarray;

/**
 * Given an array with integers.
 * 
 * Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)| is the
 * largest.
 * 
 * Return the largest difference.
 * 
 * Notice: The subarray should contain at least one number
 * 
 * Example For [1, 2, -3, 1], return 6. (i.e. SUMA: (1+2) SUMB = -3)
 *
 */
public class MaximumSubarrayDifference {

	public static void main(String[] args) {
		int[] A = { 1, 2, -3, 1 };
		maxDiffSubArrays(A);
	}

	/**
	 * O(n) time and O(n) space. 原理和BestTimetoBuyandSellStockIII 类似
	 * 
	 */

	public static int maxDiffSubArrays(int[] nums) {// write your code here
		// 下面这些数组，实际上就等价于，Maximum Subarray 里的globalMax, globalMin数组
		int size = nums.length;
		int[] left_max = new int[size];
		int[] left_min = new int[size];
		int[] right_max = new int[size];
		int[] right_min = new int[size];

		/**
		 * Forward: get global max and global min subarray from left to right
		 * 
		 * 就是 MaximumSubarray.maxSubArrayDP的方法, left_max[i]对应globalMax数组
		 * 
		 * 区别是MaximumSubarray那题目，我们只要求最后一个最大值就可以，就不用一定要弄个数组去保存到每个一个位置为止的maximum
		 * subarray,但这个题目需要保存所有路过位置，之前的maximum subarray，因为这个题目牵涉到要分割
		 */
		left_max[0] = nums[0];
		left_min[0] = nums[0];
		int localMax = nums[0];
		int localMin = nums[0];

		for (int i = 1; i < size; i++) {
			localMax = Math.max(localMax + nums[i], nums[i]);
			localMin = Math.min(localMin + nums[i], nums[i]);
			left_max[i] = Math.max(left_max[i - 1], localMax);
			left_min[i] = Math.min(left_min[i - 1], localMin);
		}

		/**
		 * backward: get global max and global min subarray from right to left
		 * 
		 * 其实就是 MaximumSubarray.maxSubArrayDP的方法
		 */
		right_max[nums.length - 1] = nums[nums.length - 1];
		right_min[nums.length - 1] = nums[nums.length - 1];
		// just reuse above two variables
		localMax = nums[nums.length - 1];
		localMin = nums[nums.length - 1];

		for (int i = nums.length - 2; i >= 0; i--) {
			localMax = Math.max(localMax + nums[i], nums[i]);
			localMin = Math.min(localMin + nums[i], nums[i]);
			right_max[i] = Math.max(right_max[i + 1], localMax);
			right_min[i] = Math.min(right_min[i + 1], localMin);
		}

		// maxDiff =0即表示绝对值最小,题目要找最大值，所以设置成最小，
		int maxDiff = 0;

		int diff1 = 0;
		int diff2 = 0;

		/**
		 * Note:
		 * 
		 * 1.对下标的处理，与MaximumSubarrayII 原理类似，但是不同于BestTimetoBuyandSellStockIII
		 * 
		 * 之所以这样因为题目要求 non-overlapping
		 * 
		 * 2. 因为就绝对值，所以， 两种可能都要试试
		 */
		for (int i = 0; i < size - 1; i++) {
			diff1 = Math.abs(left_max[i] - right_min[i + 1]);
			diff2 = Math.abs(left_min[i] - right_max[i + 1]);
			maxDiff = Math.max(maxDiff, Math.max(diff1, diff2));
		}
		return maxDiff;
	}
}
