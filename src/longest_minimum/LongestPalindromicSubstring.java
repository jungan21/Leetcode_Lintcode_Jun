package longest_minimum;

/**
 * Given a string S, find the longest palindromic substring in S. You may assume
 * that the maximum length of S is 1000, and there exists one unique longest
 * palindromic substring.
 * 
 */

// Dynamic Programming

// Time O(n^2) Space O(n^2)
public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		// dabcba
		System.out.println(longestPalindrome1("daad"));
	}

	/**
	 * Dynamic Programming method1:
	 * http://blog.csdn.net/linhuanmars/article/details/20888595 Time O(n^2)
	 * Space O(n^2)
	 * 
	 * 基本思路是外层循环i从后往前扫，内层循环j从i当前字符扫到结尾处。过程中使用的历史信息是从i+1到n之间的任意子串是否是回文已经被记录下来，
	 * 所以不用重新判断
	 * ，只需要比较一下头尾字符即可。这种方法使用两层循环，时间复杂度是O(n^2)。而空间上因为需要记录任意子串是否为回文，需要O(n^2
	 * )的空间，代码如下：
	 * 
	 * follow up:
	 * 这个题目中假设最长回文子串只有一个，实际面试中一般不做这种假设，如果要返回所有最长回文串，只需要稍做变化就可以，维护一个集合，
	 * 如果等于当前最大的，即加入集合，否则，如果更长，则清空集合，加入当前这个。
	 */

	public static String longestPalindrome(String s) {
		if (s == null || s.length() == 0)
			return "";
		boolean[][] dp = new boolean[s.length()][s.length()];
		String res = "";
		int maxLen = 0;
		// have to iterate from the then end, otherwise, you will get wrong
		// answer, since the current value depends on the value that has NOT
		// been filled in the table
		// 从table的最后一行，最后一个元素，还是填表， 以为上面行的计算需要之前计算的结果
		for (int i = s.length() - 1; i >= 0; i--) {
			for (int j = i; j < s.length(); j++) {
				if (s.charAt(i) == s.charAt(j)
						&& (j - i <= 2 || dp[i + 1][j - 1])) {
					dp[i][j] = true;
					if (maxLen < j - i + 1) {
						maxLen = j - i + 1;
						res = s.substring(i, j + 1);
					}
				}
			}
		}
		return res;
	}

	/**
	 * jiuzhang https://www.youtube.com/watch?v=V-sEwsca1ak
	 */
	// http://articles.leetcode.com/longest-palindromic-substring-part-ii
	public static String longestPalindrome1(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}

		int length = s.length();
		int max = 0;
		String result = "";
		/**
		 * jiuzhang 算法：
		 * http://www.jiuzhang.com/solutions/longest-palindromic-substring/
		 * abcdzdcab ==> #a#b#c#d#z#d#c#a#b# index (0 ~ 2*length-1)
		 */
		// i 从第二个字符到倒数第二个字符, 因为第一个和最后一个字符都是"#", 没必要考虑
		for (int i = 1; i < 2 * length; i++) {
			int count = 1;// 因为本身一个字符也算一个
			while (i - count >= 0 && i + count <= 2 * length
					&& get(s, i - count) == get(s, i + count)) {
				count++;
			}
			count--; // there will be one extra count for the outbound #
			if (count > max) {
				result = s.substring((i - count) / 2, (i + count) / 2);
				max = count;
			}
		}

		return result;
	}

	private static char get(String s, int i) {
		// 下标为偶数的，全是"#"
		if (i % 2 == 0)
			return '#';
		else
			return s.charAt(i / 2);
	}

	/**
	 * http://www.cnblogs.com/TenosDoIt/p/3675788.html
	 */

	// another method:
	/**
	 * 对于每个中心往两边扫描的复杂度为O(n),所以时间复杂度为O((2*n-1)*n)=O(n^2),空间复杂度为O(1)，代码如下：
	 * 
	 * 第一种方法比较直接，实现起来比较容易理解。基本思路是对于每个子串的中心（可以是一个字符，或者是两个字符的间隙，比如串abc,中心可以是a,b,c,
	 * 或者是ab的间隙
	 * ，bc的间隙）往两边同时进行扫描，直到不是回文串为止。假设字符串的长度为n,那么中心的个数为2*n-1(字符作为中心有n个，间隙有n
	 * -1个）。对于每个中心往两边扫描的复杂度为O(n),所以时间复杂度为O((2*n-1)*n)=O(n^2),空间复杂度为O(1)，代码如下：
	 */
	public String longestPalindrome2(String s) {
		if (s == null || s.length() == 0)
			return "";
		int maxLen = 0;
		String res = "";
		for (int i = 0; i < 2 * s.length() - 1; i++) {
			int left = i / 2;
			int right = i / 2;
			if (i % 2 == 1)
				right++;
			String str = lengthOfPalindrome(s, left, right);
			if (maxLen < str.length()) {
				maxLen = str.length();
				res = str;
			}
		}
		return res;
	}

	private String lengthOfPalindrome(String s, int left, int right) {

		while (left >= 0 && right < s.length()
				&& s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return s.substring(left + 1, right);
	}
}
