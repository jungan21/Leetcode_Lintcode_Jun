package DP;

public class CoininaLineII {

	public static void main(String[] args) {
		int[] values = { 5, 1, 2, 10 };
		// int[] values = { 1, 2, 4};
		System.out.println(firstWillWin(values));
	}

	/**
	 * dp[i] 现在还剩i个硬币,现在当前取硬币的人最后最多取硬币价值
	 * 
	 */
	public static boolean firstWillWin(int[] values) {
		// write your code here
		int n = values.length;
		int[] dp = new int[n + 1];
		boolean[] flag = new boolean[n + 1];
		int[] sum = new int[n + 1];

		/**
		 * values = { 5, 1, 2, 10 } ==> sum [0, 10, 12, 13, 18]
		 */
		// sum[i] 后i个硬币的总和
		sum[0] = 0;
		sum[1] = values[n - 1];
		for (int i = 2; i <= n; i++) {
			sum[i] += sum[i - 1] + values[n - i];
		}
		/**
		 * 根据上面sum数组的定义， sum[n]实际上是所有coin的总和
		 * 
		 * 如果当前Player能取到的值大于 总和的一半，那肯定赢了，后手不可能赢，再怎么取都小于一半
		 */
		return sum[n] / 2 < MemorySearch(n, dp, flag, values, sum);
	}

	static int MemorySearch(int n, int[] dp, boolean[] flag, int[] values,
			int[] sum) {
		if (flag[n] == true)
			return dp[n];
		flag[n] = true;
		if (n == 0) {
			dp[n] = 0;
		} else if (n == 1) {
			// ！！！ dp[n] = values[n - 1];
			dp[n] = sum[1];
		} else if (n == 2) {
			dp[n] = sum[2];
		} else {
			// dp[i] = sum[i]-min(dp[i-1], dp[i-2])
			/**
			 * 画出搜索树，从叶子节点（从下）往上找， 当前层要想选的最大值，其下一层要选出小的，并且用该层的硬币总和减去
			 * 
			 * dp[当前状态] = 当前总和 - 对手状态的最小值
			 */
			dp[n] = sum[n]
					- Math.min(MemorySearch(n - 1, dp, flag, values, sum),
							MemorySearch(n - 2, dp, flag, values, sum));
		}
		return dp[n];
	}

}
