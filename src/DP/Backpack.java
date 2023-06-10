package DP;

import java.util.Arrays;

/**
 * Problem 单次选择+最大体积
 * 
 * Given m items with size Ai, an integer m denotes the size of a backpack. How
 * full you can fill this backpack?
 * 
 * Notice: You can not divide any item into small pieces.
 * 
 * If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can
 * select [2, 3, 5], so that the max size we can fill this backpack is 10. If
 * the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the
 * backpack.
 * 
 * You function should return the max size we can fill in the given backpack.
 * https://segmentfault.com/a/1190000006325321
 */
public class Backpack {

	public static void main(String[] args) {
		int[] A = { 3, 4, 8, 5 };
		new Backpack().backPack(10, A);
	}

	/**
	 * jiuzhang template:
	 */

	public int backPackJun(int m, int[] A) {
		int dp[][] = new int[A.length + 1][m + 1];
		dp[0][0] = 0;

		for (int i = 1; i <= A.length; i++) {
			dp[i][0] = 0;
		}
		for (int i = 1; i <= m; i++) {
			dp[0][i] = 0;
		}

		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= m; j++) {
				// 不取当前物品
				dp[i][j] = dp[i - 1][j];
				// 取当前物品
				if (j >= A[i - 1]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + A[i - 1]);
				}
			} // for j
		} // for i
		return dp[A.length][m];
	}

	/**
	 * Best :https://segmentfault.com/a/1190000006325321
	 * 
	 * dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-A[i] + A[j]);
	 * 
	 * 
	 * 动规经典题目，用数组dp[i]表示书包空间为i的时候能装的A物品最大容量。两次循环，外部遍历数组A，内部反向遍历数组dp，
	 * 若j即背包容量大于等于物品体积A
	 * [i]，则取前i-1次循环求得的最大容量dp[j]，和背包体积为j-A[i]时的最大容量dp[j-A[i]]与第i个物品体积A
	 * [i]之和即dp[j-A[i]]+A[i]的较大值，作为本次循环后的最大容量dp[i]。
	 * 
	 * 注意dp[]的空间要给m+1，因为我们要求的是第m+1个值dp[m]，否则会抛出OutOfBoundException。
	 */
	public int backPack1(int m, int[] A) {
		int[] dp = new int[m + 1];
		for (int i = 1; i <= A.length; i++) {
			for (int j = m; j >= 0; j--) {
				/**
				 * 在Backpack II中，仅仅换成下面的公式
				 * 
				 * dp[j] = Math.max(dp[j], dp[j - A[i-1]] + V[i-1]);
				 */
				if (j >= A[i - 1]) {
					dp[j] = Math.max(dp[j], dp[j - A[i - 1]] + A[i - 1]);
				}
			}
		}
		return dp[m];
	}

	/**
	 * 2D Array jiuzhang template
	 */
	public int backPack(int m, int[] A) {

		/**
		 * http://www.cnblogs.com/EdwardLiu/p/4269149.html
		 * 
		 * http://www.code123.cc/docs/leetcode-notes/dynamic_programming/
		 * backpack.html
		 * 
		 * http://7371901.blog.51cto.com/7361901/1602320
		 * 
		 * dp[i][j]: 前 i 个 items(注意其在数组里的下标为i-1)）, 是否能 fill a backpack of size
		 * j? true or false.
		 */
		boolean dp[][] = new boolean[A.length + 1][m + 1];

		// 前0个物品，可以组成size为0的背包
		dp[0][0] = true;

		// 前1~n个物品组成体积和为0的背包始终为真
		for (int i = 1; i <= A.length; i++) {
			dp[i][0] = true;
		}
		// 前 0个物品，不可能组成任何size不等于0的背包
		for (int i = 1; i <= m; i++) {
			dp[0][i] = false;
		}

		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= m; j++) {
				/**
				 * dp[i - 1][j]: 不取第i个物品，前i-1个物品，已经fill backpack with size j
				 * 
				 * || 之后的表示表示放入第i个物品情况（其下标为i-1），前 i−1 个物品能否取出一些体积和为，
				 * j减去第i个物品的重量的背包（注意，第i个物品在数组中的下标为i-1）
				 */
				dp[i][j] = dp[i - 1][j]
						|| (j - A[i - 1] >= 0 && dp[i - 1][j - A[i - 1]]);
			} // for j
		} // for i

		for (int i = m; i >= 0; i--) {
			if (dp[A.length][i]) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * 1D Array 优化
	 * 
	 * https://segmentfault.com/a/1190000006325321
	 * 
	 * 二维表的图，很好的解释了如何优化降维度： http://www.hawstein.com/posts/dp-knapsack.html
	 * 
	 * 如何优化： https://github.com/tianyicui/pack/blob/master/V2.pdf
	 * 
	 * 原始方案中用到了二维矩阵来保存result，注意到result的第i行仅依赖于第i-1行的结果（即DP的记忆深度只有2），那么
	 * 能否用一维数组来代替这种隐含的关系呢？我们在内循环j处递减即可。如此即可避免result[i][S]的值由本轮result[i][S-A[i]
	 * ]递推得到。
	 * 
	 * 定义f(i, j)表示前i个物品放入大小为j的空间里能够占用的最大体积，则递推式为：
	 * 
	 * f(i, j) = max( f(i-1, j), f(i-1, j-A[i-1]) + A[i-1] )
	 * 
	 * 递推的时候，对每个i，从后往前计算即可，这样就用一维数组就行了
	 * 
	 * http://www.code123.cc/docs/leetcode-notes/dynamic_programming/backpack.
	 * html
	 * 
	 * http://ihuafan.com/%E7%AE%97%E6%B3%95/lintcode-dynamic-programming-
	 * summary#lintcode-92-backpack-%E8%83%8C%E5%8C%85%E9%97%AE%E9%A2%98
	 */
	public int backPack1D(int m, int[] A) {

		/**
		 * http://www.cnblogs.com/EdwardLiu/p/4269149.html
		 * 
		 * http://www.code123.cc/docs/leetcode-notes/dynamic_programming/
		 * backpack.html
		 */
		boolean dp[] = new boolean[m + 1];
		Arrays.fill(dp, false);
		// 前任意个背包都可以组成size为0的背包
		dp[0] = true;

		for (int a : A) {
			for (int j = m; j >= 0; j--) {
				if (j - a >= 0 && dp[j - a]) {
					dp[j] = dp[j - a];
				}
			} // for j
		} // for i

		for (int j = m; j >= 0; j--) {
			if (dp[j]) {
				return j;
			}
		}
		return 0;
	}

}
