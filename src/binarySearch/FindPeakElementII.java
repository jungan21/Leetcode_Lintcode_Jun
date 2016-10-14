package binarySearch;

import java.util.ArrayList;
import java.util.List;

public class FindPeakElementII {

	public static void main(String[] args) {
		int[][] A = { { 1, 5, 3 }, { 4, 10, 9 }, { 2, 8, 7 } };
		System.out.println(findPeakIIJun(A));
	}

	/**
	 * *http://www.tangjikai.com/algorithms/lintcode-390-find-peak-element-ii
	 *
	 * Time complexility: O(nlogm)
	 * http://courses.csail.mit.edu/6.006/spring11/lectures/lec02.pdf
	 */

	public static List<Integer> findPeakII(int[][] A) {
		// this is the nlog(n) method
		int low = 1, high = A.length - 2;
		List<Integer> ans = new ArrayList<Integer>();
		while (low <= high) {
			int mid = (low + high) / 2;
			int col = find(mid, A);
			if (A[mid][col] < A[mid - 1][col]) {
				high = mid - 1;
			} else if (A[mid][col] < A[mid + 1][col]) {
				low = mid + 1;
			} else {
				// 表示A[mid][col] > A[mid + 1][col] and A[mid - 1][col]
				ans.add(mid);
				ans.add(col);
				break;
			}
		}
		return ans;
	}

	// 找到该行里最大的数所在的列
	static int find(int row, int[][] A) {
		int col = 0;
		for (int i = 0; i < A[row].length; i++) {
			if (A[row][i] > A[row][col]) {
				col = i;
			}
		}
		return col;
	}

	public static List<Integer> findPeakIIJun(int[][] A) {
		// write your code here
		List<Integer> result = new ArrayList<Integer>();

		int low = 1;
		int high = A.length - 2;

		while (low + 1 < high) {
			int mid = low + (high - low) / 2;
			// 找中间行的最大值, 这样 该最大值肯定 在这一行比他左右的都大，那么接下来就要判断一下与上一行，以及与下一行就可以了
			int col = find(mid, A);
			if (A[mid][col] < A[mid - 1][col]) {
				high = mid;
			} else if (A[mid][col] < A[mid + 1][col]) {
				low = mid;
			} else {
				result.add(mid);
				result.add(col);
				break;
			}
		}
		int low_col = find(low, A);
		int high_col = find(high, A);
		if (A[low][low_col] > A[high][high_col]) {
			result.add(low);
			result.add(low_col);
		} else {
			result.add(high);
			result.add(high_col);
		}

		return result;
	}
}
