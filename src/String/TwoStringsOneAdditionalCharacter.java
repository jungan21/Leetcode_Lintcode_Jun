package String;

/**
 * Given two strings with the same characters except for one additional
 * character in one string, return that additional character.
 *
 */
public class TwoStringsOneAdditionalCharacter {

	public static void main(String[] args) {
		String A = "abcd";
		String B = "abcdd";
		System.out.println(getOneAdditionalChar(A, B));
	}

	private static char getOneAdditionalChar(String A, String B) {
		int[] arr = new int[256];
		char ch = ' ';
		for (int i = 0; i < A.length(); i++) {
			arr[A.charAt(i)]++;
		}

		for (int i = 0; i < B.length(); i++) {
			arr[B.charAt(i)]--;
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				return (char) i;
			}
		}

		return ch;
	}

	/**
	 * follow up:
	 * 
	 * Checking if a string matches all characters but one of another string
	 */

	public static boolean matchesAlmost(String str1, String str2) {
		if (str1.length() != str2.length())
			return false;
		int same = 0;
		for (int i = 0; i < str1.length(); ++i) {
			if (str1.charAt(i) == str2.charAt(i))
				same++;
		}
		// 如果两个String完全相同： same = str1.length();
		return same == str1.length() - 1;
	}

}
