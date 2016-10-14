package Recursive;

public class Fibonacci {

	public static void main(String[] args) {
		System.out.println(fiboIterative(2));
	}

	// recursive
	public static int fibo(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else if (n > 1) {
			return fibo(n - 1) + fibo(n - 2);
		} else {
			return -1;
		}
	}

	// Iterative
	public static int fiboIterative(int n) {
		if (n < 0) {
			return -1;
		}
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}

		int a = 0;
		int b = 1;
		for (int i = 2; i <= n; i++) {
			int c = a + b;
			a = b;
			b = c;
		}
		return b;
	}
}
