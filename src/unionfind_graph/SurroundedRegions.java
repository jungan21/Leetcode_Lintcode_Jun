package unionfind_graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by
 * 'X'.
 * 
 * A region is captured by flipping all 'O''s into 'X''s in that surrounded
 * region.
 * 
 *
 */

/**
 * http://blog.csdn.net/linhuanmars/article/details/22904855
 * 
 * 根据题目要求，边缘上的'O'是不需要填充的，所以我们的办法是对上下左右边缘做Flood fill算法，把所有边缘上的'O'都替换成另一个字符，比如'#'
 * 。接下来我们知道除去被我们换成'#'的那些顶点，剩下的所有'O'都应该被替换成'X'
 * ，而'#'那些最终应该是还原成'O'，如此我们可以做最后一次遍历，然后做相应的字符替换就可以了
 * 
 * 
 * 复杂度分析上，我们先对边缘做Flood fill算法，因为只有是'O'才会进行，而且会被替换成'#'，所以每个结点改变次数不会超过一次，因而是O(m
 * *n)的复杂度，最后一次遍历同样是O(m *n)，所以总的时间复杂度是O(m*n)。空间上就是递归栈（深度优先搜索）或者是队列（广度优先搜索）的空间，
 * 同时存在的空间占用不会超过O(m+n)（
 * 以广度优先搜索为例，每次队列中的结点虽然会往四个方向拓展，但是事实上这些结点会有很多重复，假设从中点出发，可以想象最大的扩展不会超过一个菱形
 * ，也就是n/2*2+m/2*2=m+n，所以算法的空间复杂度是O(m+n)
 */
public class SurroundedRegions {

	public static void main(String[] args) {
		char[][] board = { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'O', 'X' },
				{ 'X', 'X', 'O', 'X' }, { 'X', 'O', 'X', 'X' } };
		surroundedRegionsUnionFind(board);
	}

	/**
	 * DFS Recursive --- 由于图形一般很大，This solution will causes
	 * java.lang.StackOverflowError, because for a large board, too many method
	 * calls are pushed to the stack and causes the overflow.
	 */
	public static void surroundedRegionsBFS(char[][] board) {
		if (board == null || board.length == 0) {
			return;
		}

		int row = board.length;
		int col = board[0].length;

		// 从board 的外围四条边做BFS BFS函数里控制了，只有外边边上是'O'的点，才做BFS
		for (int i = 0; i < row; i++) {
			BFS(board, i, 0);
			BFS(board, i, col - 1);
		}

		for (int i = 0; i < col; i++) {
			BFS(board, 0, i);
			BFS(board, row - 1, i);
		}
		/**
		 * 上面两个for循环后，是为了处理四周的O，以及把所有这个O连接的O（包括这个O）都以及被涂成#。
		 * 
		 * 接下来，所有还是所有生下来的O都是符合题目要求，需要被替换成X的O。替换好了以后，记得还原原来那些被设置成#的O
		 */
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				// 所有剩下来的O都是符合题目要求，需要被替换成X的O。替换好了以后，记得还原原来那些被设置成#的O
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				} else if (board[i][j] == '#') {
					board[i][j] = 'O';
				}
			}
		}
		System.out.println("end");
	}

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	// recursive DFS, 提交Leetcode时候 会stack over flow, 所以必须用BFS，是非递归版本
	private static void DFS(char[][] board, int i, int j) {
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
				|| board[i][j] != 'O') {
			return;
		}
		// 思路就是先对四周的O进行特殊处理, 把所有这个O连接的O（包括这个O）都涂成#。
		board[i][j] = '#';
		for (int k = 0; k < 4; k++) {
			int newX = i + dx[k];
			int newY = j + dy[k];
			DFS(board, newX, newY);
		}
	}

	// Non-recursive BFS
	private static void BFS(char[][] board, int i, int j) {
		if (board[i][j] != 'O') {
			return;
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		// 初始化
		queue.offer(i * board[0].length + j);
		board[i][j] = '#';
		while (!queue.isEmpty()) {
			int index = queue.poll();
			int row = index / board[0].length;
			int col = index % board[0].length;
			for (int k = 0; k < 4; k++) {
				int newX = row + dx[k];
				int newY = col + dy[k];
				if (newX < 0 || newX >= board.length || newY < 0
						|| newY >= board[0].length || board[newX][newY] != 'O') {
					continue;
				}
				queue.offer(newX * board[0].length + newY);
				board[newX][newY] = '#';
			}
		}
	}

	/**
	 * Union Find method 有问题 http://likesky3.iteye.com/blog/2240270
	 *
	 * 将所有边界可达的O union在一起。设置一个根节点oRoot，边界上的所有O同其union上，非边界的O同其上下左右的O进行union。
	 */

	public static void surroundedRegionsUnionFind(char[][] board) {
		if (board == null || board.length == 0) {
			return;
		}
		UnionFind uf = new UnionFind();
		int row = board.length;
		int col = board[0].length;
		// init Union Find father map, 每个O都是属于自己的集合
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == 'O') {
					uf.father.put(i * col + j, i * col + j);
				}
			}
		}

		// 表示总的所有 和四周围O相邻的O的大集合
		int oRoot = row * col;
		uf.father.put(oRoot, oRoot);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == 'X') {
					continue;
				}
				// 仅仅处理出现在四周为的O， union这些和四周O想连接的O
				if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
					// 表示总的所有 和四周围O相邻的O的大集合
					int allRoot = uf.find(oRoot);
					int curRoot = uf.find(i * col + j);
					if (allRoot != curRoot) {
						uf.union(allRoot, curRoot);
					}
					for (int k = 0; k < 4; k++) {
						int x = i + dx[k];
						int y = j + dy[k];
						if (x < 0 || x >= row || y < 0 || y >= col
								|| board[x][y] != 'O') {
							continue;
						}
						// cRoot 计算必须放在这里，不能挪到 内循环外面，因为每次boss会变
						int cRoot = uf.find(i * col + j);
						int nRoot = uf.find(x * col + y);
						if (nRoot != cRoot) {
							uf.union(cRoot, nRoot);
						}
					}

				}
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == 'O'
						&& uf.find(i * col + j) != uf.find(oRoot)) {
					board[i][j] = 'X';
				}
			}
		}

	}
}
