package a.DP;

/**
 * Given three strings: s1, s2, s3, determine whether s3 is formed by the
 * interleaving of s1 and s2.
 *
 * For s1 = "aabcc", s2 = "dbbca"
 * 
 * When s3 = "aadbbcbcac", return true. When s3 = "aadbbbaccc", return false.
 * 
 *
 */
public class InterLeavingString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * DP: http://blog.csdn.net/u011095253/article/details/9248073
	 * 
	 * @return
	 */
	public boolean isInterleave(String s1, String s2, String s3) {

		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}
		/**
		 * 
		 * http://blog.csdn.net/u011095253/article/details/9248073
		 * http://www.cnblogs.com/springfor/p/3896159.html
		 * 
		 * dp[i][j]表示s1取前i位，s2取前j位，是否能组成s3的前i+j位
		 * 
		 * dp[1][1], 表示s1取第1位a, s2取第1位d，是否能组成s3的前两位aa
		 * 
		 * 从dp[0][1] 往下的箭头表示，s1目前取了0位，s2目前取了1位，我们添加s1的第1位，看看它是不是等于s3的第2位，( i + j
		 * 位）
		 * 
		 * 从dp[1][0] 往右的箭头表示，s1目前取了1位，s2目前取了0位，我们添加s2的第1位，看看它是不是等于s3的第2位，( i + j
		 * 
		 * 
		 * False很直观，如果不等就是False了嘛。
		 * 
		 * 那True呢？首先第一个条件，新添加的字符，要等于s3里面对应的位( i + j 位)，第二个条件，之前那个格子也要等于True
		 * 
		 * 举个简单的例子s1 = ab, s2 = c, s3 = bbc
		 * ，假设s1已经取了2位，c还没取，此时是False（ab!=bb），我们取s2的新的一位c
		 * ，即便和s3中的c相等，但是之前是False，所以这一位也是False
		 * 
		 * 同理，如果s1 = ab, s2 = c, s3=abc
		 * ，同样的假设，s1取了2位，c还没取，此时是True（ab==ab），我们取s2的新的一位c
		 * ,和s3中的c相等，且之前这一位就是True，此时我们可以放心置True （abc==abc） 位)
		 */
		boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
		// s1的第0个字符和s2的第0个字符， 能否组成s3(0+0)个字符
		dp[0][0] = true;

		for (int i = 1; i <= s1.length(); i++) {
			if (s3.charAt(i - 1) == s1.charAt(i - 1) && dp[i - 1][0])
				dp[i][0] = true;
		}

		for (int j = 1; j <= s2.length(); j++) {
			if (s3.charAt(j - 1) == s2.charAt(j - 1) && dp[0][j - 1])
				dp[0][j] = true;
		}

		for (int i = 1; i < s1.length() + 1; i++) {
			for (int j = 1; j < s2.length() + 1; j++) {
				// 新添加的s1里的第i字符，是否等于 s3的第i+j个字符
				if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j]) {
					dp[i][j] = true;
				}
				if (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1]) {
					dp[i][j] = true;
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}

	/**
	 * recursive - http://blog.csdn.net/u011095253/article/details/9248073
	 */
	public boolean isInterleave2(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length())
			return false;
		return rec(s1, 0, s2, 0, s3, 0);
	}

	public boolean rec(String s1, int p1, String s2, int p2, String s3, int p3) {
		if (p3 == s3.length())
			return true;
		if (p1 == s1.length())
			return s2.substring(p2).equals(s3.substring(p3));
		if (p2 == s2.length())
			return s1.substring(p1).equals(s3.substring(p3));
		if (s1.charAt(p1) == s3.charAt(p3) && s2.charAt(p2) == s3.charAt(p3))
			return rec(s1, p1 + 1, s2, p2, s3, p3 + 1)
					|| rec(s1, p1, s2, p2 + 1, s3, p3 + 1);
		else if (s1.charAt(p1) == s3.charAt(p3))
			return rec(s1, p1 + 1, s2, p2, s3, p3 + 1);
		else if (s2.charAt(p2) == s3.charAt(p3))
			return rec(s1, p1, s2, p2 + 1, s3, p3 + 1);
		else
			return false;
	}

}
