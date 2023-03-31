package twopoints;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargestElement {

	public static void main(String[] args) {
		int[] array = { 9, 3, 2, 4, 8 };

		System.out.println(kthLargestElement2(array, 3));

	}

	/**
	 * if bad pivots are consistently chosen, such as decreasing by only a
	 * single element each time, then worst-case performance is quadratic:
	 * O(n2). This occurs for example in searching for the maximum element of a
	 * set, using the first element as the pivot, and having sorted data.
	 * 
	 * https://en.wikipedia.org/wiki/Quickselect#Time_complexity
	 * https://segmentfault.com/a/1190000003704825
	 */

	public static int kthLargestElement(int k, int[] nums) {
		if (nums == null || nums.length == 0 || nums.length < k) {
			return -1;
		}

		int start = 0;
		int end = nums.length - 1;
		// 下面quickSelect,按逆序排序，kth largest的下面就是k-1
		return quickSelect(nums, start, end, k - 1);
	}

	/**
	 * Average: O(n)
	 * 
	 * 详细分析 Average Case Analysis:
	 * 
	 * First Step: T(n) = cn + T(n/2) where, cn = time to perform partition,
	 * where c is any constant(doesn't matter). T(n/2) = applying recursion on
	 * one half of the partition. Since it's an average case we assume that the
	 * partition was the median.
	 * 
	 * As we keep on doing recursion, we get the following set of equation:
	 * 
	 * T(n/2) = cn/2 + T(n/4), T(n/4) = cn/2 + T(n/8) . . . T(2) = c.2 + T(1),
	 * T(1) = c.1 + ... Summing the equations and cross-cancelling like values
	 * produces a linear result.
	 * 
	 * c(n + n/2 + n/4 + ... + 2 + 1) = c(2n) //sum of a GP Hence, it's O(n)
	 * 
	 * 简单的计算： As in quick sort, we have to do partition in halves *, and then in
	 * halves of a half, but this time, we only need to do the next round
	 * partition in one single partition (half) of the two where the element is
	 * expected to lie in.
	 * 
	 * It is like (not very accurate)
	 * 
	 * 第一遍partition, O(n), 然后剩下的一般 O(1/2n) n + 1/2 n + 1/4 n + 1/8 n + .....1 <
	 * 2 n
	 */
	// https://www.jiuzhang.com/solutions/kth-largest-element/
	public static int quickSelect(int[] nums, int start, int end, int k) {
		int left = start;
		int right = end;
		// Note: 不同与binary search, quick sort的mid 是在while循环外面先算好，
		// 每一个while循环利用一个pivot分割数组
		int mid = start + (end - start) / 2;
		int pivot = nums[mid];

		// decedning order, partition Array
		while (left <= right) {
			while (left <= right && nums[left] > pivot) {
				left++;
			}
			while (left <= right && nums[right] < pivot) {
				right--;
			}
			if (left <= right) {
				int temp = nums[left];
				nums[left] = nums[right];
				nums[right] = temp;
				left++;
				right--;
			}
		}// end while
			// 最后退出的情况应该是右指针在左指针左边一格
			// 这时如果右指针还大于等于k，说明kth在左半边， 通过这个&& 关系，每次其实只要排序k所在的半边就好
		if (start < right && k <= right) {
			quickSelect(nums, start, right, k);
		}
		// 这时如果左指针还小于等于k，说明kth在右半边
		if (left < end && k >= left) {
			quickSelect(nums, left, end, k);
		}
		// quick select 不同于 quick sort在于， 不用sort整个array
		// [6, 5, 4, 2, 3, 1]
		// 本题目， 当出现上面排序后，结果已经返回
		return nums[k];
	}// end method
	
	
	
	/**
	 * jiuzhang 算法强化班，老师模板
	 * 
	 * http://www.jiuzhang.com/solutions/kth-largest-element/
	 */

	public static int kthLargestElement2(int[] nums, int k) {
		// write your code here
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (k <= 0) {
			return 0;
		}
		return helper(nums, 0, nums.length - 1, nums.length - k + 1);

	}

	public static int helper(int[] nums, int l, int r, int k) {
		if (l == r) {
			return nums[l];
		}
		int position = partition(nums, l, r);
		if (position == k - 1) {
			return nums[position];
			// K是第几大或小的数，若比较index, k需要减1
		} else if (k - 1 > position) {
			return helper(nums, position + 1, r, k);
		} else {
			return helper(nums, l, position - 1, k);
		}
	}

	public static int partition(int[] nums, int l, int r) {
		// 初始化左右指针和pivot
		int left = l, right = r;
		int pivot = nums[left];

		// 进行partition
		while (left < right) {
			while (left < right && nums[right] >= pivot) {
				right--;
			}
			nums[left] = nums[right];
			while (left < right && nums[left] <= pivot) {
				left++;
			}
			nums[right] = nums[left];
		}

		// 返还pivot点到数组里面
		nums[left] = pivot;
		return left;
	}

	/**
	 * Jun's 降序排列， from 令狐老师课上写的
	 */
	public int kthLargestElementJun(int k, int[] nums) {
		if (nums == null || nums.length == 0 || k > nums.length) {
			return -1;
		}

		return quickSelect(nums, 0, nums.length - 1, k);
	}

	public int quickSelectJun(int[] nums, int start, int end, int k) {

		if (start == end) {
			return nums[start];
		}

		int left = start;
		int right = end;
		int mid = start + (end - start) / 2;
		int pivot = nums[mid];
		// descending order
		while (left <= right) {
			while (left <= right && nums[left] > pivot) {
				left++;
			}
			while (left <= right && nums[right] < pivot) {
				right--;
			}

			if (left <= right) {
				int temp = nums[left];
				nums[left] = nums[right];
				nums[right] = temp;
				left++;
				right--;
			}
		}// end while

		// start ~ right, right+1 ~ left-1, left ~ end
		if (start + (k - 1) <= right) {
			return quickSelectJun(nums, start, right, k);
		}
		if (start + (k - 1) >= left) {
			return quickSelectJun(nums, left, end, k - (left - start));
		}
		// 除了递归循环调用后，right都跑到要找的数字的前面去了
		return nums[right + 1];
	}

	// ============下面都是Heap的方法
	/**
	 * method 3 Min Heap O(nlog(k)). Space complexity is O(k) k是heap的大小
	 * （这题目如果先排序真个数组再找，就是O(nlog(n))）
	 * 
	 * 用min Heap，因为min heap 堆顶(也就是peek(),poll()返回的值)始终维护着当前堆里的最小元素,
	 * 这样在循环操作的过程中，用poll，我们容易去除掉小的数（即堆顶元素）
	 * 
	 * 那么剩下的在 min heap,里的元素都是kth大 或 larger than kth大的数字。由于kth大的数字，相对其还在min
	 * heap里的数字是最小的，所以会在堆顶位置，最后peek 或 poll操作就可以得到
	 * 
	 * 虽然剩下来的数字，第一个是kth，大小的元素（带当前堆里是最小的），但是，你debug时候，会看到这个minHeap,里的元素并不一定是排序的
	 * minHeap仅仅保证堆顶是最小的
	 * 
	 * 注意：对于min heap 1. 无论你什么时候加一个元素进来，这要该元素最小，就会跑到堆顶
	 * 2.如果你删除了当前的堆顶元素，那么堆里第二小的数字，会自动跑到堆顶
	 * 
	 * 
	 */
	public static int findKthLargestMinHeap(int[] nums, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
		for (int i : nums) {
			// Note: you must put it into the queue first
			minHeap.offer(i);

			if (minHeap.size() > k) {
				minHeap.poll();
			}
		}
		return minHeap.peek();
	}

	/**
	 * 用Max Heap
	 */
	public static int findKthLargestMaxHeap(int[] nums, int k) {
		// 逆序排列，即：构建max heap,堆顶部一直是最大的那个元素
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		};
		// Max Heap，仅仅能保证了堆顶的元素是整个堆最大的那一个， 所以需要把所有元素放到queue,里
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k,
				comparator);
		for (int i : nums) {
			// Note: you must put it into the queue first
			maxHeap.offer(i);
		}

		// 每poll 一次，取出来是当前堆里最大的，要取kth大的，我们poll k-1次，最后剩下的堆顶元素就是kth
		int result = 0;
		for (int i = 0; i < k; i++) {
			result = maxHeap.poll();
		}
		return result;
	}
}
