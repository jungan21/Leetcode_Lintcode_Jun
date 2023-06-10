package company.walmart.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * http://systemdesigns.blogspot.ca/2015/12/rate-limiter.html
 *
 * http://yuanhsh.iteye.com/blog/2233446
 */

public class RateLimiter {

	public static void main(String[] args) {
		RateLimiter test = new RateLimiter();
		System.out.println(test.isRatelimited(1, "login", "3/m", true));
		System.out.println(test.isRatelimited(11, "login", "3/m", true));
		System.out.println(test.isRatelimited(21, "login", "3/m", true));
		System.out.println(test.isRatelimited(30, "login", "3/m", true));
		System.out.println(test.isRatelimited(65, "login", "3/m", true));
		System.out.println(test.isRatelimited(300, "login", "3/m", true));

	}

	private HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();

	public boolean isRatelimited(int timestamp, String event, String rate,
			boolean increment) {
		int start = rate.indexOf("/");
		int total_time = Integer.parseInt(rate.substring(0, start));
		String type = rate.substring(start + 1);

		int time = 1;
		if (type.equals("m"))
			time = time * 60;
		else if (type.equals("h"))
			time = time * 60 * 60;
		else if (type.equals("d"))
			time = time * 60 * 60 * 24;
		int last_time = timestamp - time + 1;

		if (!map.containsKey(event))
			map.put(event, new ArrayList<Integer>());

		boolean rt = find_event(map.get(event), last_time) >= total_time;
		if (increment && !rt)
			insert_event(map.get(event), timestamp);
		return rt;
	}

	public void insert_event(List<Integer> event, int timestamp) {
		event.add(timestamp);
	}

	/**
	 * event ArrayList [1, 11, 21], last_time = - 58, current time = 30
	 * 
	 * last_time = 30 - 60 + 1 = -29
	 * 
	 * find first index >= -29, index = 0;
	 * 
	 * (event.size() -1) the last index of event list - firstInde >= -29
	 * 
	 * 2-0+1 = 3 >= total_time limit 3
	 * 
	 */

	public int find_event(List<Integer> event, int last_time) {
		int left = 0, right = event.size() - 1;
		if (right == -1)
			return 0;
		// means after last_time, there is no call to the login event
		if (event.get(right) < last_time)
			return 0;
		int firstIndex = 0;
		while (left + 1 < right) {
			int mid = (left + right) >> 1;
			if (event.get(mid) >= last_time) {
				right = mid;
			} else {
				left = mid;
			}
		}
		if (event.get(left) >= last_time) {
			firstIndex = left;
		} else if (event.get(right) >= last_time) {
			firstIndex = right;
		}
		// event.size()-1 is the last index
		// firstIndex: first index >= last_time
		return (event.size() - 1) - firstIndex + 1;
	}

}
