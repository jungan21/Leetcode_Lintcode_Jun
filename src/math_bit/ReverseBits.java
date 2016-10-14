package math_bit;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 * 
 * For example, given input 43261596 (represented in binary as
 * 00000010100101000001111010011100), return 964176192 (represented in binary as
 * 00111001011110000010100101000000).
 * 
 * Follow up: If this function is called many times, how would you optimize it?
 * 
 */

public class ReverseBits {

	public static void main(String[] args) {
		System.out.println(reverseBits(1));
	}

	public static int reverseBits(int n) {
		int[] bits = new int[32];
		int result = 0;
		for (int i = 0; i < 32; i++) {
			if ((n >> i & 1) == 1) {
				bits[i]++;
				result = result | (bits[i] << (31 - i));
			}
		}
		return result;
	}

	public static int reverseBits1(int n) {
		int result = 0;
		for (int i = 0; i < 32; i++) {
			// 只有某位是1的时候（即n >> i & 1）才去挪位置
			result = result | ((n >> i & 1) << (31 - i));
		}
		return result;
	}
}
