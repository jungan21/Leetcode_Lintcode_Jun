package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * 
 * Note: Each element in the result should appear as many times as it shows in
 * both arrays. The result can be in any order.
 * 
 * @author jungan
 *
 */
public class IntersectionofTwoArrays2 {

	public static void main(String[] args) {
		int[] nums1 = { 1, 2, 2, 1 };
		int[] nums2 = { 2, 2 };
		for (int i : intersection2(nums1, nums2)) {
			System.out.println(i);
		}
	}

	// Time = O(n). Space = O(n).
	public static int[] intersection(int[] nums1, int[] nums2) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i : nums1) {
			if (map.containsKey(i)) {
				map.put(i, map.get(i) + 1);
			} else {
				map.put(i, 1);
			}
		}

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i : nums2) {
			if (map.containsKey(i)) {
				if (map.get(i) > 1) {
					map.put(i, map.get(i) - 1);
				} else {
					map.remove(i);
				}
				list.add(i);
			}
		}

		int[] result = new int[list.size()];
		int i = 0;
		while (i < list.size()) {
			result[i] = list.get(i);
			// 注意不能傻逼的写成result[i++] = list.get(i++); 这样每次i被加了两次
			i++;
		}

		return result;
	}

	/**
	 * method 2: if sorted， merge sort, two points
	 */
	public static int[] intersection2(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		ArrayList<Integer> list = new ArrayList<Integer>();
		int p1 = 0, p2 = 0;
		while (p1 < nums1.length && p2 < nums2.length) {
			if (nums1[p1] < nums2[p2]) {
				p1++;
			} else if (nums1[p1] > nums2[p2]) {
				p2++;
			} else {

				// if (p1 == 0 || nums1[p1] != nums1[p1-1]){
				// list.add(nums1[p1]);
				// }
				// 如果要去重复，问题变成： IntersectionofTwoArrays， 可以用上面的条件
				list.add(nums1[p1]);
				p1++;
				p2++;
			}
		}

		int[] result = new int[list.size()];
		int i = 0;
		while (i < list.size()) {
			result[i] = list.get(i);
			i++;
		}
		return result;
	}

}
