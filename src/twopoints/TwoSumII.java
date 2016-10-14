package twopoints;

import java.util.Arrays;

/**
 * Given an array of integers that is already sorted in ascending order, find
 * two numbers such that they add up to a specific target number. The function
 * twoSum should return indices of the two numbers such that they add up to the
 * target, where index1 must be less than index2. Please note that your returned
 * answers (both index1 and index2) are not zero-based. You may assume that each
 * input would have exactly one solution.
 * 
 *
 */
public class TwoSumII {


	public int[] twoSum(int[] nums, int target) {
		if (nums == null || nums.length < 1)
			return null;
		int i = 0, j = nums.length - 1;

		while (i < j) {

			int sum = nums[i] + nums[j];
			if (target > sum) {
				i++;
			} else if (target < sum) {
				j--;
			} else {
				return new int[] { i, j };
			}

		}

		return null;
	}

	/**
	 * Given an array of integers, find how many pairs in the array such that
	 * their sum is bigger than a specific target number. Please return the
	 * number of pairs.
	 * 
	 * find how many pairs that sum > target
	 * 
	 * http://www.lintcode.com/en/problem/two-sum-ii/
	 */
	public int twoSum2(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		Arrays.sort(nums);
		int left = 0;
		int right = nums.length - 1;
		int count = 0;

		while (left < right) {
			if (nums[left] + nums[right] > target) {
				count = count + (right - left);
				right--;
			} else if (nums[left] + nums[right] <= target) {
				left++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] s = { 2, 7, 11, 15 };
		int value = 9;

		TwoSumII finder = new TwoSumII();
		int[] indexArray = finder.twoSum(s, value);

		if (indexArray != null) {
			for (int index : indexArray) {
				System.out.println(index);
			}
		} else {
			System.out.println("No such two numbers found in the array!");
		}
	}
}