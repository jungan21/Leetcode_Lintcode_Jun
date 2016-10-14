package math_bit;

/**
 * Implement int sqrt(int x). Compute and return the square root of x.
 * 
 */
public class Sqrt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Math.sqrt(808201));
		System.out.println(3.1622776601683795 * 3.1622776601683795);
	}

	/**
	 * jiuzhang: http://www.jiuzhang.com/solutions/sqrtx/
	 * 
	 * 
	 * follow up question can be: what if return a double, not an integer
	 */
	public int sqrt(int x) {
		// find the last number which square of it <= x
		long start = 1, end = x;
		while (start + 1 < end) {
			long mid = start + (end - start) / 2;
			// 二分模板 ， last number： number^2 <= x
			// sqrt(10) = 3
			if (mid * mid <= x) {
				start = mid;
			} else {
				end = mid;
			}
		}

		if (end * end <= x) {
			return (int) end;
		}
		return (int) start;
	}

	/**
	 * another method Note:对于一个非负数n，它的平方根不会小于大于（n/2+1）。在[0,
	 * n/2+1]这个范围内可以进行二分搜索，求出n的平方根。
	 */

	// The complexity is O(log x).
	/**
	 * We can use binary search for this problem. It could be pretty easy to
	 * implement. It’s possible that integer overflows. So in the following code
	 * I use long type.
	 */

	// 对于一个非负数n，它的平方根不会小于大于（n/2+1）。在[0,
	// n/2+1]这个范围内可以进行二分搜索，求出n的平方根。
	public static int sqrt1(int x) {
		long left = 0;
		long right = x / 2 + 1;
		while (left <= right) {
			long mid = (left + right) / 2;
			if (mid * mid == x)
				return (int) mid;
			if (mid * mid < x)
				left = mid + 1;
			else
				right = mid - 1;
		}
		/**
		 * Note: you must return right: 1. we need int (1.4 ==> 1) 2. after
		 * while loop, right < left, so we need to get lower end
		 */
		return (int) right;
	}

}
