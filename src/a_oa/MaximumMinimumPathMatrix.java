package a_oa;

import java.util.ArrayList;
import java.util.List;

/**
 * 给一个int[][]的matirx，对于一条从左上到右下的path pi，pi中的最小值是mi，求所有mi中的最大值。只能往下或右. 比如：
 * 
 * [8, 4, 7] [6, 5, 9] 有3条path：
 * 
 * 8-4-7-9 min: 4
 * 
 * 8-4-5-9 min: 4
 * 
 * 8-6-5-9 min: 5
 * 
 * return: 5
 *
 */
public class MaximumMinimumPathMatrix {

	public static void main(String[] args) {
		int[][] A = { { 8, 4, 7 }, { 6, 5, 9 } };
		System.out.println(minInPathJun(A));
		System.out.println(maxMinPath(A));
		System.out.println(maxMinPathDP(A));
		System.out.println(maxMinPathDP1(A));
	}

	/**
	 * http://wdxtub.com/interview/14520850399861.html
	 */
	static int maxJun = Integer.MIN_VALUE;

	public static int minInPathJun(int[][] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		List<Integer> path = new ArrayList<>();
		helper(A, 0, 0, path);
		return maxJun;
	}

	public static void helper(int[][] A, int x, int y, List<Integer> path) {
		if (x < 0 || y < 0 || x >= A.length || y >= A[x].length) {
			return;
		}

		if (x == A.length - 1 && y == A[A.length - 1].length - 1) {
			int min = Integer.MAX_VALUE;
			for (int num : path) {
				min = Math.min(min, num);
			}
			maxJun = Math.max(maxJun, min);
		}
		path.add(A[x][y]);
		helper(A, x + 1, y, path);
		helper(A, x, y + 1, path);
		path.remove(path.size() - 1);
	}

	/**
	 * Recommend
	 * 
	 * https://wenyuanjiao.gitbooks.io/algorithm_practice/content/max_min_path.
	 * html
	 * 
	 * http://wdxtub.com/interview/14520850399861.html
	 * 
	 * 用DFS遍历所有edge。
	 * 
	 * 对于这道题来说，不记录visited的点，每次只往下或者右走，就可以保证：遍历所有的path（普通DFS只遍历vertex和edge）
	 * 不会死循环（因为每次只忘右或者下走）
	 * 
	 * max用一个global的变量更新。min作为parameter path到下一个点去就可以记录每条path的local min
	 */
	static private int maxResult, row, col;

	public static int maxMinPath(int[][] matrix) {
		row = matrix.length;
		col = matrix[0].length;
		maxResult = Integer.MIN_VALUE;

		int pathMin = Integer.MAX_VALUE;

		dfsHelper(matrix, pathMin, 0, 0);
		return maxResult;
	}

	public static void dfsHelper(int[][] matrix, int min, int i, int j) {
		if (i >= row || j >= col || i < 0 || j < 0) {
			return;
		}
		if (i == row - 1 && j == col - 1) {
			min = Math.min(min, matrix[i][j]);
			// compare path min with overall max in all path
			maxResult = Math.max(maxResult, min);
			return;
		}
		// find min in each path
		min = Math.min(min, matrix[i][j]);
		dfsHelper(matrix, min, i, j + 1);
		dfsHelper(matrix, min, i + 1, j);
	}

	/**
	 * DP method， 坐标类型 Best One
	 * 
	 * http://www.chenguanghe.com/tag/amazon/
	 */
	public static int maxMinPathDP(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int n = matrix.length;
		int m = matrix[0].length;
		int[][] dp = new int[n][m]; // dp[i,j]路径中最小值 (不过下边有所不同)
		dp[0][0] = matrix[0][0];// [0,0] 肯定在考虑点中
		for (int i = 1; i < n; i++) {
			// 因为方向上只能往下 后往右边走
			dp[i][0] = Math.min(dp[i - 1][0], matrix[i][0]);
		}
		for (int i = 1; i < m; i++) {
			dp[0][i] = Math.min(dp[0][i - 1], matrix[0][i]);
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				// 每次选点的时候,因为路径只可能是从上或者从左,所以选两条路径中较大的,再去合当前值比较.即可
				dp[i][j] = Math.min(Math.max(dp[i - 1][j], dp[i][j - 1]),
						matrix[i][j]);
			}
		}
		return dp[n - 1][m - 1];
	}

	public static int maxMinPathDP1(int[][] matrix) {
		int[] result = new int[matrix[0].length];
		result[0] = matrix[0][0];
		for (int i = 1; i < result.length; i++) {
			result[i] = Math.min(result[i - 1], matrix[0][i]);
		}
		for (int i = 1; i < matrix.length; i++) {
			result[0] = Math.min(matrix[i][0], result[0]);
			for (int j = 1; j < matrix[0].length; j++) {
				result[j] = (result[j] < matrix[i][j] && result[j - 1] < matrix[i][j]) ? Math
						.max(result[j - 1], result[j]) : matrix[i][j];
			}
		}
		return result[result.length - 1];
	}

}
