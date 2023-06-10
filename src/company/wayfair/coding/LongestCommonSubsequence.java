package company.wayfair.coding;

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// O(lenA*lenB).
	public int longestCommonSubsequence(String A, String B) {
		if (A == null || B == null) {
			return 0;
		}
		if (A.length() == 0 || B.length() == 0) {
			return 0;
		}

		int lenA = A.length();
		int lenB = B.length();
		// length has to be lenA + 1 & lenB + 1
		int dp[][] = new int[lenA + 1][lenB + 1];

		dp[0][0] = 0;
		// since below are all 0, you can ignore bleow two for loop
		for (int i = 0; i < lenA + 1; i++) {
			dp[i][0] = 0;
		}
		for (int i = 0; i < lenB + 1; i++) {
			dp[0][i] = 0;
		}

		for (int i = 1; i < lenA + 1; i++) {
			for (int j = 1; j < lenB + 1; j++) {

				/**
				 * charAt(i-1) != charAt(j-1) dp[i][j] =dp[i][j-1],
				 * dp[i-1][j]中最大的一个 (只有最后一个不同, 可能i字符和j-1相同 或者j和i-1相同)
				 */
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[lenA][lenB];
	}
}
