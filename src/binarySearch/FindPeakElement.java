package binarySearch;

/**
 * 
 There is an integer array which has the following features:
 * 
 * The numbers in adjacent positions are different. A[0] < A[1] && A[A.length -
 * 2] > A[A.length - 1].
 * 
 * We define a position P is a peek if:
 * 
 * A[P] > A[P-1] && A[P] > A[P+1]
 * 
 * Find a peak element in this array. Return the index of the peak. Notice
 * 
 * The array may contains multiple peeks, find any of them.
 * 
 *
 */
public class FindPeakElement {

	public static void main(String[] args) {
		int[] A = { 1, 2, 6, 15, 14, 16 };
		System.out.println(findPeak(A));
	}

	/**
	 * http://www.jiuzhang.com/solutions/find-peak-element/
	 * 
	 * you can draw stock curve, to help you understand the situation when M
	 * (mid) is put in different position
	 *
	 */
	public static int findPeak(int[] A) {
		// write your code here
		int start = 1, end = A.length - 2; // 1.答案在之间，2.不会出界 3.下面用到A[mid - 1]
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (A[mid] < A[mid - 1]) {
				end = mid;
			} else if (A[mid] < A[mid + 1]) {
				start = mid;
			} else {
				/**
				 * you can also use start = mid; which means you can ignore any
				 * half, there must be a peak element in either half
				 * side.也可以用下面三句，因为 mid处就是峰值
				 * 
				 * start = mid;
				 * 
				 * end = mid;
				 * 
				 * break;
				 * 
				 */
				end = mid;
				// 这里也可以直接返回 mid, 因为题目条件： numbers in adjacent positions are
				// different， 能运行到这里，就表示 A[mid] > A[mid-1] && A[mid] > A[mid+1]

			}
		}
		if (A[start] < A[end]) {
			return end;
		} else {
			return start;
		}
	}
}
