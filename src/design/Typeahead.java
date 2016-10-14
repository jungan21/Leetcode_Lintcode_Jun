package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * jiuzhang: http://www.lintcode.com/en/problem/typeahead/
 * 
 * Implement typeahead. Given a string and a dictionary, return all words that
 * contains the string as a substring. The dictionary will give at the
 * initialize method and wont be changed. The method to find all words with
 * given substring would be called multiple times.
 *
 *
 * Given dictionary = {"Jason Zhang", "James Yu", "Bob Zhang", "Larry Shi"}
 *
 * search "Zhang", return ["Jason Zhang", "Bob Zhang"].
 *
 * search "James", return ["James Yu"].
 *
 *
 */
public class Typeahead {

	public static void main(String[] args) {
		String[] dictArray = { "Jason Zhang", "James Yu", "Bob Zhang",
				"Larry Shi" };
		HashSet<String> set = new HashSet<>();
		for (String word : dictArray) {
			set.add(word);
		}
		Typeahead test = new Typeahead(set);
		test.search("Zhang");

	}

	// pre build the mapping
	// key: substring value: [word list] this substring is included in which
	// words
	private HashMap<String, List<String>> map = new HashMap<String, List<String>>();

	// @param dict A dictionary of words dict
	public Typeahead(Set<String> dict) {
		// do initialize if necessary
		for (String str : dict) {
			int len = str.length();
			for (int i = 0; i < len; ++i)
				// j from i+1 to <=len
				for (int j = i + 1; j <= len; ++j) {
					String tmp = str.substring(i, j);
					if (!map.containsKey(tmp)) {
						// substring is the key, list 表示哪些words包含该substring
						map.put(tmp, new ArrayList<String>());
						map.get(tmp).add(str);
					} else {
						List<String> index = map.get(tmp);
						//其实就是去重 也可以用： 	if (!index.contains(str)){
						if (!str.equals(index.get(index.size() - 1))) {
							index.add(str);
						}
					}
				}
		}
	}

	// @param str: a string
	// @return a list of words
	public List<String> search(String str) {
		// Write your code here
		if (!map.containsKey(str)) {
			return new ArrayList<String>();
		} else {
			return map.get(str);
		}
	}
}
