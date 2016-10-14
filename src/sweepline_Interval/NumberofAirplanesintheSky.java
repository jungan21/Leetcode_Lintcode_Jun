package sweepline_Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * http://www.jiuzhang.com/qa/933/ 思路： 这道题是扫描线的基础题目。
 * 首先题目虽然给我们很多区间，很多同学喜欢把区间弄来排序，比如按照区间开始，或者区间结尾排序。
 * 这样做就是题目麻烦很多。比较好的方式是，把每个区间拆开成为两个时间的点
 * ，一个是开时间点，一个是闭时间点，每个点当然要知道他的具体时间是多少。然后把所有的点按照时间排序。
 * 然后模拟一条扫描线，从最开始的时间一直扫描到最终的时间，
 * 扫描的过程当中，维护一个值count记录当前在天上飞机数目，如果遇到一个开始点，那么说明有一架飞机起飞count
 * +1，如果遇到闭时间点，说明有一架飞机下落count-1。 然后看count最大的时候，就是天上飞机最多的时候。
 */

public class NumberofAirplanesintheSky {

	public static void main(String[] args) {
		List<Interval> list = new ArrayList<>();
		list.add(new Interval(10, 14));
		list.add(new Interval(10, 15));
		list.add(new Interval(1, 10));
		System.out.println(new NumberofAirplanesintheSky()
				.countOfAirplanes(list));

	}

	/**
	 * sweep line:
	 * 
	 * 算法介绍：https://www.youtube.com/watch?v=dePDHVovJlE
	 * 
	 * http://www.jiuzhang.com/solutions/number-of-airplanes-in-the-sky/
	 * 
	 * https://nickboy.gitbooks.io/technical-interview-tutorial/content/unknown/
	 * number_of_airplanes_in_the_sky.html
	 * 
	 */

	public int countOfAirplanes(List<Interval> airplanes) {
		List<Point> pointList = new ArrayList<>(airplanes.size() * 2);
		for (Interval interval : airplanes) {
			pointList.add(new Point(interval.start, 1));
			pointList.add(new Point(interval.end, 0));
		}
		/**
		 * 假设有n个interval, 每个interval两个时间点，那么就有2n个时间点，对这2n个时间点按升序排序
		 * 
		 * 对于时间点相等的，按照flag升序排序，即 end(0)在先，start(1)在后，必须这么写，否则出错,
		 * 
		 * 其意思就是，如果在同一时间点既有起飞又有降落，那么必须先把end放前边，让飞机数量降下去，然后再count,
		 * 因为我们的算法是返回这个过程中，最大的值，如果不先把降落的飞机数量减去，计算结果就会大于我们实际求得值
		 * 
		 */
		Collections.sort(pointList, new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				if (p1.time == p2.time) {
					// 因为end 用0，表示， start 用1表示， 所有如果time相等，end
					// 就在先，就表示模拟，让上一家飞机先降落，后面一家飞机再起飞
					return p1.flag - p2.flag;
				} else {
					return p1.time - p2.time;
				}

			}
		});

		int count = 0, ans = 0;
		for (Point p : pointList) {
			if (p.flag == 1)
				count++;
			else
				count--;
			ans = Math.max(ans, count);
		}

		return ans;
	}

}
