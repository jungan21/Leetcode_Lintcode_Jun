package heap;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

import sort.QuickSort;

/**
 * 在N个数组中找到第K大元素，输入是个二维数组，与KthSmallestNumberinSortedMatrix，不同点在于，每行每列都是无序的
 * 
 * n = 2 数组 [[9,3,2,4,7],[1,2,3,4,8]], 第三大元素是 7. n = 2 数组
 * [[9,3,2,4,8],[1,2,3,4,2]], 最大数是 9, 次大数是 8, 第三大数是 7
 */
public class KthLargestinNArrays {

	public static void main(String[] args) {
		int[][] arrays = { { 11 }, { 1, 2, 3, 4, 8 } };
		int k = 4;
		int result = new KthLargestinNArrays().KthInArraysMaxHeap(arrays, k);
		System.out.println(result);
	}

	/**
	 * 时间复杂度高， 用Max Heap会好
	 * 
	 * 维护一个大小为k的Min Heap，扫面N个数组的每一个数
	 * 
	 * 若小于等于堆顶，跳过
	 * 
	 * 若大于堆顶，则剔除堆顶元素，加入该元素
	 * 
	 * http://www.jianshu.com/p/b745b8f0afc3
	 */

	public int KthInArraysMinHeap(int[][] A, int k) {
		// min heap
		Queue<Integer> minHeap = new PriorityQueue<Integer>(k);

		for (int[] aa : A) {
			for (int a : aa) {
				if (minHeap.size() < k) {
					minHeap.offer(a);
				} else {
					if (a < minHeap.peek()) {

					} else {
						minHeap.offer(a);
						minHeap.poll();
					}
				}
			}
		}
		return minHeap.peek();
	}

	/**
	 * Max Heap: http://www.jiuzhang.com/solutions/kth-largest-in-n-arrays/
	 * 
	 * 把每个数组按逆序
	 * 排序，，一行放一个数组，放在一起，看起来就是像是matrix,原理和KthSmallestNumberinSortedMatrix
	 * 一样，只不过这里不同点在于，每行每列都是无序的
	 * 
	 * 
	 * 
	 * n 个 Array, 每个长度为m, O(nm(logm) + klogn)
	 * 
	 * n*mlogm: 每个数组排序需要，mlogm， n个数组
	 * 
	 * klogn：从大小为n的堆里弹出k,次
	 * 
	 * 
	 */

	public int KthInArraysMaxHeap(int[][] A, int k) {
		// k > (A.length * A[0].length) 这个条件不对，因为有可能第一行只有1个元素，但是k很大
		if (A == null || A.length == 0 || k < 1) {
			return 0;
		}

		// MaxHeap
		PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(k,
				new Comparator<Point>() {
					public int compare(Point a, Point b) {
						return b.val - a.val;
					}
				});

		// sort each row in descending order, so that we will user max heap
		for (int i = 0; i < A.length; i++) {
			QuickSort.quickSortDescend(A[i], 0, A[i].length - 1);
		}

		HashSet<String> set = new HashSet<String>();

		// 每行逆序排序，最大元素都在第一列
		for (int i = 0; i < A.length; i++) {
			// 有可能某几行为空数组
			if (A[i] != null && A[i].length != 0) {
				maxHeap.offer(new Point(i, 0, A[i][0]));
				set.add(i + "," + 0);
			}
		}

		int result = 0;
		for (int i = 0; i < k; i++) {
			Point p = maxHeap.poll();
			result = p.val;
			/**
			 * 只能逐行往右边移动，因为你在最开始初始化的时候，每一行的第一个元素加进去heap了，慢慢取出来，逐个往右移就好了
			 * 
			 * 与KthSmallestNumberinSortedMatrix 和
			 * KthSmallestSumInTwoSortedArrays 的不同是这题，最开始把第一列所有元素都加进去了，
			 * 而其他两题目都是只把A[0][0] 放进去
			 */
			put2Heap(p.x, p.y + 1, A, set, maxHeap);
			// 这句话不能加，加入 x=1，
			// x+1=2表示第三行，而我们输入只有2行的情况下，那么put2Heap方法里的A[x].length会报错
			// put2Heap(p.x + 1, p.y + 1, A, set, maxHeap);
		}
		return result;
	}

	public void put2Heap(int x, int y, int[][] A, HashSet<String> set,
			PriorityQueue<Point> maxHeap) {
		int n = A.length;
		// 每一行的长度不一样，题目没有说每行长度一样，不可以用 A[0].length， 之所以这里可以用A[x].length，
		// 因为被调用之前，x没有被加上1的可能性， 注意与KthSmallestNumberinSortedMatrix的区别
		// int m = A[x].length;
		int m = Integer.MIN_VALUE;
		if (x < A.length && A[x] != null) {
			m = A[x].length;
		}

		if (x < 0 || x >= n || y < 0 || y >= m || set.contains(x + "," + y)) {
			return;
		}
		maxHeap.offer(new Point(x, y, A[x][y]));
		set.add(x + "," + y);
	}

	// reverse order
	public void quickSortReverseOrder(int[] A, int start, int end) {
		if (A == null || A.length == 0)
			return;
		// ending point
		if (start >= end) {
			return;
		}

		int left = start;
		int right = end;
		int mid = start + (end - start) / 2;
		int pivot = A[mid];

		while (left <= right) {
			while (left <= right && A[right] < pivot) {
				right--;
			}

			while (left <= right && A[left] > pivot) {
				left++;
			}

			if (left <= right) {
				int temp = A[left];
				A[left] = A[right];
				A[right] = temp;
				left++;
				right--;
			}

		}

		if (start < right) {
			quickSortReverseOrder(A, start, right);
		}
		if (left < end) {
			quickSortReverseOrder(A, left, end);
		}
	}
}
