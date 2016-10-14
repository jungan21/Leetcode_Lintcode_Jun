package String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a list of strings, find all the palindromic pairs of the string and
 * output the concatenated palindrome.
 * 
 * e.g. [abc, cba], output is abccba, cbaabc. e.g. [aabc, cb], output is cbaabc
 *
 * Consider a list {bat, tab, cat}. Then bat and tab can be joined together to
 * form a palindrome.
 * 
 * 
 * !!! Expecting a O(nk) solution where n = number of words and k is length of
 * the longest word.
 */
public class PalindromicPairofaListofStrings {

	public static void main(String[] args) {
		String[] input = new String[] { "abc", "cba", "c", "c" };

		List<String> result = getPalindromaticPairs(input);

		for (String s : result) {
			System.out.println(s);
		}
	}

	/**
	 * http://buttercola.blogspot.ca/2015/11/airbnb-palindromic-pair-of-list-of.
	 * html
	 */
	public static List<String> getPalindromaticPairs(String[] input) {
		Set<String> result = new HashSet<>();
		if (input == null || input.length == 0) {
			return new ArrayList<>();
		}

		// Step 1: put the reversed order of each word into the map
		Map<String, List<Integer>> map = new HashMap<>();

		for (int i = 0; i < input.length; i++) {
			String str = input[i];
			String reStr = reverse(str);
			if (!map.containsKey(reStr)) {
				List<Integer> indices = new ArrayList<>();
				indices.add(i);
				map.put(reStr, indices);
			} else {
				List<Integer> indices = map.get(reStr);
				indices.add(i);
			}
		}

		// Step 2: Iterate each word
		for (int i = 0; i < input.length; i++) {
			String str = input[i];

			// Get all the prefix of str, and append to the end
			for (int j = 1; j <= str.length(); j++) {
				String prefix = str.substring(0, j);
				String postfix = str.substring(j);

				if (map.containsKey(prefix) && isPalindrome(postfix)) {
					if (map.get(prefix).size() > 1
							|| !map.get(prefix).equals(str)) {
						String palin = str + reverse(prefix);
						result.add(palin);
					}
				}
			}

			// Get all postfix of the str, and insert to front
			for (int j = str.length() - 1; j >= 0; j--) {
				String postfix = str.substring(j);
				String prefix = str.substring(0, j);

				if (map.containsKey(postfix) && isPalindrome(prefix)) {
					if (map.get(postfix).size() > 1
							|| !map.get(postfix).equals(str)) {
						String palin = reverse(postfix) + str;
						result.add(palin);
					}
				}
			}
		}

		return new ArrayList<String>(result);
	}

	private static String reverse(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}

		char[] array = s.toCharArray();
		int lo = 0;
		int hi = array.length - 1;
		while (lo < hi) {
			char temp = array[lo];
			array[lo] = array[hi];
			array[hi] = temp;
			lo++;
			hi--;
		}

		return new String(array);
	}

	private static boolean isPalindrome(String s) {
		if (s == null || s.length() <= 1) {
			return true;
		}

		int lo = 0;
		int hi = s.length() - 1;

		while (lo < hi) {
			if (s.charAt(lo) != s.charAt(hi)) {
				return false;
			}

			lo++;
			hi--;
		}

		return true;
	}

}
