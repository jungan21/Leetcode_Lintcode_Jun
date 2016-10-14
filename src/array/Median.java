package array;

/**
 * 
 * Given a unsorted array with integers, find the median of it.
 * 
 * A median is the middle number of the array after it is sorted.
 * 
 * If there are even numbers in the array, return the N/2-th number after
 * sorted.
 * 
 * 
 * Given [4, 5, 1, 2, 3], return 3.
 * 
 * Given [7, 9, 4, 5], return 5.
 * 
 *
 */
public class Median {

	public static void main(String[] args) {
		int[] nums = { 4, 5, 1, 2, 3 };
		System.out.println(median(nums));
	}

	/**
	 * 原理 类似于： KthLargestElement
	 */
	public static int median(int[] nums) {
		int k = nums.length % 2 == 0 ? nums.length / 2 : nums.length / 2 + 1;

		// find kth , k-1 means index
		return quickSelect(nums, 0, nums.length - 1, k - 1);
	}

	public static int quickSelect(int[] nums, int start, int end, int k) {
		int left = start;
		int right = end;
		int mid = start + (end - start) / 2;
		int pivot = nums[mid];

		// ending point
		if (start >= end) {
			return nums[start];
		}

		while (left <= right) {
			while (left <= right && nums[left] < pivot) {
				left++;
			}
			while (left <= right && nums[right] > pivot) {
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

		/**
		 * 和quick sort 基本一样，只不过通过下面的k的判断，保证，只排序半边
		 * 
		 * 其半边排序好了以后，就在k所在的半边取值就好
		 */
		if (start < right && k <= right) {
			System.out.println("start: " + start + ", right " + right);
			quickSelect(nums, start, right, k);
		}
		if (left < end && k >= left) {
			System.out.println("left: " + left + ", en <d " + end);
			quickSelect(nums, left, end, k);

		}
		return nums[k];
	}// end method

	/**
	 * 
	 * http://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/72962
	 * 
	 * 
	 * http://www.cnblogs.com/EdwardLiu/p/4340933.html
	 * 
	 * http://blog.csdn.net/nicaishibiantai/article/details/43529037
	 * 
	 * https://github.com/shawnfan/LintCode/blob/master/Java/Median.java
	 * 
	 */
	public int median1(int[] nums) {

		int k = (nums.length + 1) / 2;
		// 寻找第k大数字
		return sub(nums, 0, nums.length - 1, k);
	}

	private int sub(int[] nums, int start, int end, int size) {
		int mid = (start + end) / 2;
		int pivot = nums[mid];
		int i = start - 1, j = end + 1;
		for (int k = start; k < j; k++) {
			if (nums[k] < pivot) {
				i++;
				int tmp = nums[i];
				nums[i] = nums[k];
				nums[k] = tmp;
			} else if (nums[k] > pivot) {
				j--;
				int tmp = nums[j];
				nums[j] = nums[k];
				nums[k] = tmp;
				k--;
			}
		}
		if (i - start + 1 >= size) {
			return sub(nums, start, i, size);
		} else if (j - start >= size) {
			return nums[j - 1];
		} else {
			return sub(nums, j, end, size - (j - start));
		}
	}

}
