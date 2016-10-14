package binarySearch;

/**
 * 
 * No Duplicate
 * 
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * Notice
 * 
 * You may assume no duplicate exists in the array.
 * 
 */
public class FindMinimumInRotatedSortedArray {

	public static void main(String[] args) {
		int[] nums = { 6, 7, 1, 2, 3, 4, 5 };
		int[] nums1 = { 3, 4, 5, 6, 7, 1, 2 };
		System.out.println(findMin(nums1));
	}

	/**
	 * Note: we just need to find the First position <= Last Number
	 * 
	 * e.g. before rotation, we have [0, 1, 2, 3, 4, 5, 6, 7]
	 * 
	 * rotation 1: [3, 4, 5, 6, 7, 0, 1, 2]
	 * 
	 * rotation 1: [6, 7, 0, 1, 2, 3, 4, 5]
	 * 
	 * 
	 * Note: WRONG: can't use First number in above condition,
	 * 
	 * e.g. [1, 2, 3] (unrotated array is subset of rotated array), if you use
	 * First number, it does't work
	 */
	public static int findMin(int[] nums) {
		if (nums == null || nums.length == 0) {
			// Note: to return -1
			return -1;
		}

		int start = 0, end = nums.length - 1;

		/**
		 * target is the last number in array
		 * 
		 * 用最后一个数做target, after while loop, we will find the rotation point
		 * 
		 * i.e. nums[start] is the largest number, nums[end] is the smallest
		 * number
		 */

		/**
		 * Note: you have to use the last number as target [2,1] (if you use
		 * nums[0] as target), this will return wrong result, because, in a
		 * rotated array, usually nums[0] < nums[target]
		 */
		int target = nums[nums.length - 1];
		// find the first element <= target
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] <= target) {
				end = mid;
			} else {
				start = mid;
			}
		}

		// because our condition (start + 1) < end, we check start first
		if (nums[start] <= target) {
			return nums[start];
		} else {
			return nums[end];
		}
	}

	/**
	 * 强烈不推荐 recursively Define a helper function, otherwise, we will need to
	 * use Arrays.copyOfRange() function, which may be expensive for large
	 * arrays.
	 */

	public int findMin2(int[] num) {
		return findMin(num, 0, num.length - 1);
	}

	public int findMin(int[] num, int left, int right) {
		if (left == right)
			return num[left];
		if ((right - left) == 1)
			return Math.min(num[left], num[right]);

		int middle = left + (right - left) / 2;

		// not rotated
		if (num[left] < num[right]) {
			return num[left];
			// go right side
		} else if (num[middle] > num[left]) {
			return findMin(num, middle, right);
			// go left side
		} else {
			return findMin(num, left, middle);
		}
	}

}
