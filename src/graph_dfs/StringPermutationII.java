package graph_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a string, find all permutations of it without duplicates.
 *
 *
 * Example Given "abb", return ["abb", "bab", "bba"].
 * 
 * Given "aabb", return ["aabb", "abab", "baba", "bbaa", "abba", "baab"].
 */
public class StringPermutationII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Jun
	 */
	public List<String> stringPermutation2(String str) {
		List<String> result = new ArrayList<String>();
		if (str == null || str.length() == 0) {
			result.add("");
			return result;
		}
		String s = "";
		char[] arr = str.toCharArray();
		int[] visit = new int[arr.length];
		// you must sort for eliminate duplicate
		Arrays.sort(arr);
		DFS(arr, result, s, visit);
		return result;
	}

	public void DFS(char[] arr, List<String> result, String s, int[] visit) {

		if (s.length() == arr.length) {
			result.add(new String(s));
		}

		for (int i = 0; i < arr.length; i++) {

			if (visit[i] == 1
					|| (i != 0 && arr[i] == arr[i - 1] && visit[i - 1] == 0)) {
				continue;
			}

			visit[i] = 1;
			s = s + arr[i];
			DFS(arr, result, s, visit);
			//回朔
			s = s.substring(0, s.length() - 1);
			visit[i] = 0;
		}
	}

}
