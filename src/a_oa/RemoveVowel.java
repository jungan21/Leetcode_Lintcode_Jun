package a_oa;

public class RemoveVowel {

	public static void main(String[] args) {
		System.out.println(removeVowel("teIUT"));
	}

	/**
	 * String indexOf() method: reutnr the index of the first occurrence of the
	 * character in the character sequence represented by this object, or -1 if
	 * the character does not occur.
	 */
	public static String removeVowel(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		String v = "aeiouAEIOU";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			// > -1, 表示在v里找到了，
			if (v.indexOf(str.charAt(i)) == -1) {
				// 没找到的是非Vowel字符
				sb.append(str.charAt(i));
			}
		}
		return sb.toString();
	}
	
}
