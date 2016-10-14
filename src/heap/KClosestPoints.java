package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * K closest points to a starting point in cartesian plane
 * 
 * Question: Given a list of points and a central point, find the k nearest
 * points from the central point.
 * 
 * Given an array containing N points find the K closest points to the origin in
 * the 2D plane. You can assume K is much smaller than N and N is very large.
 *
 */

// http://www.zrzahid.com/k-closest-points/
// https://shepherdyuan.wordpress.com/2014/07/23/linkedin-k-closest-points/
public class KClosestPoints {
	class Point {
		public int x;
		public int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// O(nlogK) (题目中说了k is much smaller than N)所以用max heap,也可以用min
	// Heap不过是O(nlogn),由于N很大，不合适
	public List<Point> findKClosest(Point[] p, int k) {

		// 降序排
		PriorityQueue<Point> maxHeap = new PriorityQueue<>(10,
				new Comparator<Point>() {
					public int compare(Point a, Point b) {
						return (b.x * b.x + b.y * b.y)
								- (a.x * a.x + a.y * a.y);
					}
				});

		for (int i = 0; i < p.length; i++) {
			if (i < k)
				maxHeap.offer(p[i]);
			else {
				Point peek = maxHeap.peek();
				if ((p[i].x * p[i].x + p[i].y * p[i].y) < (peek.x * peek.x + peek.y
						* peek.y)) {
					maxHeap.poll();
					maxHeap.offer(p[i]);
				}
			}
		}

		List<Point> result = new ArrayList<>();
		while (!maxHeap.isEmpty())
			result.add(maxHeap.poll());

		return result;
	}

	public static void main(String[] args) {

	}

	/**
	 * Actually this problem is similar to finding Kth largest element in an
	 * array, which you can use the Selection algorithm to find the top K
	 * elements in O(n) time. However it is a non-trivial algorithm.
	 */
}
