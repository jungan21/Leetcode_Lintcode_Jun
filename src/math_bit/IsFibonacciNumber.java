package math_bit;

/**
 * Check if a given number is Fibonacci number. Fibonacci number is a number
 * which occurs in Fibonacci series.
 * 
 * Input: The first line of input contains an integer T denoting the number of
 * test cases. Each test case contains a number.
 * 
 * Output: Print "Yes" if the entered number is Fibonacci number else "No".
 * 
 * Constraints: 1<=t<=100 1<=n<=100
 * 
 * Example:
 * 
 * Input 2 34 41
 * 
 * Output Yes No
 *
 */
public class IsFibonacciNumber {

	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			if (isFibonacci2(i)) {
				System.out.println(i + " is a Fibonacci Number");
			} else {
				System.out.println(i + " is NOT a Fibonacci Number");
			}
		}
	}

	/**
	 * http://hubpages.com/education/How-to-Tell-if-a-Number-is-a-Fibonacci-
	 * Number
	 */
	private static boolean isFibonacci(int n) {
		// n is Fibinacci if one of 5*n*n + 4 or 5*n*n - 4 or both
		// is a perferct square
		return isPerfectSquare(5 * n * n + 4) || isPerfectSquare(5 * n * n - 4);
	}

	private static boolean isPerfectSquare(int x) {
		int s = (int) Math.sqrt(x);
		return (s * s == x);
	}

	/**
	 * method 2
	 * http://www.guideforschool.com/1905171-java-program-to-check-if-a-
	 * number-is-in-fibonacci-series-or-not/
	 * 
	 * Fibonacci序列： 0、1、1、2、3、5、8、13、21
	 */

	private static boolean isFibonacci2(int n) {
		int a = 0, b = 1, c = 0;
		/*
		 * 'a' is the 1st term, 'b' is the 2nd term and 'c' is the 3rd term 'c'
		 * stores the last generated term of the Fibonacci series
		 */

		while (c < n) // Loop goes on till the 3rd term is less than the given
		{
			c = a + b; // Generating the terms of Fibonacci Series
			a = b;
			b = c;
		}

		/*
		 * When the control comes out of the while loop, either the 3rd term is
		 * equal to the number or greater than it
		 */
		// If the last term = number, then it belongs to Fibonacci Series
		if (c == n) {
			return true;
		} else {
			return false;
		}
	}
}
