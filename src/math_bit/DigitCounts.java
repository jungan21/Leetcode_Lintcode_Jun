package math_bit;

public class DigitCounts {

	public static void main(String[] args) {
		System.out.println(digitCounts(1, 12));
	}

	public static int digitCounts(int k, int n) {
		int count = 0;
		for (int i = 0; i < n + 1; i++) {
			count += count4EachNum(k, i);
		}
		return count;
	}

	public static int count4EachNum(int k, int n) {
		if (n == 0 && k == 0) {
			return 1;
		}

		int count = 0;
		while (n > 0) {
			int mod = n % 10;
			if (mod == k) {
				count++;
			}
			n /= 10;
		}
		return count;
	}
}
