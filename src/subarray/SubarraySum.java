package subarray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array, find a subarray where the sum of numbers is 0. Your
 * code should return the index of the first number and the index of the last
 * number.
 * 
 * Notice
 * 
 * There is at least one subarray that it's sum equals to zero.
 * 
 * Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].
 */
public class SubarraySum {

	public static void main(String[] args) {
		int[] nums = { -2, 1, 2, -3, 4 };
		System.out.println(subarraySum(nums));
	}

	public static ArrayList<Integer> subarraySum(int[] nums) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (nums == null || nums.length == 0) {
			return list;
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		/**
		 * you can't ignore it 因为譬如{1, -1} 或 {1，1， -2}，即从第一个数开始的前几个数加起来为0，
		 * 在map里就能找到0
		 */
		map.put(0, -1);
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum = sum + nums[i];
			/**
			 * 原理：因为前面有一个一样的sum的和在map里，就表是，我加了几个数以后，又等于那个sum, 即表示， 新加的这几个数的和，为0
			 */
			if (map.containsKey(sum)) {
				list.add(map.get(sum) + 1);
				list.add(i);
				return list;
			}
			map.put(sum, i);
		}
		return list;

	}

}
