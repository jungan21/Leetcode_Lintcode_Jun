package math_bit;

/**
 * Given a positive integer, return its corresponding column title as appear in
 * an Excel sheet.
 * 
 * For example:
 * 
 * 1 -> A, 2 -> B, 3 -> C, ... 26 -> Z, 27 -> AA, 28 -> AB,
 *
 */
public class ExcelSheetColumnTitle {

	public static void main(String[] args) {
		System.out.println((char) ((28 - 2) % 26 + 'A'));
		// AAB 704, ABC 731
		int a = 26 + 26 * 26 + 2; // (704)
		System.out.println(convertToTitle(731));
		System.out.println(convertToTitleJun(731));
	}

	// 时间复杂度 O(n)，空间复杂度 O(1)
	public static String convertToTitle(int n) {
		String result = "";
		if (n <= 0) {
			return "";
		}
		while (n > 0) {
			/**
			 * 其思路 和将一个十进制数，每次一最末尾的一个数意思差不多，这里是26进制，
			 * 
			 * 1. 由于10进制是0到9， 而这里是1到26， 所以每次(n-1) % 26
			 * 
			 * 2. 由于10进制，左边是高位，右边是低位，而这里顺序相反
			 * 
			 * reverse 10进制: result = 0; result = result * 10 + n % 10; n = n / 10;
			 * 但是这里不是reverse, 只是求出每位字符就好，然后再拼接成字符串就好
			 */
			result = (char) ((n - 1) % 26 + 'A') + result;
			n = (n - 1) / 26;
		}
		return result;
	}

	/**
	 * method 2
	 */
	public static String convertToTitle1(int n) {
		StringBuilder sb = new StringBuilder();
		while (n > 0) {
			sb.insert(0, (char) ((n - 1) % 26 + 'A'));
			n = (n - 1) / 26;
		}
		return sb.toString();
	}
	
	/**
	 * Jun
	 */
	
	public static String convertToTitleJun(long num) {
		String dict = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
		String result = "";
		while (num > 0) {
			int mod = (int) num % 26;
			result = dict.charAt(mod-1) + result;
			num = num / 26;
		}

		return result;
	}
}
