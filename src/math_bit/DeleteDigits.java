package math_bit;

public class DeleteDigits {

	public static void main(String[] args) {
		String A = "178542";
		int k = 4;
		// 178542 -> 17542 -> 1542->142->12
		System.out.println(deleteDigits(A, k));
	}

	/**
	 * have to go from left to right, delete the first number that is larger
	 * than its next number.
	 * 
	 * This equals replacing a larger number with a smaller number, at the most
	 * significant position so as to producing the smallest number.
	 */
	public static String deleteDigits(String A, int k) {
		if (A.length() == k) {
			return "";
		}
		StringBuilder sb = new StringBuilder(A);
		int i, j;
		for (i = 0; i < k; i++) {
			j = 0;
			// 一直找到 sb.charAt(j) >sb.charAt(j + 1),即高位的数字比低位的数字大
			while (j < sb.length() - 1 && sb.charAt(j) <= sb.charAt(j + 1)) {
				j++;
			}
			sb.deleteCharAt(j);
		}
		while (sb.length() > 1 && sb.charAt(0) == '0'){
			// sb.deleteCharAt(0);
			sb.delete(0, 1);
		}

		return sb.toString();
	}

}
