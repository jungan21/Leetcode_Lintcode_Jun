package unionfind_graph;
import java.util.HashSet;

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
public class MaxAreaOfIsland {

	public static void main(String[] args) {
		int[][] grid = { { 1, 1, 0, 0, 0 }, { 1, 1, 0, 0, 1 },
				{ 0, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1 } };

		System.out.print(new MaxAreaOfIsland().numIslands(grid));
	}

	/**
	 * DFS - mark visted or not：
	 * http://lintcode.peqiu.com/content/lintcode/number_of_islands.html
	 * 
	 * http://www.programcreek.com/2014/04/leetcode-number-of-islands-java/
	 * 
	 * Each element is visited once only. So time is O(m*n).
	 */
	public int numIslands(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		HashSet<String> set = new HashSet<String>();
		int maxIslandCount = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1 && !set.contains(i + "," + j)) {
					int tempResult = DFS(grid, set, i, j);
					System.out.println(tempResult);
					maxIslandCount = Math.max(maxIslandCount, tempResult);
				}
			}
		}
		return maxIslandCount;
	}

	// 其实这个 DFS过程就是merge的过程，将附近的符合一个岛要求的岛都忽略掉,其实就是把联通区域内的岛屿merge掉
	int[] dx = { 1, -1, 0, 0 };
	int[] dy = { 0, 0, 1, -1 };
	public int DFS(int[][] grid, HashSet<String> set, int x, int y) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length
				|| grid[x][y] != 1 || set.contains(x + "," + y)) {
			return 0;
		}
		// 都不一定需要用set 来track访问过没有，由于没有回朔的过程，直接grid[i][j]='#';都行
		int count = 1;
		set.add(x + "," + y);
		for (int k = 0; k < 4; k++) {
			int newX = x + dx[k];
			int newY = y + dy[k];
			count = DFS(grid, set, newX, newY) + count;
		}
		return count;
	}
}
