package math_bit;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 *
 * Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 */
public class FirstMissingPositive {

	public static void main(String[] args) {
		//int[] A = { 3, 4, -1, 1 };
		//int[] A = { 1, 2, 0 };
		int[] A = {3 };
		System.out.println(firstMissingPositive(A));
	}

	// http://www.cnblogs.com/yuzhangcmu/p/4200096.html#3455270
	// http://www.cnblogs.com/AnnieKim/archive/2013/04/21/3034631.html
	public static int firstMissingPositive(int[] A) {
		// bug 3: when length is 0, return 1;
		if (A == null) {
			return 0;
		}

		for (int i = 0; i < A.length; i++) {
			// 1: A[i] is in the range;
			// 2: A[i] > 0.
			// 3: The target is different;

			// 假设 A[2] = 2, A[A[2]-1] =A[1], 2在有序数组里的位置应该是1
			// (如： 1, 2, 3, 下标风别为0, 1, 2)
			while ((A[i] - 1) < A.length && (A[i] - 1) >= 0
					&& A[A[i] - 1] != A[i]) {
				swap(A, i, A[i] - 1);
			}
		}

		for (int i = 0; i < A.length; i++) {
			if (A[i] != i + 1) {
				return i + 1;
			}
		}
		// inptut {1} return 2;
		// inptut {3} return 1;
		return A.length + 1;
	}

	public static void swap(int[] A, int l, int r) {
		int tmp = A[l];
		A[l] = A[r];
		A[r] = tmp;
	}

}
