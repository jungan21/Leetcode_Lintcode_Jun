package sweepline_Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * Have you met this question in a real interview? Yes
 * 
 * Example Given intervals => merged intervals:
 * 
 * [ [ [1, 3], [1, 6], [2, 6], => [8, 10], [8, 10], [15, 18] [15, 18] ] ]
 *
 */
public class MergeIntervals {

	public static void main(String[] args) {
		ArrayList<Interval> list = new ArrayList<Interval>();
		Interval interval1 = new Interval(1, 3);
		Interval interval2 = new Interval(2, 6);
		Interval interval3 = new Interval(8, 10);
		Interval interval4 = new Interval(15, 18);

		list.add(interval1);
		list.add(interval2);
		list.add(interval3);
		list.add(interval4);

		System.out.println(new MergeIntervals().merge(list));

	}

	/**
	 * Jun's method:
	 */

	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> result = new ArrayList<>();
		if (intervals == null || intervals.size() == 0) {
			return result;
		}
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval a, Interval b) {
				if (a.start == b.start) {
					return a.end - b.end;
				} else {
					return a.start - b.start;
				}
			}
		});

		Interval pre = intervals.get(0);
		result.add(pre);
		for (int i = 1; i < intervals.size(); i++) {
			Interval cur = intervals.get(i);
			pre = result.get(result.size() - 1);
			// 下面注释里写法也对，不过java是reference传递，如果改变了pre的end属性，在result里的pre也就自动跟着一起改了
			if (cur.start <= pre.end) {
				// Interval newInterval = new Interval(pre.start,
				// Math.max(pre.end, cur.end));
				// result.remove(pre);
				// result.add(newInterval);
				pre.end = Math.max(pre.end, cur.end);
			} else {
				result.add(cur);
			}
		}
		return result;
	}

	/**
	 * O(nlogn) time and O(1) extra space.
	 */

	public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		if (intervals == null || intervals.size() <= 1) {
			return intervals;
		}
		// 必须排序， 否则结果不对
		Collections.sort(intervals, new Comparator<Interval>() {
			// compare 方法的return值得类型必须是int, 不能写成Integer
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
		});

		ArrayList<Interval> result = new ArrayList<Interval>();
		Interval pre = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			Interval curt = intervals.get(i);
			// <= 符号
			if (curt.start <= pre.end) {
				pre.end = Math.max(pre.end, curt.end);
			} else {
				result.add(pre);
				pre = curt;
			}
		}
		result.add(pre);
		return result;
	}
}
