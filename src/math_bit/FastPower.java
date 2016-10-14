package math_bit;

/**
 * Calculate the an % b where a, b and n are all 32bit integers.
 * 
 * 
 * (a * b) % p = (a%p * b%p) % p
 * 
 * 将 a^n % b ==> [a^(n/2) * a^(n/2) * (a)] %b ==> {[a^(n/2) * a^(n/2)]%b *
 * (a)%b} %b = ((a^(n/2)%b * a^(n/2)%b)%b * (a)%b) %b
 *
 */
public class FastPower {

	public static void main(String[] args) {
		FastPower fp = new FastPower();
		// System.out.println(fp.pow(2, 2147483647));
		System.out.println(fp.fastPower(2, 2147483647, 2147483647));
	}

	/**
	 * method 1: http://www.jiuzhang.com/solutions/fast-power/
	 * http://www.cnblogs.com/yuzhangcmu/p/4174781.html
	 * http://massivealgorithms.blogspot.ca/2015/07/fast-power-lintcode-java-welkin-lan.html
	 * 
	 * 
	 * Note: 如果先算pow 在求余的方法也不对，会溢出 return (int) (pow (a, n) % b );
	 */
	public int fastPower(int a, int b, int n) {
		// 2个base case: n = 0 n = 1都要特别处理。因为n = 1时，会分解出一个pow(a, b, 1)，这个会不断循环调用。
		if (n == 1) {
			return a % b;
		}
		if (n == 0) {
			return 1 % b;
		}
		//只能是long, double 不对
		long product = fastPower(a, b, n / 2);
		// 为了防止溢出必须按这个顺序写
		product = (product * product) % b;
		if (n % 2 == 1) {
			product = (product * a%b) % b;
		}

		return (int) product;
	}

	/**
	 * method 1另一版本，Wrong!!! 会溢出
	 */

	public int fastPower1(int a, int b, int n) {
		if (n == 1) {
			return a % b;
		}
		if (n == 0) {
			return 1 % b;
		}
		long product = fastPower(a, b, n / 2);
		if (n % 2 == 1) {
			// 这样写就会溢出
			product = (product * product * a) % b;
		} else {
			product = (product * product) % b;
		}
		return (int) product;
	}

}
