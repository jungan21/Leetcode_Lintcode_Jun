package a_oa;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * http://wdxtub.com/interview/14520850399861.html
 *
 */
public class FindKNearestPoint {

	public static void main(String[] args) {

	}

	class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public class kNearestPoint {
		public Point[] Solution(Point[] array, final Point origin, int k) {
			Point[] rvalue = new Point[k];
			int index = 0;
			PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(k,
					new Comparator<Point>() {
						public int compare(Point a, Point b) {
							// 因为找nearest, build maxHeap
							return (int) (getDistance(b, origin) - getDistance(
									a, origin));
						}
					});

			for (int i = 0; i < array.length; i++) {
				if (maxHeap.size() < k) {
					maxHeap.offer(array[i]);
				} else {
					Point temp = maxHeap.peek();
					if (getDistance(temp, origin) > getDistance(array[i],
							origin)) {
						maxHeap.poll();
						maxHeap.offer(array[i]);
					}
				}
			}
			while (!maxHeap.isEmpty())
				rvalue[index++] = maxHeap.poll();
			return rvalue;
		}

		private double getDistance(Point a, Point b) {
			return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y)
					* (a.y - b.y));
		}
	}

}
