package twopoints;

public class InterleavingPositiveandNegativeNumbers {

	public static void main(String[] args) {
		int[] A = { -33, 19, 30, -26, -21, -9 };
		//int[] A = { -33, 19, 30, 26, 21};
		rerange(A);
	}

	// https://lefttree.gitbooks.io/leetcode/content/twoPointer/interleavingPositiveAndNegativeNum.html
	// https://segmentfault.com/a/1190000004868995
	public static int[] rerange(int[] A) {
		// write your code here
		int posNum = 0, negNum = 0;
		for (int elem : A) {
			if (elem < 0) {
				negNum++;
			} else {
				posNum++;
			}
		}
		/**
		 * 先找出正数和负数哪个多，多的那个先开始 先设定好两个指针，一个代表正数的，一个代表负数的，每次往前走2个
		 * 如果正数多，那么正数指针从0开始，负数指针从1开始 找到正数指针位置第一个为负的，负数指针位置第一个为正的，swap, 直到走到最后
		 */
		int posInd = 1, negInd = 0;
		if (posNum > negNum) {
			negInd = 1;
			posInd = 0;
		}

		while (posInd < A.length && negInd < A.length) {
			while (posInd < A.length && A[posInd] > 0) {
				posInd += 2;
			}
			while (negInd < A.length && A[negInd] < 0) {
				negInd += 2;
			}
			if (posInd < A.length && negInd < A.length) {
				swap(A, posInd, negInd);
				posInd += 2;
				negInd += 2;
			}
		}
		return A;
	}

	public static void swap(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

}
