package math_bit;

/**
 * Convert a non-negative integer to its english words representation. Given
 * input is guaranteed to be less than 231 - 1.
 * 
 * For example, 123 -> "One Hundred Twenty Three"
 * 
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 
 * 1234567
 * ->"One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 
 * Hint:
 * 
 * 1. Did you see a pattern in dividing the number into chunk of words? For
 * example, 123 and 123000.
 * 
 * 2. Group the number by thousands (3 digits). You can write a helper function
 * that takes a number less than 1000 and convert just that chunk to words.
 * 
 * 3There are many edge cases. What are some good test cases? Does your code
 * work with input such as 0? Or 1000010? (middle chunk is zero and should not
 * be printed out)
 * 
 *
 */
public class IntegerToEnglishWrods {

	public static void main(String[] args) {
		System.out.print(numberToWords(1234567));
	}

	public static String numberToWords(int num) {
		if (num == 0) {
			return "Zero";
		}
		//先把末尾三位数字装换成english
		String result = convertHundred(num % 1000);
		String[] arr = { "Thousand", "Million", "Billion" };
		for (int i = 0; i < 3; i++) {
			num /= 1000;
			result = num % 1000 != 0 ? convertHundred(num % 1000) + " "
					+ arr[i] + " " + result : result;
		}

		/**
		 * 不能省，否则如果输入时1000，==> "One Thousand ", 后面多了一个空格
		 */
		result = result.trim();
		return result.length() == 0 ? "Zero" : result;

	}

	public static String convertHundred(int num) {
		String result;
		String[] arr1 = { "", "One", "Two", "Three", "Four", "Five", "Six",
				"Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
				"Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
				"Eighteen", "Nineteen" };
		String[] arr2 = { "", "", "Twenty", "Thirty", "Forty", "Fifty",
				"Sixty", "Seventy", "Eighty", "Ninety" };
		int a = num / 100, b = num % 100, c = num % 10;
		result = b < 20 ? arr1[b] : arr2[b / 10]
				+ (c != 0 ? " " + arr1[c] : "");
		if (a > 0) {
			result = arr1[a] + " Hundred" + (b != 0 ? " " + result : "");
		}

		return result;

	}
}
