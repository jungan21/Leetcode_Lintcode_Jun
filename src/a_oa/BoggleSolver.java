package a_oa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given a dictionary, a method to do lookup in dictionary and a M x N board
 * where every cell has one character. Find all possible words that can be
 * formed by a sequence of adjacent charactersNote that we can move to any of 8
 * adjacent characters, but a word should not have multiple instances of same
 * cell.
 *
 */
public class BoggleSolver {

	public static void main(String[] args) {
		String[] dict = { "GEEKS", "FOR", "QUIZ", "GO" };
		char[][] boggle = { { 'G', 'I', 'Z' }, { 'U', 'E', 'K' },
				{ 'Q', 'S', 'E' } };
		System.out.println(findWordsInDict(boggle, dict));
	}

	/**
	 * http://www.geeksforgeeks.org/boggle-find-possible-words-board-characters/
	 */

	public static List<String> findWordsInDict(char[][] boggle, String[] dict) {
		List<String> result = new ArrayList<>();
		HashSet<String> dictSet = new HashSet<>();
		for (String word : dict) {
			dictSet.add(word);
		}
		boolean[][] visited = new boolean[boggle.length][boggle[0].length];

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < boggle.length; i++) {
			for (int j = 0; j < boggle[i].length; j++) {
				helper(boggle, dictSet, i, j, visited, sb, result);
			}
		}
		return result;
	}

	static int[] dx = { 0, 0, 1, -1, 1, -1, 1, -1 };
	static int[] dy = { 1, -1, 0, 0, 1, -1, -1, 1 };

	public static void helper(char[][] boggle, HashSet<String> dictSet, int i,
			int j, boolean visited[][], StringBuilder sb, List<String> result) {

		if (i < 0 || i >= boggle.length || j < 0 || j >= boggle[i].length
				|| visited[i][j]) {
			return;
		}

		if (dictSet.contains(sb.toString()) && !result.contains(sb.toString())) {
			result.add(new String(sb));
		}

		visited[i][j] = true;
		sb.append(boggle[i][j]);
		for (int k = 0; k < 8; k++) {
			int newI = i + dx[k];
			int newJ = j + dy[k];
			helper(boggle, dictSet, newI, newJ, visited, sb, result);
		}
		sb.deleteCharAt(sb.length() - 1);
		visited[i][j] = false;
	}
}
