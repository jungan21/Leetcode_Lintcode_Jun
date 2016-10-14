package twopoints;

/**
 * Given a string which contains only letters. Sort it by lower case first and
 * upper case second.
 * 
 * 
 * Notice
 * 
 * It's NOT necessary to keep the original order of lower-case letters and upper
 * case letters.
 *
 */
public class SortLettersbyCase {

	public static void main(String[] args) {

	}

	public void sortLetters(char[] chars) {

		if (chars == null || chars.length == 0) {
			return;
		}

		int left = 0;
		int right = chars.length - 1;

		while (left < right) {
			// Character.isLowerCase
			while (left < right && Character.isLowerCase(chars[left])) {
				left++;
			}
			// Character.isUpperCase
			while (left < right && Character.isUpperCase(chars[right])) {
				right--;
			}
			if (left < right) {
				char temp = chars[left];
				chars[left] = chars[right];
				chars[right] = temp;
				left++;
				right--;
			}
		}
	}

}
