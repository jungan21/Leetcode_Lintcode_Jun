package binarySearch;

public class ClosestNumberinSortedArray {

	public static void main(String[] args) {
		int[] A = { 3, 4, 6, 8 };
		closestNumber(A, 2);
	}

	/**
	 * http://www.jiuzhang.com/solutions/closest-number-in-sorted-array/
	 */
	public static int closestNumber(int[] A, int target) {
		if (A == null || A.length == 0) {
			return -1;
		}

		// return first index that >= target
		int index = firstIndex(A, target);

		if (index == 0) {
			return 0;
		}
		if (index == A.length) {
			return A.length - 1;
		}

		// index is the first number >= target, index 后面的数字肯定不会比index的数字更close
		// to target，所以只要和index-1前面的开始比较
		if (target - A[index - 1] < A[index] - target) {
			return index - 1;
		}
		return index;
	}

	// find first number >= target, similar to SearchInsertPosition
	private static int firstIndex(int[] A, int target) {
		int start = 0, end = A.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (A[mid] < target) {
				start = mid;
			} else if (A[mid] >= target) {
				end = mid;
			}
		}

		if (A[start] >= target) {
			return start;
		}
		if (A[end] >= target) {
			return end;
		}
		// which means all number in array < target
		return A.length;
	}

	/**
	 * method 2: Jun's
	 */

	public static int closestNumberJun(int[] A, int target) {
		if (A == null || A.length == 0) {
			return -1;
		}

		int start = 0;
		int end = A.length - 1;

		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (A[mid] > target) {
				end = mid;
			} else if (A[mid] < target) {
				start = mid;
			} else {
				return mid;
			}
		}
		int diff1 = Math.abs(A[start] - target);
		int diff2 = Math.abs(A[end] - target);
		if (diff1 < diff2) {
			return start;
		} else {
			return end;
		}
	}
}
