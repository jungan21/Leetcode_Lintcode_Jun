package DP;

/**
 * There are a row of n houses, each house can be painted with one of the three
 * colors: red, blue or green. The cost of painting each house with a certain
 * color is different. You have to paint all the houses such that no two
 * adjacent houses have the same color.
 * 
 * The cost of painting each house with a certain color is represented by a n x
 * 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with
 * color red; costs[1][2] is the cost of painting house 1 with color green, and
 * so on... Find the minimum cost to paint all houses.
 *
 */
public class PaintHouse {

	public static void main(String[] args) {

	}

	/**
	 * dp method: https://segmentfault.com/a/1190000003903965
	 * 
	 * 直到房子i，其最小的涂色开销是直到房子i-1的最小涂色开销，加上房子i本身的涂色开销。但是房子i的涂色方式需要根据房子i-1的涂色方式来确定，
	 * 所以我们对房子i
	 * -1要记录涂三种颜色分别不同的开销，这样房子i在涂色的时候，我们就知道三种颜色各自的最小开销是多少了。我们在原数组上修改，可以做到不用空间。
	 * 
	 * 时间 O(N) 空间 O(1)
	 * 
	 */

	public int minCost(int[][] costs) {
		if (costs != null && costs.length == 0)
			return 0;
		int m = costs.length - 1;
		for (int i = 1; i < costs.length; i++) {
			// 涂第一种颜色的话，上一个房子就不能涂第一种颜色，这样我们要在上一个房子的第二和第三个颜色的最小开销中找最小的那个加上
			costs[i][0] = costs[i][0]
					+ Math.min(costs[i - 1][1], costs[i - 1][2]);
			// 涂第二或者第三种颜色同理
			costs[i][1] = costs[i][1]
					+ Math.min(costs[i - 1][0], costs[i - 1][2]);
			costs[i][2] = costs[i][2]
					+ Math.min(costs[i - 1][0], costs[i - 1][1]);
		}
		// 返回涂三种颜色中开销最小的那个
		return Math.min(costs[m][0], Math.min(costs[m][1], costs[m][2]));
	}

	//
	public int minCost1(int[][] costs) {
		if (costs != null && costs.length == 0)
			return 0;
		int m = costs.length;
		int n = costs[0].length;
		// 因为就3种颜色
		int[][] f = new int[m][3];
		for (int i = 0; i < 3; i++) {
			f[0][i] = costs[0][i];
		}

		for (int i = 1; i < m; i++) {
			f[i][0] = Math.min(f[i - 1][1], f[i - 1][2]) + costs[i][0];
			f[i][1] = Math.min(f[i - 1][0], f[i - 1][2]) + costs[i][1];
			f[i][2] = Math.min(f[i - 1][0], f[i - 1][1]) + costs[i][2];
		}
		return Math.min(Math.min(f[m - 1][0], f[m - 1][1]), f[m - 1][2]);
	}

	public int minCost2(int[][] costs) {
		if (costs != null && costs.length == 0)
			return 0;
		int m = costs.length;
		int n = costs[0].length;
		// 因为就3种颜色
		int[][] f = new int[m][3];
		for (int i = 0; i < 3; i++) {
			f[0][i] = costs[0][i];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 0; j < 3; j++) {
				f[i][j] = costs[i][j]
						+ Math.min(f[i - 1][(j + 1) % 3], f[i - 1][(j + 2) % 3]);
			}
		}
		return Math.min(Math.min(f[m - 1][0], f[m - 1][1]), f[m - 1][2]);
	}

	/**
	 * Jun's brute force DFS， 类似于N Queen
	 * 
	 * Lintcode 大数据集过不了
	 * 
	 */

	public int minCostDFS(int[][] costs) {
		if (costs == null || costs.length == 0) {
			return 0;
		}
		int m = costs.length;
		int n = costs[0].length;
		int[] columnForRow = new int[m];
		return helper(costs, columnForRow, 0, 0);
	}

	int result = Integer.MAX_VALUE;

	public int helper(int[][] costs, int[] columnForRow, int sum, int row) {
		if (row == costs.length) {
			return sum;
		}
		int col = 0;
		for (col = 0; col < costs[0].length; col++) {
			if (row != 0 && col == columnForRow[row - 1]) {
				continue;
			}
			columnForRow[row] = col;
			result = Math
					.min(result,
							helper(costs, columnForRow, sum + costs[row][col],
									row + 1));
			columnForRow[row] = 0;
		}
		return result;
	}
}
