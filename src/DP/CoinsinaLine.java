package a.DP;

public class CoinsinaLine {

	public static void main(String[] args) {

	}

	/**
	 * dp[i], 还剩i个coins, 当前选手能否获胜
	 * 
	 * dp[4]=false, dp[3]=false ==> dp[5]=true
	 * 
	 * dp[4]=false, dp[3]=true ==> dp[5]=true
	 * 
	 * dp[4]=true, dp[3]=false ==> dp[5]=true
	 * 
	 * dp[4]=true, dp[3]=true ==> dp[5]=false
	 * 
	 */
	public boolean firstWillWin(int n) {
		// write your code here
		boolean[] dp = new boolean[n + 1];
		if (n == 0) {
			return false;
		}

		if (n <= 2) {
			return true;
		}

		dp[0] = false;
		dp[1] = true;
		dp[2] = true;

		for (int i = 3; i < n + 1; i++) {
			dp[i] = !dp[i - 1] || !dp[i - 2];
		}
		return dp[n];

	}

	// method2
	public boolean firstWillWin1(int n) {
		// write your code here
		return n % 3 != 0;
	}

}
