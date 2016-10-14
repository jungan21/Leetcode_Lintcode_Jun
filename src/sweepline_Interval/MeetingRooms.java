package sweepline_Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MeetingRooms {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Interval meeting1 = new Interval(9, 12);
		Interval meeting2 = new Interval(12, 13);
		Interval meeting3 = new Interval(13, 14);
		ArrayList<Interval> meetings = new ArrayList<Interval>();
		meetings.add(meeting1);
		meetings.add(meeting2);
		meetings.add(meeting3);
		// System.out.println(minRoom(meetings));

		Interval[] intervals = { meeting1, meeting2, meeting3 };
		System.out.println(canAttendMeetings2(intervals));

	}

	/**
	 * method1 扫描线 ???
	 * 
	 * https://github.com/shawnfan/LintCode/blob/master/Java/Meeting%20Rooms.
	 * java
	 * 
	 */
	public static boolean canAttendMeetings1(Interval[] intervals) {
		if (intervals == null || intervals.length == 0 || intervals.length < 2)
			return true;

		List<Point> pointList = new ArrayList<>();

		for (Interval interval : intervals) {
			pointList.add(new Point(interval.start, 1));
			pointList.add(new Point(interval.end, 0));
		}

		Collections.sort(pointList, new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				if (p1.time == p2.time) {
					return p1.flag - p2.flag;
				} else {
					return p1.time - p2.time;
				}

			}
		});

		int count = 0;
		for (Point p : pointList) {
			if (p.flag == 1) {
				count++;
			} else if (p.flag == 0) {
				count--;
			}

		}
		// Note
		return count == 0;
	}

	// method 2: easy to understand
	// https://hzhou.me/LeetCode/LeetCode-Meeting-Rooms.html
	// sorting takes O(nlogn) time and the overlapping checks take O(n) time, so
	// this idea isO(nlogn) time in total.
	public static boolean canAttendMeetings2(Interval[] intervals) {
		if (intervals.length < 2)
			return true;
		// need to sort first
		Arrays.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval m1, Interval m2) {
				if (m1.start == m2.start) {
					return (int) (m1.end - m2.end);
				}
				return (int) (m1.start - m2.start);
			}
		});
		Interval pre = intervals[0];
		for (int i = 1; i < intervals.length; i++) {
			Interval cur = intervals[i];
			if (cur.start < pre.end) {
				return false;
			}
			pre = cur;
		}
		return true;
	}
}
