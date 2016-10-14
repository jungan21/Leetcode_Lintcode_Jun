package math_bit;

/**
 * Related to question Excel Sheet Column Title
 * 
 * Given a column title as appear in an Excel sheet, return its corresponding
 * column number.
 * 
 * For example:
 * 
 * A -> 1 B -> 2 C -> 3 ... Z -> 26, AA -> 27, AB -> 28
 *
 */
public class ExcelSheetColumnNumber {

	public static void main(String[] args) {
		System.out.println(titleToNumber("AAB"));
		System.out.println(titleToNumber("ABC"));
		System.out.println(titleToNumber1("AAB"));
		System.out.println(titleToNumber1("ABC"));
	}

	public static int titleToNumber(String s) {
		int res = 0;
		if (s.length() == 0)
			return 0;
		for (int i = 0; i < s.length(); ++i) {
			res = res * 26 + (s.charAt(i) - 'A' + 1);
		}
		return res;
	}

	/**
	 * Jun' 和 HexToInt.java类似
	 * 
	 * String digits = "0123456789ABCDEF";
	 */
	public static int titleToNumber1(String s) {
		String dict = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			int d = dict.indexOf(ch);
			result = 26 * result + (d + 1);
		}
		return result;
	}
}
