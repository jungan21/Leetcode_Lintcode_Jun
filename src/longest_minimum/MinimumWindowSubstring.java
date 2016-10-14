package longest_minimum;

import java.util.HashMap;

public class MinimumWindowSubstring {

	/**
	 * 
	 这道题也是用滑动窗口的思想，思想跟 Substring with Concatenation of All
	 * Words是一样的，同样是利用HashMap来存Dict
	 * ，然后来遍历整个母串。因为这里是要求最短的包含子串的字符串，所以中间是可以允许有非子串字符的
	 * ，当遇见非子串字符而count又没到子串长度时，可以继续走。
	 * 
	 * 当count达到子串长度，说明之前遍历的这些有符合条件的串，用一个pre指针帮忙找，pre指针帮忙找第一个在HashMap中存过的，
	 * 并且找到后给计数加1后的总计数是大于0的，判断是否为全局最小长度，如果是，更新返回字符串res，更新最小长度，如果不是，继续找。
	 *
	 * http://www.cnblogs.com/springfor/p/3872559.html
	 * 
	 * http://blog.csdn.net/linhuanmars/article/details/20343903
	 * 
	 * http://www.lifeincode.net/programming/leetcode-minimum-window-substring-
	 * java/
	 * 
	 */

	public static void main(String[] args) {
		// minWindow("ADOBECODEBANC", "ABC");
		System.out.println(new MinimumWindowSubstring().minWindowJiuzhang(
				"abcd", "ac"));
	}

	public static String minWindow(String S, String T) {
		String result = "";
		if (S == null || T == null || S.length() == 0 || T.length() == 0)
			return result;

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < T.length(); i++) {
			if (!map.containsKey(T.charAt(i)))
				map.put(T.charAt(i), 1);
			else
				map.put(T.charAt(i), map.get(T.charAt(i)) + 1);
		}

		int count = 0;
		int pre = 0;
		int minLen = S.length() + 1;
		for (int i = 0; i < S.length(); i++) {
			if (map.containsKey(S.charAt(i))) {
				if (map.get(S.charAt(i)) > 0) {
					count++;
				}
				map.put(S.charAt(i), map.get(S.charAt(i)) - 1);

				// 移动窗口左端的(pre)条件是当找到满足条件的串之后，一直移动窗口左端直到有字典里的字符不再在窗口里
				while (count == T.length()) {
					if (minLen > i - pre + 1) {
						result = S.substring(pre, i + 1);
						minLen = i - pre + 1;
					}
					System.out.println(result);
					// 移动窗口
					if (map.containsKey(S.charAt(pre))) {
						// ict.get(S.charAt(pre)) + 1
						// 意思，表示移动窗口，我们在将来后面的序列中还需要找到pre
						map.put(S.charAt(pre), map.get(S.charAt(pre)) + 1);
						// map.get(S.charAt(pre)) > 0
						// 表示，我们后面还需要重新找这个数，然后左窗口pre往前移
						if (map.get(S.charAt(pre)) > 0) {
							count--;
						}
					}
					pre++;
				}
			}// end for if(map.containsKey(S.charAt(i)))
		}

		return result;
	}

	/**
	 * jiuzhang:http://www.jiuzhang.com/solutions/minimum-window-substring/
	 */

	public String minWindowJiuzhang(String Source, String Target) {
		// queueing the position that matches the char in T
		/**
		 * note: 必须设置为最大值，如果设置为Source.length(), 结果不对，列如："abc", "ac"， 正确答案是abc,
		 * 但是如果设置为source.length(),返回空结果，因为 if (j-i < minlen)条件得不到满足，不会执行
		 * 
		 */
		int minLen = Integer.MAX_VALUE;
		String minStr = "";

		int[] sourcehash = new int[256];
		int[] targethash = new int[256];

		initTargetHash(targethash, Target);
		int j = 0, i = 0;
		for (i = 0; i < Source.length(); i++) {
			while (!valid(sourcehash, targethash) && j < Source.length()) {
				sourcehash[Source.charAt(j)]++;
				j++;
			}
			/**
			 * 必须在valid的时候才能计算长度，否则不对，第一次出了上面的while
			 * loop，表示得到一个valid的解，而如果后面没有解了，i由于for
			 * loop,会一直往前做，j-i会越来越小，即使对于不valid的结果
			 * 
			 * 例如source: abcd, target: ac,
			 * 如果省略if(valid)判断，结果是d,因为j以及跑到终点了，上面while
			 * loop不可能执行，而下面如果没有valid判断，由于i一直往前走j-i会越来越小，当时得到的结果是不合题意要求的
			 */
			if (valid(sourcehash, targethash)) {
				// 只有当前长度(ie.j-i) 比之前的长度都笑的时候，我们才substring, 截取字符串
				if (j - i < minLen) {
					minLen = Math.min(minLen, j - i);
					// 不包括j, 因为除了上面的while loop j已经指向下一个位置了
					minStr = Source.substring(i, j);
				}
			}
			sourcehash[Source.charAt(i)]--;
		}
		return minStr;
	}

	// 其实就是统计target里要找的每个字母出现的次数
	void initTargetHash(int[] targethash, String Target) {
		for (char ch : Target.toCharArray()) {
			targethash[ch]++;
		}
	}

	// 要保证，sourcehash数组里每个字母出现的次数>=, target里的相应字母出现的次数，source相当于dict,
	// 这样才能保证target里的每个字母才能在source里找到
	boolean valid(int[] sourcehash, int[] targethash) {
		for (int i = 0; i < 256; i++) {
			if (targethash[i] > sourcehash[i])
				return false;
		}
		return true;
	}
}
