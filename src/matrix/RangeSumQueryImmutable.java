package matrix;

/**
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive.
 * 
 * Example: Given nums = [-2, 0, 3, -5, 2, -1]
 * 
 * sumRange(0, 2) -> 1 sumRange(2, 5) -> -1 sumRange(0, 5) -> -3
 * 
 * Note: You may assume that the array does not change. There are many calls to
 * sumRange function.
 * 
 *
 */
// Your NumArray object will be instantiated and called as such:
// RangeSumQueryImmutable numArray = new RangeSumQueryImmutable(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);
public class RangeSumQueryImmutable {

	public static void main(String[] args) {

	}

	public int[] sums;

	public RangeSumQueryImmutable(int[] nums) {
		if (nums == null) {
			sums = null;
		} else if (nums.length == 0) {
			sums = new int[0];
		} else {
			// 类似于DP赋值
			this.sums = new int[nums.length];
			sums[0] = nums[0];
			for (int i = 1; i < nums.length; i++) {
				sums[i] = sums[i - 1] + nums[i];
			}
		}
	}

	// 相当于前缀和
	public int sumRange(int i, int j) {
		if (sums == null) {
			return 0;
		}
		if (i >= sums.length || j >= sums.length || i > j || i < 0 || j < 0) {
			return 0;
		} else if (i == 0) {
			return sums[j];
		} else {
			return sums[j] - sums[i - 1];
		}
	}

}
