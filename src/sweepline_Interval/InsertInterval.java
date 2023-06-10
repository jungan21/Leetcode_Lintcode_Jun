package sweepline_Interval;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of non-overlapping & sorted intervals, insert a new interval into
 * the intervals (merge if necessary).
 *
 * Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as
 * [1,5],[6,9].
 * 
 * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in
 * as [1,2],[3,10],[12,16].
 * 
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 *
 */

public class InsertInterval {

	public static void main(String[] args) {

	}

	/**
	 * http://www.programcreek.com/2012/12/leetcode-insert-interval/
	 */
	public ArrayList<Interval> insert(ArrayList<Interval> intervals,
			Interval newInterval) {

		ArrayList<Interval> result = new ArrayList<Interval>();

		if (intervals == null || intervals.size() == 0) {
			result.add(newInterval);
			return result;
		}

		for (Interval interval : intervals) {
			// 绝对的大于 >, >=不对
			if (newInterval.start > interval.end) {
				result.add(interval);
				// 绝对的小于 >, <=不对
			} else if (newInterval.end < interval.start) {
				result.add(newInterval);
				newInterval = interval;
				// 这个地方直接写 else {} 就可以了，其他任何情况都应该做如下的Math.min和Math.max
			} else if (newInterval.start <= interval.end
					|| newInterval.end >= interval.start) {
				newInterval.start = Math.min(interval.start, newInterval.start);
				newInterval.end = Math.max(interval.end, newInterval.end);
			}
		}

		result.add(newInterval);
		return result;
	}

	public int[][] insert(int[][] intervals, int[] newInterval) {
		List<int[]> result = new LinkedList<>();
		if (intervals == null || intervals.length == 0) {
			result.add(newInterval);
			return result.toArray(new int[0][0]);
		}

		for (int i = 0; i < intervals.length; i++) {
			int[] cur = intervals[i];
			if(newInterval[0] > cur[1]){
				result.add(cur);
			} else if (newInterval[1] < cur[0]){
				result.add(newInterval);
				newInterval = cur;
			} else if (newInterval[0] <= cur[1] || newInterval[1] >= cur[0]){
				newInterval[0] = Math.min(cur[0], newInterval[0]);
				newInterval[1] = Math.max(cur[1], newInterval[1]);
			}
		}
		result.add(newInterval);
		return result.toArray(new int[0][0]);
	}

}
