package math_bit;

public class PalindromeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPalindrome(1221));
	}

	// http://www.programcreek.com/2013/02/leetcode-palindrome-number-java/
	public static boolean isPalindrome(int x) {
		// negative numbers are not palindrome
		if (x < 0)
			return false;

		// initialize how many zeros
		// e.g. if x= 11211, then div=10000
		int div = 1;
		// 这样写会越界： while (div * 10 <= x) {
		// 只能像下面这样写
		while (x / div >= 10) {
			div = div * 10;
		}

		while (x > 0) {
			int left = x / div;
			int right = x % 10;

			if (left != right)
				return false;

			x = (x % div) / 10;
			div /= 100;
		}

		return true;
	}

}
