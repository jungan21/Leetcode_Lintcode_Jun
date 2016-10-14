package binarySearch;

// has duplicate number
public class FindMinimumInRotatedSortedArray2 {

	public static void main(String[] args) {
		int[] nums = { 6, 7, 1, 2, 3, 4, 5 };
		int[] nums1 = { 3, 4, 5, 6, 7, 1, 2 };
		System.out.println(findMin2(nums1));
	}

	public static int findMin(int[] num) {
		// 这道题目在面试中不会让写完整的程序
		// 只需要知道最坏情况下 [1,1,1....,1] 里有一个0
		// 这种情况使得时间复杂度必须是 O(n) (读了下面的另一种方法，就明白为什么 O(n)了， 因为 end--一直要执行))
		// 因此写一个for循环就好了。
		// 如果你觉得，不是每个情况都是最坏情况，你想用二分法解决不是最坏情况的情况，那你就写一个二分吧。
		// 反正面试考的不是你在这个题上会不会用二分法。这个题的考点是你想不想得到最坏情况。
		int min = num[0];
		for (int i = 1; i < num.length; i++) {
			if (num[i] < min)
				min = num[i];
		}
		return min;
	}

	// binary-search method
	public static int findMin2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}

		int start = 0, end = nums.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] == nums[end]) {
				// if mid equals to end, that means it's fine to remove end
				// the smallest element won't be removed
				end--;
			} else if (nums[mid] < nums[end]) {
				end = mid;
				// of course you can merge == & <
			} else {
				start = mid;
				// or start = mid + 1
			}
		}

		if (nums[start] <= nums[end]) {
			return nums[start];
		}
		return nums[end];
	}
}
