package longest_minimum;

/**
 * Give an integer array，find the longest increasing continuous subsequence in
 * this array.
 * 
 * An increasing continuous subsequence:
 * 
 * Can be from right to left or from left to right. Indices of the integers in
 * the subsequence should be continuous. Notice
 * 
 * O(n) time and O(1) extra space.
 * 
 * For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.
 * 
 * For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.
 *
 */
public class LongestIncreasingContinuousSubsequence {

	public static void main(String[] args) {
		int[] A = { 5, 4, 2, 1, 3 };

		System.out.println(longestIncreasingContinuousSubsequence(A));
	}

	/**
	 * jiuzhang : version 1: enumeration
	 */
	public static int longestIncreasingContinuousSubsequence(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		// just one number, itself
		int leftMax = 1;
		int rightMax = 1;

		// from left to right
		int count = 1;
		for (int i = 1; i < A.length; i++) {
			if (A[i] > A[i - 1]) {
				count++;
			} else {
				count = 1;
			}
			leftMax = Math.max(leftMax, count);
		}
		// from right to left
		count = 1;
		for (int i = A.length - 2; i >= 0; i--) {
			if (A[i] > A[i + 1]) {
				count++;

			} else {
				count = 1;
			}
			rightMax = Math.max(rightMax, count);
		}
		return Math.max(leftMax, rightMax);
	}

}
