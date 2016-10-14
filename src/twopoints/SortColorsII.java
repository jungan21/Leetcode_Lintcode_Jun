package twopoints;

/**
 * Given an array of n objects with k different colors (numbered from 1 to k),
 * sort them so that objects of the same color are adjacent, with the colors in
 * the order 1, 2, ... k.
 * 
 * Example Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors
 * in-place to [1, 2, 2, 3, 4].
 * 
 * 下面的解法都是O(n^2)的， 如果要 O(n)的解法： http://www.cnblogs.com/yuzhangcmu/p/4177326.html
 *
 */
public class SortColorsII {

	public static void main(String[] args) {
		int[] colors = { 1, 2, 4, 4, 3, 1, 4 };
		sortColors2(colors, 4);
		for (int num : colors) {
			System.out.println(num);
		}
	}

	/**
	 * 
	 * 杂度是O(n^2): T(n) = T(n-2) + n
	 * 
	 * Best one, same idea with sort color 1: 即多次利用sort color1里面分成三份的思想
	 * 
	 * Each time sort the array into three parts:
	 * 
	 * [all min], [all unsorted others], [all max], then update min and max and
	 * sort the [all unsorted others] with the same method.和sort color类似，
	 * 也把看成三种颜色排序， min一种颜色，max,一种颜色，all other numbers 一种颜色
	 */

	public static void sortColors2(int[] A, int k) {
		int pl = 0;
		int pr = A.length - 1;
		int i = 0;
		int min = 1, max = k;
		while (min < max) {
			while (i <= pr) {
				if (A[i] == min) {
					swap(A, pl, i);
					i++;
					pl++;
				} else if (A[i] == max) {
					swap(A, pr, i);
					pr--;
				} else {
					i++;
				}
			}
			i = pl;
			min++;
			max--;
		}
	}

	/**
	 * Jun's own
	 * 
	 * 与sortColor1不同， sortColor1中只有3个颜色(0,1, 2), 这里有k个颜色
	 * 
	 */
	public static void sortColors2_Jun(int[] A, int k) {
		helper(A, 0, A.length - 1, k);
	}

	/**
	 * Jun's: Recursive
	 * 
	 * 思路： 假设K=4， 首先第一遍循环，将所有的4，放到最右边，此时右指针，从最右边已经移到第一个不是4的数字(其实下面while循环第一遍结束后，
	 * high即指向该位置)， 然后，recursive call, 对A数组从0 到 high, 再把所有的3，移到左右边
	 * 
	 */
	public static void helper(int[] A, int low, int high, int k) {
		if (A == null || A.length <= 1 || k == 0) {
			return;
		}
		while (low <= high) {
			while (low <= high && A[high] == k) {
				high--;
			}
			while (low <= high && A[low] == 1) {
				low++;
			}
			if (low <= high && A[low] == k) {
				swap(A, low, high);
				high--;
				low++;
			} else if (low <= high && A[low] != k) {
				low++;
			}
		}
		helper(A, 0, high, k - 1);
	}

	/**
	 * Jun's: rewrite above recursive method to Non-recursive manner
	 */
	public static void helper1(int[] A, int low, int high, int k) {
		if (A == null || A.length <= 1 || k == 0) {
			return;
		}
		for (int i = k; i >= 1; i--) {
			while (low <= high) {
				while (low <= high && A[high] == i) {
					high--;
				}
				while (low <= high && A[low] == 1) {
					low++;
				}
				if (low <= high && A[low] == i) {
					swap(A, low, high);
					high--;
					low++;
				} else if (low <= high && A[low] != i) {
					low++;
				}
			}
			low = 0;
		}
	}

	public static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	/**
	 * 上面的方法都是没用用额外的space，如果可以
	 */
	public void sortColors2WithExtraSpace(int[] colors, int k) {
		int[] count = new int[k];
		for (int color : colors) {
			count[color - 1]++;
		}
		int index = 0;
		for (int i = 0; i < k; i++) {
			while (count[i] > 0) {
				colors[index++] = i + 1;
				count[i]--;
			}
		}
	}
}
