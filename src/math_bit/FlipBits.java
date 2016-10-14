package math_bit;

/**
 * Determine the number of bits required to flip if you want to convert integer
 * n to integer m.
 * 
 * Notice
 * 
 * Both n and m are 32-bit integers.
 * 
 * Example Given n = 31 (11111), m = 14 (01110), return 2.
 *
 */
public class FlipBits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int bitSwapRequired(int a, int b) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if (((a >> i & 1) ^ (b >> i & 1)) == 1) {
				count++;
			}
		}
		return count;
	}

}
