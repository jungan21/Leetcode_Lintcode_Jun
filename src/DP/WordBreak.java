package DP;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be break
 * into a space-separated sequence of one or more dictionary words.
 * 
 * Given s = "lintcode", dict = ["lint", "code"].Return true because "lintcode"
 * can be break as "lint code".
 * 
 * 这题的意思是给出一本词典以及一个字符串，能否切分这个字符串使得每个字串都在字典里面存在。判断给定的字符串能否用词典进行切分，切分的每一部分都是词典中的单词
 */
public class WordBreak {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("123");

		System.out.println(sb.delete(0, sb.length()));

		String s = "lintcode";
		Set<String> set = new HashSet<String>();
		set.add("lint");
		set.add("code");
		System.out.println(wordBreak(s, set));
	}

	/**
	 * 时间： O(N*L*L)
	 * 
	 * Define an array dp[] such that dp[i]==true => 0-(i-1) can be segmented
	 * using dictionary Initial state dp[0] == true
	 * 
	 * O(string length * dict size).
	 * 
	 * 1 用dp[i]表示到字符串s的第i个字符时，是否可以用dict中的词来表示。
	 * 
	 * 2.然后，假设我们有location[0…i-1]的结果，那么location[i]的值应该是：location[i] = location[j]
	 * && s.substring(j, i + 1) in dict，其中j属于[0…i - 1]。这也就是我们所说的状态方程。
	 */
	public static boolean wordBreak(String s, Set<String> dict) {
		if (s == null || s.length() == 0) {
			return true;
		}
		int lenS = s.length();
		// dict里最长的字符串的长度,为下面的第二层 for loop缩减loop时间
		int maxLength = getMaxLengthString(dict);

		boolean[] dp = new boolean[lenS + 1];
		// dp[0],相当于 "" 空串， 一定在任何字典了
		dp[0] = true;

		for (int i = 1; i <= lenS; i++) {
			dp[i] = false;
			/**
			 * 由于上面，我们得到字典里最长单词的长度，我们从i的位置往后推，最远到 i - lastWordLength=0,
			 * 这样就没有必要像下面的方法那样，每次从最开始0的位置一直循环
			 */
			for (int lastWordLength = 1; lastWordLength <= maxLength && lastWordLength <= i; lastWordLength++) {
				if (!dp[i - lastWordLength]) {
					continue;
				}
				String word = s.substring(i - lastWordLength, i);
				if (dict.contains(word)) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[lenS];
	}

	private static int getMaxLengthString(Set<String> dict) {
		int maxLength = 0;
		for (String word : dict) {
			maxLength = Math.max(maxLength, word.length());
		}
		return maxLength;
	}

	/**
	 * DP - 不能提交(lintcode, 不能提交，不过leetcode可以)，超时，但是很好理解
	 * 
	 * N: 字符串长度 M： 词典里单词个数 L： 单词的平均长度
	 * 
	 * 前两个for循环： N^2
	 * 
	 * if 条件判断力的dict.contains 语句： 因为substring的操作就是O(n)的操作，再判断
	 * dict.contains是O(字符串长度)， 总的来说就是if条件判断里的要耗费O(n)
	 * 
	 * 总的时间复杂度是 N^3 = n*n*n
	 */

	public boolean wordBreak1(String s, Set<String> dict) {
		if (s == null) {
			return false;
		}
		int maxLength = getMaxLengthString(dict);
		int len = s.length();
		if (len == 0) {
			return true;
		}

		boolean[] dp = new boolean[len + 1];

		// initiate the DP. 注意，这里设置为true是不得已，因为当我们划分字串为左边为0，右边为n的时候，
		// 而右边的n是一个字典string,那么左边必然要设置为true，才能使结果为true。所以空字符串我们需要
		// 认为true
		dp[0] = true;

		// D[i] 表示到第i个字符的时候，字符串能否被word break.
		for (int i = 1; i <= len; i++) {
			// 把子串划分为2部分，分别讨论, j 表示左边的字符串的长度
			// 成立的条件是：左边可以break, 而右边是一个字典单词
			dp[i] = false;
			// NOTE: 应为这里每次从0开始到i-1,花费时间太长，提交会报错
			for (int j = i; j >= 0; j--) {
				if (i - j > maxLength) {
					break;
				}
				if (dp[j] && dict.contains(s.substring(j, i))) {
					// 只要找到任意一个符合条件，我们就可以BREAK; 表示我们检查的
					// 这一个子串符合题意
					dp[i] = true;
					break;
				}
			}
		}

		return dp[len];
	}

	/**
	 * Recursive - Jun OKTA interview 2018/05
	 */

	public boolean wordBreakRecursive(String word, Set<String> dict) {
		return DFS(word, dict, 0);
	}

	public boolean DFS(String word, Set<String> dict, int start) {
		if (start == word.length())
			return true;

		for (String curWordInDict : dict) {
			int len = curWordInDict.length();
			
			// end index should be <= string length
			if ((start + len) > word.length())
				continue;

			if (word.substring(start, start + len).equals(curWordInDict))
				if (DFS(word, dict, start + len))
					return true;
		}
		return false;
	}
}
