package Regex;

/**
 * Implement regular expression matching with support for '.' and '*'.
 * 
 * '.' Matches any single character. '*' Matches zero or more of the preceding
 * element.
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be: bool isMatch(const char *s, const char *p)
 *
 *
 * Example isMatch("aa","a") → false isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false isMatch("aa", "a*") → true isMatch("aa", ".*") →
 * true isMatch("ab", ".*") → true isMatch("aab", "c*a*b") → true
 * 
 * http://www.cnblogs.com/yuzhangcmu/p/4105529.html
 */
public class RegularExpressionMatching {

	public static void main(String[] args) {
		String test = "abc";
		System.out.println(test.substring(3));
		System.out.println(isMatch("aa", "a*"));
	}

	/**
	 * method 2 http://www.cnblogs.com/grandyang/p/4461713.html
	 * 
	 * http://www.cnblogs.com/springfor/p/3893593.html
	 * 
	 * 这道求正则表达式匹配的题和那道 Wildcard Matching
	 * 通配符匹配的题很类似，不同点在于*的意义不同，在之前那道题中，*表示可以代替任意个数的字符
	 * ，而这道题中的*表示之前那个字符可以有1个或是多个，就是说
	 * ，字符串a*b，可以表示b或是aaab，即a的个数任意，这道题的难度要相对之前那一道大一些
	 * ，分的情况的要复杂一些，需要用递归Recursion来解，大概思路如下：
	 * 
	 * - 若p为空，若s也为空，返回true，反之返回false
	 * 
	 * - 若p的长度为1，若s长度也为1，且相同或是p为'.'则返回true，反之返回false
	 * 
	 * - 若p的第二个字符不为*，若此时s为空返回false，否则判断首字符是否匹配，且从各自的第二个字符开始调用递归函数匹配
	 * 
	 * - 若p的第二个字符为*，若s不为空且字符匹配，调用递归函数匹配s和去掉前两个字符的p，若匹配返回true，否则s去掉首字母
	 * 
	 * - 返回调用递归函数匹配s和去掉前两个字符的p的结果
	 * 
	 * 
	 */
	public static boolean isMatch(String s, String p) {
		if (p.length() == 0)
			return s.length() == 0;

		// length == 1 is the case that is easy to forget.
		// as p is subtracted 2 each time, so if original
		// p is odd, then finally it will face the length 1
		if (p.length() == 1)
			// 如果写成if () reutrn true, 则也要加上retur false,的condition
			return (s.length() == 1)
					&& (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');

		// p.length() >=2的情况
		// next char is not '*': must match current character
		if (p.charAt(1) != '*') {
			if (s.length() == 0)
				return false;
			else
				return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
						&& isMatch(s.substring(1), p.substring(1));
		} else {
			// "aab", "c*a*b"
			// next char is *
			// "a*" can stand for 0 element
			// 相当于： isMatch(s.substring(0), p.substring(2))
			if (isMatch(s, p.substring(2))) {
				return true;
			}

			// "a*" can stand for 1 or more preceding element,
			// so try every sub string
			// "aa", "a*"
			// substring(2)不会溢出，只会返回空值
			int i = 0;
			while (i < s.length()
					&& (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
				/**
				 * 上面的if条件相当于从position 0试了一遍， 接下来从1开始的位置都试一遍
				 * 
				 * isMatch(s.substring(0), p.substring(2))
				 * 
				 * test = "ab" test.substring(2) = null,
				 * 
				 * test.substring(3) return error, 这里最后i+1会运行到i = s.length()
				 */
				if (isMatch(s.substring(i + 1), p.substring(2))) {
					return true;
				}
				i++;
			}
			return false;
		}
	}

}
