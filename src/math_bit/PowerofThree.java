package math_bit;

public class PowerofThree {

	public static void main(String[] args) {
		System.out.println(Math.log10(100));
	}

	// http://www.cnblogs.com/grandyang/p/5138212.html
	// http://www.programcreek.com/2015/04/leetcode-power-of-four-java/
	// http://www.cnblogs.com/grandyang/p/5138212.html
	// http://www.programcreek.com/2014/04/leetcode-power-of-three-java/
	/**
	 * 高中学过的换底公式为logab = logcb / logca，那么如果n是3的倍数，则log3n一定是整数，我们利用换底公式可以写为log3n
	 * = log10n / log103，注意这里一定要用10为底数，不能用自然数或者2为底数，否则当n=243时会出错 原因见：
	 * https://discuss
	 * .leetcode.com/topic/33536/a-summary-of-all-solutions-new-method
	 * -included-at-15-30pm-jan-8th
	 * 
	 */
	public boolean isPowerOfThreeBit(int n) {
		if (n <= 0) {
			return false;
		}
		// Math.log(100)表示 e为底数，即 e的多少次方为100
		// Math.log10(100), 表示10为底，即10的多少次方是100，return 2.0 (double类型)
		// double pow = log10(n) / log10(3) = log3(n),也就是 3的多少次幂是n, 由于Math.log10默认返回的是double类型，如果 返回的
		double pow = Math.log10(n) / Math.log10(3);
		if (pow - (int) pow == 0) {
			return true;
		}
		return false;
	}

	public boolean isPowerOfThreeBit1(int n) {
		if (n <= 0) {
			return false;
		}
		int pow = (int) (Math.log10(n) / Math.log10(3));
		if (n == (int) Math.pow(3, pow)) {
			return true;
		}
		return false;
	}

	public boolean isPowerOfThree(int n) {
		if (n == 1)
			return true;

		boolean result = false;

		while (n > 0) {
			int m = n % 3;
			if (m == 0) {
				n = n / 3;
				if (n == 1)
					return true;
			} else {
				return false;
			}
		}

		return result;
	}

	public boolean isPowerOfThreeRecusion(int n) {
		if (n == 0)
			return false;

		if (n == 1)
			return true;

		if (n > 1)
			return n % 3 == 0 && isPowerOfThree(n / 3);
		else
			return false;
	}

}
