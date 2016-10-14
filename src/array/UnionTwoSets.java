package array;

import java.util.Arrays;

/**
 * 其实就是merge two sorted array, 在merge的同时要去重
 *
 */
public class UnionTwoSets {

	public static void main(String[] args) {
		int[] nums1 = { 1, 2, 2, 3, 3 , 7, 8};
		int[] nums2 = { 1, 2, 3, 4, 5 , 7, 10};
		System.out.println(Arrays.toString(unionDataSets(nums1, nums2)));
	}

	public static int[] unionDataSets(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);

		int i = 0, j = 0;
		int[] temp = new int[nums1.length + nums2.length];
		int index = 0;

		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] == nums2[j]) {
				if (index == 0 || temp[index - 1] != nums1[i]) {
					temp[index++] = nums1[i];
				}
				i++;
				j++;
			} else if (nums1[i] < nums2[j]) {
				if (index == 0 || temp[index - 1] != nums1[i]) {
					temp[index++] = nums1[i];
				}
				i++;
			} else {
				if (index == 0 || temp[index - 1] != nums2[j]) {
					temp[index++] = nums2[j];
				}
				j++;
			}
		}

		while (i < nums1.length) {
			if (index == 0 || temp[index - 1] != nums1[i]) {
				temp[index++] = nums1[i];
			}
			i++;
		}

		while (j < nums2.length) {
			if (index == 0 || temp[index - 1] != nums2[j]) {
				temp[index++] = nums2[j];
			}
			j++;
		}

		// index is the actualy size for the temp array
		int[] result = new int[index];
		for (int k = 0; k < index; k++) {
			result[k] = temp[k];
		}
		return result;
	}
}
