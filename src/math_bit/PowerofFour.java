package math_bit;

public class PowerofFour {

	public static void main(String[] args) {
		System.out.println(0x55555555);
	}

	// https://segmentfault.com/a/1190000003481153
	// http://www.cnblogs.com/grandyang/p/5403783.html
	public boolean isPowerOfFour(int n) {
		if (n <= 0) {
			return false;
		}
		/**
		 * https://segmentfault.com/a/1190000003481153
		 * http://www.cnblogs.com/grandyang/p/5403783.html
		 * 
		 * 一个数是4的幂，肯定是2的幂，但是，是2的幂，不一定是4的次方数，（比如8），所以我们还要其他的限定条件
		 * 
		 * 那4的幂和2的幂有什么区别呢？观察发现4的幂的1只可能在奇数位(从右边往左数，最低位表示第1位)，而2的幂的1可能在任意位，
		 * 所以我们只要判断是不是奇数位是1就行了。因为前面的n & (n - 1)==0的条件我们已经筛出来那些只有1个1的数了，所以和
		 * 0x55555555求&，就可以判断1是否在奇数位上了
		 * 
		 * 0x55555555 = 01010101 01010101 01010101 01010101
		 * 
		 * 0x means hexadecimal(16进制), 5 is 4+1 = 2^2 +2^0. So 5 is 0101. each
		 * 5表示 0101， 一共8个0101， 所以8个5
		 * 
		 * 0x和8个5
		 */
		return (n & (n - 1)) == 0 && (n & 0x55555555) == n;
	}
	//推荐
	public boolean isPowerOfFour2(int n) {
		if (n <= 0) {
			return false;
		}
		double pow = Math.log10(n) / Math.log10(4);
		if (pow - (int) pow == 0) {
			return true;
		}
		return false;
	}
	//不推荐
	public boolean isPowerOfFour3(int num) {
		if (num == 0)
			return false;

		int pow = (int) (Math.log(num) / Math.log(4));
		if (num == Math.pow(4, pow)) {
			return true;
		} else {
			return false;
		}
	}

	// Naive method
	public boolean isPowerOfFourNaive(int n) {
		if (n == 1)
			return true;

		boolean result = false;

		while (n > 0) {
			int m = n % 4;
			if (m == 0) {
				n = n / 4;
				if (n == 1)
					return true;
			} else {
				return false;
			}
		}

		return result;
	}

	public boolean isPowerOfFourOld(int n) {
		if (n <= 0) {
			return false;
		}
		// n & (n-1) == 0 表示 pow of 2
		// 由于是2的次方数，不一定是4的次方数，比如8，所以我们还要其他的限定条件
		return (n & (n - 1)) == 0 && (n - 1) % 3 == 0;
	}

}
