package DP;

import java.util.ArrayList;

/**
 * Given an integer array, adjust each integers so that the difference of every
 * adjacent integers are not greater than a given number target.
 * 
 * If the array before adjustment is A, the array after adjustment is B, you
 * should minimize the sum of |A[i]-B[i]|
 *
 *
 * Example
 * 
 * Given [1,4,2,3] and target = 1, one of the solutions is [2,3,2,3], the
 * adjustment cost is 2 and it's minimal.
 * 
 * Return 2.
 * 
 * You can assume each number in the array is a positive integer and not greater
 * than 100.
 */
public class MinimumAdjustmentCost {

	public static void main(String[] args) {

	}

	/**
	 * State: f[i][v] 前i个数,第i个数(A[i-1])调整为v,满足相邻两数<=target,所需要的最小代价
	 * 
	 * Function: f[i][v] = min(f[i-1][v’] + |A[i]-v|, |v-v’| <= target)
	 * 
	 * Answer: f[n][a[n]-target~a[n]+target]
	 * 
	 * O(n * A * T)
	 * 
	 * http://www.cnblogs.com/yuzhangcmu/p/4153927.html
	 */

	/**
	 * Backpack DP problem. Three for loops.
	 * 
	 * For each A[i]
	 * 
	 * For each possible value curV (1 … 100) that A[i] could be adjusted to.
	 * 
	 * For each valid value lastV (1 … 100) that A[i – 1] could be adjusted to
	 * (|curV – lastV| < target). Calculate the sum of local adjustment
	 * cost:|curV – A[i]| and the accumulative min adjustment cost for A[0 … i]
	 * saved in minCost[lastV]
	 * 
	 * Space Complexity: O(m*n)
	 * 
	 * Time: O(n * A * T) (n: 最大可能的数， A: A.sie(), T: required range)
	 * 
	 */

	public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
		int n = A.size();
		int[][] f = new int[n + 1][101];
		for (int i = 0; i <= n; ++i) {
			for (int j = 0; j <= 100; ++j) {
				if (i == 0) {
					// i==0,表示第前0个数， 没有数字，所以不需要调整，所以cost为0
					f[i][j] = 0;
				} else {
					f[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			// j 表示 当前第i的位置可取的值，从1到100枚举
			for (int j = 1; j <= 100; j++) {
				/**
				 * k表示： 枚举i上一个位置可取的值，就是当前index为第i个数字时(从1开始计数)，我们把i的前一个index
				 * (i-1)从1-100(j)全部过一次，取其中的最小值（判断一下前一个跟当前的是不是abs<= target）
				 */
				for (int k = 1; k <= 100; k++) {
					if (Math.abs(j - k) > target) {
						continue;
					}
					// f[i - 1][k] 表示i的上一个位置取k的 adjust的最小代价，加上当前第i位置取j的调整代价
					f[i][j] = Math.min(f[i][j],
							f[i - 1][k] + Math.abs(A.get(i - 1) - j));
				} // for
			}// for
		}// for

		int result = Integer.MAX_VALUE;
		// f[i][v]二维表的最后一行的最小值就是答案
		for (int i = 0; i <= 100; i++) {
			result = Math.min(result, f[n][i]);
		}
		return result;
	}

	/**
	 * http://wdxtub.com/interview/14520604916010.html
	 * 
	 */
	public int MinAdjustmentCostNew(ArrayList<Integer> A, int target) {
		// write your code here
		if (A == null || A.size() == 0) {
			return 0;
		}
		int n = A.size();
		int[][] f = new int[n + 1][101];
		for (int i = 0; i <= n; ++i) {
			for (int j = 0; j <= 100; ++j) {
				if (i == 0) {
					// i==0,表示第前0个数， 没有数字，所以不需要调整，所以cost为0
					f[i][j] = 0;
				} else {
					f[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			// 当前第i 个数字，adjust to j
			for (int j = 1; j <= 100; j++) {
				int lowerBound = Math.max(1, j - target);
				int upperBound = Math.min(100, j + target);
				// 上一个数，i-1 个数的取值范围
				for (int k = lowerBound; k <= upperBound; k++) {
					f[i][j] = Math.min(f[i][j],
							f[i - 1][k] + Math.abs(A.get(i - 1) - j));
				}
			}
		}
		int result = Integer.MAX_VALUE;
		// f[i][v]二维表的最后一行的最小值就是答案
		for (int i = 0; i <= 100; i++) {
			result = Math.min(result, f[n][i]);
		}
		return result;
	}

	/**
	 * method 1, DFS recursive - can't AC in lintcode 最普通的递归方法。
	 * 
	 * http://www.cnblogs.com/yuzhangcmu/p/4153927.html
	 */

	public static int MinAdjustmentCost1(ArrayList<Integer> A, int target) {
		if (A == null) {
			return 0;
		}
		// 把A 全部copy去B list, 0表示第0位置，pos
		return rec(A, new ArrayList<Integer>(A), target, 0);
	}

	public static int rec(ArrayList<Integer> A, ArrayList<Integer> B,
			int target, int index) {
		int len = A.size();
		// The index is out of range.
		if (index >= len) {
			return 0;
		}
		int diff = 0;
		int min = Integer.MAX_VALUE;

		// 每一个index位置都可以取 0 到100之间的值
		for (int k = 1; k <= 100; k++) {
			if (index != 0 && Math.abs(k - B.get(index - 1)) > target) {
				continue;
			}
			B.set(index, k);
			diff = Math.abs(k - A.get(index));
			diff += rec(A, B, target, index + 1);
			min = Math.min(min, diff);
			// 回溯
			B.set(index, A.get(index));
		}
		return min;
	}

	/**
	 * memorized DFS -recursive
	 * 
	 * M[i][j]的定义是：从index = i处之前所有的有的differ和，并且A[i]的取值取为j + 1;
	 */
	public static int MinAdjustmentCost3(ArrayList<Integer> A, int target) {
		// write your code here
		if (A == null || A.size() == 0) {
			return 0;
		}

		int[][] M = new int[A.size()][100];
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < 100; j++) {
				M[i][j] = Integer.MAX_VALUE;
			}
		}

		return rec3(A, new ArrayList<Integer>(A), target, 0, M);
	}

	public static int rec3(ArrayList<Integer> A, ArrayList<Integer> B,
			int target, int index, int[][] M) {
		int len = A.size();
		if (index >= len) {
			return 0;
		}

		int dif = 0;
		int min = Integer.MAX_VALUE;

		// If this is the first element, it can be from 1 to 100;
		for (int k = 1; k <= 100; k++) {
			if (index != 0 && Math.abs(k - B.get(index - 1)) > target) {
				continue;
			}
			// 对于该index, 已经取过k-1的值，就直接返回
			if (M[index][k - 1] != Integer.MAX_VALUE) {
				dif = M[index][k - 1];
				min = Math.min(min, dif);
				continue;
			}

			B.set(index, k);
			dif = Math.abs(k - A.get(index));
			dif += rec3(A, B, target, index + 1, M);

			min = Math.min(min, dif);

			// Record the result.
			M[index][k - 1] = dif;

			// 回溯
			B.set(index, A.get(index));
		}

		return min;
	}
}
