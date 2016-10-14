package longest_minimum;

/**
 * Given an array of numbers, return the longest substring in which the
 * difference between every consecutive number pair varies difference in sign
 * (I.e. +-+-)
 * 
 *
 */
public class LongestSubstringwithConsecutiveNumberinDifferentSign {

	public static void main(String[] args) {
		int[] A = { +2, -3, +4, -3, -5, +6, -7, +8, -9 };
		System.out.println(longSubString(A));
	}
	/**
	 * Wrong!!!
	 */
	public static String longSubString(int[] A) {

		StringBuffer sb = new StringBuffer();
		int j = 0;
		int maxLen = 0;
		String result = "";
		for (int i = 0; i < A.length - 1; i++) {
			while (j < A.length
					&& ((j == 0 && (A[j] * A[j + 1]) < 0) || (j > 0
							&& A[j - 1] * A[j] < 0 && A[j] * A[j + 1] < 0))) {
				sb.append(A[j]);

				if ((j - i + 1) > maxLen) {
					maxLen = (j - i + 1);
					result = sb.toString();
				}
				j++;
			}
			sb.deleteCharAt(0);
		}

		return result;

	}
}
