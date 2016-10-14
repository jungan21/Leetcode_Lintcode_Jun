package a.DP;

/**
 * There are a row of n houses, each house can be painted with one of the k
 * colors. The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the
 * same color.
 * 
 * The cost of painting each house with a certain color is represented by a n x
 * k cost matrix. For example, costs[0][0] is the cost of painting house 0 with
 * color 0; costs[1][2] is the cost of painting house 1 with color 2, and so
 * on... Find the minimum cost to paint all houses.
 * 
 * Notice
 * 
 * All costs are positive integers.
 *
 */
public class PaintHouseII {

	public static void main(String[] args) {

	}

	/**
	 * http://www.cnblogs.com/EdwardLiu/p/5069692.html
	 * 
	 * 和 MinimumAdjustmentCost类似
	 * 
	 * O(n*k*k)
	 */
	public int minCostII(int[][] costs) {
		if (costs == null || costs.length == 0) {
			return 0;
		}
		int n = costs.length;
		int k = costs[0].length;

		int[][] dp = new int[n][k];
		for (int i = 0; i < k; i++) {
			dp[0][i] = costs[0][i];
		}

		for (int i = 1; i < n; i++) {
			// j means color for current house
			for (int j = 0; j < k; j++) {
				int tempMin = Integer.MAX_VALUE;
				// t means color for previous house
				for (int t = 0; t < k; t++) {
					if (t == j) {
						continue;
					}
					tempMin = Math.min(tempMin, dp[i - 1][t]);
				}
				dp[i][j] = costs[i][j] + tempMin;
			}
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < k; i++) {
			// compare last hose with differernt color costs
			result = Math.min(result, dp[n - 1][i]);
		}
		return result;
	}

	/**
	 * 时间 O(N*k) 空间 O(1)
	 * 
	 * 和I的思路一样，不过这里我们有K个颜色，不能简单的用Math.min方法了。如果遍历一遍颜色数组就找出除了自身外最小的颜色呢？
	 * 我们只要把最小和次小的都记录下来就行了，这样如果和最小的是一个颜色，就加上次小的开销，反之，则加上最小的开销。
	 * 
	 * https://segmentfault.com/a/1190000003903965
	 */
	public int minCostIIBetter(int[][] costs) {
		if (costs != null && costs.length == 0)
			return 0;
		int n = costs.length;
		int k = costs[0].length;

		int prevMin = 0, prevSecMin = 0, prevMinIndex = -1;
		for (int i = 0; i < n; i++) {
			// 最小以及 次小
			int currMin = Integer.MAX_VALUE, currSecMin = Integer.MAX_VALUE, currMinIndex = -1;
			for (int j = 0; j < k; j++) {
				costs[i][j] = costs[i][j]
						+ (prevMinIndex == j ? prevSecMin : prevMin);
				// 找出最小和次小的，最小的要记录下标，方便下一轮判断
				if (costs[i][j] < currMin) {
					currSecMin = currMin;
					currMin = costs[i][j];
					currMinIndex = j;
				} else if (costs[i][j] < currSecMin) {
					currSecMin = costs[i][j];
				}
			}
			prevMin = currMin;
			prevSecMin = currSecMin;
			prevMinIndex = currMinIndex;
		}
		return prevMin;
	}
}
