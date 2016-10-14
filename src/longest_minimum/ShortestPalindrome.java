package longest_minimum;

/**
 * Given a string S, you are allowed to convert it to a palindrome by adding
 * characters in front of it. Find and return the shortest palindrome you can
 * find by performing this transformation.
 */
public class ShortestPalindrome {

	public static void main(String[] args) {
		// String s = "aacecaaa";
		String s = "acbda";
		System.out.println(shortestPalindrome(s));
		System.out.println(shortestPalindrome2(s));
	}

	// 是O(n)， 因为你发现整个字符串只遍历了一遍。
	public static String shortestPalindrome(String s) {
		int i = 0;
		int j = s.length() - 1;
		// 找到第一个使他不回文的位置
		while (j >= 0) {
			if (s.charAt(i) == s.charAt(j)) {
				i++;
			}
			j--;
		}

		// 该条件不能省略，也是recursion的终止条件之一
		if (i == s.length()) { // 本身是回文
			return s;
		}
		// 后缀不能够匹配的字符串
		String suffix = s.substring(i);

		// 前面补充prefix让他和suffix回文匹配
		// Note: 之所以要new StringBuilder,因为String 没有reverse()方法，或者我们也可以自己实现一个
		String prefix = new StringBuilder(suffix).reverse().toString();

		// 递归调用找 [0,i]要最少可以补充多少个字符让他回文
		// System.out.println("mid" + );
		String mid = shortestPalindrome(s.substring(0, i));
		String result = prefix + mid + suffix;

		// re suffix.compareTo(mid);

		return result;

	}

	/**
	 * jiuzhang 是O(n)， 因为你发现整个字符串只遍历了一遍。
	 */
	public static String shortestPalindrome2(String s) {
		int i = 0;

		for (int j = s.length() - 1; j >= 0; j--) {// 找到第一个使他不回文的位置
			if (s.charAt(j) == s.charAt(i)) {
				i += 1;
			}
		}
		if (i == s.length()) { // 本身是回文
			return s;
		}
		System.out.println("2_s and i: " + s + ", " + i);
		String suffix = s.substring(i);
		System.out.println(suffix);

		String prefix = new StringBuilder(suffix).reverse().toString();

		String mid = shortestPalindrome2(s.substring(0, i));
		String result = prefix + mid + suffix;

		return result;

	}
}
