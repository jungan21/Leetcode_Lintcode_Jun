package subarray;

/**
 * Find the contiguous subarray within an array (containing at least one number)
 * which has the largest product.
 *
 * Example For example, given the array [2,3,-2,4], the contiguous subarray
 * [2,3] has the largest product = 6.
 * 
 * 推荐 maxProduct2
 */

public class MaximumProductSubarray {

	public static void main(String[] args) {
		int[] A = { -4, -3, -2 };
		System.out.println(maxProduct2(A));
	}

	/**
	 * 
	 * http://www.programcreek.com/2014/03/leetcode-maximum-product-subarray-
	 * java/
	 * 
	 * When iterating the array, each element has two possibilities: positive
	 * number or negative number. We need to track a minimum value, so that when
	 * a negative number is given, it can also find the maximum value. We define
	 * two local variables, one tracks the maximum and the other tracks the
	 * minimum.
	 * 
	 * 乘法中有可能现在看起来小的一个负数，后面跟另一个负数相乘就会得到最大的乘积。我们只需要在维护一个局部最大的同时，
	 * 在维护一个局部最小，这样如果下一个元素遇到负数时，就有可能与这个最小相乘得到当前最大的乘积和，这也是利用乘法的性质得到的
	 */
	public static int maxProduct(int[] A) {
		int[] max = new int[A.length];
		int[] min = new int[A.length];

		min[0] = max[0] = A[0];
		int result = A[0];
		for (int i = 1; i < A.length; i++) {
			if (A[i] > 0) {
				max[i] = Math.max(max[i - 1] * A[i], A[i]);
				min[i] = Math.min(min[i - 1] * A[i], A[i]);
			} else if (A[i] < 0) {
				max[i] = Math.max(min[i - 1] * A[i], A[i]);
				min[i] = Math.min(max[i - 1] * A[i], A[i]);
			}

			result = Math.max(result, max[i]);
		}

		return result;
	}

	/**
	 * 上面解法的优化版本：去除了对正负数的判断，
	 * 
	 * 简而言之： 以A[i]结尾的max product subarray同时取决于以A[i-1]结尾的max / min product
	 * subarray以及A[i]本身。因此，对每个i，需要记录min/max product两个状态：
	 */
	public int maxProduct1(int[] A) {
		int[] max = new int[A.length];
		int[] min = new int[A.length];

		min[0] = max[0] = A[0];
		int result = A[0];
		for (int i = 1; i < A.length; i++) {
			max[i] = Math.max(A[i],
					Math.max(max[i - 1] * A[i], min[i - 1] * A[i]));
			min[i] = Math.min(A[i],
					Math.min(min[i - 1] * A[i], max[i - 1] * A[i]));
			result = Math.max(result, max[i]);
		}
		return result;
	}

	/**
	 * 推荐这种解法， 上面算法的进一步优化，去除了多余的2个数组
	 * 
	 * http://bangbingsyb.blogspot.ca/2014/11/leetcode-maximum-product-
	 * subarray. html
	 * 
	 * Maximum Subarray那题的变种。由于正负得负，负负得正的关系。以A[i]结尾的max product
	 * subarray同时取决于以A[i-1]结尾的max / min product
	 * subarray以及A[i]本身。因此，对每个i，需要记录min/max product两个状态：
	 * 
	 * max_product[i] = max(max_product[i-1]*A[i], min_product[i-1]*A[i], A[i])
	 * min_product[i] = min(max_product[i-1]*A[i], min_product[i-1]*A[i], A[i])
	 * 
	 */
	public static int maxProduct2(int[] A) {
		if (A == null || A.length == 0)
			return 0;

		int max, curMax, curMin;
		max = curMax = curMin = A[0];
		// 注意： i 从1 开始,和乘法和加减不一样，需要从第二个数开始，与第一个数字相城， 加减法，可以用sum=0做为开始
		for (int i = 1; i < A.length; i++) {
			// 必须要设置一个temp to track the max for last/previous
			// round,因为第一行计算curMax的时候覆盖了上一轮的max
			int temp = curMax;
			curMax = Math.max(Math.max(curMax * A[i], curMin * A[i]), A[i]);
			curMin = Math.min(Math.min(temp * A[i], curMin * A[i]), A[i]);
			max = Math.max(max, curMax);
		}
		return max;

	}
}
