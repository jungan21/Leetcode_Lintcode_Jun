package window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class SlidingWindowMaximum {

	public static void main(String[] args) {
		// int[] nums = { 1, 2, 7, 7, 8 };

		int[] nums = { 7, 6, 5, 7, 2 };
		System.out.println(maxSlidingWindow(nums, 3));

	}

	/**
	 * jiuzhang + Jun自己结合
	 * 
	 * http://www.jiuzhang.com/solutions/sliding-window-maximum/
	 * 
	 * Time: 每个元素只会进入Queue以及 出Queue一次,并且 remove,add 操作都是O(1)时间 所以总的时间就是O(n)
	 * 
	 */
	public static ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (nums == null || nums.length == 0) {
			return result;
			// return new int[0];
		}

		// Note: LinkedList implements Deque
		// 也可以直接： LinkedList<Integer> deque = new LinkedList<Integer>();
		Deque<Integer> deque = new LinkedList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			// 队列里所有小于nums[i]的数都要出列，以保证deque.peek(),也就是第一个数就是k个数字里maxium
			// 也就是说queue,里面从头到尾应该是个单调递减 queue
			// 单调递增，也就是说 只能是 < ， <=答案不对
			while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
				deque.pollLast();
			}
			// add to the end of the deque
			deque.offer(nums[i]);

			if (i >= k && deque.peekFirst() == nums[i - k]) {
				deque.pollFirst();
			}
			if (i + 1 >= k) {
				// 第一个元素始终是最大的
				result.add(deque.peekFirst());
			}
		}
		return result;
	}

	/**
	 * 时间 O(N) 空间 O(K) with Deque
	 * 
	 * https://segmentfault.com/a/1190000003903509
	 * 
	 * 为什么时间复杂度是O(N)呢？因为每个数只可能被操作最多两次，一次是加入队列的时候，一次是因为有别的更大数在后面，所以被扔掉，
	 * 或者因为出了窗口而被扔掉
	 *
	 *
	 *
	 * 遍历数组nums，使用双端队列deque维护滑动窗口内有可能成为最大值元素的数组下标
	 * 
	 * 由于数组中的每一个元素至多只会入队、出队一次，因此均摊时间复杂度为O(n)
	 * 
	 * 记当前下标为i，则滑动窗口的有效下标范围为[i - (k - 1), i]
	 * 
	 * 若deque中的元素下标＜ i - (k - 1)，则将其从队头弹出，deque中的元素按照下标递增顺序从队尾入队。
	 * 
	 * 这样就确保deque中的数组下标范围为[i - (k - 1), i]，满足滑动窗口的要求。
	 * 
	 * 当下标i从队尾入队时，顺次弹出队列尾部不大于nums[i]的数组下标（这些被弹出的元素由于新元素的加入而变得没有意义）
	 * 
	 * deque的队头元素即为当前滑动窗口的最大值
	 *
	 */
	public static ArrayList<Integer> maxSlidingWindow1(int[] nums, int k) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (nums == null || nums.length == 0)
			return result;

		LinkedList<Integer> deque = new LinkedList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			// 每当新数进来时，如果发现队列头部的数的下标，是窗口最左边数的下标，则扔掉
			// 如果此时队列的首元素是i -
			// k的话，表示此时窗口向右移了一步，则移除队首元素。然后比较队尾元素和将要进来的值，如果小的话就都移除，然后此时我们把队首元素加入结果中即可，参见代码如下：
			if (!deque.isEmpty() && deque.peekFirst() == i - k) {
				deque.poll();
			}
			// 当我们遇到新的数时，将新的数和双向队列的末尾比较，如果末尾比新数小，则把末尾扔掉，直到该队列的末尾比新数大或者队列为空的时候才住手.保证队列是降序的
			while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
				deque.removeLast();
			}
			// 加入新数
			deque.offerLast(i);
			// 队列头部就是该窗口内第一大的
			if ((i + 1) >= k) {
				result.add(nums[deque.peek()]);
			}
		}
		return result;
	}

	/**
	 * 维护一个大小为K的最大堆，依此维护一个大小为K的窗口，每次读入一个新数，都把堆中窗口最左边的数扔掉，再把新数加入堆中，
	 * 这样堆顶就是这个窗口内最大的值。
	 * 
	 * 时间 O(NlogK) 空间 O(K)
	 */
	public int[] maxSlidingWindowHeap(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			// how to return empty array
			return new int[0];
		// max
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k,
				Collections.reverseOrder());
		int[] res = new int[nums.length + 1 - k];
		for (int i = 0; i < nums.length; i++) {
			// 把窗口最左边的数去掉
			if (i >= k)
				maxHeap.remove(nums[i - k]);

			// 把新的数加入窗口的堆中
			maxHeap.offer(nums[i]);

			// 堆顶就是窗口的最大值
			if (i + 1 >= k)
				res[i + 1 - k] = maxHeap.peek();
		}
		return res;
	}

	/**
	 * Jun's method
	 */
	public int[] maxSlidingWindowJun(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return new int[0];
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k,
				Collections.reverseOrder());
		List<Integer> temp = new ArrayList<>();

		for (int i = 0; i < nums.length; i++) {
			if (maxHeap.size() < k) {
				maxHeap.offer(nums[i]);
			}

			if (maxHeap.size() == k) {
				temp.add(maxHeap.peek());
				maxHeap.remove(nums[i + 1 - k]);
			}
		}
		int[] result = new int[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			result[i] = temp.get(i);
		}
		return result;
	}
}
