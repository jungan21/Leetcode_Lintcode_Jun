package math_bit;

import java.util.Stack;

/**
 * Given a non-negative number represented as an array of digits, plus one to
 * the number.
 * 
 * The digits are stored such that the most significant digit is at the head of
 * the list.
 * 
 *
 */
public class PlusOne {

	public static void main(String[] args) {
		int[] A = { 1, 0 };

		System.out.println(plusOne(A));
	}

	// Best one: jiuzhang, no extra stack
	public static int[] plusOneJiuzhang(int[] digits) {
		if (digits == null || digits.length == 0) {
			return new int[0];
		}
		int carry = 1;
		for (int i = digits.length - 1; i >= 0; i--) {
			carry = carry + digits[i];
			if (carry >= 10) {
				digits[i] = (carry % 10);
				carry = carry / 10;
			} else {
				digits[i] = carry;
				carry = 0;
			}
		}

		if (carry == 0) {
			return digits;
		} else {
			int[] result = new int[digits.length + 1];
			result[0] = carry;
			for (int i = 1; i < result.length; i++) {
				result[i] = digits[i - 1];
			}
			return result;
		}
	}

	// jiuzhang 结合 Jun
	public static int[] plusOne(int[] digits) {
		if (digits == null || digits.length == 0) {
			return new int[0];
		}
		Stack<Integer> stack = new Stack<Integer>();
		int carry = 1;
		for (int i = digits.length - 1; i >= 0; i--) {
			carry = carry + digits[i];
			if (carry >= 10) {
				stack.push(carry % 10);
				carry = carry / 10;
			} else {
				stack.push(carry);
				carry = 0;
			}
		}

		if (carry != 0) {
			stack.push(carry);
		}
		int size = stack.size();
		int[] result = new int[size];
		for (int i = 0; i < size; i++) {
			result[i] = stack.pop();
		}
		return result;
	}

	// Jun's old way
	public static int[] plusOneJun(int[] digits) {
		if (digits == null || digits.length == 0) {
			return new int[0];
		}

		Stack<Integer> stack = new Stack<Integer>();

		int carry = 0;
		for (int i = digits.length - 1; i >= 0; i--) {
			if (i == digits.length - 1) {
				carry = carry + digits[i] + 1;
				if (carry >= 10) {
					stack.push(carry % 10);
					carry = carry / 10;
				} else {
					stack.push(carry);
					carry = 0;
				}
			} else {
				carry = carry + digits[i];
				if (carry >= 10) {
					stack.push(carry % 10);
					carry = carry / 10;
				} else {
					stack.push(carry);
					carry = 0;
				}
			}
		}

		if (carry != 0) {
			stack.push(carry);
		}
		// 必须把size先取出来，否则，每次pop后，这个stack.size()大小会变
		int size = stack.size();
		int[] result = new int[size];
		for (int i = 0; i < size; i++) {
			result[i] = stack.pop();
		}
		return result;
	}
}
