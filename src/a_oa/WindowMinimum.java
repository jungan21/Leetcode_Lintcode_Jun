package a_oa;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 实战唯一不同在于给的是arraylist，没错，你需要可耻的这么声明：ArrayList result=new ArrayList();
 * 
 * 另外，1. 若是用Java，用到queue, list啥的记得前面手动import java.util.*
 * 2.所有函数都是static的，所以自己写其他helper函数的时候记得加上static
 *
 */
public class WindowMinimum {

	public static void main(String[] args) {
		int[] nums = { 4, 2, 12, 11, -5 };
		int k = 2;
		System.out.println(windowMinimum(nums, 1));
	}

	public static List<Integer> windowMinimum(int[] nums, int k) {
		List<Integer> result = new ArrayList<>();
		if (nums == null || nums.length == 0 || k <= 0) {
			return result;
		}
		Deque<Integer> deque = new LinkedList<Integer>();

		for (int i = 0; i < nums.length; i++) {
			// 只能是 <, <=不行
			while (!deque.isEmpty() && nums[i] < deque.peekLast()) {
				deque.pollLast();
			}
			deque.offer(nums[i]);

			if (i >= k && nums[i - k] == deque.peekFirst()) {
				deque.pollFirst();
			}

			if (i + 1 >= k) {
				result.add(deque.peekFirst());
			}
		}
		return result;
	}
}
