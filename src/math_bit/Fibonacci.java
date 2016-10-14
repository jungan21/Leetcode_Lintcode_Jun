package math_bit;

public class Fibonacci {

	public static void main(String[] args) {
		System.out.println(fibonacciNonRecursive(4));
	}

	// 0、1、1、2、3、5、8、13、21
	public static int fibonacci(int n) { // 此算法在N=40时出现明显卡顿
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public static long fibonacciNonRecursive(int n) {
		if (n < 0) {
			// error condition
			return -1;
		}
		if (n == 0)
			return 0;

		if (n == 1)
			return 1;

		int n1 = 0, n2 = 1;
		int sn = 0;
		for (int i = 2; i <= n; i++) {
			sn = n1 + n2;
			n1 = n2;
			n2 = sn;
		}
		return sn;
	}
}
