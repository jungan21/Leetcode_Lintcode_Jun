package longest_minimum;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Given a string s, find the length of the longest substring T that contains at
 * most k distinct characters.
 * 
 * For example, Given s = "eceba", k = 3,
 * 
 * T is "eceb" which its length is 4.
 *
 */
public class LongestSubstringwithAtMostKDistinctCharacters {

	public static void main(String[] args) {

		// String s = "WORLD";
		// int k = 4;
		String s = "ecebbb";
		int k = 3;
		System.out.println(lengthOfLongestSubstringKDistinctJiuzhang(s, k));

	}

	/**
	 * HashMap
	 * 
	 * http://www.cnblogs.com/grandyang/p/5185561.html
	 * 
	 * http://www.cnblogs.com/grandyang/p/5351347.html
	 * 
	 * https://codesolutiony.wordpress.com/2015/05/22/lintcode-longest-substring
	 * -with-at-most-k-distinct-characters/
	 */

	public static int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		if (s != null && s.length() != 0 && k <= 0) {
			return 0;
		}

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		int pre = 0;
		int maxLen = Integer.MIN_VALUE;

		for (int i = 0; i < s.length(); i++) {
			if (!map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), 1);
			} else {
				map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
			}

			// 因为题目要求at most k distinct characters, 所以这时候我们要跑while循环，除了循环，
			// map.size = k
			/**
			 * 不是在 k 的时候移动 pre，而是在 k+1 的时候移动 pre，因为后面出现的 char 可能还是之前出现过的 k
			 * 个里面的,这样就不能吐左边的数字，而是一直往hashmap里塞，因为我们要保证longest substring
			 * （也就是i尽可能大，pre尽可能小）
			 * 
			 * 。另外更新 max 也不对，如果压根没有 k 和不同字符你的 code 就不会更新 max 了。应该先加
			 * 右面的字符，如果超了在移动左面指针直到 k 个不同字符，每加一个右边就更新一次max
			 * 
			 * 这种题目，稍微想一下想达成的条件（invariant）是啥，什么时候这个条件（invariant）broken，
			 * 怎么restore, 然后再去restore. 对于这题目： 这里想要的 invariant 是窗口里有 k 或者更少
			 * distinct char，k 的时候 invariant 没 broken，左边不要吐
			 */
			while (map.size() > k) {
				if (map.get(s.charAt(pre)) > 1) {
					map.put(s.charAt(pre), map.get(s.charAt(pre)) - 1);
				} else if (map.get(s.charAt(pre)) == 1) {
					map.remove(s.charAt(pre));
				}
				pre++;
			}

			// 这一句必须放外面， 否则如果压根没有 k 和不同字符你的code就不会更新max了，每加一个右边就更新一次max
			maxLen = Math.max(maxLen, i - pre + 1);
		}

		return maxLen == Integer.MIN_VALUE ? 0 : maxLen;
	}

	/**
	 * Jiuzhang Template
	 * 
	 * i 左窗口，， j右窗口
	 */
	public static int lengthOfLongestSubstringKDistinctJiuzhang(String s, int k) {
		// write your code here
		int maxLen = 0;

		// Key: letter; value: the number of occurrences.
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int i, j = 0;
		char c;
		for (i = 0; i < s.length(); i++) {
			// 要想得到最长的at most k的长度dinstict character, 就一直往里面塞，一直到map.size()==k
			// break出来
			// while condition 也可以写成： 加上条件map.size() <= k
			while (j < s.length()) {
				c = s.charAt(j);
				if (map.containsKey(c)) {
					map.put(c, map.get(c) + 1);
				} else {
					// 注意不可以把这个条件 写成 map.size() < k 放在 while(j < s.length() &&
					// map.size()<k),
					// 因为map.size()==k的时候，进来有可能是重复的map已经存在的字符，这样，map.put(c,
					// map.get(c) + 1); 这一句就执行不到了
					/**
					 * map.size()==k break与map.put(c,1)顺序不能反， 放在这里表示，
					 * 当有一个新的字符加入进来会导致map.size()>k
					 * 这个时候停止，这时，j也会停留在那个会导致size>k的位置
					 * 
					 * 1. 如果把，map.size()==k break放在map.put(c,1)后，表示刚把一个字符放入map,
					 * 使得size =k的时候就break了退出while loop,
					 * 那么如果之后都是重复的，就少算了长度。再者size刚到k的时候break了，
					 * j++得不到执行，下一轮while循环，还会从同样的j位置， 这样，第一次到达k的j的位置的字符会被处理两次
					 * 譬如eceba k=3来说，字符b就会被执行两次
					 * 
					 * 2. 只有把map.size() ==
					 * k条件放在前面才能一直确保map.size==k这个条件，从而break后能正确计算maxLen,
					 * 再者，才能保证，只要map.size ==k j的位置就停止前进
					 */
					if (map.size() == k)
						break;
					map.put(c, 1);
				}
				j++;
			}
			// 出了上面的while lopp, map.size()==k, 由于j++,j已经跑到下面的位置了
			maxLen = Math.max(maxLen, j - i);

			// 下一次for loop, 左窗口i将会，往前移动，这时候，要去除掉
			c = s.charAt(i);
			if (map.containsKey(c)) {
				int count = map.get(c);
				if (count > 1) {
					map.put(c, count - 1);
				} else {
					map.remove(c);
				}
			}
		}
		return maxLen;
	}

	/**
	 * working version
	 * 
	 * https://discuss.leetcode.com/topic/36337/java-easy-version-to-understand
	 */

	public int lengthOfLongestSubstringKDistinctJun(String s, int k) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		if (s != null && s.length() != 0 && k <= 0) {
			return 0;
		}

		HashMap<Character, Integer> map = new HashMap<>();

		int pre = 0;
		int maxLen = Integer.MIN_VALUE;

		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
			} else if (!map.containsKey(s.charAt(i)) && map.size() < k) {
				map.put(s.charAt(i), 1);
			} else if (!map.containsKey(s.charAt(i)) && map.size() == k) {
				while (map.size() == k) {
					if (map.get(s.charAt(pre)) > 1) {
						map.put(s.charAt(pre), map.get(s.charAt(pre)) - 1);
					} else if (map.get(s.charAt(pre)) == 1) {
						map.remove(s.charAt(pre));
					}
					pre++;
				}
				map.put(s.charAt(i), 1);
			}
			maxLen = Math.max(maxLen, i - pre + 1);
		}

		return maxLen == Integer.MIN_VALUE ? -1 : maxLen;
	}

}
