package math_bit;

/**
 * Implement int sqrt(int x). Compute and return the square root of x.
 * 
 * Sort(x) follow up question, what if return a double, not a integer?
 * 
 */
public class SqrtII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("标准：" + Math.sqrt(6));
		System.out.println("double: " + sqrt1(6));
		// System.out.println(3.1622776601683795 * 3.1622776601683795);
		System.out.println("1E-2 " + 1E-20);
	}

	public static double sqrt1(double x) {
		double start = 1, end = x;
		while (start + 1 < end) {
			double mid = start + (end - start) / 2.0;
			// 1E-20 = 1 * 10^(-20)
			if (Math.abs(mid * mid - x) < 1E-20) {
				return mid;
			} else if (mid * mid > x) {
				end = mid;
			} else {
				start = mid;
			}
		}
		if (Math.abs(start * start - x) < 1E-20) {
			return start;
		} else if (Math.abs(end * end - x) < 1E-20) {
			return end;
		}
		// /**-----------我的意思是如果这里和end，start都不一样该怎么办呢，
		// 是不是上面while的循环条件该变成start＋1E-5<end之类的?----/
		else {
			return start;
		}
	}

}
