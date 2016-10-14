package HackerRank;

import java.util.Scanner;

/*
 * Use a long to store the value and then you can XOR it with 0xffffffffL (11111111111111111111111111111111 in binary). That will give you the correct answer.

 For example:

 00000000000000000000000000000001

 11111111111111111111111111111111 ^

 --------------------------------

 11111111111111111111111111111110
 */
public class FlippingBits {
	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		int nums = sc.nextInt();
		for (int i = 0; i < nums; i++) {
			System.out.println(flibBits(sc.nextLong()));
		}
	}

	public static long flibBits(long n) {
		return n ^ 0xffffffffL;
	}
}
