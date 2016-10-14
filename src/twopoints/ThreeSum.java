package twopoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a +
 * b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 */

public class ThreeSum {

	public static void main(String[] args) {
		int[] A = { -4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6 };

		if (threeSumBinarySearch(A) != null) {
			for (List<Integer> list : threeSumBinarySearch(A)) {
				System.out.println(list);
			}
		}

	}

	/**
	 * This problem can be solved by using two pointers. Time complexity is
	 * O(n^2).
	 * 
	 * To avoid duplicate, we can take advantage of sorted arrays, i.e., move
	 * pointers by >1 to use same element only once.
	 *
	 */
	public static List<List<Integer>> threeSum(int[] A) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (A == null || A.length < 3) {
			return result;
		}
		// you must sort it for moving pointers and avoiding duplicate easily
		Arrays.sort(A);

		for (int i = 0; i <= A.length - 3; i++) {
			// NOTE below condition is important to get unique sets, other wise,
			// you will end up with some duplicate result in result list
			if (i != 0 && A[i] == A[i - 1])
				continue;
			int left = i + 1;
			int right = A.length - 1;
			while (left < right) {
				int sum = A[left] + A[right] + A[i];
				if (sum == 0) {
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(A[i]);
					list.add(A[left]);
					list.add(A[right]);
					result.add(list);
					left++;
					right--;
					// Note: 注意位置，只有找到了和为0的集合时候，我们才移动to ignore duplicate number
					// while loop里， i 是固定的，由于A[i] +A[left] + A[right]=0， 为了去除重复,
					// 一直移动A[left],直到A[left]和之前那个找到的结果的A[left]不同，如果A[right] =
					// A[right+1],那么和肯定不为0，所以right也要跟着移动
					while (left < right && A[left] == A[left - 1]) {
						left++;
					}
					while (left < right && A[right] == A[right + 1]) {
						right--;
					}

				} else if (sum < 0) {
					left++;
				} else {
					right--;
				}

			}// while
		}// end for

		return result;
	} // end method

	public static List<List<Integer>> threeSumBinarySearch(int[] A) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (A == null || A.length < 3) {
			return result;
		}
		// you must sort it for moving pointers and avoiding duplicate easily
		Arrays.sort(A);

		int left = 0;
		int right = A.length - 1;
		while (left < right) {
			int sum = A[left] + A[right];
			int index = binarySearch(A, left + 1, right - 1, sum);
			if(index == -1){
				left++;
				continue;
			}
			if(A[index] == sum){
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(A[left]);
				list.add(A[right]);
				list.add(A[index]);
				result.add(list);
				left++;
				right--;
		    } else if (index != -1 && A[index] < sum) {
				left++;
			} else {
				right--;
			}

		}// while

		return result;
	} // end method

	public static int binarySearch(int[] A, int start, int end, int target) {
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (A[mid] > target) {
				end = mid;
			} else if (A[mid] < target) {
				start = mid;
			} else {
				return mid;
			}
		}
		if (A[start] == target) {
			return start;
		} else if (A[end] == target) {
			return end;
		}
		return -1;
	}

}