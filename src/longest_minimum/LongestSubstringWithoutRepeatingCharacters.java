package longest_minimum;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring1("aab"));
	}

	/**
	 * jiuzhang template， easy to understand O(n)
	 */
	public int lengthOfLongestSubstring(String s) {
		// ASCII 字符对应的数字是 0 ~ 255
		int[] map = new int[256]; // map from character's ASCII to its last
									// occured index
		// Arrays.fill(map, -1);
		// j相当于右窗口，正常情况下，没有重复，固定住左窗口，右边窗口移动
		int i = 0;
		int j = 0;
		int result = 0;
		// 针对每个i, j往前跑
		for (i = 0; i < s.length(); i++) {
			// 之所以该算法是O(n)是因为i, 和j 两个指针都只是遍历数组一遍， pointer j不会往回跑
			while (j < s.length() && map[s.charAt(j)] == 0) {
				map[s.charAt(j)]++;
				j++;
			}
			result = Math.max(result, j - i);

			// 除了上面while loop, 说明有重复，左边窗口由于i++, 会自动左移
			map[s.charAt(i)]--;
		}

		return result;
	}

	/**
	 * hashset version
	 */

	public static int lengthOfLongestSubstring1(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		HashSet<Character> set = new HashSet<>();
		int maxLen = 1;
		int j = 0;
		for (int i = 0; i < s.length(); i++) {
			while (j < s.length() && !set.contains(s.charAt(j))) {
				set.add(s.charAt(j));
				j++;
			}
			maxLen = Math.max(maxLen, j - i);

			char c = s.charAt(i);
			if (set.contains(c)) {
				set.remove(c);
			}
		}
		return maxLen;
	}
}
