package math_bit;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * 
 * Note: Your solution should be in logarithmic time complexity.
 * 
 * 
 * http://www.danielbit.com/blog/puzzle/leetcode/leetcode-factorial-trailing-zeroes
 *
 */

/**
 * 
 * Best one : http://www.cnblogs.com/EdwardLiu/p/4207498.html
 * 
 * O（logn）解法：
 * 
 * 一个更聪明的解法是：考虑n!的质数因子。后缀0总是由质因子2和质因子5相乘得来的。如果我们可以计数2和5的个数，问题就解决了。考虑下面的例子：
 * 
 * n = 5: 5!的质因子中 (2 * 2 * 2 * 3 * 5)包含一个5和三个2。因而后缀0的个数是1。
 * 
 * n = 11: 11!的质因子中(2^8 * 3^4 * 5^2 * 7)包含两个5和三个2。于是后缀0的个数就是2。
 * 
 * 我们很容易观察到质因子中2的个数总是大于等于5的个数。因此只要计数5的个数就可以了
 * 
 */
public class TrailingZeros {

	public static void main(String[] args) {
		System.out.println(trailingZeros(125));
	}

	public static long trailingZeros(long n) {
		long result = 0;
		while (n != 0) {
			result += n / 5;
			n /= 5;
		}
		return result;
	}

}
