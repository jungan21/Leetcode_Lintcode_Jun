package math_bit;

public class PowerofTwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (new PowerofTwo().isPowerOfTwoIterative(1)) {
			System.out.println("true");
		}
	}

	// jiuzhang
	// http://www.jiuzhang.com/solutions/o1-check-power-of-2/
	/**
	 * 如果一个数是2的次方数的话，根据上面分析，那么它的二进数必然是最高位为1，其它都为0，那么如果此时我们减1的话，则最高位会降一位，
	 * 其余为0的位现在都为变为1，那么我们把两数相与，就会得到0，
	 * 
	 */
	public static boolean checkPowerOf2(int n) {
		if (n <= 0) {
			return false;
		}
		/**
		 * & 运算符查看两个表达式的二进制表示法的值，并执行按位“与”操作
		 * 
		 * 只要两个表达式的某位都为 1，则结果的该位为 1。否则，结果的该位为 0。
		 * 
		 * 2的整数幂若用二进制来表示，则其中必只有一个1，其余全是0
		 * 
		 * http://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/
		 * 72984
		 */
		//注意 不可以用这个条件判断奇偶，仅仅用来判断是不是2的次幂
		return (n & (n - 1)) == 0;
	}

	public boolean isPowerOfTwo(int n) {
		if (n == 0)
			return false;
		if (n == 1)
			return true;

		if (n % 2 == 1)
			return false;

		return isPowerOfTwo(n / 2);
	}

	// If a number is power of 2, it's binary form should be 10...0. So if we
	// right shift a bit of the number and then left shift a bit, the value
	// should be the same when the number >= 10(i.e.,2).
	public boolean isPowerOfTwoIterative(int n) {
		if (n <= 0)
			return false;

		while (n > 2) {
			int t = n >> 1;
			int c = t << 1;

			if (n - c != 0)
				return false;

			n = n >> 1;
		}

		return true;
	}

	public boolean isPowerOfTwoIteration(int n) {
		if (n == 0)
			return false;
		if (n == 1)
			return true;

		boolean result = false;

		while (n > 0) {
			int m = n % 2;
			if (m == 0) {
				n = n / 2;
				// important condition
				if (n == 1) {
					return true;
				}
			} else {
				return false;
			}
		}

		return result;
	}
}
