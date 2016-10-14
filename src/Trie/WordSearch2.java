package Trie;

import java.util.ArrayList;
import java.util.List;

//Given a 2D board and a list of words from the dictionary, find all words in the board.
public class WordSearch2 {

	public static void main(String[] args) {

		char[][] board = { { 'A', 'B', 'C' }, { 'S', 'F', 'C' },
				{ 'A', 'D', 'E' } };
		String[] words = { "ABCCE", "ABC", "ASA" };
		ArrayList<String> list = new ArrayList<String>();
		list.add("ABCCE");
		list.add("ABC");
		list.add("ASA");

		System.out.println(new WordSearch2().wordSearchII(board, list));
	}

	/**
	 * exist复杂度大概是4的单词长度次方，因为每次要走4个方向。一共有n个单词的话再乘以n。 以为针对每个单词的每个字符，要走4个方向
	 * 
	 * 4^k*单词个数 (m*n k是单词的平均长度， words.length 就是单词个数)
	 */

	public static List<String> findWords(char[][] board, String[] words) {
		List<String> list = new ArrayList<String>();
		for (String word : words) {
			if (exist(board, word)) {
				list.add(word);
			}
		}
		return list;
	}

	// method 1: http://www.programcreek.com/2014/06/leetcode-word-search-java/

	// DFS time: O(mn) * O(mn), 第一个O(mn)表示从每个点开始， 第二个O(mn)表示每个DFS

	public static boolean exist(char[][] board, String word) {
		if (word == null || word.length() == 0)
			return true;
		if (board == null || board.length == 0 || board[0].length == 0)
			return false;

		// 这个数组不一定需要，可以使用DFS2方法，或者用一个一维的HashSet set.add(i + "," +j)
		boolean[][] used = new boolean[board.length][board[0].length];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				boolean exist = DFS(board, word, i, j, 0, used);
				if (exist) {
					return true;
				}
			}
		}

		return false;
	}

	// depth first search explanation:
	// http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Graph/dfs.html

	private static boolean DFS(char[][] board, String word, int index, int i,
			int j, boolean[][] used) {
		if (index == word.length())
			return true;
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
				|| used[i][j] || board[i][j] != word.charAt(index))
			return false;
		used[i][j] = true;
		boolean res = DFS(board, word, index + 1, i - 1, j, used)
				|| DFS(board, word, index + 1, i + 1, j, used)
				|| DFS(board, word, index + 1, i, j - 1, used)
				|| DFS(board, word, index + 1, i, j + 1, used);
		used[i][j] = false;
		return res;
	}

	/**
	 * http://blog.csdn.net/linhuanmars/article/details/24336987
	 * 这道题很容易感觉出来是图的题目，其实本质上还是做深度优先搜索。基本思路就是从某一个元素出发，往上下左右深度搜索是否有相等于word的字符串。
	 * 这里注意每次从一个元素出发时要重置访问标记
	 * （也就是说虽然单次搜索字符不能重复使用，但是每次从一个新的元素出发，字符还是重新可以用的）。深度优先搜索的算法就不再重复解释了
	 * ，不了解的朋友可以看看wiki -
	 * 深度优先搜索。我们知道一次搜索的复杂度是O(E+V)，E是边的数量，V是顶点数量，在这个问题中他们都是O(m*n
	 * )量级的（因为一个顶点有固定上下左右四条边
	 * ）。加上我们对每个顶点都要做一次搜索，所以总的时间复杂度最坏是O(m^2*n^2)，空间上就是要用一个数组来记录访问情况
	 * ，所以是O(m*n)。代码如下：
	 */
	public static boolean DFS2(char[][] board, String word, int i, int j,
			int wordIndex) {

		// ending recursive condition
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length - 1) {
			return false;
		}

		if (board[i][j] == word.charAt(wordIndex)) {
			char temp = board[i][j];
			board[i][j] = '#';
			if (wordIndex == word.length() - 1) {
				return true;
			} else if (DFS2(board, word, i - 1, j, wordIndex + 1)
					|| DFS2(board, word, i + 1, j, wordIndex + 1)
					|| DFS2(board, word, i, j - 1, wordIndex + 1)
					|| DFS2(board, word, i, j + 1, wordIndex + 1)) {
				return true;
			}

			board[i][j] = temp;
		}
		return false;
	}

	/**
	 * Best methods
	 * 
	 * 字典树 http://www.jiuzhang.com/solutions/word-search-ii/
	 */

	public ArrayList<String> wordSearchII(char[][] board,
			ArrayList<String> words) {
		ArrayList<String> result = new ArrayList<String>();

		Trie tree = new Trie();
		// 每一个要被搜查的词放入TrieTree
		for (String word : words) {
			tree.insert(word);
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// 把board里的每个位置, 看成字典树的root节点，去在书中找isString == true;
				search(board, i, j, tree.root, result);
			}
		}
		return result;
	}

	public void search(char[][] board, int x, int y, TrieNode root,
			ArrayList<String> result) {
		if (root.hasWord == true) {
			// avoid duplicate
			if (!result.contains(root.s)) {
				result.add(root.s);
			}
		}

		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length
				|| board[x][y] == '#' || root == null
				|| !root.subtree.containsKey(board[x][y]))
			return;

		char now = board[x][y];
		board[x][y] = '#';
		search(board, x + 1, y, root.subtree.get(now), result);
		search(board, x - 1, y, root.subtree.get(now), result);
		search(board, x, y + 1, root.subtree.get(now), result);
		search(board, x, y - 1, root.subtree.get(now), result);
		board[x][y] = now;

	}
}
