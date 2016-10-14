package String;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, write a function to determine if t is an anagram
 * of s.
 * 
 * For example, s = "anagram", t = "nagaram", return true. s = "rat", t = "car",
 * return false
 * 
 * http://www.cnblogs.com/grandyang/p/4694988.html
 * 
 */
public class ValidAnagram {

	public static void main(String[] args) {
		// System.out.println('b' - 'a');
		isAnagram2("a", "a");
	}

	/**
	 * 对于字符出现次数做个统计就好了。因为只有26个小写字母，所以可以建立一个大小为26的索引数组charcount，用来统计每个字符的出现次数。
	 * 对于s, 将其作为字符数组进行遍历，在遍历的过程中，对每个出现的字符计数加一。 对于t， 同样将其遍历，对每个出现的字符计数减一。
	 * 如果s和t是anagram ， 那么最后的charcount数组中所有字符的计数都应该是0，
	 * 否则就不是anagram。为小写字母组成。然后我们再来统计t字符串，如果发现不匹配则返回false。
	 * 
	 * jiuzhang: http://www.jiuzhang.com/solutions/two-strings-are-anagrams/
	 */
	public static boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		// 由于长度是常数，所以space仍然是 O(1), 而不是O(n)
		int[] count = new int[256];

		for (int i = 0; i < s.length(); i++) {
			count[s.charAt(i)]++;
		}

		for (int i = 0; i < t.length(); i++) {
			count[t.charAt(i)]--;
			if (count[t.charAt(i)] < 0) {
				return false;
			}
		}
		return true;
	}

	// What if the inputs contain unicode characters? How would you adapt your
	// solution to such case?
	// If the inputs contain unicode characters, an array with length of 26 is
	// not enough.
	public static boolean isAnagram2(String s, String t) {
		if (s.length() != t.length())
			return false;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			char c1 = s.charAt(i);
			if (map.containsKey(c1)) {
				map.put(c1, map.get(c1) + 1);
			} else {
				map.put(c1, 1);
			}
		}

		for (int j = 0; j < t.length(); j++) {
			char c2 = t.charAt(j);
			if (!map.containsKey(c2)) {
				return false;
			} else {
				if (map.get(c2) == 1) {
					// NOTE
					map.remove(c2);
				} else if (map.get(c2) > 1) {
					map.put(c2, map.get(c2) - 1);
				}
			}
		}

		if (map.size() > 0) {
			return false;
		}

		return true;
	}

}
