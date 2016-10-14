package duplicate;

import java.util.Arrays;

/**
 * What if duplicates are allowed at most twice?For example, given sorted array
 * A = [1,1,1,2,2,3], your function should return length = 5, and A is now
 * [1,1,2,2,3].
 */
public class RemoveDuplicatesfromSortedArrayII {

	public static int[] removeDuplicates(int[] A) {
		if (A.length <= 2)
			return A;

		int prev = 1;
		int curr = 2;
		// 关键是要让pre hold住位置，保证pre之前的是满足要求的，和RemoveDuplicatesfromSortedArray，
		// 原理类似，保证0..pre的位置是满足要求的
		while (curr < A.length) {
			// 其实这个if condition的意思也就是要保证， 0... pre 坐标的数字都是满足要求的， 如果if条件为true,
			// 表示pre之前以及有2个重复了，所以pr不能动了
			if (A[curr] == A[prev] && A[curr] == A[prev - 1]) {
				curr++;
			} else {
				prev++;
				A[prev] = A[curr];
				curr++;
			}
		}
		// prev is the last index of the result array (pre+1 is the length)
		int[] B = Arrays.copyOf(A, prev + 1);

		return B;
	}

	/**
	 * jiuzhang
	 */

	public int removeDuplicates1(int[] nums) {
		// write your code here
		if (nums == null)
			return 0;
		int cur = 0;
		int i, j;
		for (i = 0; i < nums.length;) {
			int now = nums[i];
			for (j = i; j < nums.length; j++) {
				if (nums[j] != now)
					break;
				if (j - i < 2)
					nums[cur++] = now;
			}
			i = j;
		}
		return cur;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 2, 2, 3, 3 };
		int[] arr1 = { 1, 1, 1, 2, 2, 3 };
		removeDuplicates(arr1);
		for (int i : removeDuplicates(arr)) {
		//	System.out.println(i);
		}

	}
}
