package String;

public class FirstNonRepeatedCharacterInAString {

	public static void main(String[] args) {
		String input = "sttrrees";
		System.out.println(findFirstNonRepeatedChar(input));
	}

	public static char findFirstNonRepeatedChar(String str) {

		int[] hash = new int[256];

		for (int i = 0; i < str.length(); i++) {
			hash[str.charAt(i)]++;
		}

		for (int i = 0; i < str.length(); i++) {
			if (hash[str.charAt(i)] == 1) {
				return str.charAt(i);
			}
		}
		return ' ';
	}

}
