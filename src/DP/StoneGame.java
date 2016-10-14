package a.DP;

/**
 * 有一个石子归并的游戏。最开始的时候，有n堆石子排成一列，目标是要将所有的石子合并成一堆。合并规则如下：
 * 
 * 每一次可以合并相邻位置的两堆石子 每次合并的代价为所合并的两堆石子的重量之和 求出最小的合并代价
 * 
 *
 */
public class StoneGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * http://www.jiuzhang.com/solutions/stone-game/
	 */

	/**
	 * Thoughts: Based on given outline. sum[i][j] = stone sum between i and j
	 * dp[i][j]: min cost/score if we merge ith pile into jth pile. It can be
	 * dp[0][1], dp[0][2].. or our final result dp[0][n-1] break it by k and
	 * check both side dp[start][k] and dp[start][k+1]; Tricky: add
	 * sum[start][end] at the end.
	 * 
	 * dp[i][j] 表示把第i到第j个石子合并到一起的最小花费
	 * 
	 * 预处理sum[i,j] 表示i到j所有石子价值和
	 * 
	 * dp[i][j] = min(dp[i][k]+dp[k+1][j]+sum[i,j]) 对于所有k属于{i,j}
	 * 
	 * for each i, dp[i][i] = 0 (因为自己与自己合并没有花费)
	 * 
	 * dp[0][n-1]
	 * 
	 * 死胡同:容易想到的一个思路从小往大,枚举第一次合并是在哪?
	 * 
	 * 记忆化搜索的思路,从大到小,先考虑最后的0-n-1 合并的总花费
	 * 
	 */
	public int stoneGame(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}

		int n = A.length;

		// initialize
		int[][] dp = new int[n][n];
		int[][] visit = new int[n][n];

		for (int i = 0; i < n; i++) {
			dp[i][i] = 0;
		}

		// preparation, sum[i][j] = stone sum between i and j
		int[][] sum = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				sum[i][j] = (i == j) ? A[i] : sum[i][j - 1] + A[j];
			}
		}
		return search(0, n - 1, dp, visit, sum);
	}

	int search(int l, int r, int[][] dp, int[][] visit, int[][] sum) {
		if (visit[l][r] == 1)
			return dp[l][r];
		if (l == r) {
			visit[l][r] = 1;
			// l == r, dp[l][r] = 0, 所以直接return 0就可以
			return dp[l][r];
		}

		dp[l][r] = Integer.MAX_VALUE;
		// 对于下标l 到 r, 的，被切割的方法就只有 l 到 r-1种
		for (int k = l; k < r; k++) {
			dp[l][r] = Math.min(dp[l][r], search(l, k, dp, visit, sum)
					+ search(k + 1, r, dp, visit, sum) + sum[l][r]);
		}
		visit[l][r] = 1;
		return dp[l][r];
	}

	/**
	 * jiuzhang method 2
	 * 
	 * http://chuansong.me/n/1987334
	 * 
	 * http://www.gegugu.com/2015/12/04/1152.html
	 * 
	 */

	public int stoneGame2(int[] A) {
		// Write your code here
		if (A.length == 0) {
			return 0;
		}
		int[][] dp = new int[A.length][A.length];
		int[] sums = new int[A.length + 1];
		sums[0] = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = i; j < A.length; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		for (int i = 0; i < A.length; i++) {
			dp[i][i] = 0;
			sums[i + 1] = sums[i] + A[i];
		}

		return search(0, A.length - 1, dp, sums);
	}

	private int search(int start, int end, int[][] dp, int[] sums) {
		if (dp[start][end] != Integer.MAX_VALUE) {
			return dp[start][end];
		}
		int min = Integer.MAX_VALUE;
		for (int k = start; k < end; k++) {
			int left = search(start, k, dp, sums);
			int right = search(k + 1, end, dp, sums);
			int now = sums[end + 1] - sums[start];
			min = Math.min(min, left + right + now);
		}
		dp[start][end] = min;
		return min;
	}

	/**
	 * method 3
	 * 
	 * https://helloyuantechblog.wordpress.com/2015/12/07/lintcode-stone-game/
	 */
}
