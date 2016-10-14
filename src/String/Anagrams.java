package String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings, return all groups of strings that are anagrams.
 * 
 * Notice
 * 
 * All inputs will be in lower-case
 *
 */
public class Anagrams {

	public static void main(String[] args) {
		String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
		System.out.println(getNewString("tea"));
		System.out.println(anagrams(strs));

	}

	/**
	 * Time: O(n*m) (m is the average length of all input Strings)
	 * 
	 * Note: 如果不用getNewString(),而改用sort方法，时间复杂度就是O(n * mlogm)，因为每个input String
	 * sort需要mlogm的时间
	 * 
	 * Space: O(n * 26), Map<String, List<String>>map里面的List可以忽略不计
	 * 
	 * Leetcode的返回结果是 List<List<String>>, 可以直接result.addAll(map.values()); 就可以了
	 * http://www.programcreek.com/2014/04/leetcode-anagrams-java/
	 * 
	 * 我们这里按照的是Lintcode的返回结果写的List<String>,需要需要迭代Map
	 */
	public static List<String> anagrams(String[] strs) {
		List<String> result = new ArrayList<>();
		if (strs == null || strs.length == 0) {
			return result;
		}

		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		for (String str : strs) {
			/**
			 * getNewString方法关键,和排序一个效果，但是比排序效率高，之所以能这样写，是因为题目说了all inputs are
			 * in Lower-case
			 * 
			 * 无论是sort, 还是getNewString, 后，只是产生了新的Sttring, 不会改变原来的输入String
			 */
			String key = getNewString(str);
			if (!map.containsKey(key)) {
				List<String> list = new ArrayList<String>();
				list.add(str);
				map.put(key, list);
			} else {
				/**
				 * 注意：list.add()方法放回的是boolean,
				 * 
				 * 不能写成：map.put(key, map.get(key).add(str)); !!!
				 */
				map.get(key).add(str);
			}
		}
		// result.addAll(map.values());
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			List<String> list = entry.getValue();
			if (list.size() < 2) {
				continue;
			}
			// 不用循环，直接addAll
			result.addAll(list);
		}
		return result;
	}

	/**
	 * Leetcode 版本， 返回结果不一样
	 */
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new ArrayList<>();
		if (strs == null || strs.length == 0) {
			return result;
		}

		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		for (String str : strs) {
			String key = getNewString(str);
			if (!map.containsKey(key)) {
				List<String> list = new ArrayList<String>();
				list.add(str);
				map.put(key, list);
			} else {
				map.get(key).add(str);
			}
		}
		// 技巧，不用迭代map，map的每个value对应一个List,相当于把许多list, 一起加入result
		result.addAll(map.values());
		return result;
	}

	/**
	 * O(m) m是字符的平均长度 Note: All inputs will be in lower-case.
	 * 由于题目说明了输入都是小写字符，所以，这种写法比排序的效率高
	 */
	//
	// http://www.programcreek.com/2014/04/leetcode-anagrams-java/
	private static String getNewString(String str) {
		char[] arr = new char[26];
		for (int i = 0; i < str.length(); i++) {
			// 两个字符如果是anagrams，通过这个函数计算出来的值一定相等
			arr[str.charAt(i) - 'a']++;
		}
		return new String(arr);
	}

	// O(mlogm) m是字符的平均长度
	private static String sort(String str) {
		char[] charArr = str.toCharArray();
		Arrays.sort(charArr);
		return new String(charArr);
	}

}
