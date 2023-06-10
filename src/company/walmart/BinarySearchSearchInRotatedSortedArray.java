package company.walmart;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). You are given a target
 * value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * 
 */
public class BinarySearchSearchInRotatedSortedArray {

	public static void main(String[] args) {
		int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(search(nums, 1));
	}

	// http://www.jiuzhang.com/solutions/search-in-rotated-sorted-array/

	public static int search(int[] A, int target) {
		if (A == null || A.length == 0) {
			return -1;
		}

		int start = 0;
		int end = A.length - 1;
		int mid;

		while (start + 1 < end) {
			mid = start + (end - start) / 2;
			// Note: 最好加上，节省时间
			if (A[mid] == target) {
				return mid;
			}
			// 表示二分线，在左边上升区间找
			// e.g: 4 5 6 7 0 1 2 3
			if (A[start] < A[mid]) {
				// in this case, the left half side is sorted
				if (A[start] <= target && target <= A[mid]) {
					end = mid;
				} else {
					start = mid;
				}
			} else {
				// 表示二分线，在右下角的上升区间找
				// // e.g: 6 7 0 1 2 3 4 5
				if (A[mid] <= target && target <= A[end]) {
					start = mid;
				} else {
					end = mid;
				}
			}
		} // while

		if (A[start] == target) {
			return start;
		}
		if (A[end] == target) {
			return end;
		}
		return -1;
	}

	/**
	 * 强烈不推荐 recursively method: 类似 binary search
	 */
	public static int search1(int[] nums, int target) {
		return binarySearch(nums, 0, nums.length - 1, target);
	}

	// rewrite: Arrays.binarySearch method
	public static int binarySearch(int[] nums, int left, int right, int target) {
		if (left > right)
			return -1;

		int mid = left + (right - left) / 2;

		if (target == nums[mid])
			return mid;

		if (nums[left] <= nums[mid]) {
			if (nums[left] <= target && target < nums[mid]) {
				return binarySearch(nums, left, mid - 1, target);
			} else {
				return binarySearch(nums, mid + 1, right, target);
			}
		} else {
			if (nums[mid] < target && target <= nums[right]) {
				return binarySearch(nums, mid + 1, right, target);
			} else {
				return binarySearch(nums, left, mid - 1, target);
			}
		}
	}

}
