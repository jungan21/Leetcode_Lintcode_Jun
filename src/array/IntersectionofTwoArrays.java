package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example: Given nums1 = [1, 2, 2, 1, 2], nums2 = [2, 2, 2], return [2].
 * 
 * Note: Each element in the result must be unique. The result can be in any
 * order.
 * 
 * @author jungan
 *
 */
public class IntersectionofTwoArrays {

	public static void main(String[] args) {
		int[] nums1 = { 1, 2, 2, 1 };
		int[] nums2 = { 2, 2 };
		for (int i : intersection2(nums1, nums2)) {
			System.out.println(i);
		}
	}

	/**
	 * 推荐 Method 1: Time = O(n). Space = O(n).
	 */
	public static int[] intersection(int[] nums1, int[] nums2) {
		HashSet<Integer> set = new HashSet<Integer>();
		HashSet<Integer> resultSet = new HashSet<Integer>();
		for (int i : nums1) {
			set.add(i);
		}

		for (int i = 0; i < nums2.length; i++) {
			if (set.contains(nums2[i])) {
				resultSet.add(nums2[i]);
			}
		}

		int[] resultArray = new int[resultSet.size()];
		int i = 0;
		for (int x : resultSet) {
			resultArray[i++] = x;
		}

		return resultArray;
	}

	/**
	 * 不推荐 ， bad example 仅做学习之用
	 * 
	 * 因为下面在循环set, 时候要对set删除操作，容易出错 Method 2: Time = O(n). Space = O(n).
	 */
	public static int[] intersection1(int[] nums1, int[] nums2) {
		HashSet<Integer> set1 = new HashSet<Integer>();
		HashSet<Integer> set2 = new HashSet<Integer>();
		for (int i : nums1) {
			set1.add(i);
		}
		for (int i : nums2) {
			set2.add(i);
		}

		/**
		 * 这里只能用iterator,因为你在iterate Set的时候，还要做删除操作， 如果为了方便，而使用 for (int i :
		 * set1),的方法，就会报错，因为该for 循环的时候，你不可以从里面删除元素，简单说，就是在用for（）方法iterate Set,时，
		 * 你不能用 set.remove(num) 方法删除元素, 对于list arraylist, 同样也有这样的问题， 不过对于list,
		 * 我们可以用for (int i =0; i< list.size(); i++), list.remove(i);
		 */
		Iterator<Integer> iter = set1.iterator();
		while (iter.hasNext()) {
			int i = iter.next();
			if (!set2.contains(i)) {
				// Note: important
				iter.remove();
			}
		}

		int[] result = new int[set1.size()];
		int i = 0;
		for (int x : set1) {
			result[i++] = x;
		}

		return result;
	}

	/**
	 * Method 2: Binary search, Time = O(nlog(n)). Space = O(n).
	 */
	public static int[] intersection2(int[] nums1, int[] nums2) {

		// have to sort, for remove duplicate from the result
		Arrays.sort(nums1);
		Arrays.sort(nums2);

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < nums1.length; i++) {
			// since we sorted array, (i > 0 && nums1[i] != nums1[i - 1])
			// condition can ignore duplicate
			if (i == 0 || (i > 0 && nums1[i] != nums1[i - 1])) {
				/**
				 * have to sort array, before you can call binarySearch method
				 * 
				 * binarySearch 的结果是返回要找元素的下标， 所有如果找到， 结果一定是是>=0;
				 */
				if (Arrays.binarySearch(nums2, nums1[i]) >= 0) {
					list.add(nums1[i]);
				}
			}
		}

		int[] result = new int[list.size()];
		int k = 0;
		for (int i : list) {
			result[k++] = i;
		}

		return result;
	}

	private static boolean binarySearch(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		int start = 0, end = nums.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] == target) {
				return true;
			} else if (nums[mid] > target) {
				end = mid;
			} else {
				start = mid;
			}
		}

		if (nums[start] == target) {
			return true;
		}
		if (nums[end] == target) {
			return true;
		}

		return false;
	}

	/**
	 * metho 3: sort & merge,
	 * 
	 * jiuzhang
	 */

	public int[] intersection3(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);

		int i = 0, j = 0;
		int[] temp = new int[nums1.length];
		int index = 0;

		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] == nums2[j]) {
				// for avoid duplicate numer in result set
				// temp[index - 1] != nums1[i] 条件也可以写成 nums1[i] != nums1[i-1]
				if (index == 0 || temp[index - 1] != nums1[i]) {
					temp[index++] = nums1[i];
				}
				i++;
				j++;
			} else if (nums1[i] < nums2[j]) {
				i++;
			} else {
				j++;
			}
		}
		// index is the actualy size for the temp array
		int[] result = new int[index];
		for (int k = 0; k < index; k++) {
			result[k] = temp[k];
		}
		return result;
	}

}
