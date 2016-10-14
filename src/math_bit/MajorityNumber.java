package math_bit;

import java.util.ArrayList;

/**
 * Given an array of integers, the majority number is the number that occurs
 * more than half of the size of the array. Find it.
 * 
 * Example Given [1, 1, 1, 1, 2, 2, 2], return 1
 * 
 * Challenge O(n) time and O(1) extra space
 */
public class MajorityNumber {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(1);
		list.add(2);
		list.add(2);
		list.add(1);
		list.add(1);
		majorityNumber(list);
	}

	public static int majorityNumber(ArrayList<Integer> nums) {
		int count = 0, candidate = -1;
		for (int i = 0; i < nums.size(); i++) {
			if (count == 0) {
				candidate = nums.get(i);
				// 因为count = 1，这里加过1了，所以下面不用执行取加1了
				count = 1;
				continue;
			}

			if (candidate == nums.get(i)) {
				count++;
			} else {
				count--;
			}
		}
		return candidate;
	}

}
