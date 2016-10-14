package a.DP;

public class HouseRobber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static long houseRobber(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		// 必须是long,否则通不过测试
		long[] dp = new long[A.length + 1];
		dp[0] = 0;
		dp[1] = A[0];
		for (int i = 2; i <= A.length; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + A[i - 1]);
		}
		return dp[A.length];
	}

	// 滚动数组优化 jiuzhang
	/**
	 * 当前i如果是偶数，就有之前的奇数(i-1)和偶数(i-2)决定
	 * 
	 * 当前i如果是奇数，就有之前的偶数(i-1)和奇数(i-2)决定
	 * 
	 * 只与前两个状态相关
	 */
	public static long houseRobber1(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		if (A.length == 1) {
			return A[0];
		}
		int n = A.length;
		long[] dp = new long[2];
		dp[0] = 0;
		dp[1] = A[0];
		for (int i = 2; i < A.length; i++) {
			dp[i % 2] = Math.max(dp[(i - 1) % 2], dp[(i - 2) % 2] + A[i - 1]);
		}
		return dp[n % 2];
	}

}
