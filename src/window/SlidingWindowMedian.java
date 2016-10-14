package window;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SlidingWindowMedian {

	public static void main(String[] args) {
		// int[] nums = { 1, 2, 7, 8, 5 };
		int[] nums = { 9, 2, 7, 8, 5 };
		System.out.println(medianSlidingWindow(nums, 3));
	}

	/**
	 * O(nlog(n)) time
	 * 
	 * http://ying.ninja/?p=807
	 * 
	 * https://gist.github.com/autolab2013/4853e916ce59eab4d516
	 * 
	 * http://stackoverflow.com/questions/12233809/how-to-remove-elements-from-a
	 * -binary-heap
	 * 
	 * java里有heap的实现，可用PriorityQueue然后可以定义Comparator, 默认是minHeap,
	 * 如果要用maxHeap要自己写一个comparator传入constructor. 但是PriorityQueue的remove方法是O(n)的
	 * 即每次remove需要遍历一遍这个Heap找到那个数再Remove
	 * ，面试时可以和面试官讨论，这一步可以用HashHeap来做，这样remove变成了O(logn), (找到这个数是O(1),
	 * remove相当于和最后一个数swap 然后siftdown操作所以是O(logn)).
	 * 
	 * 如果用HashHeap的话，整个Time Complexity就是O(nlogn).
	 * 
	 */

	public static ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {

		ArrayList<Integer> result = new ArrayList<Integer>();
		if (nums.length == 0)
			return result;

		HashHeap leftMaxHeap = new HashHeap("max");
		HashHeap rightMinHeap = new HashHeap("min");
		/**
		 * i=0, 如果设置为i=1,，则当k=1 时候，答案就集合里就会错过数组的第一个元素 原因： 如果从i=1开始，当k=1,
		 * 的时候，第一轮循环 if(i>=k) 也就是if(1>=1)条件就会满足，从而执行移除remove操作
		 * 
		 */
		int median = nums[0];
		for (int i = 0; i < nums.length; i++) {
			// 最开始，假设meidian的值为nums[0]
			if (i != 0) {
				if (nums[i] > median) {
					rightMinHeap.add(nums[i]);
				} else {
					leftMaxHeap.add(nums[i]);
				}
			}
			// i一旦>=k时，相当于窗口移动了，就要remove掉前面的数字
			if (i >= k) {
				/**
				 * 此时的median相当于上一轮,即窗口移动前上一轮的median
				 * 
				 * median == nums[i - k]
				 * 表示这个median不在任何一个heap中，无需删除操作，直接从leftHeap,
				 * rightheap中重新选出一个新的median即可
				 */
				if (median == nums[i - k]) {
					if (leftMaxHeap.size() > 0) {
						median = leftMaxHeap.poll();
					} else if (rightMinHeap.size() > 0) {
						median = rightMinHeap.poll();
					}
					// 根据第一步按照median大小，分离方法，如果num[i-k] >
					// median,表示num[i-k]就在rightMinHeap里
				} else if (median < nums[i - k]) {
					rightMinHeap.delete(nums[i - k]);
				} else if (median > nums[i - k]) {
					leftMaxHeap.delete(nums[i - k]);
				}
			}

			/**
			 * 始终保持leftMaxheap = rightMinHeap 或 leftMaxHeap +1 =rightMinHeap
			 * 
			 * 只有这样，median变量保存的值才是median
			 * 
			 * 如： 9 2 7 8， k=4
			 * 
			 * left (2) right (8, 9) median= 7
			 * 
			 */

			while (leftMaxHeap.size() > rightMinHeap.size()) {
				rightMinHeap.add(median);
				median = leftMaxHeap.poll();
			}
			while (rightMinHeap.size() > leftMaxHeap.size() + 1) {
				leftMaxHeap.add(median);
				median = rightMinHeap.poll();
			}
			// 为了保证，第一次达到k个数的时候，median会被加入到result中
			// 比如：i=2 (第三个数)， k=3时候
			if (i + 1 >= k) {
				result.add(median);
			}
		}
		return result;
	}

	/**
	 * http://www.cnblogs.com/fifi043/p/4979652.html
	 * 
	 * 用一个min heap,一个max heap维系。如果k为even,取两个peek的min
	 * value，如果k为odd,永远取maxheap的peek.
	 * 
	 * - maxheap里面的元素为median和之前的元素，且保证peek的值最大。
	 * 
	 * - minheap存的为median之后的元素，且peek最小。
	 * 
	 * - 保持maxheap的size>=rightMinHeap.size(),避免当k=1,maxheap为null的情况。同时保证k为odd的时候
	 * ， median永远是maxheap的peek value.
	 * 
	 * －当i>=k,remove(Object).
	 * 
	 * 时间开销： remove(Object) from PriorityQueue 需要o(k)的时间,
	 * 即每次remove需要遍历一遍这个Heap找到那个数再Remove, 如果用hashheap,则会变成O(logk)；find insert
	 * position需要logk的时间。所以总时间开销为n(k+logK). worst case为n^2.
	 * 
	 * 空间开销：o(k)
	 */

	public static ArrayList<Integer> medianSlidingWindow1(int[] nums, int k) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (nums == null || nums.length == 0 || k == 0) {
			return result;
		}
		PriorityQueue<Integer> rightMinHeap = new PriorityQueue<Integer>(k);
		PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<Integer>(k,
				new Comparator<Integer>() {
					public int compare(Integer a, Integer b) {
						return b - a;
					}
				});

		for (int i = 0; i < nums.length; i++) {
			if (i >= k) {
				if (!rightMinHeap.isEmpty()
						&& nums[i - k] >= rightMinHeap.peek()) {
					// remove time cost: o(K), 下面的add/poll()操作是logk===>
					// 总的程序的时间复杂度为 n(k+logk),也就是O(nk)
					rightMinHeap.remove(nums[i - k]);
				} else {
					leftMaxHeap.remove(nums[i - k]);
				}
			}

			if (leftMaxHeap.size() == 0 || nums[i] < leftMaxHeap.peek()) {
				leftMaxHeap.add(nums[i]);
			} else {
				rightMinHeap.add(nums[i]);
			}

			// always make leftMaxHeap with greater size.
			// example : 1,2,7,7,2. when remove 1&add 7. max:7,min:2,7. it's
			// wrong!! thus, move one ele to max
			if (rightMinHeap.size() > leftMaxHeap.size()) {
				leftMaxHeap.add(rightMinHeap.poll());
			} else if (leftMaxHeap.size() > rightMinHeap.size() + 1) {
				rightMinHeap.add(leftMaxHeap.poll());
			}

			if (i + 1 >= k) {
				if (rightMinHeap.size() == leftMaxHeap.size()) {
					result.add(Math.min(leftMaxHeap.peek(), rightMinHeap.peek()));
				} else {
					result.add(leftMaxHeap.peek());
				}
			}
		}
		return result;
	}

}
