package longest_minimum;

/**
 * 
 * Give you an integer matrix (with row size n, column size m)，find the longest
 * increasing continuous subsequence in this matrix. (The definition of the
 * longest increasing continuous subsequence here can start at any row or column
 * and go up/down/right/left any direction).
 * 
 * Given a matrix:
 * 
 * [ [1 ,2 ,3 ,4 ,5], [16,17,24,23,6], [15,18,25,22,7], [14,19,20,21,8],
 * [13,12,11,10,9] ]
 * 
 * return 25
 */

// http://www.jiuzhang.com/solutions/longest-increasing-continuous-subsequence-ii/
// 加上flag 具有记忆化功能以后： TimeO（n*m）,如果没有记忆化，时间复杂度很高
public class LongestIncreasingContinuousSubsequenceII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	int[][] dp;
	int[][] flag;
	int m;
	int n;

	public int longestIncreasingContinuousSubsequenceII(int[][] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		n = A.length;
		m = A[0].length;
		int result = 0;
		// dp[x][y] 以x,y作为结尾的最长子序列
		dp = new int[n][m];
		flag = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dp[i][j] = search(i, j, A);
				result = Math.max(result, dp[i][j]);
			}
		}
		return result;
	}

	int[] dx = { 1, -1, 0, 0 };
	int[] dy = { 0, 0, 1, -1 };

	private int search(int x, int y, int[][] A) {
		// 这样，下面的递归调用可以省略很多时间
		if (flag[x][y] != 0)
			return dp[x][y];

		dp[x][y] = 1;
		int nx, ny;
		for (int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			// note，这里不能用 flag[x][y] 或者visited 数组来filter, 需要单调递增， 所以这里用 >=来过滤
			if (nx < 0 || nx >= n || ny < 0 || ny >= m || A[nx][ny] >= A[x][y]) {
				continue;
			}
			// 如果符合要求，长度要加1， search(nx, ny, A) + 1
			dp[x][y] = Math.max(dp[x][y], search(nx, ny, A) + 1);
		}
		flag[x][y] = 1;
		return dp[x][y];
	}

}
