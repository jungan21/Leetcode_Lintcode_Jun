package twopoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This problem can be solved by using two pointers. Time complexity is O(n^2).
 * 
 * To avoid duplicate, we can take advantage of sorted arrays, i.e., move
 * pointers by >1 to use same element only once.
 *
 */
public class FourSum {

	public static void main(String[] args) {
		int[] nums = { 1, 0, -1, 0, -2, 2 };

		if (fourSum(nums, 0) != null) {
			for (List<Integer> list : fourSum(nums, 0)) {
				System.out.println(list);
			}
		}

	}

	// A typical left-sum problem. Time is N to the power of (left-1).
	public static ArrayList<ArrayList<Integer>> fourSum(int[] A, int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		if (A == null || A.length < 4) {
			return result;
		}
		// you must sort it for moving pointers and avoiding duplicate easily
		Arrays.sort(A);

		for (int i = 0; i < A.length - 3; i++) {
			// Note:ignore duplicate number,表示已当前i为开头的数字已经处理过了
			if (i != 0 && A[i] == A[i - 1])
				continue;
			// 注意： j=i+1, 而不能固定住j的为值为一个常量
			for (int j = i + 1; j < A.length - 2; j++) {
				// Note:ignore duplicate number,表示已当前i位置固定的情况下， j为开头的数字已经处理过了
				if (j != i + 1 && A[j] == A[j - 1])
					continue;
				int left = j + 1;
				int right = A.length - 1;

				while (left < right) {
					int sum = A[i] + A[j] + A[left] + A[right];
					if (sum == target) {
						ArrayList<Integer> list = new ArrayList<Integer>();
						list.add(A[i]);
						list.add(A[j]);
						list.add(A[left]);
						list.add(A[right]);
						result.add(list);
						left++;
						right--;
						// Note: 注意位置，只有找到了和为0的集合时候，我们才移动to ignore duplicate
						// number
						while (left < right && A[left] == A[left - 1]) {
							left++;
						}
						while (left < right && A[right] == A[right + 1]) {
							right--;
						}
					} else if (sum < target) {
						left++;
					} else {
						right--;
					}
				}// while
			} // end inner for
		}// end for
		return result;
	}
}
