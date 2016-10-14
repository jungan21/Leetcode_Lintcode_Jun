package Regex;

/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 * 
 * '?' Matches any single character. '*' Matches any sequence of characters
 * (including the empty sequence). The matching should cover the entire input
 * string (not partial).
 * 
 * isMatch("aa","a") → false
 * 
 * isMatch("aa","aa") → true
 * 
 * isMatch("aaa","aa") → false
 * 
 * isMatch("aa", "*") → true
 * 
 * isMatch("aa", "a*") → true
 * 
 * isMatch("ab", "?*") → true
 * 
 * isMatch("aab", "c*a*b") → false
 *
 */
public class WildcardMatching {

	public static void main(String[] args) {
		System.out.println(isMatch("aa", "a*"));
	}

	/**
	 * 非DP解法
	 * 
	 * To understand this solution, you can use s="aab" and p="*ab".
	 * 
	 * http://www.programcreek.com/2014/06/leetcode-wildcard-matching-java/
	 * 
	 * http://www.cnblogs.com/grandyang/p/4401196.html
	 * 
	 * ，其中sCur和pCur分别指向当前遍历到的字符，
	 * 
	 * 再定义pStar指向p中最后一个*的位置，sStar指向此时对应的s的位置，
	 * 
	 */
	public static boolean isMatch(String s, String p) {
		int i = 0;
		int j = 0;
		int iStar = -1;
		int jStar = -1;

		int sLen = s.length();
		int pLen = p.length();
		while (i < sLen) {
			if (j < pLen && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
				i++;
				j++;
			} else if (j < pLen && p.charAt(j) == '*') {
				iStar = i;
				jStar = j;
				j++;
				/**
				 * if you remove above "j++", you will get dead loop (always
				 * mactch this if condition), for example "aa", "a*"
				 */

			} else if (jStar != -1) {
				i = iStar + 1;
				iStar++;
				/**
				 * acaabbaccbbacaabbbb & a*?*b*?*a*aa*a* (corrrect answer:
				 * false, however, if you remove below one, you will get false)
				 */
				j = jStar + 1;
			} else {
				return false;
			}
		}
		// p String 结尾全是 ** 的情况，如果P的结果是？，怎不满足要求
		while (j < pLen && p.charAt(j) == '*') {
			j++;
		}
		return j == pLen;
	}

	public static boolean matchChar(char c, char p) {
		return (p == '?' || p == c);
	}

	public static boolean isMatch3(String s, String p) {
		if (s == null || p == null) {
			return false;
		}

		int indexS = 0;
		int indexP = 0;

		int lenS = s.length();
		int lenP = p.length();

		int preS = 0;
		int preP = 0;

		boolean back = false;

		while (indexS < lenS) {
			if (indexP < lenP && matchChar(s.charAt(indexS), p.charAt(indexP))) {
				indexS++;
				indexP++;
			} else if (indexP < lenP && p.charAt(indexP) == '*') {
				while (indexP < lenP && p.charAt(indexP) == '*') {
					indexP++;
				}

				// P的最后一个是 *，表示可以匹配任何字符串
				if (indexP == lenP) {
					return true;
				}

				// 记录下这个匹配位置。
				back = true;
				preS = indexS;
				preP = indexP;
			} else if (back) {
				indexS = ++preS;
				indexP = preP;
			} else {
				return false;
			}
		}

		// 跳过末尾的所有的*.
		while (indexP < lenP && p.charAt(indexP) == '*') {
			indexP++;
		}

		if (indexS == lenS && indexP == lenP) {
			return true;
		}

		return false;
	}

	/**
	 * 二维DP： http://www.cnblogs.com/yuzhangcmu/p/4116153.html
	 * 
	 * https://www.youtube.com/watch?v=3ZDZ-N0EPV0
	 * 
	 * http://www.cnblogs.com/zhuli19901106/p/3572736.html
	 * 
	 */

	public boolean isMatch1(String s, String p) {
		if (s == null && p == null) {
			return true;
		} else if (p == null) {
			return false;
		} else if (s == null) {
			return false;
		}

		// dp[i][j], s的前i个字符，能够被p的前j个字符match
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

		dp[0][0] = true;

		// first row, 初始化 重要！！！
		for (int i = 1; i < p.length() + 1; i++) {
			// 如果第一个p的第一个字符不是*， 后面肯定都不match,直接break, boolean类型默认值就是false
			if (p.charAt(0) != '*') {
				break;
			}
			// 下面这种算法，就能保证，如果p全都是*的时候，第一行能全部设置为true, 也同事能保证如果出现
			// **?**情况，从?到后面的也都能设置成 false
			if (p.charAt(i - 1) == '*' && dp[0][i - 1] == true) {
				// 也可写成， if (dp[0][i-1]== true) dp[0][i] = true;
				dp[0][i] = true;
			}
		}
		// first column 初始化 该循环可省，因为默认就是false
		for (int i = 1; i < s.length() + 1; i++) {
			dp[i][0] = false;
		}

		for (int i = 1; i < s.length() + 1; i++) {
			for (int j = 1; j < p.length() + 1; j++) {
				if (p.charAt(j - 1) == '?'
						|| s.charAt(i - 1) == p.charAt(j - 1)) {
					// 说明p的前j个字符，能够匹配s的前i个字符，那么我们就按照前面的情况来定结果
					dp[i][j] = dp[i - 1][j - 1];
				} else if (p.charAt(j - 1) == '*') {
					/**
					 * dp[i][j-1]：就是说p的前j-1个字符能够match了s的前i个字符， 这时p的第j个字符去匹配0个字符
					 * 
					 * dp[i - 1][j]: 表示拿出p的第j个字符‘*’ 去匹配一个s中的字符
					 */
					dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
				}
			}
		}
		return dp[s.length()][p.length()];

	}

	/**
	 * 1维DP ???
	 * 
	 * http://jeffzzf.github.io/2015/07/09/Wildcard-Matching/
	 */

	public boolean isMatch1D(String s, String p) {
		if (s == null || p == null)
			return false;
		if (p.replace("*", "").length() > s.length())
			return false;

		// dp[i] s的前i个字符能给被p匹配
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;
		for (int i = 1; i < p.length() + 1; ++i) {
			if (p.charAt(i - 1) == '*') {
				for (int j = 1; j < s.length() + 1; ++j)
					// fill the table
					dp[j] = dp[j - 1] || dp[j];
			} else {
				for (int j = s.length(); j >= 1; --j)
					dp[j] = (s.charAt(j - 1) == p.charAt(i - 1) || p
							.charAt(i - 1) == '?') && dp[j - 1];
				dp[0] = dp[0] && p.charAt(i - 1) == '*';
			}
		}
		return dp[s.length()];
	}

	public boolean isMatch2(String s, String p) {
		int m = s.length(), n = p.length();
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (p.charAt(i) == '*')
				count++;
		}
		if (count == 0 && m != n)
			return false;
		else if (n - count > m)
			return false;

		boolean[] match = new boolean[m + 1];
		match[0] = true;
		for (int i = 1; i < m + 1; i++) {
			match[i] = false;
		}
		for (int i = 0; i < n; i++) {
			if (p.charAt(i) == '*') {
				for (int j = 0; j < m; j++) {
					match[j + 1] = match[j] || match[j + 1];
				}
			} else {
				for (int j = m - 1; j >= 0; j--) {
					match[j + 1] = (p.charAt(i) == '?' || p.charAt(i) == s
							.charAt(j)) && match[j];
				}
				match[0] = false;
			}
		}
		return match[m];
	}

}
