package math_bit;

public class HexToInt {

	public static void main(String[] args) {
		String hexString = "AB";
		System.out.println(hex2decimal(hexString));
		System.out.println(decimal2Hex(171));
		System.out.println(binary2Int("100"));
		System.out.println(int2Binary(4));
	}

	/**
	 * 0-0, 1-1 ... 9-9, A-10, B-11, C-12, D-13, E-14, F-15
	 * 
	 * 将16进制字符串 装换为10进制的数字
	 */

	public static long hex2decimal(String s) {
		String digits = "0123456789ABCDEF";
		s = s.toUpperCase();
		long result = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			// index
			int d = digits.indexOf(c);
			result = 16 * result + d;
		}
		return result;
	}

	public static String decimal2Hex(long num) {
		String digits = "0123456789ABCDEF";
		String result = "";
		while (num > 0) {
			int mod = (int) num % 16;
			result = digits.charAt(mod) + result;
			num = num / 16;
		}

		return result;
	}

	/**
	 * 0-0, 1-1
	 * 
	 * 将2进制字符串 装换为10进制的数字
	 */

	public static long binary2Int(String s) {
		long result = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			result = 2 * result + (c - '0');
		}
		return result;
	}

	/**
	 * 0-0, 1-1
	 * 
	 * 将10进制字符串 装换为2进制的数字
	 */

	public static String int2Binary(int num) {
		String result = "";
		while (num > 0) {
			int mod = (int) num % 2;
			result = mod + result;
			num = num / 2;
		}
		return result;
	}

	/**
	 * 
	 */

	public static int hex2decimal1(String s) {
		int decimal = Integer.parseInt(s, 16);
		return decimal;
	}

	public static String decimal2Hex1(int num) {
		String hex = Integer.toHexString(num);
		return hex;
	}

}
