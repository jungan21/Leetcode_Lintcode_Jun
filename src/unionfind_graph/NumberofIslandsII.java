package unionfind_graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Union Find Algorithm
 * 
 * http://blog.csdn.net/dm_vincent/article/details/7655764
 *
 */

class Point {
	int x, y;

	Point(int a, int b) {
		this.x = a;
		this.y = b;
	}

}

public class NumberofIslandsII {

	public static void main(String[] args) {
		Point[] op = new Point[4];

		op[0] = new Point(0, 0);
		op[1] = new Point(0, 1);
		op[2] = new Point(2, 2);
		op[3] = new Point(2, 1);

		System.out.println(new NumberofIslandsII().numIslands2UnionFind(3, 3,
				op));
	}

	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		boolean[][] map = new boolean[m][n];
		// 可走的方向
		int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		List<Integer> list = new ArrayList<Integer>();
		int island = 0;

		// union find 原始数组，及初始化
		int[] fa = new int[m * n];

		for (int i = 0; i < m * n; i++) {
			fa[i] = i;
		}

		for (int i = 0; i < positions.length; i++) {
			island++;
			map[positions[i][0]][positions[i][1]] = true;
			int x, y;
			int f = positions[i][0] * n + positions[i][1];
			for (int k = 0; k < 4; k++) {
				x = positions[i][0] + dir[k][0];
				y = positions[i][1] + dir[k][1];
				if (x >= 0 && x < m && y >= 0 && y < n && map[x][y]
						&& getfather(fa, x * n + y) != f) {
					fa[getfather(fa, x * n + y)] = f;
					island--;
				}
			}
			list.add(island);
		}
		return list;
	}

	// disjoint-set and path compression
	public int getfather(int[] fa, int i) {
		if (fa[i] == i) {
			return i;
		}
		fa[i] = getfather(fa, fa[i]);// path compression here
		return fa[i];
	}

	/**
	 * Union Find
	 * 
	 * 该方法和九章类似，只不过九章单独谢了个UnionFind 类
	 * http://www.jiuzhang.com/solutions/number-of-islands-ii/
	 * 
	 * O(k) + O(mn); k是operators list的大小， O(mn)是union find初始化时间，由于用了compress
	 * find, 所以find, Union都是O(1)
	 * 
	 * Space: O(p)
	 * 
	 */

	public List<Integer> numIslands2UnionFind(int n, int m, Point[] operators) {
		List<Integer> result = new ArrayList<>();
		if (operators == null || n <= 0 || m <= 0) {
			return result;
		}
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		boolean[][] islands = new boolean[n][m];
		UnionFind uf = new UnionFind();
		int count = 0;
		for (Point p : operators) {
			islands[p.x][p.y] = true;
			count++;
			// m 是列数，得到二位数组一个元素的index (展开成一维数组，下标从0开始)
			// 只有是岛屿的地方，在这周围建立连通块， 初始化union find
			uf.father.put(p.x * m + p.y, p.x * m + p.y);
			// 类似于DFS, 尝试四个方向
			for (int k = 0; k < 4; k++) {
				// 因为4行，2列，所以下面可以hardcode成 0， 1
				int newX = p.x + dx[k];
				int newY = p.y + dy[k];
				if (newX < 0 || newX >= n || newY < 0 || newY >= m
						|| !islands[newX][newY]) {
					continue;
				}

				int root = uf.find(p.x * m + p.y);
				int newRoot = uf.find(newX * m + newY);
				// 因为direction数组里定义了4个方向，在这4个方向位置，应该属于一个集合，所以还合并root
				if (root != newRoot) {
					--count;
					// 这个步不能少，很关键
					uf.union(root, newRoot);
				}

			}// for k loop
			result.add(count);
		}
		return result;
	}

	/**
	 * method 2 采用了与 NumberofIslands 一样的思路， 不能通过 Time Limit Exceeded
	 * 
	 * 因为 DFS 时间复杂度太高： 每一次DFS O(mn), k次 O(kmn), K 就是operators的集合的大小
	 */

	public List<Integer> numIslands2(int n, int m, Point[] operators) {
		List<Integer> list = new ArrayList<Integer>();

		if (operators == null || operators.length == 0) {
			return list;
		}

		int[][] grid = new int[n][m];

		for (Point op : operators) {
			grid[op.x][op.y] = 1;
			list.add(countIsland(grid));
		}
		return list;
	}

	public int countIsland(int[][] grid) {
		HashSet<String> set = new HashSet<String>();
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1 && !set.contains(i + "," + j)) {
					count++;
					NumberofIslands.DFS(grid, set, i, j);
				}
			}
		}
		return count;
	}
}
