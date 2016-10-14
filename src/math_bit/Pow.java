package math_bit;

public class Pow {

	public static void main(String[] args) {
		pow(3, 2);
	}

	/**
	 * x^n = x^(n/2)*x^(n/2)*x^(n%2)
	 * 
	 * O(logn)
	 * 
	 * 第一层是1，第二层是2，第三层是4，第i层是2^(i -
	 * 1)，总共是log(n)层，每一层都是下一层的二分之一，所以最后的总和小于2*log(n)
	 */
	// http://www.jiuzhang.com/solutions/powx-n/
	public static double myPow(double x, int n) {
		if (n == 0) {
			return 1;
		}

		if (n == 1) {
			return x;
		}

		boolean isNegative = false;
		if (n < 0) {
			isNegative = true;
			n = -n;
		}

		int k = n / 2;
		// l = n%2;
		int l = n - k * 2;
		double t1 = myPow(x, k);
		double t2 = myPow(x, l);
		if (isNegative) {
			return 1 / (t1 * t1 * t2);
		} else {
			return t1 * t1 * t2;
		}
	}

	// method 2: http://www.programcreek.com/2012/12/leetcode-powx-n/
	/**
	 * 1. Implement x to the power y as a function. (x^y) 2. Write the solution
	 * in recursive form. 3. Improve for O(logn) run time. 4. Improve for O(1)
	 * spatial complexity. (hint: tail recursion)
	 */
	public static double pow(double x, int n) {
		if (n == 0) {
			return 1;
		} else if (n < 0) {
			return 1 / power(x, -n);
		} else {
			return power(x, n);
		}
	}

	// x^n = x^(n/2) * x^(n/2) * x^(n%2)
	public static double power(double x, int n) {
		// base case, recursion ending point, can NOT ignore
		if (n == 0)
			return 1;
		// divide
		double v = power(x, n / 2);

		// conquer
		if (n % 2 == 0) {
			return v * v;
		} else {
			return v * v * x;
		}
	}

}
