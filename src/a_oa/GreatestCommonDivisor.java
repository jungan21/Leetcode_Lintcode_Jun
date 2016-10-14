package a_oa;

import java.math.BigInteger;

public class GreatestCommonDivisor {

	public static void main(String[] args) {
		int[] A = { 112, 160, 180, 240, 288, 32, 480, 96, 60, 72 };

		// System.out.println(gcdLibrary(gcdLibrary(24, 6), gcdLibrary(9, 18)));

		System.out.println(gcd4Nums(A));
		System.out.println(gcd4Nums2By2(A));
		System.out.println(gcd4NumsDividConquerResusive(A, 0, A.length - 1));
	}

	/**
	 * recommened！！！， real 2 by 2,
	 */
	public static int gcd4Nums2By2(int[] array) {
		if (array == null || array.length == 1)
			return 0;

		while (array.length > 1) {
			int len = array.length;
			int tempSize = len % 2 == 0 ? len / 2 : len / 2 + 1;
			int[] temp = new int[tempSize];
			int j = 0;
			for (int i = 0; i <= array.length - 2; i = i + 2) {
				temp[j++] = gcdDivideIterative(array[i], array[i + 1]);
			}
			if (len % 2 != 0) {
				temp[tempSize - 1] = array[len - 1];
			}
			// array 和list一样，也可以直接这样赋值
			array = temp;
		}
		return array[0];
	}

	public static int gcd4NumsDividConquerResusive(int[] array, int start,
			int end) {
		if (start == end) {
			return array[start];
		}
		if (start + 1 == end) {
			return gcdDivideIterative(array[start], array[end]);
		}
		int mid = start + (end - start) / 2;
		int left = gcd4NumsDividConquerResusive(array, start, mid);
		int right = gcd4NumsDividConquerResusive(array, mid + 1, end);
		return gcdDivideIterative(left, right);
	}

	// too slow
	public static int gcd4Nums(int[] array) {
		if (array == null || array.length == 1)
			return 0;
		int gcd = array[0];
		for (int i = 1; i < array.length; i++) {
			gcd = gcdDivideIterative(gcd, array[i]);
		}
		return gcd;
	}

	/**
	 * When I'm returning a+b, I'm actually returning the non-zero number
	 * assuming one of them is 0.
	 */
	static int gcdDivideRecursive(int a, int b) {
		if (a == 0 || b == 0)
			return a + b; // base case
		return gcdDivideRecursive(b, a % b);
	}

	static int gcdDivideIterative(int a, int b) {
		if (a == 0 || b == 0) {
			return a + b;
		}
		// until either one of them is 0
		while (a != 0 && b != 0) {
			int c = b;
			b = a % b;
			a = c;
		}
		return a + b; // either one is 0, so return the non-zero value
	}

	public static int gcdDivide(int a, int b) {// start of your method
		if (a == 0 || b == 0) {
			return a + b;
		}
		int temp;
		// making a greater than b
		if (b > a) {
			temp = a;
			a = b;
			b = temp;
		}

		while (a != 0 && b != 0) {
			// gcd of b and a%b
			temp = a % b;
			// always make a greater than b
			a = b;
			b = temp;

		}
		return a + b;
	}

	/**
	 * Binary
	 */
	public static int gcdBinary(int p, int q) {
		if (q == 0)
			return p;
		if (p == 0)
			return q;

		// p and q even
		if ((p & 1) == 0 && (q & 1) == 0) {
			return gcdBinary(p >> 1, q >> 1) << 1;
		} else if ((p & 1) == 0) { // p is even, q is odd
			return gcdBinary(p >> 1, q);
		} else if ((q & 1) == 0) { // p is odd, q is even
			return gcdBinary(p, q >> 1);
		} else if (p >= q) { // p and q odd, p >= q
			return gcdBinary((p - q) >> 1, q);
		} else {
			return gcdBinary(p, (q - p) >> 1); // p and q odd, p < q
		}
	}

	/**
	 * 调用类库版本
	 */
	private static int gcdLibrary(int a, int b) {
		BigInteger b1 = BigInteger.valueOf(a);
		BigInteger b2 = BigInteger.valueOf(b);
		BigInteger gcd = b1.gcd(b2);
		return gcd.intValue();
	}

}
