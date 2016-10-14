package a.DP;

/**
 * Given a string S and a string T, count the number of distinct subsequences of
 * T in S.
 * 
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ACE" is a
 * subsequence of "ABCDE" while "AEC" is not).
 *
 * Analysis: 简单翻译一下，给定两个字符串S和T，求S有多少个不同的子串与T相同。S的子串定义为在S中任意去掉0个或者多个字符形成的串。 The
 * problem itself is very difficult to understand. It can be stated like this:
 * Give a sequence S and T, how many distinct sub sequences from S equals to T?
 * 
 * When you see string problem that is about subsequence or matching, dynamic
 * programming method should come to mind naturally. The key is to find the
 * initial and changing condition.
 *
 *
 */
public class DistinctSubsequences {

	public static void main(String[] args) {

	}

	/**
	 * DP O(n^2)
	 * 
	 * Let dp(i, j) stand for the number of subsequences of S(0, i) equals to
	 * T(0, j). If S.charAt(i) == T.charAt(j), dp(i, j) = dp(i-1, j-1) +
	 * dp(i-1,j); Otherwise, dp(i, j) = dp(i-1,j).
	 * 
	 * http://www.cnblogs.com/yuzhangcmu/p/4196373.html
	 * 
	 */
	public int numDistinct(String S, String T) {
		if (S == null || T == null) {
			return 0;
		}
		// 先创建二维数组存放答案，如解法数量。注意二维数组的长度要比原来字符串长度+1，因为要考虑第一个位置是空字符串。
		// 
		int[][] dp = new int[S.length() + 1][T.length() + 1];

		/**
		 * 然后考虑dp[i][j]和dp[i-1][j],dp[i][j-1],dp[i-1][j-1]的关系，如何通过判断S.charAt(i)
		 * 和T.charAt(j)的是否相等来看看如果移除了最后两个字符，能不能把问题转化到子问题。
		 * 
		 * 最后问题的答案就是dp[S.length()][T.length()]
		 *
		 */

		int lenS = S.length();
		int lenT = T.length();
		// S 是行， T 是列

		// S和T都是空串
		dp[0][0] = 1;

		// dp[1 ... S.length() - 1][0] = 1; // T是空串，S只有一种子序列匹配。
		for (int i = 1; i < lenS + 1; i++) {
			dp[i][0] = 1;
		}
		/**
		 * dp[0][1 ... T.length() - 1] = 0; // S是空串，T非空， 没有子序列匹配
		 */
		// 这一个for loop可以省略，数组默认值就是0
		for (int i = 1; i < lenT + 1; i++) {
			dp[0][i] = 0;
		}
		// 注意：循环的时候，一定要注意i的取值要到len，这个出好几次错了。
		for (int i = 1; i < lenS + 1; i++) {
			for (int j = 1; j < lenT + 1; j++) {
				/**
				 * 无论S的第i个字符（index 为i-1），与T的第j个字符是否匹配,
				 * 假设S的i-1个字符已经匹配了T的j个字符,得到的匹配个数为
				 * dp[i-1][j],现在无论S的第i个字符，是否和T的第j个字符匹配，匹配个数至少是dp[i-1][j], 除此之外，
				 * 如果S的第i个字符和T的第j个字符匹配, 我们可以让S[i]和T[j]匹配，然后让S[i - 1]和T[j -
				 * 1]去匹配。所以递推关系为：
				 */
				if (S.charAt(i - 1) == T.charAt(j - 1)) {
					/**
					 * i-1在table中，表示上一行的值，所以dp[i - 1][j]已经算出来了
					 * 
					 * S的第i个字符等于T的第j个字符:==> T is a subsequence of the rest of
					 * string S AND the rest of string T is a subsequence the
					 * rest of string S, or
					 */
					dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
				} else {
					/**
					 * S.charAt(i - 1) != T.charAt(j - 1),即，S的第i个字符不等于T的第j个字符
					 * then T cannot be a subsequence of S; it could only be the
					 * subsequence of the rest of S.
					 */
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[lenS][lenT];
	}

	/**
	 * method 2: recursive
	 *
	 * http://blog.csdn.net/abcbc/article/details/8978146
	 * 
	 * 首先找到在S中与T的第一个字符相同的字符，从这个字符开始，递归地求S和T剩下的串。T为空串时，返回1。因为空串本身是另外一个串的一个子序列。
	 * 这个算法实现简单，但是果然不出意料，大集合超时。
	 */
	public int numDistinctRecursive(String S, String T) {
		// Start typing your Java solution below
		// DO NOT write main() function
		if (S.length() == 0) {
			return T.length() == 0 ? 1 : 0;
		}
		if (T.length() == 0) {
			return 1;
		}
		int cnt = 0;
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == T.charAt(0)) {
				cnt += numDistinctRecursive(S.substring(i + 1), T.substring(1));
			}
		}
		return cnt;
	}
}
