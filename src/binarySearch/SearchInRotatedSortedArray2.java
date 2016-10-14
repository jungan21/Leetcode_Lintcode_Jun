package binarySearch;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). You are given a target
 * value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * 
 */
public class SearchInRotatedSortedArray2 {

	public static void main(String[] args) {
		int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(search(nums, 1));
	}

	// http://www.jiuzhang.com/solutions/search-in-rotated-sorted-array-ii/

	// 这个问题在面试中不会让实现完整程序
	// 只需要举出能够最坏情况的数据是 [1,1,1,1... 1] 里有一个0即可。
	// 在这种情况下是无法使用二分法的，复杂度是O(n)
	// 因此写个for循环最坏也是O(n)，那就写个for循环就好了
	// 如果你觉得，不是每个情况都是最坏情况，你想用二分法解决不是最坏情况的情况，那你就写一个二分吧。
	// 反正面试考的不是你在这个题上会不会用二分法。这个题的考点是你想不想得到最坏情况。
	public static boolean search(int[] A, int target) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] == target) {
				return true;
			}
		}
		return false;
	}

	public boolean search1(int[] A, int target) {
		if (A == null || A.length == 0) {
			return false;
		}

		int start = 0;
		int end = A.length - 1;
		int mid;

		while (start + 1 < end) {
			mid = start + (end - start) / 2;
			if (A[mid] == target) {
				return true;
			}
			if (A[start] < A[mid]) {
				if (A[start] <= target && target <= A[mid]) {
					end = mid;
				} else {
					start = mid;
				}
			} else if (A[start] > A[mid]) {
				if (A[mid] <= target && target <= A[end]) {
					start = mid;
				} else {
					end = mid;
				}
				// 处理重复
			} else if (A[start] == A[mid]) {
				start++;
			}
		}

		if (A[start] == target) {
			return true;
		}
		if (A[end] == target) {
			return true;
		}
		return false;
	}
}
