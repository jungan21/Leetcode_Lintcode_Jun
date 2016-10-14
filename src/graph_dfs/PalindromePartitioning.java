package graph_dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a
 * palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * Example Given s = "aab", return:
 * 
 * [ ["aa","b"], ["a","a","b"] ]
 *
 *
 */
public class PalindromePartitioning {

	public static void main(String[] args) {
		String s = "aab";
		System.out.println(new PalindromePartitioning().partition(s));

	}

	public List<List<String>> partition(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}
		List<List<String>> result = new ArrayList<List<String>>();
		List<String> list = new ArrayList<String>();
		DFS(s, 0, list, result);
		return result;
	}

	public void DFS(String s, int pos, List<String> list,
			List<List<String>> result) {

		if (pos == s.length()) {
			// 每轮递归调用完， list.remove(list.size() - 1); 句话会将list清空
			result.add(new ArrayList<String>(list));
			return;
		}

		for (int i = pos; i < s.length(); i++) {
			// NOTE: 必须注意， substring 下标越界会返回错误， 因为i最大为 s.length-1， i+1, 也就是表示取最后一个下标
			String subStr = s.substring(pos, i + 1);
			if (!isPalindrome(subStr)) {
				continue;
			}
			list.add(subStr);
			DFS(s, i + 1, list, result);
			list.remove(list.size() - 1);
		}

	}

	public boolean isPalindrome(String s) {
		int i = 0;
		int j = s.length() - 1;

		while (i < j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			} else {
				i++;
				j--;
			}
		}
		return true;
	}

	/**
	 * method 2:
	 */

	public List<List<String>> partition1(String s) {
		List<List<String>> result = new ArrayList<List<String>>();
		if (s == null || s.length() == 0) {
			return result;
		}
		List<String> list = new ArrayList<>();
		helper(s, list, result, 0);
		return result;
	}

	public void helper(String s, List<String> list, List<List<String>> result,
			int pos) {

		if (pos == s.length()) {
			result.add(new ArrayList<String>(list));
			return;
		}
		for (int i = pos; i < s.length(); i++) {
			// 和上面方法的区别，是不用substring 函数
			if (!isPalindrome(s, pos, i)) {
				continue;
			}
			list.add(s.substring(pos, i + 1));
			helper(s, list, result, i + 1);
			list.remove(list.size() - 1);
		}
	}

	public boolean isPalindrome(String s, int start, int end) {
		if (s == null || s.length() == 0 || s.length() == 1) {
			return true;
		}
		while (start < end) {
			if (s.charAt(start) != s.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

}
