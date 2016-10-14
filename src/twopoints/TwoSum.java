package twopoints;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array of integers, find two numbers such that they add up to a
 * specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are NOT zero-based.
 * 
 * Example numbers=[2, 7, 11, 15], target=9
 * 
 * return [1, 2]
 */
public class TwoSum {

	/**
	 * Time complexity depends on the put and get operations of HashMap which is
	 * normally O(1). Time complexity of this solution is O(n).
	 */
	public int[] twoSum(int[] numbers, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] result = new int[2];

		for (int i = 0; i < numbers.length; i++) {
			if (map.containsKey(numbers[i])) {
				int index = map.get(numbers[i]);
				// 要加1， 因为返回的结果是第几个数， 而不是数字的index
				result[0] = index + 1;
				result[1] = i + 1;
			} else {
				map.put(target - numbers[i], i);
			}
		}
		return result;
	}

	/**
	 * Jun's own, Wrong method, just for learning, know why it doesn't work,
	 * however, if the result just need true OR false, it works
	 */
	public int[] twoSum1(int[] numbers, int target) {
		if (numbers == null || numbers.length == 0) {
			return null;
		}
		int[] result = new int[2];
		// Note: once you sort it, the sequence changed. then you would lost the
		// original index number, the result is NOT correct
		Arrays.sort(numbers);

		for (int i = 0; i < numbers.length; i++) {
			int rest = target - numbers[i];
			int restIndex = Arrays.binarySearch(numbers, rest);
			if (restIndex >= 0) {
				result[0] = (i + 1);
				result[1] = (restIndex + 1);
				return result;
			} else {
				continue;
			}
		}
		return result;
	}

	public int[] twoSumJun(int[] numbers, int target) {
		if (numbers == null || numbers.length == 0) {
			return null;
		}
		int start = 0;
		int end = numbers.length - 1;
		Arrays.sort(numbers);
		int[] result = new int[2];

		while (start < end) {
			if (numbers[start] + numbers[end] == target) {
				result[0] = start + 1;
				result[1] = end + 1;
				return result;
			} else if (numbers[start] + numbers[end] < target) {
				start++;
			} else if (numbers[start] + numbers[end] > target) {
				end--;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] s = { 11, 2, 7, 15 };
		int value = 9;

		TwoSum finder = new TwoSum();
		int[] indexArray = finder.twoSumJun(s, value);

		if (indexArray != null) {
			for (int index : indexArray) {
				System.out.println(index);
			}
		} else {
			System.out.println("No such two numbers found in the array!");
		}
	}

}