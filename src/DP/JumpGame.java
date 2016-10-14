package DP;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your *maximum jump length* at that
 * position, you don't need to jump at maximum exactly [0 to maximum]
 * 
 * Determine if you are able to reach the last index. (don't need to be exactly
 * at last position, you can also pass the last index)
 *
 */
public class JumpGame {

	public static void main(String[] args) {

		int[] A = { 0, 8, 2, 0, 9 };
		System.out.println(canJump1(A));
	}

	/**
	 * DP O(n^2) http://www.jiuzhang.com/solutions/jump-game/
	 */
	public static boolean canJump(int[] A) {

		if (A == null || A.length == 0) {
			return false;
		}

		/**
		 * can[i] = true: means from index 0, you can jump to/ pass to the
		 * current index i
		 */
		boolean[] can = new boolean[A.length];

		can[0] = true;

		for (int i = 1; i < A.length; i++) {
			for (int j = 0; j < i; j++) {
				// can[j]表示能不能从0即起点调到j,然后，从j一部调到i点
				if (can[j] && j + A[j] >= i) {
					can[i] = true;
					break;
				}
			}
		}

		return can[A.length - 1];

	}

	/**
	 * greedy algorithm O(n)
	 */
	public static boolean canJump1(int[] A) {
		// think it as merging n intervals
		if (A == null || A.length == 0) {
			return false;
		}
		int farthest = A[0];
		for (int i = 1; i < A.length; i++) {
			/**
			 * i<=fathest is important,如果i > farthest, 这样的话，A[i] + i 总是大于farthest
			 * 
			 *  if you miss it, your result is wrong
			 * e.g. [0,8,2,0,9]
			 */
			if (i <= farthest && A[i] + i >= farthest) {
				farthest = A[i] + i;
			}
		}
		return farthest >= A.length - 1;

	}
}
