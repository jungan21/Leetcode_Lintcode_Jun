package math_bit;

public class SwapOddAndEvenBits {

	public static void main(String[] args) {
		System.out.println(numInstructions(23));
		 
		System.out.println(Integer.parseInt("AAAAAAAA",16));
	}

	public static int numInstructions(int num) {
		// write implementation here.
		return ((num & 0xAAAAAAAA) >> 1) | ((num & 0x55555555) << 1);
	}

	private static int swapBits(int x) {
		// Get all even bits of x
		// AAAAAAAA = 10101010101010101010101010101010
		int even_bits = x & 0xAAAAAAAA;

		// Get all odd bits of x
		int odd_bits = x & 0x55555555;
		// 55555555 = 01010101010101010101010101010101
		even_bits >>= 1; // Right shift even bits
		odd_bits <<= 1; // Left shift odd bits

		return (even_bits | odd_bits); // Combine even and odd bits
	}
}
