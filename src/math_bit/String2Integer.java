package math_bit;

public class String2Integer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int res = 0;
		int a = 1;
		char aa = '9';
		res = a + aa;
		System.out.println(atoi("1.0"));
		String test = " test ";
		// test = test.trim();
		System.out.println(test);

	}

	// http://www.jiuzhang.com/solutions/string-to-integer-ii/
	// jiuzhang
	public static int atoi(String str) {
		if (str == null)
			return 0;

		/**
		 * trim white spaces, important, 注意不可以直接str.trim();这样不改变原来str的值，必须要重新赋值给一个str
		 * 
		 * 对于 str.toLowerCase()方法也一样
		 */
		str = str.trim();
		if(str.length() == 0){
			return 0;
		}
		
		boolean netativeFlag = false;
		// check negative or positive
		int i = 0;
		if (str.charAt(0) == '-') {
			netativeFlag = true;
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}
		// !!!之前错过must be double, long, int doesn't work
		double result = 0;

		// calculate value
		// 必须要做此判断，否则如果输入时"1.0", 中间那个小数点时，就出错,
		// 由于输出结果是int,如果遇到小数点，while循环就终止了，小数点后的数字也就不用考虑
		while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {

			// result = result * 10 + str.charAt(i);
			// 上面写法不对，算出来不是数学计算的结果，必须要
			result = result * 10 + (str.charAt(i) - '0');
			//因为result 的return type是int, 如果大于max, 直接break;
			if (result > Integer.MAX_VALUE ) {
                break;
            }
			i++;
		}

		if (netativeFlag)
			result = -result;

		// handle max and min
		if (result > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;

		if (result < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		// 由于之前定义的是 double类型，必须转换为int类型
		return (int) result;
	}
}
