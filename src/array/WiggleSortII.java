package array;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it such that
 * 
 * nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * Example
 * 
 * Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * 
 * Note: 不可以用Wiggle Sort I中的算法，如 [4,5,5,6] ==> 4,5,5, 6
 */
public class WiggleSortII {

	public static void main(String[] args) {
		int[] A = { 4, 5, 5, 6 };
		wiggleSortJun(A);
	}

	public static void wiggleSortJun(int[] nums) {
		int[] tem = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			tem[i] = nums[i];
		}
		// 返回 第 （nums.length / 2）大的数字, quiickSelect 方法用的是index, 所以减1
		int mid = quickSelect(tem, 0, nums.length - 1, (nums.length / 2) - 1);
		System.out.println(mid);
		int[] ans = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			ans[i] = mid;
		}
		int l, r;
		if (nums.length % 2 == 0) {
			l = nums.length - 2;
			r = 1;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] < mid) {
					ans[l] = nums[i];
					l -= 2;
				} else if (nums[i] > mid) {
					ans[r] = nums[i];
					r += 2;
				}
			}
		} else {
			l = 0;
			r = nums.length - 2;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] < mid) {
					ans[l] = nums[i];
					l += 2;
				} else if (nums[i] > mid) {
					ans[r] = nums[i];
					r -= 2;
				}
			}
			System.out.println("l: " + l);
			System.out.println("r: " + r);
		}
		for (int i = 0; i < nums.length; i++) {
			nums[i] = ans[i];
		}
		System.out.println(Arrays.toString(nums));
	}

	// refer to KthLargestElement.java 里的quick select,
	// 既然是找第k的的数字，就按逆序排，这里传入的k是index
	public static int quickSelect(int[] nums, int start, int end, int k) {
		int left = start;
		int right = end;
		int mid = left + (right - left) / 2;
		int pivot = nums[mid];

		while (left <= right) {
			while (left <= right && nums[left] > pivot) {
				left++;
			}
			while (left <= right && nums[right] < pivot) {
				right--;
			}
			if (left <= right) {
				int temp = nums[left];
				nums[left] = nums[right];
				nums[right] = temp;
				left++;
				right--;
			}
		}// end while
		if (start < right && k <= right) {
			quickSelect(nums, start, right, k);
		}
		if (left < end && k >= left) {
			quickSelect(nums, left, end, k);
		}
		return nums[k];
	}// end method

	/**
	 * jiuzhang
	 */
	public static void wiggleSort(int[] nums) {
		int[] tem = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			tem[i] = nums[i];
		}
		// 返回 第 （nums.length / 2）大的数字
		int mid = partition(tem, 0, nums.length - 1, nums.length / 2);
		System.out.println(mid);
		int[] ans = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			ans[i] = mid;
		}
		int l, r;
		if (nums.length % 2 == 0) {
			l = nums.length - 2;
			r = 1;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] < mid) {
					ans[l] = nums[i];
					l -= 2;
				} else if (nums[i] > mid) {
					ans[r] = nums[i];
					r += 2;
				}
			}
		} else {
			l = 0;
			r = nums.length - 2;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] < mid) {
					ans[l] = nums[i];
					l += 2;
				} else if (nums[i] > mid) {
					ans[r] = nums[i];
					r -= 2;
				}
			}
		}
		for (int i = 0; i < nums.length; i++) {
			nums[i] = ans[i];
		}
	}

	// 返回 第 rank 大的数字
	public static int partition(int[] nums, int l, int r, int rank) {
		int left = l, right = r;
		int now = nums[left];
		while (left < right) {
			while (left < right && nums[right] >= now) {
				right--;
			}
			nums[left] = nums[right];
			while (left < right && nums[left] <= now) {
				left++;
			}
			nums[right] = nums[left];
		}
		if (left - l == rank) {
			return now;
		} else if (left - l < rank) {
			return partition(nums, left + 1, r, rank - (left - l + 1));
		} else {
			return partition(nums, l, right - 1, rank);
		}
	}
}
