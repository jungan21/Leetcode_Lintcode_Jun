package sweepline_Interval;

public class Point {
	int time;
	// 1 means start, 0 means end
	int flag;

	Point(int t, int s) {
		this.time = t;
		this.flag = s;
	}
}
