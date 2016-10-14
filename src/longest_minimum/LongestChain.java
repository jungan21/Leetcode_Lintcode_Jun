package longest_minimum;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 
 * 给一个字符串数组, 以任意一个单词开始，删除一个字母 ，如果形成的新字符串还在数组的单词堆里面，则是合法的，
 * chain长度增加1.然后继续往下删，每删一个则长度增加1. 举些例子吧：(a, abcd, bcd, abd, cd, c)： abcd
 * 删除一个字母可以变成 bcd ， abd， acd，abc。但是只有bcd， acd 可以往下走，所以下面只要考虑这两个。 bcd 可以变成cd
 * 再变成c。 但是abd删除一个单词不能变成数组的一个单词。所以停止
 * 
 * http://yuancrackcode.com/2015/10/25/longest-chain/
 * 
 * https://github.com/xieqilu/Qilu-leetcode/blob/master/B218.LongestChain.cs
 * 
 * http://buttercola.blogspot.ca/2015/10/zenefits-oa-longest-chain.html
 *
 */
public class LongestChain {

	public int longestchain(String[] words) {
		HashSet<String> dict = new HashSet<String>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// use map to store the longest len for string, save time
		for (String s : words) {
			dict.add(s);
		}
		int longest = 0;
		for (String word : words) {
			// no need to process, since its longest length can be at most
			// longest
			if (word.length() <= longest) {
				continue;
			}
			// word itself is already in the dict, so + 1
			int len = helper(word, dict, map) + 1;
			map.put(word, len);
			longest = Math.max(len, longest);
		}
		return longest;
	}

	public int helper(String word, HashSet<String> dict,
			HashMap<String, Integer> map) {
		int result = 0;
		for (int i = 0; i < word.length(); i++) {
			// remove index 为i的char
			String newStr = word.substring(0, i) + word.substring(i + 1);
			// create a new string by removing a char
			if (dict.contains(newStr)) {
				if (map.containsKey(newStr)) {
					result = Math.max(result, map.get(newStr));
				} else {
					result = Math.max(result, helper(newStr, dict, map) + 1);
				}

			}
		}
		return result;
	}

}
