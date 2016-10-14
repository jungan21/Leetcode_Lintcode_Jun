package unionfind_graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * http://www.lintcode.com/en/problem/number-of-islands/
 *
 * Given a boolean 2D matrix, find the number of islands.
 * 
 * Notice
 * 
 * 0 is represented as the sea, 1 is represented as the island. If two 1 is
 * adjacent, we consider them in the same island. We only consider
 * up/down/left/right adjacent.
 *
 */
public class NumberofIslands {

	public static void main(String[] args) {
		int[][] grid = { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 },
				{ 0, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1 } };

		System.out.print(numIslandsUnionFind(grid));
	}

	/**
	 * DFS - mark visted or not：
	 * http://lintcode.peqiu.com/content/lintcode/number_of_islands.html
	 * 
	 * http://www.programcreek.com/2014/04/leetcode-number-of-islands-java/
	 * 
	 * Each element is visited once only. So time is O(m*n).
	 */
	public static int numIslands(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		HashSet<String> set = new HashSet<String>();
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1 && !set.contains(i + "," + j)) {
					count++;
					BFSNonRecursive(grid, set, i, j);
				}
			}
		}
		return count;
	}

	// 其实这个 DFS过程就是merge的过程，将附近的符合一个岛要求的岛都忽略掉,其实就是把联通区域内的岛屿merge掉
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void DFS(int[][] grid, HashSet<String> set, int x, int y) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length
				|| grid[x][y] != 1 || set.contains(x + "," + y)) {
			return;
		}
		// 都不一定需要用set 来track访问过没有，由于没有回朔的过程，直接grid[i][j]='#';都行
		set.add(x + "," + y);
		for (int k = 0; k < 4; k++) {
			int newX = x + dx[k];
			int newY = y + dy[k];
			DFS(grid, set, newX, newY);
		}
	}

	/**
	 * 上面 DFS，的non recursive版本，注意即使是非递归，我们仍然需要hashset来 去重复
	 */

	public static void BFSNonRecursive(int[][] grid, HashSet<String> set,
			int i, int j) {
		Queue<Integer> queue = new LinkedList<Integer>();
		// init the queue
		set.add(i + "," + j);
		queue.offer(i * grid[0].length + j);
		while (!queue.isEmpty()) {
			int index = queue.poll();
			int row = index / grid[0].length;
			int col = index % grid[0].length;
			for (int k = 0; k < 4; k++) {
				int newRow = row + dx[k];
				int newCol = col + dy[k];
				if (newRow < 0 || newRow >= grid.length || newCol < 0
						|| newCol >= grid[0].length
						|| grid[newRow][newCol] != 1
						|| set.contains(newRow + "," + newCol)) {
					continue;
				}
				set.add(newRow + "," + newCol);
				queue.offer(newRow * grid[0].length + newCol);

			}
		}
	}

	/**
	 * DFS + Union-Find Time is O(m*n*log(k)).
	 * 
	 * http://www.programcreek.com/2014/04/leetcode-number-of-islands-java/
	 */
	public static int numIslandsUnionFind(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;

		int m = grid.length;
		int n = grid[0].length;

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		UnionFind uf = new UnionFind();

		// 先把所有的1都看成是岛屿， 是1的都count++,然后再使用Unifon Find去考虑如果是一个连通块里的，就合并 同事count--
		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					/**
					 * 对于有岛屿的地方，即grid[i][j]=1,的地方，我们把这些点看成集合的点，其数值(union
					 * find初始化的值)就是其对应的以为数组的下标 i*n+j
					 * 
					 * 然后用union find
					 */
					uf.father.put(i * n + j, i * n + j);
					count++;
				}
			}
		}

		// 然后用Union Find 去去除之前统计的重复的岛屿
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					for (int k = 0; k < 4; k++) {
						int x = i + dx[k];
						int y = j + dy[k];
						if (x < 0 || x >= m || y < 0 || y >= n
								|| grid[x][y] != 1) {
							continue;
						}
						// cRoot 计算必须放在这里，不能挪到
						// 内循环外面，因为没union一次boss会变，所以要取到最新的parent,必须每次都要计算一次
						int cRoot = uf.find(i * n + j);
						int nRoot = uf.find(x * n + y);
						if (nRoot != cRoot) {
							uf.union(cRoot, nRoot);
							count--;
						}
					}
				}// if
			}
		}
		return count;
	}
}
