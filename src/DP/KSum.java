package a.DP;

/**
 * Given n distinct positive integers, integer k (k <= n) and a number target.
 * 
 * Find k numbers where sum is target. Calculate how many solutions there are?
 *
 */
public class KSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * f[i][j][t]前i个数取j个数出来能否和为t
	 * 
	 * f[i][j][t] = f[i - 1][j - 1][t - a[i]] or f[i - 1][j][t]
	 * 
	 * f[i][0][0] = 1
	 * 
	 * f[n][k][target]
	 * 
	 * 其实就和背包问题类似
	 */
	public int kSum(int A[], int k, int target) {
		int n = A.length;
		// [target + 1];必须是target+1, 譬如target =3, 则第三维的数组从，0, 1, 2, 3
		int[][][] f = new int[n + 1][k + 1][target + 1];
		for (int i = 0; i <= n; i++) {
			// 前i个数，取0个数出来，组成和为0的 个数
			f[i][0][0] = 1;
		}
		for (int i = 1; i <= n; i++) {
			// j <=k 因为最多只能取k个数, j也同时必须<=i
			for (int j = 1; j <= k && j <= i; j++) {
				for (int t = 1; t <= target; t++) {
					f[i][j][t] = 0;
					if (t >= A[i - 1]) {
						// f[i - 1][j - 1][t - A[i - 1]] 表示取第i个元素，也就是A[i-1]
						f[i][j][t] = f[i - 1][j - 1][t - A[i - 1]];
					}
					// 每种方案都要加在一起，就方案总和
					// f[i - 1][j][t] 表示不取第i个元素，前i-1个，取j 个已经组成和为t
					f[i][j][t] += f[i - 1][j][t];
				} // for t
			} // for j
		} // for i
		return f[n][k][target];
	}

}
