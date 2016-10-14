package String;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space
 * characters ' ', return the length of last word in the string.
 * 
 * If the last word does not exist, return 0.
 * 
 * Notice
 * 
 * A word is defined as a character sequence consists of non-space characters
 * only.
 *
 */
public class LengthofLastWord {

	public static void main(String[] args) {
		System.out.println(lengthOfLastWord("a "));

	}

	// jiuzhang http://www.jiuzhang.com/solutions/length-of-last-word/
	public int lengthOfLastWordJiuzhang(String s) {
		int length = 0;
		char[] chars = s.toCharArray();
		for (int i = s.length() - 1; i >= 0; i--) {
			if (length == 0) {
				if (chars[i] == ' ') {
					continue;
				} else {
					length++;
				}
			} else {
				if (chars[i] == ' ') {
					break;
				} else {
					length++;
				}
			}
		}

		return length;
	}

	public static int lengthOfLastWord(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int result = 0;
		int len = s.length();

		boolean flag = false;
		for (int i = len - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				flag = true;
				result++;
			} else {
				if (flag)
					return result;
			}
		}

		return result;
	}

	public static int lengthOfLastWord2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		String[] words = s.split(" ");
		if (words.length == 0) {
			return 0;
		}
		return words[words.length - 1].length();
	}

}
