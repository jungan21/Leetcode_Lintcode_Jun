package company.wayfair.coding;

/**
 * find a contiguous subarray which has the largest sum.
 * 
 * Notice: The subarray should contain at least one number.
 *
 * Given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1]
 * has the largest sum = 6.
 */
public class MaximumSubarray {

	public static void main(String[] args) {
		// int[] nums = { 1, 2, -3, 1 };
		int[] nums = { -2, 2, -3, 4, -1, 2, 1, -5, 3 };
		maxSubArray(nums);
	}

	/**
	 * greedy思想: Kadane 算法 O(n), better, no need for extra space
	 * 
	 * jiuzhang: http://www.jiuzhang.com/solutions/maximum-subarray/
	 */
	public static int maxSubArray(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int globalMax = A[0];
		int localMax = A[0];
		for (int i = 1; i < A.length; i++) {
			localMax = Math.max(A[i], localMax + A[i]);
			globalMax = Math.max(globalMax, localMax);
		}
		return globalMax;
	}

	/**
	 * DP思想：这里的DP思想是用在localMax数组上所以，最后你不可以用sum[A.length-1] 作为返回结果, 这里的
	 * sum数组其实保存的localMax,而题目是要找global max
	 * 
	 * 试想一下，如果我们从头遍历这个数组。对于数组中的其中一个元素，它只有两个选择：
	 * 
	 * 1. 要么加入之前的数组加和之中（跟别人一组）2. 要么自己单立一个数组（自己单开一组）
	 * 
	 * 所以对于这个元素应该如何选择，就看他能对哪个组的贡献大。如果跟别人一组，能让总加和变大，还是跟别人一组好了；如果自己起个头一组，
	 * 自己的值比之前加和的值还要大，那么还是自己单开一组好了。
	 * 
	 * 所以利用一个localMax数组，记录每一轮localMax的最大值，localMax[i]表示当前这个元素是跟之前数组加和一组还是自己单立一组好
	 * ， 然后维护一个全局最大值即位答案。
	 */

	public static int maxSubArray1(int[] A){
		int[] localMax = new int[A.length];
		localMax[0] = A[0];
		int globalMax = A[0];
		for (int i = 1; i < A.length; i++) {
			localMax[i] = Math.max(A[i], localMax[i - 1] + A[i]);
			globalMax = Math.max(globalMax, localMax[i]);
		}
		return globalMax;
	}

	/**
	 * 重要！ 真正的 DP!: globalMax[i],表示下标 0 到 i (include i) 的时候， 的maximum subarray
	 * 
	 * 可以直接返回globalMax[A.length-1],就是结果, 这有这种方法才能真正的保存下来 到目前位置的Maximum Subarray
	 * 
	 * http://hehejun.blogspot.ca/2015/01/leetcodemaximum-subarray.html
	 */
	public static int maxSubArrayDP(int[] A) {
		int[] globalMax = new int[A.length];
		globalMax[0] = A[0];
		int localMax = A[0];
		for (int i = 1; i < A.length; i++) {
			localMax = Math.max(A[i], localMax + A[i]);
			globalMax[i] = Math.max(globalMax[i - 1], localMax);
		}
		return globalMax[A.length - 1];
	}

	/**
	 * prefix sum method, jiuzhang 也是老师在课堂上讲的
	 * 
	 * sum[i] 表示0， 到 i 下标的数的和，sum[j] 表示0， 到 j 下标的数的和 ==> 下标从i 到j 的和为 sum[j] -
	 * sum[i-1]
	 * 
	 */
	public int maxSubArrayPrefixSum(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}

		int max = Integer.MIN_VALUE, sum = 0, minSum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
			// prefix sum, use the sum from (0 to i) to minus the min {prefix
			// sum}
			max = Math.max(max, sum - minSum);
			minSum = Math.min(minSum, sum);
		}

		return max;
	}
}
