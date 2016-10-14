package DP;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 *
 */
public class ClimbingStairs {

	public static void main(String[] args) {
		System.out.println(climbStairsRecursion(3));
	}

	// http://blog.csdn.net/kenden23/article/details/17377869

	/**
	 * DP
	 */
	public static int climbStairs(int n) {
		if (n == 0 || n == 1 || n == 2) {
			return n;
		}

		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

	/**
	 * http://www.jiuzhang.com/solutions/climbing-stairs/
	 */
	public static int climbStairsIterative(int n) {
		if (n <= 1) {
			return 1;
		}
		int last = 1, lastlast = 1;
		int now = 0; // now from 2rd stair
		for (int i = 2; i <= n; i++) {
			now = last + lastlast;
			lastlast = last;
			last = now;
		}
		return now;
	}

	/**
	 * recursion: slow 每次有两种选择，两种选择之后又是各有两种选择，如此循环，正好是递归求解的问题
	 * 简单题目，相当于fibonacci数列问题，难点就是要会思维转换，转换成为递归求解问题
	 */

	public static int climbStairsRecursion(int n) {
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;
		return climbStairsRecursion(n - 1) + climbStairsRecursion(n - 2);
	}

}
