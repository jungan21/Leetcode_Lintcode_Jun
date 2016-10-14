package subarray;

import java.util.Arrays;

/**
 * Given an integer array, find a subarray where the sum of numbers is in a
 * given interval. Your code should return the number of possible answers. (The
 * element in the array should be positive)
 * 
 * Given [1,2,3,4] and interval = [1,3], return 4. The possible answers are:
 * 
 * [0, 0] [0, 1] [1, 1] [2, 2]
 * 
 */
public class SubarraySumII {

	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 4 };

		System.out.println(subarraySumII(A, 1, 3));

	}

	public static int subarraySumII(int[] A, int start, int end) {
		int len = A.length;
		for (int i = 1; i < len; ++i)
			A[i] += A[i - 1];

		Arrays.sort(A);
		int cnt = 0;
		for (int i = 0; i < len; ++i) {
			if (A[i] >= start && A[i] <= end)
				cnt++;
			int l = A[i] - end;
			int r = A[i] - start;
			cnt += find(A, len, r + 1) - find(A, len, l);
		}
		return cnt;
	}

	static int find(int[] A, int len, int value) {
		if (A[len - 1] < value)
			return len;

		int l = 0, r = len - 1, ans = 0;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (value <= A[mid]) {
				ans = mid;
				r = mid - 1;
			} else
				l = mid + 1;
		}
		return ans;
	}

}
