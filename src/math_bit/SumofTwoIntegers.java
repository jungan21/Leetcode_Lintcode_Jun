package math_bit;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the
 * operator + and -.
 * 
 * Example: Given a = 1 and b = 2, return 3.
 * 
 */
public class SumofTwoIntegers {

	public static void main(String[] args) {
		System.out.println(getSum(2, 3));
	}

	// http://www.cnblogs.com/grandyang/p/5451942.html
	/**
	 * 我们来看一个例子759+674
	 * 
	 * 1. 如果我们不考虑进位，可以得到323
	 * 
	 * 2. 如果我们只考虑进位，可以得到1110
	 * 
	 * 3. 我们把上面两个数字假期323+1110=1433就是最终结果了
	 * 
	 * 然后我们进一步分析，如果得到上面的第一第二种情况，我们在二进制下来看，
	 * 
	 * 1. 不考虑进位的加，0+0=0， 0+1=1, 1+0=1，1+1=0，这就是异或(^)的运算规则，
	 * 
	 * 2. 只考虑进位的加, 0+0=0, 0+1=0, 1+0=0, 1+1=1，而这其实这就是与(&)的运算，
	 * 
	 * 3. 第三步在将两者相加时, 我们再递归调用这个算法，终止条件是当进位为0时，我们直接返回第一步的结果，参见代码如下：
	 * 
	 */
	public static int getSum(int a, int b) {
		// getSum(sum, carry), a 相当于sum, b 相当于carry, 直到carry =0
		if (b == 0) {
			return a;
		}
		int sum = a ^ b;
		// << 相当于 进位操作, 之所以a&b才进位，因为只有两位都为1的时候，才进位
		int carry = (a & b) << 1;
		System.out.println(carry);
		return getSum(sum, carry);
	}

	public int getSum1(int a, int b) {

		while (b != 0) {
			int c = a & b;
			a = a ^ b;
			b = c << 1;
		}

		return a;
	}

	/**
	 * Wrong, 不可以用 + , - operation
	 * http://www.cnblogs.com/grandyang/p/5631814.html
	 * 
	 */
	public static int getSumWrong(int a, int b) {
		int[] bits = new int[32];
		int result = 0;
		int carry = 0;
		for (int i = 0; i < 32; i++) {
			if ((a >> i & 1) == 1) {
				bits[i]++;
			}
			if ((b >> i & 1) == 1) {
				bits[i]++;
			}
			bits[i] += carry;
			if (bits[i] > 2) {
				carry = bits[i] % 2;
				bits[i] = bits[i] / 2;
			} else if (bits[i] == 2) {
				carry = 1;
				bits[i] = 0;
			} else {
				carry = 0;
			}
			result = result | (bits[i] << i);
		}
		return result;
	}

}
