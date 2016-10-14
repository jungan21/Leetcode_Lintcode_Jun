package array;

import java.util.Arrays;

/**
 * Rotate an array of n elements to the right by k steps. (从右边数， 第三个数开始rotate)
 * 
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to
 * [5,6,7,1,2,3,4]. s *
 */

// http://www.programcreek.com/2015/03/rotate-array-in-java/
public class RotateArray {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
		rotate(nums, 3);
	}

	// method3: Best one
	// Space is O(1) and time is O(n)

	public static void rotate(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k < 0) {
			throw new IllegalArgumentException("Illegal argument!");
		}

		if (k > arr.length) {
			k = k % arr.length;
		}

		// length of first part
		int a = arr.length - k;

		reverse(arr, 0, a - 1);
		reverse(arr, a, arr.length - 1);
		reverse(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));

	}

	public static void reverse(int[] arr, int left, int right) {
		if (arr == null || arr.length == 1)
			return;

		while (left < right) {
			int temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}
	}

	// Bubble Rotate: time O(n*k), space: O(1)
	// can't submit to leetcode, break time limit
	public static void rotate1(int[] nums, int k) {
		if (nums == null || k < 0) {
			throw new IllegalArgumentException("Illegal argument!");
		}

		for (int i = 0; i < k; i++) {
			for (int j = nums.length - 1; j > 0; j--) {
				int temp = nums[j];
				nums[j] = nums[j - 1];
				nums[j - 1] = temp;
			}
			for (int n : nums) {
				System.out.print(n + " ");
			}
			System.out.println("===========");
		}

	}

	// method2
	// Space is O(n) and time is O(n)
	public void rotate2(int[] nums, int k) {
		if (k > nums.length)
			k = k % nums.length;

		int[] result = new int[nums.length];

		for (int i = 0; i < k; i++) {
			result[i] = nums[nums.length - k + i];
		}

		int j = 0;
		for (int i = k; i < nums.length; i++) {
			result[i] = nums[j];
			j++;
		}

		System.arraycopy(result, 0, nums, 0, nums.length);
	}

}
