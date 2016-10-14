package Trie;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell, where
 * "adjacent" cells are those horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.
 *
 */
public class WordSearch {

	public static void main(String[] args) {

		String str = "";
		char a = 'a';

		System.out.println(str + a);

		char[][] board = { { 'A', 'B', 'C' }, { 'S', 'F', 'C' },
				{ 'A', 'D', 'E' } };

		System.out.println(exist2(board, "ABCCE"));

	}

	// Time: m*n*4^k (k is the word size)，因为，word的每个字符有四个方向要走，每个字符4个方向，k个字符
	// recuision最深是k层，recursive部分空间复杂度应该是O(k) + O(m*n)(visit array)
	// http://www.cnblogs.com/yuzhangcmu/p/4040418.html
	// method 1: http://www.programcreek.com/2014/06/leetcode-word-search-java/

	/**
	 * 我们知道一次搜索的复杂度是O(E+V)，E是边的数量，V是顶点数量，在这个问题中他们都是O(m*n)量级的（因为一个顶点有固定上下左右四条边）
	 * 加上我们对每个顶点都要做一次搜索，所以总的时间复杂度最坏是O(m^2*n^2)
	 * 
	 * @return
	 */
	public static boolean exist(char[][] board, String word) {
		if (word == null || word.length() == 0)
			return true;
		if (board == null || board.length == 0 || board[0].length == 0)
			return false;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				// last parameter 0 indicate the starting index of the word
				// (seach char by char for word)
				if (board[i][j] == word.charAt(0)) {
					boolean result = dfs(board, word, i, j, 0);
					if (result) {
						return true;
					}
				}
			}
		}

		return false;
	}

	// depth first search explanation:
	// http://www.jiuzhang.com/solutions/word-search/

	public static boolean dfs(char[][] board, String word, int i, int j,
			int wordIndex) {

		if (wordIndex == word.length())
			return true;

		// ending recursive condition
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length - 1) {
			return false;
		}

		board[i][j] = '#';
		// means all char in word have been found
		boolean result = (dfs(board, word, i - 1, j, wordIndex + 1)
				|| dfs(board, word, i + 1, j, wordIndex + 1)
				|| dfs(board, word, i, j - 1, wordIndex + 1) || dfs(board,
				word, i, j + 1, wordIndex + 1));
		// 其实就是还原成原来的字符
		board[i][j] = word.charAt(wordIndex);
		return result;
	}

	// method 2: http://blog.csdn.net/linhuanmars/article/details/24336987
	public static boolean exist2(char[][] board, String word) {
		if (word == null || word.length() == 0)
			return true;
		if (board == null || board.length == 0 || board[0].length == 0)
			return false;
		boolean[][] used = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				/**
				 * Note:
				 * 
				 * 1. 不可以直接写出 return DFS(board, word, 0, i, j,
				 * used)，这种写法，仅仅表示从board,起点（0，0）出出发做DFS能否找到
				 * 当时题目意思是，可以从任一点(i,j)出发去做DFS， 不管起点，知道能找就return true;
				 * 
				 * 2. 下面参数一直是0的意思表示，始终从word的第一个字符开始去在board里找匹配
				 */
				if (DFS(board, word, 0, i, j, used))
					return true;
			}
		}
		return false;
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

	private static boolean DFS(char[][] board, String word, int index, int i,
			int j, boolean[][] used) {
		if (index == word.length())
			return true;
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
				|| used[i][j] || board[i][j] != word.charAt(index))
			return false;

		used[i][j] = true;
		boolean result = DFS(board, word, index + 1, i - 1, j, used)
				|| DFS(board, word, index + 1, i + 1, j, used)
				|| DFS(board, word, index + 1, i, j - 1, used)
				|| DFS(board, word, index + 1, i, j + 1, used);
		used[i][j] = false;
		return result;
	}

	/**
	 * 上面都是普通的 DFS方法
	 * 
	 * Mehtod 2: Trie truee字典树
	 */
	static Trie trie = new Trie();

	public boolean exisJun(char[][] board, String word) {
		if (board == null || word == null) {
			return false;
		}
		// 待搜索的单词 插入Trie
		trie.insert(word);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				// 以字典里没有字符为 Trie.root去做DFS
				if (helper(board, i, j, trie.root)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean helper(char[][] board, int x, int y, TrieNode now) {
		if (now.hasWord) {
			return true;
		}
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length
				|| board[x][y] == '#' || now == null
				|| !now.subtree.containsKey(board[x][y]))
			return false;

		char ch = board[x][y];
		board[x][y] = '#';
		boolean result = helper(board, x + 1, y, now.subtree.get(ch))
				|| helper(board, x - 1, y, now.subtree.get(ch))
				|| helper(board, x, y + 1, now.subtree.get(ch))
				|| helper(board, x, y - 1, now.subtree.get(ch));
		board[x][y] = ch;
		return result;
	}

}
