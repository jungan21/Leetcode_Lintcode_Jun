package math_bit;

import java.util.HashMap;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on
 * the same straight line.
 *
 * Example Given 4 points: (1,2), (3,6), (0,0), (1,3).
 * 
 * The maximum number is 3.
 */
public class MaxPointsOnaLine {

	static class Point {
		int x;
		int y;

		Point() {
			x = 0;
			y = 0;
		}

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	public static void main(String[] args) {
		Point a = new MaxPointsOnaLine.Point(2, 3);
		Point b = new MaxPointsOnaLine.Point(3, 3);
		Point c = new MaxPointsOnaLine.Point(-5, 3);
		Point[] points = new Point[3];
		points[0] = a;
		points[1] = b;
		points[2] = c;

		// HashMap<Double, Integer> map = new HashMap<Double, Integer>();
		// map.put(0.0, 1);
		// if (map.containsKey(-0.0 + 0.0)) {
		// System.out.println(true);
		// }
		System.out.println(0.0 + 0.0);
		System.out.println(-0.0 + 0.0);
		System.out.println(maxPoints(points));
	}

	/**
	 * （1）两点确定一条直线
	 * 
	 * （2）斜率相同的点落在一条直线上
	 * 
	 * （3）坐标相同的两个不同的点 算作2个点
	 * 
	 */
	public static int maxPoints(Point[] points) {
		if (points == null || points.length == 0) {
			return 0;
		}

		HashMap<Double, Integer> map = new HashMap<Double, Integer>();
		int max = 1;

		for (int i = 0; i < points.length; i++) {
			// shared point changed, map should be cleared and server the new
			// point
			map.clear();

			// maybe all points contained in the list are same points,and same
			// points' k is
			// represented by Integer.MIN_VALUE, 不能少，否则 [[0,0],[0,0]]，正确答案是2，
			// 缺少了就输出1, 如果全是duplicate, 如果不放进去，map里就是空,
			// map.values()返回的是空值，就不会计算max的值
			map.put((double) Integer.MIN_VALUE, 1);

			int dup = 0;
			for (int j = i + 1; j < points.length; j++) {
				if (points[j].x == points[i].x && points[j].y == points[i].y) {
					dup++;
					continue;
				}

				// 注意： 0.0+不能省略， 虽然从数学，数字比较的角度来看 0.0 == - 0.0 is true，
				// 不过，如果0.0是map里已经存在的key, map.containsKey(-0.0)将会返回false,
				// 通过这层关系， 0.0+ 0.0 = 0.0 , 0.0 + -0.0 = 0.0 可以避开这个问题
				// because (double)0/-1 是 -0.0, so we should use 0.0+-0.0=0.0
				// to solve 0.0 !=-0.0
				// problem

				// if the line through two points are parallel to y coordinator,
				// then K(slop) is
				// Integer.MAX_VALUE,slope 就是map的 key 就是斜例
				// Note 0.0不能省略否则答案main函数的输入的数据答案不对
				double slope = points[j].x - points[i].x == 0 ? (double) Integer.MAX_VALUE
						: 0.0 + (double) (points[j].y - points[i].y)
								/ (double) (points[j].x - points[i].x);
				if (map.containsKey(slope)) {
					map.put(slope, map.get(slope) + 1);
				} else {
					// 2个点
					map.put(slope, 2);
				}
			}

			for (int pointsNum : map.values()) {
				// duplicate may exist
				if (pointsNum + dup > max) {
					max = pointsNum + dup;
				}
			}

		}
		return max;
	}

	public static int maxPoints2(Point[] points) {
		if (points == null || points.length == 0) {
			return 0;
		}

		HashMap<Double, Integer> map = new HashMap<Double, Integer>();
		int max = 1;

		for (int i = 0; i < points.length; i++) {
			map.clear();
			map.put((double) Integer.MIN_VALUE, 1);

			int dup = 0;
			for (int j = i + 1; j < points.length; j++) {
				if (points[j].x == points[i].x && points[j].y == points[i].y) {
					dup++;
					continue;
				}
				double key = points[j].x - points[i].x == 0 ? Integer.MAX_VALUE
						: 0.0 + (double) (points[j].y - points[i].y)
								/ (double) (points[j].x - points[i].x);

				if (map.containsKey(key)) {
					map.put(key, map.get(key) + 1);
				} else {
					// 2个点
					map.put(key, 2);
				}
			}
			for (int temp : map.values()) {
				if (temp + dup > max) {
					max = temp + dup;
				}
			}

		}
		return max;
	}

}
