package math_bit;

/**
 * Given a positive integer num, write a function which returns True if num is a
 * perfect square else False.
 * 
 * Note: Do not use any built-in library function such as sqrt.
 * 
 * Example 1:
 * 
 * Input: 16 Returns: True Example 2:
 * 
 * Input: 14 Returns: False
 *
 */

public class ValidPerfectSquare {

	public static void main(String[] args) {
		isPerfectSquare(808201);
	}

	public static boolean isPerfectSquare(int num) {
		if (num < 1) {
			return false;
		}
		// 必须是long的，因为下面的mid计算需要long, 否则mid* mid很容易就溢出， 导致计算结果不对
		long start = 1;
		// end = num /2 +1即可
		long end = num;
		// find last one mid * mid <= num;
		while (start + 1 < end) {
			long mid = start + (end - start) / 2;
			if (mid * mid <= num) {
				start = mid;
			} else {
				end = mid;
			}
		}

		if (end * end == num) {
			return true;
		} else if (start * start == num) {
			return true;
		}
		return false;
	}
}
