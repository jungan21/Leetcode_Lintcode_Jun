package DP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * ??? Given a string s and a dictionary of words dict, add spaces in s to
 * construct a sentence where each word is a valid dictionary word.
 * 
 * Return all such possible sentences.
 * 
 * For example, given s = "catsanddog", dict = ["cat", "cats", "and", "sand",
 * "dog"].
 * 
 * A solution is ["cats and dog", "cat sand dog"].
 * 
 * 这道题不同于上一题，不仅要求需要得出字符串s是否可以被切分，同时还需要得出所有的切分组合,
 * 需要我们得到所有能切分的解。前者毫无疑问还是需要使用动态规划来解决，而对于 后者，列出所有组合这种情况基本都是使用回溯的方法。
 * 
 * 这道题难度很大，我们需要采用dp + dfs的方式求解，首先根据dp得到该字符串能否被切分，同时在dp的过程中记录属于字典的子串信息，供后续dfs使用。
 */
public class WordBreakII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "catsanddog";
		Set<String> set = new HashSet<String>();
		set.add("cat");
		set.add("cats");
		set.add("and");
		set.add("sand");
		set.add("dog");
		System.out.println(wordBreakII(s, set));
	}

	/**
	 * DP + DFS 不能提交，主要是， wordBreakII方法里的DP程序慢了
	 */
	public static ArrayList<String> wordBreakII(String s, Set<String> dict) {
		if (s == null || s.length() == 0 || dict == null) {
			return null;
		}
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> wordList = new ArrayList<String>();
		int maxLength = getMaxLengthString(dict);

		boolean[][] isWord = new boolean[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {
				String word = s.substring(i, j + 1);
				isWord[i][j] = dict.contains(word);
				System.out.print(isWord[i][j] + " ");
			}
			System.out.println();
		}
		
		

		boolean[] possible = new boolean[s.length() + 1];
//		possible[s.length()] = true;
//		for (int i = s.length() - 1; i >= 0; i--) {
//			for (int j = i; j < s.length(); j++) {
//				if (isWord[i][j] && possible[j + 1]) {
//					possible[i] = true;
//					break;
//				}
//			}
//		}
		possible[0] = true;
		for (int i = 1; i <= s.length(); i++ ){
			for (int j = i; j >= 0; j--){
				if (i - j > maxLength) {
					break;
				}
				if (possible[j] && isWord[j][i-1]) {
					possible[i] = true;
					break;
				}
			}
		}
		
		
		// 0 is the index;
		DFS(s, dict, 0, wordList, result, possible, isWord, maxLength);
		return result;
	}

	public static void DFS(String s, Set<String> dict, int index,
			ArrayList<String> wordList, ArrayList<String> result,
			boolean canBreak[], boolean[][] isWord, int maxLength) {
		// if can't break, we exit directly
		if (!canBreak[index]) {
			return;
		}
		// 要遍历完s里所有的字符，看看是否得到一句话
		if (index == s.length()) {
			StringBuilder sb = new StringBuilder();
			for (String word : wordList) {
				sb.append(word);
				sb.append(" ");
			}
			// remove the last " "
			sb.deleteCharAt(sb.length() - 1);
			result.add(sb.toString());
			return;
		}
		// 由于最长是maxLength
		for (int i = index; i < s.length() && i < index + maxLength; i++) {
			// 注意这些索引的取值。左字符串的长度从0到len
			if (!isWord[index][i]) {
				// 如果左字符串不在字典中，不需要继续递归
				continue;
			}
			String substring = s.substring(index, i + 1);
			wordList.add(substring);
			DFS(s, dict, i + 1, wordList, result, canBreak, isWord, maxLength);
			wordList.remove(wordList.size() - 1);
		}
	}

	private static int getMaxLengthString(Set<String> dict) {
		int maxLength = 0;
		for (String word : dict) {
			maxLength = Math.max(maxLength, word.length());
		}
		return maxLength;
	}

}
