package company.walmart;

import sweepline_Interval.Interval;

import java.util.*;

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
public class IntervalsMerge {

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

		System.out.println(new IntervalsMerge().merge(list));

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

	public int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
		List<int[]> result = new LinkedList<>();

		int[] pre = intervals[0];
		result.add(pre);

		for (int i = 1; i < intervals.length; i++) {
			int[] cur = intervals[i];
			pre = result.get(result.size()-1);
			if (cur[0] > pre[1]){
				result.add(cur);
			} else {
				pre[1] = Math.max(pre[1], cur[1]);
			}
		}
		return result.toArray(new int[0][0]);
		//return result.toArray(new int[result.size()][]);
	}

//	C++ Version
//	https://leetcode.com/problems/merge-intervals/editorial/
//	class Solution {
//		public:
//		vector<vector<int>> merge(vector<vector<int>>& intervals) {
//			sort(intervals.begin(), intervals.end());
//
//			vector<vector<int>> merged;
//			for (auto interval : intervals) {
//				// if the list of merged intervals is empty or if the current
//				// interval does not overlap with the previous, simply append it.
//				if (merged.empty() || merged.back()[1] < interval[0]) {
//					merged.push_back(interval);
//				}
//				// otherwise, there is overlap, so we merge the current and previous
//				// intervals.
//				else {
//					merged.back()[1] = max(merged.back()[1], interval[1]);
//				}
//			}
//			return merged;
//		}
//	};


}
