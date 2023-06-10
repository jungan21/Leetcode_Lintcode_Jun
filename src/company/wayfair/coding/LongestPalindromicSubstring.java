package company.wayfair.coding;

/**
 * Given a string S, find the longest palindromic substring in S. You may assume
 * that the maximum length of S is 1000, and there exists one unique longest
 * palindromic substring.
 * 
 */
// Time O(n^2) Space O(n^2)
public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		// dabcba
		System.out.println(longestPalindrome1("daad"));
	}

	// HW PDd candidate
	public static String longestPalindromeHW(String s) {
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int[] oddResult = expand(s, i, i); //输入：babad， 结果为bab, i=1，以a为中心expand（及时l,r都为1）
			int[] evenResult = expand(s, i, i+1);//输入：cbbd, 结果为bb,以第一个b位置为l, 第二个b位置为r
			if(oddResult[1] - oddResult[0] > end - start){
				start = oddResult[0];
				end = oddResult[1];
			}
			if(evenResult[1] - evenResult[0] > end - start){
				start = evenResult[0];
				end = evenResult[1];
			}
		}

		System.out.print(start + "," + end);
		return s.substring(start, end + 1 );
	}

	private static int[] expand(String s, int l, int r) {
		while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
			l--;
			r++;
		}
		int[] result = new int[2];
		result[0] = l + 1;
		result[1] = r  -1;
		return result;
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


	//===== DFS cbbd
	private static String result = "";
	public static String longestPalindromeDFS(String s) {
		StringBuilder str = new StringBuilder();
		//int[] visited = new int[s.length()];
		helper(s.toCharArray(), str,  0);
		return result;
	}

	public static void helper(char[] s, StringBuilder str, int pos){
		System.out.println(str.toString());
		if(isPalindrom(str.toString())){
			if (str.toString().length() > result.length()){
				result = str.toString();
			}
		}

		for (int i = pos; i < s.length; i++ ){
			str = str.append(s[i]);
			helper (s, str, i + 1);
			str = str.deleteCharAt(str.length() -1);
		}

	}

	public static boolean isPalindrom(String s){
		int i = 0;
		int j = s.length() - 1;

		while (i < j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			} else {
				i++;
				j--;
			}
		}
		return true;
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
