package longest_minimum;

/**
 * Given two strings, find the longest common substring.Return the length of it.
 * 
 * Example: Given A = "ABCD", B = "CBCE", return 2.
 * 
 * Notice:
 * 
 * The characters in substring should occur continuously in original string.
 * This is different with subsequence.
 *
 */
public class LongestCommonSubstring {

	public static void main(String[] args) {
		String A = "ABCD";
		String B = "CBCE";
		System.out.println(longestCommonSubstring(A, B));
	}

	/**
	 * https://www.youtube.com/watch?v=BysNXJHzCEs
	 */
	public static int longestCommonSubstring(String A, String B) {
		// write your code here
		if (A == null || B == null) {
			return 0;
		}
		/**
		 * note: as a followup, if asked what's the longest substring,
		 * dp[i][j]=max的那个数，沿着对角线，往左上方走，知道dp[i][j] = 0
		 */
		int[] maxIndex = new int[2];

		int lenA = A.length();
		int lenB = B.length();

		int dp[][] = new int[lenA + 1][lenB + 1];

		dp[0][0] = 0;
		// since below are all 0, you can ignore bleow two for loop
		for (int i = 0; i < lenA + 1; i++) {
			dp[i][0] = 0;
		}
		for (int i = 0; i < lenB + 1; i++) {
			dp[0][i] = 0;
		}
		int max = 0;
		for (int i = 1; i < lenA + 1; i++) {
			for (int j = 1; j < lenB + 1; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = 0;
				}

				if (dp[i][j] > max) {
					max = dp[i][j];
					maxIndex[0] = i;
					maxIndex[1] = j;
				}
			}
		}
		// print out the longest substring in reversed order
		int t = maxIndex[0];
		int k = maxIndex[1];
		while (dp[t][k] > 0) {
			System.out.print(A.charAt(t - 1));
			t--;
			k--;
		}

		return max;
	}

}
