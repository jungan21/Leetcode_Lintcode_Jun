package twopoints;

/**
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red,
 * white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
 * and blue respectively.
 * 
 * Notice
 * 
 * You are not suppose to use the library's sort function for this problem. You
 * should do it in-place (sort numbers in the original array).
 * 
 * Example Given [1, 0, 1, 2], sort it in-place to [0, 1, 1, 2].
 * 
 *
 */
public class SortColors {

	public static void main(String[] args) {
		int[] nums = { 2, 0, 0, 1, 2, 0, 1, 1, 2 };
		SortColors.sortColors(nums);
	}

	/**
	 * jiuzhang: http://www.jiuzhang.com/solutions/sort-colors/
	 */

	public static void sortColors(int[] a) {
		if (a == null || a.length <= 1) {
			return;
		}
		/**
		 * 3 points,
		 * 
		 * pl means left point for 0; pr means right point for 2; i as a point
		 * is to find the 1
		 * 
		 * pl 左边的都是0， pr右边全是2， i 一直跑
		 * 
		 * pl的左右是固定1的位置， 其实整个程序结束运行后， pl 会指向第一个1的位置
		 */
		int pl = 0;
		int pr = a.length - 1;
		int i = 0;
		// i < pr, 不行， 必须<=
		while (i <= pr) {
			if (a[i] == 0) {
				// 把i发现的0 和前面pl位置swap后，由于Pl的作用是固定1的位置，所以pl可以往后移动,
				// 由于pl是固定1的，所有交换后， i处的位置是1，所以i可以移动
				swap(a, pl, i);
				pl++;
				i++;
			} else if (a[i] == 1) {
				// 由于Pl的作用是固定1的位置, 所以当发现1时候，我们只能移动i, 其实整个程序结束运行后， pl 会指向第一个1的位置
				i++;
				// a[i]=2
			} else {
				// 把后面的2swap到数组前面来之后， pl和i不能往后移动，因为你不知道从后面刚swap到前面来的数是几，
				// 而我们确定后面的是2， 所以pr--
				swap(a, pr, i);
				pr--;
			}
		}
		System.out.println(pl);
	}

	/**
	 * 
	 * http://www.cnblogs.com/springfor/p/3872313.html 这道题就是运用指针来解决了，可以说叫3指针吧。
	 * 
	 * 一个指针notred从左开始找，指向第一个不是0（红色）的位置；一个指针notblue从右开始往左找，指向第一个不是2（蓝色）的位置。
	 * 
	 * 然后另一个新的指针i指向notred指向的位置，往后遍历，遍历到notred的位置。
	 * 
	 * 这途中需要判断：
	 * 
	 * 当i指向的位置等于0的时候，说明是红色，把他交换到notred指向的位置，然后notred++，i++。
	 * 
	 * 当i指向的位置等于2的时候，说明是蓝色，把他交换到notblue指向的位置，然后notblue--。
	 * 
	 * 当i指向的位置等于1的时候，说明是白色，不需要交换，i++即可。
	 * 
	 * @param A
	 */
	public static void sortColors1(int A[]) {
		if (A == null || A.length == 0)
			return;

		int notred = 0;
		int notblue = A.length - 1;

		while (notred < A.length && A[notred] == 0)
			notred++;

		while (notblue >= 0 && A[notblue] == 2)
			notblue--;

		int i = notred;
		while (i <= notblue) {
			if (A[i] == 0) {
				swap(A, i, notred);
				notred++;
				i++;
			} else if (A[i] == 2) {
				swap(A, i, notblue);
				notblue--;
			} else
				i++;
		}
	}

	public static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	/**
	 * jiuzhang:
	 * 
	 * A rather straight forward solution is a two-pass algorithm using counting
	 * sort. First, iterate the array counting number of 0's, 1's, and 2's, then
	 * overwrite array with total number of 0's, then 1's and followed by 2's.
	 */
	public void sortColors2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}

		int redCnt = 0;
		int whileCnt = 0;
		int blueCnt = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				redCnt++;
			} else if (nums[i] == 1) {
				whileCnt++;
			} else {
				blueCnt++;
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (i < redCnt) {
				nums[i] = 0;
			} else if (redCnt <= i && (i - redCnt) < whileCnt) {
				nums[i] = 1;
			} else if (i >= (redCnt + whileCnt)) {
				nums[i] = 2;
			}
		}
	}

}
