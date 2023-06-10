package window;

import java.util.Collections;
import java.util.PriorityQueue;

public class DataStreamMedian {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] medianII(int[] nums) {
		int[] result = new int[nums.length];
		if (nums == null || nums.length == 0) {
			return result;
		}

		PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<Integer>(
				nums.length, Collections.reverseOrder());
		PriorityQueue<Integer> rightMinHeap = new PriorityQueue<Integer>(
				nums.length);

		int median = nums[0];
		// 必须放在外面先赋值
		result[0] = median;
		// 从1开始，因为0的时候上面已经赋值过了
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > median) {
				rightMinHeap.add(nums[i]);
			} else {
				leftMaxHeap.add(nums[i]);
			}
			//if (leftMaxHeap.size() > rightMinHeap.size()) 也工作
			while (leftMaxHeap.size() > rightMinHeap.size()) {
				rightMinHeap.offer(median);
				median = leftMaxHeap.poll();
			}
			// if (leftMaxHeap.size() + 1 < rightMinHeap.size()) 也工作
			while (leftMaxHeap.size() + 1 < rightMinHeap.size()) {
				leftMaxHeap.offer(median);
				median = rightMinHeap.poll();
			}
			result[i] = median;
		}
		return result;
	}
}
