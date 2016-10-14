package a_oa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

class LogEntry {
	String visitDate;
	String customerId;

	LogEntry(String visitDate, String customerId) {
		this.visitDate = visitDate;
		this.customerId = customerId;
	}

	public String getDate() {
		return this.visitDate;
	}

	public String getCustomerId() {
		return this.customerId;
	}
}

/**
 * 分析log, 返回 有多少用户，在不同的日期内访问了website
 * 
 * 11-08-2015 A
 *
 * 11-08-2015 A
 * 
 * 11-08-2015 B
 * 
 * 12-08-2015 B
 * 
 * 11-08-2015 C
 * 
 * 12-08-2015 C
 *
 * return 2, 因为只有B 和C 2个用户，在不同的日期访问了website. A，是在同一天访问的 不能算
 */
public class WebSiteVisitLog {

	public static void main(String[] args) {
		List<LogEntry> log = new ArrayList<LogEntry>();
		LogEntry entry1 = new LogEntry("11-08-2015", "A");
		LogEntry entry2 = new LogEntry("11-08-2015", "A");
		LogEntry entry3 = new LogEntry("11-08-2015", "B");
		LogEntry entry4 = new LogEntry("12-08-2015", "B");
		LogEntry entry5 = new LogEntry("13-08-2015", "B");
		LogEntry entry6 = new LogEntry("11-08-2015", "C");
		LogEntry entry7 = new LogEntry("12-08-2015", "C");
		log.add(entry1);
		log.add(entry2);
		log.add(entry5);
		log.add(entry6);
		log.add(entry7);
		log.add(entry3);
		log.add(entry4);

		System.out.println(countRepeatVisitorsOA(log));
		System.out.println(countRepeatVisitors1(log));
	}

	// 提交的版本
	public static int countRepeatVisitorsOA(List<LogEntry> logEntries) {
		if (logEntries == null || logEntries.size() == 0) {
			return 0;
		}
		int count = 0;
		HashMap<String, HashSet<String>> map = new HashMap<>();
		for (LogEntry logEntry : logEntries) {
			String visitDate = logEntry.getDate();
			String customerId = logEntry.getCustomerId();
			if (!map.containsKey(customerId)) {
				HashSet<String> set = new HashSet<String>();
				set.add(visitDate);
				map.put(customerId, set);
			} else {
				HashSet<String> set = map.get(customerId);
				set.add(visitDate);
				map.put(customerId, set);
			}
		}

		for (Map.Entry<String, HashSet<String>> entry : map.entrySet()) {
			if (entry.getValue().size() > 1) {
				count++;
			}
		}
		return count;
	}

	// Space O(n)
	public static int countRepeatVisitors(List<LogEntry> logEntries) {
		if (logEntries == null || logEntries.size() == 0) {
			return 0;
		}
		int count = 0;
		HashSet<String> set = new HashSet<>();
		for (LogEntry logEntry : logEntries) {
			set.add(logEntry.getCustomerId() + " " + logEntry.getDate());
		}

		HashMap<String, Integer> map = new HashMap<>();
		for (String entry : set) {
			String customerId = entry.split(" ")[0];
			if (!map.containsKey(customerId)) {
				map.put(customerId, 1);
			} else {
				map.put(customerId, map.get(customerId) + 1);
			}
		}

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() > 1) {
				count++;
			}
		}
		return count;
	}

	public static int countRepeatVisitors1(List<LogEntry> logEntries) {
		if (logEntries == null || logEntries.size() == 0) {
			return 0;
		}
		int count = 0;
		Collections.sort(logEntries, new Comparator<LogEntry>() {
			public int compare(LogEntry a, LogEntry b) {
				if (a.customerId.compareTo(b.customerId) == 0) {
					return a.getDate().compareTo(b.getDate());
				}
				return a.customerId.compareTo(b.customerId);
			}
		});
		int size = logEntries.size();
		String preUserId = logEntries.get(0).getCustomerId();
		String preDate = logEntries.get(0).getDate();
		int flag = 0;
		for (int i = 1; i < size; i++) {
			String userId = logEntries.get(i).getCustomerId();
			String date = logEntries.get(i).getDate();
			if (userId.equals(preUserId) && !date.equals(preDate)) {
				if (flag == 0) {
					count++;
					flag = 1;
				}
			} else if (!userId.equals(preUserId)) {
				preUserId = userId;
				flag = 0;
			}
		}
		return count;
	}
}
