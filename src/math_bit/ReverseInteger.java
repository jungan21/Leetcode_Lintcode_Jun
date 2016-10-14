package math_bit;

/**
 * Reverse digits of an integer. Returns 0 when the reversed integer overflows
 * (signed 32-bit integer).
 * 
 */
public class ReverseInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new ReverseInteger().reverse(123));
		System.out.println(new ReverseInteger().reverse(1));
		System.out.println(new ReverseInteger().reverse(-1));
		System.out.println(new ReverseInteger().reverse(-123));
		System.out.println(new ReverseInteger().reverse(1534236469));
		String s = "";
	}

	// jiuzhang, 正负数 不用单独考虑
	public int reverse(int n) {
		int result = 0;
		// 必须是n!=0, 若写成n>0不对，因为有负数的情况
		while (n != 0) {
			int temp = result * 10 + n % 10;
			// 判断越界的方法，或者也可以把result 设置为long, while loop后再判断是否越界
			if (temp / 10 != result) {
				result = 0;
				break;
			}
			n /= 10;
			result = temp;
		}
		return result;
	}

	/**
	 * method 2
	 */

	public int reverseInteger(int n) {
		long result = 0;
		while (n != 0) {
			result = result * 10 + n % 10;
			if (result > Integer.MAX_VALUE) {
				return 0;
			}
			n = n / 10;
		}
		return (int) result;
	}

}
