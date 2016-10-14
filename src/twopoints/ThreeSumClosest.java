package twopoints;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is
 * closest to a given number, target. Return the sum of the three integers.
 * 
 * Notice You may assume that each input would have exactly one solution.
 * 
 * Example For example, given array S = [-1 2 1 -4], and target = 1. The sum
 * that is closest to the target is 2. (-1 + 2 + 1 = 2)
 */
public class ThreeSumClosest {

	public static void main(String[] args) {
		int[] A = { -1, 2, 1, -4 };

		System.out.println(threeSumClosest(A, 1));
		// threeSumClosest(A, 1);

	}

	// 总的时间复杂度为O(n^2+nlogn)=(n^2),空间复杂度是O(n)
	public static int threeSumClosest(int[] A, int target) {
		Arrays.sort(A);
		int minDiff = Integer.MAX_VALUE;
		int closest_sum = 0;
		for (int i = 0; i < A.length - 2; i++) {
			if (i != 0 && A[i] == A[i - 1])
				continue;
			int left = i + 1;
			int right = A.length - 1;
			while (left < right) {
				int sum = A[i] + A[left] + A[right];
				int diff = Math.abs(sum - target);
				if (diff == 0)
					return sum;
				if (diff < minDiff) {
					minDiff = diff;
					closest_sum = sum;
				}
				if (sum <= target) {
					left++;
				} else if (sum > target) {
					right--;
				}
			}
		}
		return closest_sum;
	}
}
