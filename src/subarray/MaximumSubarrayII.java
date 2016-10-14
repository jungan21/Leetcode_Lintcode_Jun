package subarray;

import java.util.ArrayList;

/**
 * find 2 non-overlapping subarrays which have the *largest sum*. The number in
 * each subarray should be contiguous. Return the largest sum.
 * 
 * Example For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2,
 * -1, 2] or [1, 3, -1, 2] and [2], they both have the largest sum 7.
 */
public class MaximumSubarrayII {

	public static void main(String[] args) {
		int[] nums = { 1, 3, -1, 2, -1, 2 };
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int a : nums) {
			list.add(a);
		}
		System.out.println(maxTwoSubArrays(list));
	}

	public static int maxTwoSubArrays(ArrayList<Integer> nums) {
		if (nums == null || nums.size() < 1) {
			return 0;
		}

		int size = nums.size();

		int[] leftMax = new int[size];
		int[] rightMax = new int[size];

		int localMax = nums.get(0);
		leftMax[0] = nums.get(0);
		for (int i = 1; i < size; i++) {
			localMax = Math.max(localMax + nums.get(i), nums.get(i));
			leftMax[i] = Math.max(leftMax[i - 1], localMax);
		}
		// re-use above variable
		localMax = nums.get(size - 1);
		rightMax[size - 1] = nums.get(size - 1);
		for (int i = size - 2; i >= 0; i--) {
			localMax = Math.max(localMax + nums.get(i), nums.get(i));
			rightMax[i] = Math.max(rightMax[i + 1], localMax);
		}

		int maxSum = Integer.MIN_VALUE;
		/**
		 * Note:
		 * 
		 * 1.对下标的处理，与MaximumSubarrayDifference类似，
		 * 但是不同于BestTimetoBuyandSellStockIII
		 * 
		 * 之所以这样因为题目要求 non-overlapping， 所以下标不能重叠
		 * 
		 * 2. 因为就绝对值，所以， 两种可能都要试试
		 */
		for (int i = 0; i < size - 1; i++) {
			maxSum = Math.max(maxSum, leftMax[i] + rightMax[i + 1]);
		}
		return maxSum;
	}

}
