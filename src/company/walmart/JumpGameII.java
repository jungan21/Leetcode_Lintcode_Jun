package company.walmart;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 */
public class JumpGameII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = { 2, 3, 1, 1, 4 };
		System.out.println(canJump1(A));
	}

	/**
	 * DP O(n^2), 坐标类型DP
	 */
	public static int jump(int[] A) {

		int[] dp = new int[A.length];
		// dp[i], minimun steps need to jump to 0 from i
		dp[0] = 0;

		for (int i = 1; i < A.length; i++) {
			// if it can't reach, we will use the largest
			dp[i] = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				if (dp[j] != Integer.MAX_VALUE && j + A[j] >= i) {
					dp[i] = dp[j] + 1;
					// can NOT ignore this break, if you want to remove this
					// "break", you need to use: dp[i] = Math.min(dp[j] + 1,
					// dp[i]);
					break;
				}
			}
		}
		return dp[A.length - 1];
	}

	/**
	 * Greedy - O(n)
	 * 
	 * http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
	 */

	public static int canJump1(int[] A) {
		// think it as merging n intervals
		if (A == null || A.length == 0) {
			return -1;
		}
		int start = 0, end = 0, jumps = 0;
		// 即end 到倒数第二个数停止
		// end 是index, 最后一个数字的index 是 A.length-1, 当 end >= A.length-1的时候必须停止
		while (end < A.length - 1) {
			jumps++;
			int farthest = end;
			for (int i = start; i <= end; i++) {
				if (A[i] + i > farthest) {
					farthest = A[i] + i;
				}
			}
			start = end + 1;
			end = farthest;
		}
		return jumps;
	}

	/**
	 * We use "last" to keep track of the maximum distance that has been reached
	 * by using the minimum steps "ret", whereas "curr" is the maximum distance
	 * that can be reached by using "ret+1" steps. Thus, curr = max(i+A[i])
	 * where 0 <= i <= last.
	 */
	public static int canJump2(int[] A) {
		int result = 0;
		int last = 0;
		int curr = 0;
		for (int i = 0; i < A.length; ++i) {
			if (i > last) {
				last = curr;
				++result;
			}
			curr = Math.max(curr, i + A[i]);
		}
		return result;
	}
}
