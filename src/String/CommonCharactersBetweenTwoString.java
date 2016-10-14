package String;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two different strings, find the common characters between the two. For
 * example if string A is "hello" and string B is "elbow" the common characters
 * would be ['e', 'l', 'o']. Give a method that returns unique or duplicate
 * entries.
 *
 */
public class CommonCharactersBetweenTwoString {

	public static void main(String[] args) {
		String A = "hello";
		String B = "elllbow";
		// String B = "hello";
		System.out.println(getCommonCharacter(A, B));
	}

	public static List<Character> getCommonCharacter(String A, String B) {
		List<Character> result = new ArrayList<>();
		if (A.equals(B)) {
			int i = 0;
			while (i < A.length()) {
				result.add(A.charAt(i++));
			}
			return result;
		}

		int[] arr = new int[256];

		for (int i = 0; i < A.length(); i++) {
			arr[A.charAt(i)]++;
		}

		for (int i = 0; i < B.length(); i++) {
			if (arr[B.charAt(i)] != 0) {
				result.add(B.charAt(i));
				arr[B.charAt(i)]--;
			}
		}

		return result;
	}

	/**
	 * Another method is to sort the String (char Array), then two points to
	 * move
	 */

}
