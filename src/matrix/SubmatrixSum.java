package matrix;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer A, find a submatrix where the sum of numbers is zero. Your
 * code should return the coordinate of the left-up and right-down number.
 * 
 * http://www.lintcode.com/en/problem/submatrix-sum/
 * 
 * [ [1 ,5 ,7], [3 ,7 ,-8], [4 ,-8 ,9], ]
 * 
 * return [(1,1), (2,2)]
 */
public class SubmatrixSum {

	public static void main(String[] args) {
		int[][] A = { { 1, 5, 7 }, { 3, 7, -8 }, { 4, -8, 9 } };
		// int[][] A = {{1,1,1,1,1,1,1,1,1,1,1,-10,1,1,1,1,1,1,1,1,1,1,1}};
		// int[][] A = { { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 },
		// { 1 }, { 1 }, { 1 }, { -10 }, { 1 }, { 1 }, { 1 }, { 1 },
		// { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 } };

		submatrixSumJiuzhang(A);
	}

	/**
	 * jiuzhang
	 */

	public static int[][] submatrixSumJiuzhang(int[][] A) {
		int[][] result = new int[2][2];
		int M = A.length;
		if (M == 0)
			return result;
		int N = A[0].length;
		if (N == 0)
			return result;
		// pre-compute: sum[i][j] = sum of submatrix [(0, 0), (i, j)]
		// (i, j) 表示第i行，第j列
		// 相当于 填写DP 矩阵
		int[][] sum = new int[M + 1][N + 1];
		// 填写第一行， 取前0行，每列都是0
		for (int j = 0; j <= N; ++j)
			sum[0][j] = 0;

		// 填写第一列 取前0列，每行都是0
		for (int i = 0; i <= M; ++i)
			sum[i][0] = 0;

		for (int i = 1; i <= M; ++i) {
			for (int j = 1; j <= N; ++j)
				// 之所以要减去sum[i - 1][j - 1]，因为在算sum[i][j - 1]和sum[i -
				// 1][j]的时候，各自都算了一遍，总的来说就是多算了一遍，所以要去掉
				sum[i][j] = A[i - 1][j - 1] + sum[i][j - 1] + sum[i - 1][j]
						- sum[i - 1][j - 1];
		}

		// 在sum数组上计算
		// 枚举起点行 (从第一行 到倒数第二行)
		for (int start = 0; start < M; start++) {
			// 枚举终点行，从
			for (int end = start + 1; end <= M; end++) {
				// do same as subarray sum
				Map<Integer, Integer> map = new HashMap<Integer, Integer>();
				// 枚举列
				for (int col = 0; col <= N; col++) {
					int diff = sum[end][col] - sum[start][col];
					if (map.containsKey(diff)) {
						int k = map.get(diff);
						result[0][0] = start;
						result[0][1] = k;
						result[1][0] = end - 1;
						result[1][1] = col - 1;
						return result;
					} else {
						map.put(diff, col);
					}
				}
			}
		}
		return result;
	}
}
