package String;

public class ReverseWordsInString {

	public static void main(String[] args) {
		String s = "I am a cat";
		System.out.println(reverseWords(s));
		System.out.println(reverseWordsRecursive1(s));
		System.out.println(reverseWordsRecursive(s));
		System.out.println(s.indexOf(" "));
	}

	public static String reverseWords(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		// split to words by space
		String[] words = s.split(" ");
		StringBuffer sb = new StringBuffer();
		for (int i = words.length - 1; i >= 0; i--) {
			if (!words[i].equals("")) {
				sb.append(words[i]);
			}
			if (i != 0) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}

	/**
	 * recursive method
	 */

	public static String reverseWordsRecursive(String s) {
		if (s == null || s.indexOf(" ") == -1) {
			return s;
		}
		int index = s.indexOf(" ");
		return reverseWordsRecursive(s.substring(index + 1)) + " "
				+ s.substring(0, index);
	}

	public static String reverseWordsRecursive1(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		// split to words by space
		String[] words = s.split(" ");
		helper(words, 0, words.length - 1);
		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			sb.append(word);
			sb.append(" ");
		}
		return sb.toString().substring(0, sb.length() - 1);
	}

	public static void helper(String[] words, int start, int end) {
		if (start >= end) {
			return;
		}

		String temp = words[start];
		words[start] = words[end];
		words[end] = temp;
		helper(words, start + 1, end - 1);
	}

}
