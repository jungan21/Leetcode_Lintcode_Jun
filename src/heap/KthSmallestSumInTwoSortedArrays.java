package heap;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Given two integer arrays sorted in ascending order and an integer k. Define
 * sum = a + b, where a is an element from the first array and b is an element
 * from the second one.
 * 
 * Find the kth smallest sum out of all possible sums.
 * 
 * Example Given [1, 7, 11] and [2, 4, 6].
 * 
 * For k = 3, return 7.
 * 
 * For k = 4, return 9.
 * 
 * For k = 8, return 15.
 * 
 * Challenge: Do it in either of the following time complexity:
 * 
 * 1. O(k log point(n,m, k)). where n is the size of A, and m is the size of B.
 * 
 * 2. O( (m + n) log
 */

class Point {
	int x, y, val;

	public Point(int x, int y, int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}
}

public class KthSmallestSumInTwoSortedArrays {

	public static void main(String[] args) {
		int[] A = { 1, 7, 11 };
		int[] B = { 2, 4, 6 };
		int k = 8;
		int sum = new KthSmallestSumInTwoSortedArrays().kthSmallestSum(A, B, k);
		System.out.println(sum);
	}

	/**
	 * Min Heap:
	 * 
	 * https://github.com/shawnfan/LintCode/blob/master/Java/Kth%20Smallest%20
	 * Sum%20In%20Two%20Sorted%20Arrays.java
	 * 
	 * 用Min Heap. 每次把最小的展开，移位。分别x+1,或者y+1:
	 * 因为当下的Min里面x,y都是最小的。所以下一个最小的不是（x+1,y）,就是（x,y+1）。
	 * 
	 * 每次就poll（）一个，放2个新candidate进去就好了。
	 * 注意，这样的做法会用重复，比如例子（7,4）会出现两次。用一个HashSet挡一下。
	 * 
	 * 注意，HashSet的唯一性，用一个"x,y"的string就可以代为解决。
	 * 
	 */

	public int kthSmallestSum(int[] A, int[] B, int k) {
		if (A == null || B == null || A.length == 0 || B.length == 0 || k < 0) {
			return -1;
		}

		PriorityQueue<Point> minHeap = new PriorityQueue<Point>(2,
				new Comparator<Point>() {
					public int compare(Point p1, Point p2) {
						return p1.val - p2.val;
					}
				});
		// avoid 重复访问相同坐标, 只能用String 不可以用Integer
		HashSet<String> set = new HashSet<String>();

		// init minHeap
		Point point = new Point(0, 0, A[0] + B[0]);
		minHeap.offer(point);

		// 由于下标组合和的唯一性, 注意，不能用 x+y, 这种组合不唯一，只能拼接成Sting
		set.add(point.x + "," + point.y);
		int result = 0;

		for (int i = 0; i < k; i++) {
			point = minHeap.poll();
			result = point.val;
			put2Heap(point.x + 1, point.y, A, B, set, minHeap);
			put2Heap(point.x, point.y + 1, A, B, set, minHeap);
		}
		return result;
	}

	private void put2Heap(int x, int y, int[] A, int[] B, HashSet<String> set,
			PriorityQueue<Point> heap) {
		int n = A.length;
		int m = B.length;
		if (x < 0 || x >= n || y < 0 || y >= m || set.contains(x + "," + y)) {
			return;
		}

		// 通过上面的if判断， 被访问过得，以及下标越界的就不考虑了
		Point point = new Point(x, y, A[x] + B[y]);
		heap.offer(point);
		set.add(point.x + "," + point.y);
	}
}