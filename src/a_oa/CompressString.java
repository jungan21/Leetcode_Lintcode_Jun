package a_oa;

class CompressString {

	public static void main(String[] args) {
		// a2blc5a3
		System.out.println(compress("aabcccccaaa"));
	}

	public static String compress(String s) {
		int newLen = countCompression(s);
		if (newLen >= s.length()) {
			return s;
		}
		StringBuilder sb = new StringBuilder();

		int result = 0, cnt = 1;
		char preChar = s.charAt(0);
		// int preIndex = 0;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == preChar) {
				cnt++;
			} else {
				sb.append(preChar).append(cnt);
				preChar = s.charAt(i);
				// preIndex = i;
				cnt = 1;
			}
		}
		sb.append(preChar).append(cnt);
		return sb.toString();
	}

	public static int countCompression(String s) {
		if (s.length() == 0)
			return 0;
		int result = 0, cnt = 1;
		char preChar = s.charAt(0);
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == preChar) {
				cnt++;
			} else {
				preChar = s.charAt(i);
				// 1表示字母本身，数字表示出现次数
				result = result + 1 + String.valueOf(cnt).length();
				cnt = 1;
			}
		}
		result = result + 1 + String.valueOf(cnt).length();
		return result;
	}
}
