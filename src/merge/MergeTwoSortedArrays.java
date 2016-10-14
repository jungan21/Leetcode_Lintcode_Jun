package merge;

/**
 * Merge two given sorted integer array A and B into a new sorted integer array.
 * 
 * How can you optimize your algorithm if one array is very large and the other
 * is very small?
 */
public class MergeTwoSortedArrays {

	public static void main(String[] args) {
		int[] A = { 1, 4, 5 };
		int[] B = { 2 };
		mergeSortedArray(A, B);
	}

	public static int[] mergeSortedArray(int[] A, int[] B) {
		if (A == null || A.length == 0) {
			return B;
		}
		if (B == null || B.length == 0) {
			return A;
		}

		int lenA = A.length;
		int lenB = B.length;
		int[] result = new int[lenA + lenB];

		int i = 0;
		int j = 0;
		int k = 0;
		while (i < lenA && j < lenB) {
			if (A[i] <= B[j]) {
				result[k++] = A[i++];
				// Note: else if 两个if不能都被执行， 只能有一个被执行
			} else if (A[i] > B[j]) {
				result[k++] = B[j++];
			}
		}

		while (i < lenA) {
			result[k++] = A[i++];
		}
		while (j < lenB) {
			result[k++] = B[j++];
		}
		return result;
	}

}
