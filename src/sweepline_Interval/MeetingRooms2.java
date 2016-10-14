package sweepline_Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRooms2 {

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
		System.out.println(minMeetingRooms(intervals));

	}

	/**
	 * method 1.
	 * 
	 * 扫描线：https://github.com/shawnfan/LintCode/blob/master/Java/Meeting%
	 * 20Rooms %20II.java
	 * 
	 * http://www.meetqun.com/thread-12267-1-1.html
	 */
	public static int minRoom(Interval[] intervals) {
		if (intervals == null || intervals.length == 0)
			return 0;

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
		/**
		 * 其实和airplane in the sky类似，就是求最大的overlapping的时候的数值
		 */
		int result = 0;
		int count = 0;
		for (Point p : pointList) {
			if (p.flag == 1) {
				count++;
			} else if (p.flag == 0) {
				count--;
			}
			result = Math.max(result, count);
		}
		return result;
	}

	/**
	 * method 2
	 */
	public static int minRoom1(ArrayList<Interval> meetings) {
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		Collections.sort(meetings, new Comparator<Interval>() {
			public int compare(Interval m1, Interval m2) {
				if (m1.start == m2.start) {
					return (int) (m1.end - m2.end);
				}
				return (int) (m1.start - m2.start);
			}
		});
		// System.out.println(meetings.get(0).end);
		int curRoom = 0;
		int maxRoom = 0;
		for (int i = 0; i < meetings.size(); i++) {
			int curStart = meetings.get(i).start;
			int curEnd = meetings.get(i).end;
			while (!heap.isEmpty() && heap.peek() <= curStart) {
				heap.poll();
				curRoom--;
			}
			heap.add(curEnd);
			curRoom++;
			maxRoom = Math.max(maxRoom, curRoom);
		}
		return maxRoom;

	}

	/**
	 * method 3
	 */
	// http://www.programcreek.com/2014/05/leetcode-meeting-rooms-ii-java/
	public static int minMeetingRooms(Interval[] intervals) {
		if (intervals == null || intervals.length == 0)
			return 0;

		Arrays.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				return (int) (i1.start - i2.start);
			}
		});

		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		int count = 1;
		queue.offer(intervals[0].end);

		for (int i = 1; i < intervals.length; i++) {
			// only if the current start < the minumum number in queue i.e.
			// peek(), then we need a extra room
			if (intervals[i].start < queue.peek()) {
				count++;

			} else {
				queue.poll();
			}

			queue.offer(intervals[i].end);
		}

		return count;
	}

}
