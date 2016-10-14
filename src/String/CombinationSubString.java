package String;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Extract all the the combinations of a target substring that appear in a
 * source string. I.e: target: "abc", source:"abcdefgbcahijkacb12df", solution:
 * {abc, bca, acb}
 * 
 *
 */
public class CombinationSubString {

	public static void main(String[] args) {
		String str = "abcdefgbcahijkacb12df";
		String subStr = "abc";

		String test = "cba";
		char[] testArr = test.toCharArray();
		Arrays.sort(testArr);
		// System.out.println(test);
		// System.out.println(new String(testArr));

		// solution: {abc, bca, acb}
		System.out.println(combinationSubStringJun(str, subStr));
	}

	public static ArrayList<String> combinationSubStringJun(String str,
			String subStr) {
		ArrayList<String> result = new ArrayList<String>();
		if (str == null || str.length() == 0) {
			return result;
		}
		int hashedSubStr = hash(subStr);
		int len = subStr.length();
		for (int i = 0; i < str.length() - len + 1; i++) {
			String sub = str.substring(i, i + len);
			if (hash(sub) == hashedSubStr) {
				result.add(sub);
			}
		}
		return result;
	}

	// use hash function to replace below sort method
	// similiar to hashfunction in Anagrams.java
	private static int hash(String str) {
		int hashCode = 0;
		for (int i = 0; i < str.length(); i++) {
			hashCode = hashCode + (str.charAt(i) - '0');
		}
		return hashCode;
	}

	// sort works as hash function
	private static String sort(String str) {
		char[] charArr = str.toCharArray();
		Arrays.sort(charArr);
		return new String(charArr);
	}
}
