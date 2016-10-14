package array;

import java.util.ArrayList;

public class RecoverRotatedSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Jun's
	 */
	public void recoverRotatedSortedArrayJun(ArrayList<Integer> nums) {
		if (nums == null || nums.size() == 0) {
			return;
		}
		for (int i = 1; i < nums.size(); i++) {
			if (nums.get(i) < nums.get(i - 1)) {
				reverse(nums, 0, i - 1);
				reverse(nums, i, nums.size() - 1);
				reverse(nums, 0, nums.size() - 1);
				return;
			}
		}
	}

	public void reverse(ArrayList<Integer> nums, int start, int end) {
		if (start >= end) {
			return;
		}
		while (start < end) {
			int temp = nums.get(start);
			nums.set(start, nums.get(end));
			nums.set(end, temp);
			start++;
			end--;
		}
	}

}
