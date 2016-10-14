package String;

/**
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric(字符或数字的意思) characters and ignoring cases.
 * 
 * For example, "A man, a plan, a canal: Panama" is a palindrome. "race a car"
 * is not a palindrome.
 * 
 */
public class ValidPalindrome {

	public static void main(String[] args) {
		ValidPalindrome checker = new ValidPalindrome();
		String s = "1  8A,a";
		System.out.println(isPalidromeRecurisve("abca"));

	}

	// jiu zhang template:
	public static boolean isPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		int i = 0;
		int j = s.length() - 1;
		// must be (i<j), (i<=j) doesn't work
		while (i < j) {
			while (i < j && !isValid(s.charAt(i))) {
				i++;
			}
			if (i == s.length()) { // for emtpy string “.,,,”
				return true;
			}
			while (i < j && !isValid(s.charAt(j))) {
				j--;
			}
			if (i < j
					&& Character.toLowerCase(s.charAt(i)) != Character
							.toLowerCase(s.charAt(j))) {
				return false;
			} else {
				i++;
				j--;
			}

		}
		return true;
	}

	/**
	 * method 2, 与上面的区别是，预先把string大小写处理一下
	 */
	public boolean isPalindrometest(String s) {
		if (s == null || s.length() == 0 || s.length() == 1) {
			return true;
		}
		// 必须要重新赋值，否则仅仅 s.trim(), s.toLowerCase() 不会改变原来string的值
		s = s.trim();
		s = s.toLowerCase();
		int i = 0;
		int j = s.length() - 1;

		while (i < j) {
			while (i < j && !isValid(s.charAt(i))) {
				i++;
			}
			while (i < j && !isValid(s.charAt(j))) {
				j--;
			}
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	// is Valid alphanumeric (字符或数字都是valid的)
	private static boolean isValid(char ch) {
		return (Character.isLetter(ch) || Character.isDigit(ch));
	}

	private boolean isValid2(char ch) {
		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
			return true;
		}
		// 也可以直接 ch >='0' && ch <=9
		if ((ch - '0') >= 0 && (ch - '0') <= 9) {
			return true;
		}
		return false;
	}

	/**
	 * Another method with regular expression
	 */

	public static boolean isPalidrome(String s) {
		if (s == null)
			return false;
		s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		if (s.isEmpty()) {
			return true;
		}

		char[] ch = s.toCharArray();
		int len = ch.length;
		// i<=j
		for (int i = 0, j = len - 1; i <= j; i++, j--) {
			if (ch[i] == ch[j]) {

			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * recursive Palidrome (仅仅包含字符)
	 */

	public static boolean isPalidromeRecurisve(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		return helper(s, 0, s.length() - 1);
	}

	public static boolean helper(String s, int start, int end) {
		if (start >= end) {
			return true;
		}
		if (s.charAt(start) != s.charAt(end)) {
			return false;
		}
		return helper(s, start + 1, end - 1);
	}
}
