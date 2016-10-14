package subarray;

/**
 * Given an array of integers and a number k, find k non-overlapping subarrays
 * which have the largest sum.
 * 
 * The number in each subarray should be contiguous. Return the largest sum.
 * 
 * Notice: The subarray should contain at least one number
 *
 * Example Given [-1,4,-2,3,-2,3], k=2, return 8
 */
public class MaximumSubarrayIII {

	public static void main(String[] args) {
		int[] nums = { -1, -2, -3, -100, -1, -50 };
		System.out.println(maxSubArray(nums, 2));
	}

	/**
	 * jiuzhang 方法一 划分类DP
	 * 
	 * Time: O(k * n^2)
	 * 
	 * Space: O(k * n)
	 * 
	 * http://www.jiuzhang.com/solutions/maximum-subarray-iii/
	 * 
	 * http://hehejun.blogspot.ca/2015/01/lintcodemaximum-subarray-iii.html
	 * 
	 * localMax[i][j]为进行了i次partition前j个数字的最大subarray，并且最后一个subarray以A[j -
	 * 1](A为输入的数组)结尾
	 * 
	 * globalMax[i][j]为进行了i次partition前j个数字的最大subarray(最后一个subarray不一定需要,以j -
	 * 1结尾)
	 */

	/**
	 * from jiuzhang 课件
	 * 
	 * localMax[i][j] 表示前j个元素,取i个子数组,第j个元素必须取的最大值
	 * 
	 * globalMax[i][j]表示前j个元素,取i个子数组,第j个元素随便取不取的最大值
	 */
	public static int maxSubArray(int[] A, int k) {
		if (A.length < k) {
			return 0;
		}
		int len = A.length;

		/**
		 * i ==0, j==0, 都不用初始化，i=0,表示0次partiiton,
		 * 没有意义，1次partition,表示整个数组，j=0,表示前0个数字，也都是0
		 */
		int[][] globalMax = new int[k + 1][len + 1];
		int[][] localMax = new int[k + 1][len + 1];

		for (int i = 1; i <= k; i++) {
			localMax[i][i - 1] = Integer.MIN_VALUE;
			/**
			 * 小于 i 的数组不能够partition, 譬如 global[3][2], 前两个数，不可能partition成 3 个non
			 * overlapping的subarray, 所以 j从 i 开始
			 */
			for (int j = i; j <= len; j++) {
				// globalMax[i - 1][j - 1]) 前j-1个元素组成i-1组数组，
				/**
				 * 先取max再 加上 A[j-1], 否则，如果 local/ global是MIN_VALUE, 而A[j-1]是负数，就会越界
				 */
				localMax[i][j] = Math.max(localMax[i][j - 1],
						globalMax[i - 1][j - 1]) + A[j - 1];
				if (j == i)
					globalMax[i][j] = localMax[i][j];
				else
					// globalMax[i][j - 1] 表示前j-1 个元素，组成i组数组
					globalMax[i][j] = Math.max(globalMax[i][j - 1],
							localMax[i][j]);
			}
		}
		return globalMax[k][len];
	}

}
