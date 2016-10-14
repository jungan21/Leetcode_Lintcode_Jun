package graph_dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given a digit string excluded 01, return all possible letter combinations
 * that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below.
 * 
 * Notice
 * 
 * Although the above answer is in lexicographical order, your answer could be
 * in any order you want.
 * 
 * Example Given "23"
 * 
 * Return ["ad", "ae",
 *
 */
public class LetterCombinationsofaPhoneNumber {

	public static void main(String[] args) {
		System.out.println(new LetterCombinationsofaPhoneNumber()
				.letterCombinations3("23"));
		System.out.println(new LetterCombinationsofaPhoneNumber()
				.letterCombinationsBFS("23"));
	}

	/**
	 * 
	 * https://soulmachine.gitbooks.io/algorithm-essentials/content/java/brute-
	 * force/letter-combinations-of-a-phone-number.html
	 * 
	 * http://blog.csdn.net/kenden23/article/details/17011971
	 * 
	 * http://blog.csdn.net/linhuanmars/article/details/19743197
	 * 
	 * http://www.cnblogs.com/TenosDoIt/p/3771254.html
	 * 
	 * // 时间复杂度O(3^n)，空间复杂度O(n)
	 * 
	 * 假设总共有n个digit，每个digit可以代表k个字符，那么时间复杂度是O(k^n) 就是结果的数量
	 */

	// jiuzhang

	public ArrayList<String> letterCombinations1(String digits) {
		ArrayList<String> result = new ArrayList<String>();

		if (digits == null || digits.equals("")) {
			return result;
		}

		Map<Character, char[]> map = new HashMap<Character, char[]>();
		map.put('0', new char[] {});
		map.put('1', new char[] {});
		map.put('2', new char[] { 'a', 'b', 'c' });
		map.put('3', new char[] { 'd', 'e', 'f' });
		map.put('4', new char[] { 'g', 'h', 'i' });
		map.put('5', new char[] { 'j', 'k', 'l' });
		map.put('6', new char[] { 'm', 'n', 'o' });
		map.put('7', new char[] { 'p', 'q', 'r', 's' });
		map.put('8', new char[] { 't', 'u', 'v' });
		map.put('9', new char[] { 'w', 'x', 'y', 'z' });

		StringBuilder sb = new StringBuilder();
		helper(map, digits, sb, result);

		return result;
	}

	private void helper(Map<Character, char[]> map, String digits,
			StringBuilder sb, ArrayList<String> result) {
		if (sb.length() == digits.length()) {
			result.add(sb.toString());
			return;
		}

		for (char c : map.get(digits.charAt(sb.length()))) {
			sb.append(c);
			helper(map, digits, sb, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	/**
	 * method 2:
	 * 
	 * index 对应是： '0','1','2',...'9'
	 */
	private static final String[] keyboard = new String[] { " ", "", "abc",
			"def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public ArrayList<String> letterCombinations2(String digits) {
		ArrayList<String> result = new ArrayList<String>();

		if (digits == null || digits.equals("")) {
			return result;
		}

		StringBuilder sb = new StringBuilder();
		DFS(digits, sb, result);

		return result;
	}

	private void DFS(String digits, StringBuilder sb, ArrayList<String> result) {
		if (sb.length() == digits.length()) {
			result.add(new String(sb.toString()));
			return;
		}
		/**
		 * ch 就是输入字符里的某一位
		 * 
		 * ch - '0' 就可以把char ch装换成数字，也就是数组的下标
		 */
		char ch = digits.charAt(sb.length());
		String str = keyboard[ch - '0'];
		// 该数字可能取到的字符
		for (char c : str.toCharArray()) {
			sb.append(c);
			DFS(digits, sb, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	/**
	 * method 3: rewrite DFS method (add position parameter)
	 * 
	 * index 对应是： '0','1','2',...'9'
	 */
	public ArrayList<String> letterCombinations3(String digits) {
		ArrayList<String> result = new ArrayList<String>();
		if (digits == null || digits.equals("")) {
			return result;
		}
		StringBuilder sb = new StringBuilder();
		// 0 相当于控制生成结果的position, 从0位开始排列
		DFS(digits, sb, 0, result);

		return result;
	}

	private void DFS(String digits, StringBuilder sb, int pos,
			ArrayList<String> result) {
		if (pos == digits.length()) {
			result.add(new String(sb.toString()));
			return;
		}
		/**
		 * ch 就是输入字符里的某一位
		 * 
		 * ch - '0' 就可以把char ch装换成数字，也就是数组的下标
		 * 
		 * 上面的method里， char ch = digits.charAt(sb.length());
		 * 其实用了sb.length()来起到pos的作用
		 */
		// 取出当前位置的字符数字
		char ch = digits.charAt(pos);
		// 找出当前位数字对应可能的字母
		String str = keyboard[ch - '0'];
		// 该数字可能取到的字符， 对于permutation.java也一样，for循环就是列举出所有可能的结果
		// 对每个可能字母进行DFS搜索
		for (char c : str.toCharArray()) {
			sb.append(c);
			// 当0位排列好了，pos+1,去排下一位值
			DFS(digits, sb, pos + 1, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	/**
	 * 
	 * BFS http://www.jyuan92.com/blog/leetcode-letter-combinations-of-a-phone-number/
	 */
	public List<String> letterCombinationsBFS(String digits) {
		List<String> result = new LinkedList<String>();
		if (null == digits || digits.length() == 0) {
			return result;
		}
		//Note:不能省略，如果result list里面什么都没有， for (String cur : result) 不会被执行
		result.add("");
		String[] keyboards = new String[] { " ", "", "abc", "def", "ghi",
				"jkl", "mno", "pqrs", "tuv", "wxyz" };
		for (int i = 0; i < digits.length(); i++) {
			String keyboard = keyboards[digits.charAt(i) - '0'];
			List<String> list = new LinkedList<String>();
			for (String cur : result) {
				for (int k = 0; k < keyboard.length(); k++) {
					list.add(cur + keyboard.charAt(k));
				}
			}
			result = list;
		}
		return result;
	}

}
