package a_oa;

/**
 * FindFirstRepeatingNumberinArray
 *
 */
public class FirstRepeatLetterInString {

	public static void main(String[] args) {
		// System.out.println(firstRepeatCharacter("abcba"));
		System.out.println(firstRepeatCharacter2("abc"));
	}

	public static char firstRepeatCharacter(String s) {
		int[] hash = new int[256];
		int index = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (hash[s.charAt(i)] == 0) {
				hash[s.charAt(i)]++;
			} else {
				index = i;
			}
		}
		return s.charAt(index);
	}

	public static char firstRepeatCharacter2(String s) {
		int[] hash = new int[256];
		for (int i = 0; i <= s.length()-1; i++) {
			hash[s.charAt(i)]++;
		}
		for (int i = 0; i <= s.length()-1; i++) {
			if (hash[s.charAt(i)] > 1) {
				return s.charAt(i);
			}
		}
		return ' ';
	}

}
