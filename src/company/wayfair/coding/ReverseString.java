package company.wayfair.coding;

public class ReverseString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "hello";
		System.out.println(reverse(s));
		System.out.println(reverse(s.toCharArray(), 0, s.length() - 1));
		System.out.println(reverse1(s));
	}

	public static String reverse(String input) {
		char[] in = input.toCharArray();
		int begin = 0;
		int end = in.length - 1;
		char temp;
		// (beign < end) and (begin <=end) all works
		while (begin < end) {
			temp = in[begin];
			in[begin] = in[end];
			in[end] = temp;
			end--;
			begin++;
		}
		return new String(in);
	}

	/**
	 * amaon 只能用recursive方法
	 */
	public static String reverse(char[] charArr, int start, int end) {
		if (start >= end) {
			return null;
		}

		char temp = charArr[start];
		charArr[start] = charArr[end];
		charArr[end] = temp;
		reverse(charArr, start + 1, end - 1);

		return new String(charArr);

	}

	public static String reverse1(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}
		return reverse(s.substring(1)) + s.charAt(0);
	}
}
