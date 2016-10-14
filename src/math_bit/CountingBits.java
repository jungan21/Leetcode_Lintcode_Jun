package math_bit;

/**
 * Given a non negative integer number num. For every numbers i in the range 0 ≤
 * i ≤ num calculate the number of 1's in their binary representation and return
 * them as an array.
 * 
 * Example:
 * 
 * For num = 5 you should return [0,1,1,2,1,2].
 * 
 * 
 *
 */
public class CountingBits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int[] countBits(int num) {
		int[] result = new int[num + 1];

		for (int i = 0; i <= num; i++) {
			result[i] = countEach(i);
		}

		return result;
	}

	public static int countEach(int num) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if ((num >> i & 1) == 1) {
				count++;
			}
		}
		return count;
	}

	/**
	 * method2 :
	 * http://www.programcreek.com/2015/03/leetcode-counting-bits-java/
	 */
	public int[] countBits2(int num) {
		int[] result = new int[num + 1];

		int p = 1; // p tracks the index for number x
		int pow = 1;
		for (int i = 1; i <= num; i++) {
			if (i == pow) {
				result[i] = 1;
				pow = (pow << 1);
				p = 1;
			} else {
				result[i] = result[p] + 1;
				p++;
			}

		}

		return result;
	}

}
