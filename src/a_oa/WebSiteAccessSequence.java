package a_oa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.careercup.com/question?id=9981709
 *
 * Given a log file containing (User_Id, URL, Timestamp) user can navigate page
 * from one to the other. Find the three page subset sequence repeated maximum
 * number of times. Records are sorted by Timestamp.
 *
 * https://www.careercup.com/question?id=9981709
 */
public class WebSiteAccessSequence {

	public static void main(String[] args) {

		List<String> input = new ArrayList<>();
		input.add("user1:url1");
		input.add("user2:url1");
		input.add("user1:url2");
		input.add("user2:url2");
		input.add("user1:url3");
		input.add("user2:url3");
		input.add("user1:url1");
		input.add("user1:url1");
		WebSiteAccessSequence test = new WebSiteAccessSequence(input);
		test.mostRepeatingPattern(3);

	}

	private Map<String, List<String>> userMap = new HashMap<String, List<String>>();

	WebSiteAccessSequence(List<String> inputList) {
		for (String line : inputList) {
			String user = line.split(":")[0];
			String url = line.split(":")[1];
			if (!userMap.containsKey(user)) {
				List<String> temp = new ArrayList<String>();
				temp.add(url);
				userMap.put(user, temp);
			} else {
				userMap.get(user).add(url);
			}
		}
		System.out.println(userMap);
	}

	// length 就是要找的sequence的长度3
	public List<String> mostRepeatingPattern(int length) {
		Map<List<String>, Integer> patternCunterMap = new HashMap<List<String>, Integer>();
		int max = 0;
		List<String> maxPattern = null;

		for (List<String> pages : userMap.values()) {
			// 因为题目说的是求sequence, 那么这三个一定是连在一起的，每三个都试一下
			for (int i = 0; i <= pages.size() - length; i++) {
				// sublist function
				List<String> pattern = pages.subList(i, i + length);
				if (!patternCunterMap.containsKey(pattern)) {
					patternCunterMap.put(pattern, 1);
				} else {
					Integer count = patternCunterMap.get(pattern) + 1;
					patternCunterMap.put(pattern, count);

				}
				Integer count = patternCunterMap.get(pattern);
				if (count > max) {
					max = count;
					maxPattern = pattern;
				}

			}
		}
		System.out.println(patternCunterMap);
		return maxPattern;
	}
	
	
}
