package math_bit;

/**
 * string s1 = "0"; // True string s2 = " 0.1 "; // True string s3 = "abc"; //
 * False string s4 = "1 a"; // False string s5 = "2e10"; // True
 * 
 * string s6 = "-e10"; // False string s7 = " 2e-9 "; // True string s8 = "+e1";
 * // False string s9 = "1+e"; // False string s10 = " "; // False
 * 
 * string s11 = "e9"; // False string s12 = "4e+"; // False string s13 = " -.";
 * // False string s14 = "+.8"; // True string s15 = " 005047e+6"; // True
 * 
 * string s16 = ".e1"; // False string s17 = "3.e"; // False string s18 =
 * "3.e1"; // True string s19 = "+1.e+5"; // True string s20 = " -54.53061"; //
 * True
 * 
 * string s21 = ". 1"; // False
 * 
 * Some examples: "0" => true " 0.1 " => true "abc" => false "1 a" => false
 * "2e10" => true
 *
 */
public class ValidNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * http://www.cnblogs.com/grandyang/p/4084408.html
	 * 
	 * http://www.jiuzhang.com/solutions/valid-number/
	 */
	public boolean isNumber(String s) {
		int len = s.length();
		int i = 0, e = len - 1;
		// Character.isLetter(s.charAt(i)); Character.isDigit(s.charAt(i));
		while (i <= e && Character.isWhitespace(s.charAt(i)))
			i++;
		if (i >= len)
			return false;

		while (e >= i && Character.isWhitespace(s.charAt(e)))
			e--;

		// skip leading +/-
		if (s.charAt(i) == '+' || s.charAt(i) == '-')
			i++;

		boolean num = false; // is a digit
		boolean dot = false; // is a '.'
		boolean exp = false; // is a 'e'
		while (i <= e) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				num = true;
			} else if (c == '.') {
				if (exp || dot)
					return false;
				dot = true;
			} else if (c == 'e') {
				// 如果之前出现过自然底数或者之前从未出现过数字，返回false，否则标记exp为true，num为false。
				if (exp || num == false)
					return false;
				exp = true;
				num = false;
				// 必须要reset num = false, 否则 "0e"input不对
				/**
				 * "2e10","2e-9", "005047e+6", "3.e1", "+1.e+5" true situation
				 */

				// 符号前面如果有字符的话必须是空格或者是自然底数，标记sign为true。
			} else if (c == '+' || c == '-') {
				if (s.charAt(i - 1) != 'e')
					return false;
			} else {
				return false;
			}
			i++;
		}
		return num;
	}

}
