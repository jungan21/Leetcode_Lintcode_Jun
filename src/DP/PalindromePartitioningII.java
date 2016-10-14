package DP;

/**
 * Given a string s, cut s into some substrings such that every substring is a
 * palindrome.
 * 
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 *
 * Given s = "aab",
 * 
 * Return 1 since the palindrome partitioning ["aa", "b"] could be produced
 * using 1 cut.
 */
public class PalindromePartitioningII {

	public static void main(String[] args) {
		String s = "bb";

		minCut(s);

	}
	// O(n^3)
	// dp[i] 表示前i个字母，最少被切割几次可以切割为都是回文串。
	// 最后return dp[s.length()]
	public static int minCut(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		// initialize, 假设都切成单个字符
		int[] dp = new int[s.length() + 1];
		for (int i = 0; i <= s.length(); i++) {
			// e.g. dp[4] = 3刀，切成单个字符
			dp[i] = i - 1;
			// dp[0] =-1
			// dp[0] 表示空串
		}

		// main
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				// index 从j 到i, 第i 个字符在原始输入字符中的下标为i-1
				if (isPalindrome(s.substring(j, i))) {
					// dp[0] = -1
					dp[i] = Math.min(dp[i], dp[j] + 1);
				}
			}
		}

		return dp[s.length()];
	}

	/**
	 * jun's
	 */
	public static int minCut1(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		// initialize, 假设都切成单个字符
		int[] dp = new int[s.length() + 1];
		dp[0] = -1;
		dp[1] = 0;

		// main
		for (int i = 2; i <= s.length(); i++) {
			int min = i - 1;
			for (int j = 0; j < i; j++) {
				if (isPalindrome(s.substring(j, i))) {
					// dp[0] = -1
					min = Math.min(min, dp[j] + 1);
				}
			}
			dp[i] = min;
		}

		return dp[s.length()];
	}

	public static boolean isPalindrome(String s) {
		if (s == null) {
			return false;
		}
		if (s.isEmpty()) {
			return true;
		}

		char[] ch = s.toCharArray();
		int len = ch.length;

		for (int i = 0, j = len - 1; i <= j; i++, j--) {
			if (ch[i] != ch[j]) {
				return false;
			}
		}
		return true;
	}

}
