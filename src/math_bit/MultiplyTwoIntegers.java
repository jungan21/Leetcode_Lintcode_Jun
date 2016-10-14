package math_bit;

public class MultiplyTwoIntegers {

	public static void main(String[] args) {
		System.out.println(FnTimes(3, 4));
	}

	// Multiply a by b by adding a to itself b times
	public static int FnTimes(int a, int b) {
		if (a < b)
			return FnTimes(b, a); // algo is faster if b < a
		int result = 0;
		for (int iter = 0; iter < Math.abs(b); iter++) {
			result += a;
		}
		if (b < 0)
			result = FnNegate(result);
		return result;
	}

	// Flip a positive sign to negative, or a negative sign to pos
	public static int FnNegate(int a) {
		int neg = 0;
		int d = a < 0 ? 1 : -1;
		while (a != 0) {
			neg += d;
			a += d;
		}
		return neg;
	}
}
