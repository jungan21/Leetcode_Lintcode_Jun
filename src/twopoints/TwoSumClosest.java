package twopoints;

import java.util.Arrays;

/**
 * Given an array nums of n integers, find two integers in nums such that the
 * sum is closest to a given number, target.
 * 
 * Return the difference between the sum of the two integers and the target.
 * 
 * Example Given array nums = [-1, 2, 1, -4], and target = 4.
 * 
 * The minimum difference is 1. (4 - (2 + 1) = 1).
 * 
 * Do it in O(nlogn) time complexity.
 */
public class TwoSumClosest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// O(nlogn) time complexity.
	public int twoSumCloset(int[] nums, int target) {

		// have to sort it first
		Arrays.sort(nums);

		int minDiff = Integer.MAX_VALUE;
		int sum = 0;
		int diff = 0;

		int i = 0, j = nums.length - 1;
		while (i < j) {
			sum = nums[i] + nums[j];
			diff = Math.abs(sum - target);
			minDiff = Math.min(minDiff, diff);
			if (sum < target) {
				i++;
			} else {
				j--;
			}
		}
		return minDiff;
	}
}
