package math_bit;

import java.util.HashSet;

/**
 * Given a (decimal - e g 3.72) number that is passed in as a string,return the
 * binary representation that is passed in as a string.If the number can not be
 * represented accurately in binary, print “ERROR”
 * 
 * Example n = 3.72, return ERROR
 * 
 * n = 3.5, return 11.1
 */

/**
 * For int part, similar approach of extracting numbers from int:
 * 
 * 1. use %2 to get each digit from lowest bit to highest bit.
 * 
 * 2. int right shift 1 position (=>>1).
 * 
 * 3. construct the binary number (always add to the higher position of the
 * current binary number)
 *
 * 
 */

public class BinaryRepresentation {

	public static void main(String[] args) {
		System.out.println(new BinaryRepresentation()
				.binaryRepresentation("35"));
	}

	public String binaryRepresentation(String n) {
		if (n.indexOf('.') == -1) {
			return parseInteger(n);
		}
		// 由于 . 是正则表达式里关键字，需要转义
		String[] params = n.split("\\.");
		String flt = parseFloat(params[1]);
		// 如果小数部分是ERROR, 就不用考虑整数部分了
		if (flt == "ERROR") {
			return flt;
		}
		if (flt.equals("0") || flt.equals("")) {
			return parseInteger(params[0]);
		}
		return parseInteger(params[0]) + "." + flt;
	}

	// parse整数部分： 除2取余，余数逆序排列
	// http://www.cnblogs.com/xkfz007/articles/2590472.html
	private String parseInteger(String str) {
		int n = Integer.valueOf(str);
		if (str.equals("") || str.equals("0")) {
			return "0";
		}
		String binary = "";
		while (n != 0) {
			binary = String.valueOf(n % 2) + binary;
			n = n / 2;
		}
		return binary;
	}

	// parse 小数点后面几个数字： 乘2取整，整数部分顺序排列
	// http://www.cnblogs.com/xkfz007/articles/2590472.html
	private String parseFloat(String str) {
		// Note:
		double d = Double.valueOf("0." + str);
		String binary = "";
		// 如果都是0， d = d * 2; 还等于0， 所以，不用运行32次，用set仅仅提高效率，去掉也可以
		HashSet<Double> set = new HashSet<Double>();
		while (d > 0) {
			// 意味着，binary = binary + "0"; 一直运行，结果长度超过32了，就ERROR
			if (binary.length() > 32 || set.contains(d)) {
				return "ERROR";
			}
			set.add(d);
			d = d * 2;
			if (d >= 1) {
				binary = binary + "1";
				d = d - 1;
			} else {
				binary = binary + "0";
			}
		}
		return binary;
	}
}
