package math_bit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LargestNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 3, 30, 34, 5, 9 };
		System.out.println(largestNumber(nums));

		PriorityQueue<String> maxHeap = new PriorityQueue<String>();

	}

	// This problem can be solve by simply sorting strings, not sorting integer.
	// Define a comparator to compare strings by concat() right-to-left or
	// left-to-right.
	public static String largestNumber(int[] nums) {
		String[] strs = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			strs[i] = String.valueOf(nums[i]);
			// Integer.toString(nums[i]);
		}

		// descending order; { 3, 30, 34, 5, 9 }; ==> {9, 5, 34 ,3, 30}
		Comparator<String> comparator = new Comparator<String>() {
			public int compare(String s1, String s2) {
				String leftRight = s1 + s2;
				String rightLeft = s2 + s1;
				return rightLeft.compareTo(leftRight);
				/**
				 * 题目如果求 minumum number, 就按升序排列 ascending order return
				 * leftRight.compareTo(rightLeft);
				 */
			}
		};

		Arrays.sort(strs, comparator);
		// 不可以用sb = sb + s; 只能用 sb.append(s);
		StringBuilder sb = new StringBuilder();
		for (String s : strs) {
			sb.append(s);
		}
		/**
		 * 下面几行也可以这么写 int index = 0;
		 *
		 * while (index < sb.length()-1 && sb.charAt(index) == '0') {
		 * 
		 * sb.deleteCharAt(0); index++; }
		 * 
		 * return sb.toString().substring(index);
		 * 
		 */
		// sb.length() > 1， 如果数组里只有一个0，或全是0，最后一个0不能删除，否则返回的是空字符串
		while (sb.length() > 1 && sb.charAt(0) == '0') {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

}
