package math_bit;

public class AddBinary {

	public static void main(String[] args) {
		String a = "1";
		String b = "111";
		System.out.println(addBinary(a, b));
	}

	public static String addBinary(String a, String b) {

		int lenA = a.length();
		int lenB = b.length();
		int i = lenA - 1;
		int j = lenB - 1;

		int carry = 0;
		String result = "";
		while (i >= 0 && j >= 0) {
			// learn how to convert char to intt
			int sum = (int) (a.charAt(i) - '0') + (int) (b.charAt(j) - '0')
					+ carry;
			result = String.valueOf(sum % 2) + result;
			carry = sum / 2;
			i--;
			j--;
		}

		while (i >= 0) {
			int sum = (int) (a.charAt(i) - '0') + carry;
			result = String.valueOf(sum % 2) + result;
			carry = sum / 2;
			i--;
		}
		while (j >= 0) {
			int sum = (int) (b.charAt(j) - '0') + carry;
			result = String.valueOf(sum % 2) + result;
			carry = sum / 2;
			j--;
		}
		if (carry == 1) {
			result = "1" + result;
		}
		return result;
	}

}
