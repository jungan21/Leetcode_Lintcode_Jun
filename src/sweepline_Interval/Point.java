package sweepline_Interval;

public class Point {
	public int time;
	// 1 means start, 0 means end
	public int flag;

	public Point(int t, int s) {
		this.time = t;
		this.flag = s;
	}
}
