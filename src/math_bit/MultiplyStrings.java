package math_bit;

/**
 * Given two numbers represented as strings, return multiplication of the
 * numbers as a string.
 * 
 * Note: The numbers can be arbitrarily large and are non-negative.
 * 
 * 题意就是给你两个字符串型的数字，给这两个数字做乘法。
 * 
 * 注意：如果直接转换成Integer做乘法就会溢出。
 * 
 * 所以要一步一步来。
 * 
 */
public class MultiplyStrings {

	public static void main(String[] args) {
		System.out.println(multiply("0", "0"));
		System.out.println(multiply2("12", "13"));
		System.out.println(multiply3("99", "99"));
	}

	/**
	 * jiuzhang 推荐
	 */
	public static String multiply(String num1, String num2) {
		if (num1 == null || num2 == null) {
			return null;
		}

		int len1 = num1.length(), len2 = num2.length();
		int len3 = len1 + len2;
		int i, j, product, carry;

		int[] num3 = new int[len3];
		// 倒着来 因为，是要先从个位数字相乘
		for (i = len1 - 1; i >= 0; i--) {
			carry = 0;
			for (j = len2 - 1; j >= 0; j--) {
				// product = carry + num3[i+j+1] + (num1.charAt(i) - '0') *
				// (num2.charAt(j)-'0');
				product = carry + num3[i + j + 1]
						+ Character.getNumericValue(num1.charAt(i))
						* Character.getNumericValue(num2.charAt(j));
				num3[i + j + 1] = product % 10;
				carry = product / 10;
			}
			// 除了上面的内部for loop,j--, 实际上等于是把carry带到前面一位上去了
			num3[i + j + 1] = carry;
		}

		StringBuilder sb = new StringBuilder();
		i = 0;
		/*
		 * 因为如果最后一位是0，就表示结果为0;
		 * 
		 * 这里也可以写成i < len3, 不过要加一句 if (i >= num3.length){return "0"},因为表示全部为0
		 */
		while (i < len3 - 1 && num3[i] == 0) {
			i++;
		}

		while (i < len3) {
			sb.append(num3[i++]);
		}

		return sb.toString();
	}

	public static String multiply2(String num1, String num2) {
		num1 = new StringBuilder(num1).reverse().toString();
		num2 = new StringBuilder(num2).reverse().toString();
		// even 99 * 99 is < 10000, so maximaly 4 digits
		int[] d = new int[num1.length() + num2.length()];
		for (int i = 0; i < num1.length(); i++) {
			int a = num1.charAt(i) - '0';
			for (int j = 0; j < num2.length(); j++) {
				int b = num2.charAt(j) - '0';
				d[i + j] += a * b;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < d.length; i++) {
			int digit = d[i] % 10;
			int carry = d[i] / 10;
			sb.insert(0, digit);
			if (i < d.length - 1)
				d[i + 1] += carry;
		}
		// trim starting zeros
		while (sb.length() > 0 && sb.charAt(0) == '0') {
			sb.deleteCharAt(0);
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}

	public static String multiply3(String num1, String num2) {
		StringBuffer sb = new StringBuffer();
		int n1 = num1.length(), n2 = num2.length();
		int k = n1 + n2 - 2, carry = 0;
		int[] v = new int[n1 + n2];
		for (int i = 0; i < n1; ++i) {
			for (int j = 0; j < n2; ++j) {
				v[k - i - j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
			}
		}
		// 处理进位
		for (int i = 0; i < n1 + n2; ++i) {
			v[i] += carry;
			carry = v[i] / 10;
			v[i] %= 10;
		}
		int i = n1 + n2 - 1;
		// 去掉乘积的前导0
		while (v[i] == 0)
			--i;
		// 注意乘积为0的特殊情况
		if (i < 0)
			return "0";
		while (i >= 0)
			sb.append(v[i--] + '0');
		return sb.toString();
	}
}
