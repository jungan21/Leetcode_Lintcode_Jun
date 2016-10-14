package a_oa;

import java.util.ArrayList;
import java.util.List;

public class MazePath {

	public static void main(String[] args) {
		int[][] A = { { 1, 2, 3 }, { 4, 7, 0 }, { 5, 6, 9 } };
		// int[][] A = { { 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1
		// },{ 0, 0, 9, 1, 1 } };
		// int[][] A = { { 1, 0, 0, 0 }, { 1, 1, 1, 1 }, { 1, 9, 0, 0 } };
		// int[][] A= {{9}};
		// int[][] A = { { 1, 1, 1, 1, 1, 1 } };
		int[][] B = { { 1, 1, 1, 1, 0, 0 }, { 0, 0, 1, 0, 0, 0 },
				{ 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1, 0 },
				{ 1, 1, 1, 0, 9, 0 } };
		System.out.println(DFS(A));
		// System.out.println(BFS(A, 0, 0));
	}

	// 0表示墙， 其他表示通路
	public static List<List<Integer>> DFS(int[][] A) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		boolean[][] visited = new boolean[A.length][];
		for (int i = 0; i < A.length; i++) {
			visited[i] = new boolean[A[i].length];
		}
		if (A[0][0] == 0) {
			return result;
		}
		if (A[0][0] == 9) {
			path.add(9);
			result.add(path);
			return result;
		}
		helper(A, visited, 0, 0, path, result);
		return result;
	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	/**
	 * http://123.57.208.150/bbs/forum.php?mod=viewthread&tid=154566&fromguid=
	 * hot&extra=&mobile=2
	 * 
	 * 0表示墙， 1 表示通路
	 * 
	 * Note:也可以不用visited 数组，而用题目中的方法int temp = A[x][y]
	 */
	public static void helper(int[][] A, boolean[][] visited, int x, int y,
			List<Integer> path, List<List<Integer>> result) {

		if (x < 0 || y < 0 || x >= A.length || y >= A[x].length || A[x][y] == 0
				|| visited[x][y]) {
			return;
		}
		visited[x][y] = true;
		path.add(A[x][y]);

		if (A[x][y] == 9) {
			result.add(new ArrayList<Integer>(path));
		}

		for (int k = 0; k < 4; k++) {
			int newX = x + dx[k];
			int newY = y + dy[k];
			helper(A, visited, newX, newY, path, result);
		}
		visited[x][y] = false;
		path.remove(path.size() - 1);
	}
}
