package company.walmart;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given [3,10,1000,-99,4,100] and k = 3. Return [1000, 100, 10].
 *
 *
 * https://aaronice.gitbooks.io/lintcode/content/data_structure/
 * top_k_largest_numbers.html
 */
public class TopkLargestNumbers {

	public static void main(String[] args) {
		// int[] nums = { 1, 2, 3, 4, 5, 6, 8, 9, 10, 7 };
		int[] nums = { 3, 10, 1000, -99, 4, 100 };
		topkMinHeap(nums, 3);
	}

	/**
	 * 
	 * 这里要用 Max Heap的性质：max heap仅仅能保证了堆顶的元素(也就是poll()操作)是整个堆大的那一个
	 * 
	 * 第一步，先构建一个capacity为k的Max Heap，这需要O(n)时间
	 * 
	 * 第二步，对这个Max
	 * Heap进行k次poll()操作，（每次poll()操作，可以取到当前堆里最大的值），取出k个maximum的元素，这一步操作需要O(klogn)
	 * 
	 * 整个操作的时间复杂度是 O(n + k logn)，空间复杂度是O(k)
	 */
	public static int[] topk(int[] nums, int k) {
		if (nums == null || nums.length == 0 || nums.length < k) {
			return null;
		}

		int[] result = new int[k];

		// Comparator<Integer> comparator = new Comparator<Integer>() {
		// public int compare(Integer o1, Integer o2) {
		// return o2 - o1;
		// }
		// };

		// better one
		//PriorityQueue<Integer> maxHeap1 = new PriorityQueue<Integer>(k, Collections.reverseOrder());

		// 逆序排列，即：构建max heap,堆顶部一直是最大的那个元素
		// Max Heap，仅仅能保证了堆顶的元素是整个堆最大的那一个， 所以需要把所有元素放到queue,里
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k,
				new Comparator<Integer>() {
					public int compare(Integer a, Integer b) {
						return b - a;
					}
				});


		for (int num : nums) {
			maxHeap.offer(num);
		}

		for (int i = 0; i < result.length; i++) {
			// 关键点： Max Heap poll,
			// 每次从堆顶取得最大的数组（即使你debug看到maxHeap里的数据不一定是有序的，每poll()一次就取到最大的元素，所以返回的是前k大元素
			result[i] = maxHeap.poll();
		}
		return result;
	}

	/**
	 * http://www.jiuzhang.com/solutions/top-k-largest-numbers/
	 * 
	 */

	/**
	 * 
	 * 如果用min heap, 原理和 find KthLargestElement 类似，堆里剩下的是kth大小，以及larger than
	 * kth大小的数字,堆顶即是the kth lartest数， 不过其他剩下的是无序的， 我们需要排序，才能返回结果,相对麻烦，所以考虑用max
	 * heap
	 * 
	 * 而对于 find KthLargestElement那题，只要求返回最后堆里的堆顶元素， 而这道题，要求从大到小顺序，放回最后堆里剩下的元素
	 * 
	 */

	public static int[] topkMinHeap(int[] nums, int k) {
		if (nums == null || nums.length == 0 || nums.length < k) {
			return null;
		}

		int[] result = new int[k];
		int[] finalResult = new int[k];

		// min heap, i.e. 每次poll操作取到的是当前堆里最小的数字
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);

		for (int i : nums) {
			// Note: you must put it into the queue first
			minHeap.offer(i);
			if (minHeap.size() > k) {
				minHeap.poll();
			}
		}
		// 到此为止，现在堆里剩下的是kth大小，以及larger than kth大小的数字,堆顶即是the kth lartest数，
		// 不过其他剩下的是无序的， 我们需要排序，才能返回结果
		
		for (int i = 0; i < k; i++) {
			//由于每次pop出来的是最小的数，result[i]是升序排列，但是结果要求降序排列，所以下面finalResult输出
			result[i] = minHeap.poll();
		}
		
		int j = 0;
		for (int i = k - 1; i >= 0; i--) {
			finalResult[i] = result[j++];
		}
		return finalResult;
	}
}
