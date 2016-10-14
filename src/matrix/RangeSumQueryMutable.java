package matrix;

/**
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive.
 * 
 * The update(i, val) function modifies nums by updating the element at index i
 * to val.
 * 
 * Example: Given nums = [1, 3, 5]
 * 
 * sumRange(0, 2) -> 9 update(1, 2) sumRange(0, 2) -> 8
 * 
 * Note: The array is only modifiable by the update function. You may assume the
 * number of calls to update and sumRange function is distributed evenly.
 *
 */

/**
 * http://www.programcreek.com/2014/04/leetcode-range-sum-query-mutable-java/
 * 
 * https://www.youtube.com/watch?v=CWDQJGaN1gY
 * 
 * http://blog.csdn.net/int64ago/article/details/7429868
 * 
 * http://www.cnblogs.com/grandyang/p/4985506.html
 * 
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree
 * /FenwickTree.java
 */

public class RangeSumQueryMutable {

	public static void main(String[] args) {
		int[] nums = { 1, 3, 5 };
		RangeSumQueryMutable test = new RangeSumQueryMutable(nums);
		System.out.println(test.sumRange(1, 2));
		test.update(1, 2);
		System.out.println(test.sumRange(1, 2));

	}

	/**
	 * Fenwick Tree / Binary Indexed Tree
	 * https://www.youtube.com/watch?v=CWDQJGaN1gY
	 * 
	 * https://www.youtube.com/watch?v=v_wj_mOAlig&index=2&list=
	 * PLZ_pfgKiIBrTdUKevYPsS0c7jAhgkPb6e
	 * 
	 * http://blog.csdn.net/int64ago/article/details/7429868
	 */
	int[] btree;
	int[] arr;

	public RangeSumQueryMutable(int[] nums) {
		btree = new int[nums.length + 1];
		arr = nums;

		for (int i = 0; i < nums.length; i++) {
			add(i + 1, nums[i]);
		}
		System.out.println("test");
	}

	void add(int i, int val) {
		/**
		 * http://www.cnblogs.com/grandyang/p/4985506.html 对比文章中的图看，就知道为什么 j+ (j
		 * & (-j))
		 */
		for (int j = i; j < btree.length; j = j + (j & (-j))) {
			btree[j] += val;
		}
	}

	void update(int i, int val) {
		add(i + 1, val - arr[i]);
		arr[i] = val;
	}

	public int sumRange(int i, int j) {
		// 其实和求prefix sum 一样： sums[j] - sums[i-1], 只不过由于Btree数组比原始数组长1位，
		// sums[j+1] - sum[i]
		int sum1 = sumRangeHelper(j + 1);
		int sum2 = sumRangeHelper(i);
		return sum1 - sum2;
	}

	public int sumRangeHelper(int i) {
		int sum = 0;
		for (int j = i; j >= 1; j = j - (j & (-j))) {
			sum += btree[j];
		}
		return sum;
	}
}
