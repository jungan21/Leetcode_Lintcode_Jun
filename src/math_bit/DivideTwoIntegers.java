package math_bit;

/**
 * Divide two integers without using multiplication, division and mod operator.
 * 
 * If it is overflow, return MAX_INT.
 * 
 */
public class DivideTwoIntegers {

	public static void main(String[] args) {
		System.out.println(divide(10, 3));
		// System.out.println(1 << 1);
	}

	/**
	 * This problem can be solved based on the fact that any number can be
	 * converted to the format of the following:
	 * 
	 * num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n
	 * 
	 */
	// dividend / divisor

	/**
	 * 我们知道任何一个整数可以表示成以2的幂为底的一组基的线性组合，即num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n。
	 * 基于以上这个公式以及左移一位相当于乘以2
	 * ，我们先让除数左移直到大于被除数之前得到一个最大的基。然后接下来我们每次尝试减去这个基，如果可以则结果增加加2
	 * ^k,然后基继续右移迭代，直到基为0为止。因为这个方法的迭代次数是按2的幂直到超过结果，所以时间复杂度为O(logn)
	 * 
	 */
	public static int divide(int dividend, int divisor) {
		// handle special cases
		if (divisor == 0) {
			return dividend >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		}
		if (dividend == 0) {
			return 0;
		}
		if (divisor == -1 && dividend == Integer.MIN_VALUE)
			return Integer.MAX_VALUE;

		// get positive values
		long pDividend = Math.abs((long) dividend);
		long pDivisor = Math.abs((long) divisor);

		int result = 0;
		//一定要是 >=
		while (pDividend >= pDivisor) {
			/**
			 * calculate number of left shifts for each iteration (i.e. each
			 * pDividend)
			 * 
			 * for each pDividend, we need to find the largest base (base <=
			 * pDividend)
			 */
			int numShift = 0;
			while (pDividend >= (pDivisor << numShift)) {
				numShift++;
			}
			long base = pDivisor << (numShift - 1);

			/**
			 * 每往左位移一次， 相当于乘以2一次
			 * 
			 * 假设pDivisor=3， pDividend = 32， pDivisor左移了3位达到最接近32的24， 32-24 = 8
			 * 
			 * 如果不用此方法，用32 不断的减去3， 要减掉8次才能达到8， 由于位移了3次达到24，
			 * 
			 * 1 << 3, 相当于2^3=8 ==> result = result + 2^(位移次数)
			 */
			result += 1 << (numShift - 1);
			// dividend minus the largest shifted divisor
			// Note: if you write like pDividend = pDividend - base (can't
			// submit to leetcode)
			pDividend -= base;
		}

		if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
			return result;
		} else {
			return -result;
		}
	}

	/**
	 * method 2: O(n) naive method:
	 * http://www.acmerblog.com/leetcode-divide-two-integers-5977.html
	 * 
	 * 比较直接的方法是用被除数一直减去除数，直到为0，记录下被减的次数就是结果。这种方法的迭代次数是结果的大小， 即比如结果为n，算法复杂度是O(n)
	 */

	/**
	 * method 3: O(logn) naive method:
	 * http://www.acmerblog.com/leetcode-divide-two-integers-5977.html
	 * 我们知道任何一个整数可以表示成以2的幂为底的一组基的线性组合
	 * ，即num=a_0*2^0+a_1*2^1+a_2*2^2+…+a_n*2^n。基于以上这个公式以及左移一位相当于乘以2
	 * ，我们先让除数左移直到大于被除数之前得到一个最大的基
	 * 。然后接下来我们每次尝试减去这个基，如果可以则结果增加加2^k,然后基继续右移迭代，直到基为0为止
	 * 。因为这个方法的迭代次数是按2的幂知道超过结果，所以时间复杂度为O(logn)。
	 * 
	 * 需要注意的是，整数的溢出问题，例如 divide(-2147483648, 2)。我这里问了方便，直接使用了long类型
	 */

}
