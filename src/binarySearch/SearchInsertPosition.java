package binarySearch;

/**
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order. You may assume no duplicates in the array. Here are few examples.
 * [1,3,5,6], 5 → 2 ; [1,3,5,6], 2 → 1; [1,3,5,6], 7 → 4 ; [1,3,5,6], 0 → 0
 * 
 * http://www.jiuzhang.com/solutions/search-insert-position/
 *
 */
public class SearchInsertPosition {

	public static void main(String[] args) {
		int[] A = { 1, 3, 5, 6 };
		System.out.println(searchInsert(A, 2));
	}

	/**
	 * version 1: find the first position >= target
	 * 
	 * [1,3,5,6], insert 5, we need to put 5 in position 2 (i.e. current
	 * position of 5, 5 == 2)
	 * 
	 * [1,3,5,6], insert 2, we need to put 2 in position 1 (i.e. current
	 * position of 3, 3 > 2)
	 * 
	 * ==> so we need to find the position of the number that >= target
	 */
	public static int searchInsert(int[] A, int target) {
		if (A == null || A.length == 0) {
			// i.e insert in the very first position
			return 0;
		}
		int start = 0, end = A.length - 1;

		while (start + 1 < end) {
			// mid = start + ((end - start) >> 1)
			int mid = start + (end - start) / 2;
			if (A[mid] == target) {
				return mid;
			} else if (A[mid] < target) {
				start = mid;
			} else {
				end = mid;
			}
		}

		// NOTE: find first position of the number that >= target
		if (A[start] >= target) {
			return start;
		} else if (A[end] >= target) {
			return end;
		}
		/**
		 * NOTE [1,3,5,6], insert 7, we can't find the number that >=7, so we
		 * just put at the very end. (i.e. after while loop, end points to the
		 * last number which has index 3, we need to put 7 after that )
		 */
		return A.length;
	}

	// version 2: find the last position < target, return +1，
	public static int searchInsert2(int[] A, int target) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int start = 0;
		int end = A.length - 1;
		int mid;
		// 要特判一下target小于所有数组里面的元素
		if (target < A[0]) {
			return 0;
		}
		// find the last number less than target
		while (start + 1 < end) {
			mid = start + (end - start) / 2;
			if (A[mid] == target) {
				return mid;
			} else if (A[mid] < target) {
				start = mid;
			} else {
				end = mid;
			}
		}

		if (A[end] == target) {
			return end;
		}
		if (A[end] < target) {
			return end + 1;
		}
		if (A[start] == target) {
			return start;
		}
		return start + 1;
	}

	/**
	 * method 3
	 */
	public int searchInsert3(int[] A, int target) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int start = 0, end = A.length - 1;

		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (A[mid] == target) {
				return mid;
			} else if (A[mid] < target) {
				start = mid;
			} else {
				end = mid;
			}
		}

		if (A[start] == target) {
			return start;
		}
		if (A[end] == target) {
			return end;
		}
		// start, end中间
		if (A[start] < target && target < A[end]) {
			return start + 1;
		}
		// end 右边
		if (A[end] < target) {
			return end + 1;
		}
		// start 左边
		if (A[start] > target && start > 0) {
			return start - 1;
		}
		// 插在首位
		return 0;
	}
}
