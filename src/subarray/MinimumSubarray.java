package subarray;

import java.util.ArrayList;

/**
 * Given an array of integers, find the subarray with smallest sum.
 * 
 * Return the sum of the subarray.
 * 
 * Notice
 * 
 * The subarray should contain one integer at least.
 * 
 * Example For [1, -1, -2, 1], return -3
 * 
 */
public class MinimumSubarray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Greedy 原理和MaximumSubarray.java 一样, 具体解释， 可参考那个类里
	 * 
	 * better than DP method, you don't need extra space i.e. array
	 */
	public int minSubArray(ArrayList<Integer> nums) {
		if (nums == null || nums.size() == 0) {
			return 0;
		}
		// global min
		int globalMin = nums.get(0);
		// local min
		int localMin = nums.get(0);
		for (int i = 1; i < nums.size(); i++) {
			localMin = Math.min(localMin + nums.get(i), nums.get(i));
			globalMin = Math.min(globalMin, localMin);
		}
		return globalMin;
	}

	/**
	 * 重要！ 真正的 DP!: globalMin[i],表示下标 0 到 i (include i) 的时候的 min subarray
	 * 
	 * 可以直接返回globalMin[A.length-1],就是结果, 只有有这种方法才能真正的保存下来 到目前位置的Min Subarray
	 * 
	 * http://hehejun.blogspot.ca/2015/01/leetcodemaximum-subarray.html
	 */
	public static int minSubArrayDP(int[] A) {
		int[] globalMin = new int[A.length];
		globalMin[0] = A[0];
		int localMin = A[0];
		for (int i = 1; i < A.length; i++) {
			localMin = Math.min(A[i], localMin + A[i]);
			globalMin[i] = Math.min(globalMin[i - 1], localMin);
		}
		return globalMin[A.length - 1];
	}

	/**
	 * DP思想：这里的DP思想是用在localMin数组上所以，最后你不可以用sum[A.length-1] 作为返回结果, 这里的
	 * sum数组其实保存的localMin,而题目是要找global min
	 */
	public int minSubArray1(ArrayList<Integer> nums) {
		if (nums == null || nums.size() == 0) {
			return 0;
		}
		int[] sum = new int[nums.size()];

		// sum其实是存的是localMin
		sum[0] = nums.get(0);
		// min 为global min
		int min = nums.get(0);
		for (int i = 1; i < nums.size(); i++) {
			sum[i] = Math.min(sum[i - 1] + nums.get(i), nums.get(i));
			min = Math.min(min, sum[i]);
		}
		return min;
	}
}
