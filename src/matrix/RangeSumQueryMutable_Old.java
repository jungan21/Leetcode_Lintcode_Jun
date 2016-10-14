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
public class RangeSumQueryMutable_Old {

	public static void main(String[] args) {
		int[] nums = { 7, 2, 7, 2, 0 };
		RangeSumQueryMutable_Old test = new RangeSumQueryMutable_Old(nums);
		test.update(4, 6);
		test.update(0, 2);
		test.update(0, 9);
		System.out.println(test.sumRange(4, 4));
	}

	/**
	 * Leetcode: Time Limit Exceeded
	 */
	int[] sums;
	int[] numsCopy;

	public RangeSumQueryMutable_Old(int[] nums) {
		if (nums == null) {
			this.sums = null;
			this.numsCopy = null;
		} else if (nums.length == 0) {
			this.sums = new int[0];
			this.numsCopy = new int[0];
			return;
		}
		this.sums = new int[nums.length];
		this.numsCopy = new int[nums.length];
		sums[0] = nums[0];
		numsCopy[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			sums[i] = sums[i - 1] + nums[i];
			numsCopy[i] = nums[i];
		}
	}

	void update(int i, int val) {
		int diff = val - numsCopy[i];
		numsCopy[i] = val;
		for (int k = i; k < sums.length; k++) {
			sums[k] += diff;
		}
	}

	public int sumRange(int i, int j) {
		if (i >= sums.length || j >= sums.length || i > j || i < 0 || j < 0) {
			return 0;
		} else if (i == 0) {
			return sums[j];
		}
		return sums[j] - sums[i - 1];
	}

}
