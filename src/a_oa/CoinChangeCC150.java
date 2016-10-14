package a_oa;

import java.util.ArrayList;
import java.util.List;

/**
 * cc150里recursionve 章节
 * 
 * 9.8 Represent N Cents 美分的组成, 总共有多少种组成方式
 * 
 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5
 * cents) and pennies (1 cent), write code to calculate the number of ways of
 * representing n cents.
 *
 */
public class CoinChangeCC150 {

	public static void main(String[] args) {
		int[] coins = { 25, 10, 5, 1 };
		int amount = 50;
		System.out.println(coindChangeDFS(coins, amount));
		System.out.println(coindChangeDP(coins, amount));
		System.out.println(coindChangeDP1Array(coins, amount));
	}

	/**
	 * Best DP: http://www.acmerblog.com/dp6-coin-change-4973.html
	 * 
	 */
	public static int coindChangeDP(int[] coins, int amount) {
		int len = coins.length;
		/**
		 * dp[i][j] 前i中硬币，组成面值为j的前，有多少种方法
		 */
		int[][] dp = new int[amount + 1][len + 1];
		dp[0][0] = 1;
		// 前任意个硬币，都可以组成面值为0的钱
		for (int i = 1; i <= len; i++) {
			dp[0][i] = 1;
		}
		// 前0个硬币，都不可以组成任何大于1的面值的钱
		for (int i = 1; i <= amount; i++) {
			dp[i][0] = 0;
		}

		for (int i = 1; i <= amount; i++) {
			for (int j = 1; j <= len; j++) {
				// 不包括 coin[j-1] 的方案数
				dp[i][j] = dp[i][j - 1];

				// 包括 coin[j-1] 的方案数
				if (i - coins[j - 1] >= 0) {
					dp[i][j] = dp[i][j] + dp[i - coins[j - 1]][j];
				}
			}
		}
		return dp[amount][len];
	}

	/**
	 * 上面DP方法的空间优化
	 * 
	 * 以下为上面程序的优化版本。这里所需要的辅助空间为O(n)。因为我们在打表时，本行只和上一行有关，类似01背包问题。
	 * 
	 */
	public static int coindChangeDP1Array(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		dp[0] = 1;

		for (int coin : coins) {
			for (int i = 1; i <= amount; i++) {
				if (i >= coin) {
					dp[i] += dp[i - coin];
				}
			}
		}
		return dp[amount];
	}

	/**
	 * coindChange DP 仅仅求出多少种方法 http://www.cnblogs.com/python27/p/3303721.html
	 */

	public static int coindChangeDP2(int[] coins, int amount) {
		int len = coins.length;
		/**
		 * dp[i][j] 前i中硬币，组成面值为j的前，有多少种方法
		 */
		int[][] dp = new int[len + 1][amount + 1];
		dp[0][0] = 1;
		// 前任意个硬币，都可以组成面值为0的钱
		for (int i = 0; i <= len; i++) {
			dp[i][0] = 1;
		}

		// 前0个硬币，不可以组成任何面值大于0的前
		for (int i = 1; i <= amount; i++) {
			dp[0][i] = 0;
		}

		for (int i = 1; i <= len; i++) {
			for (int j = 1; j <= amount; j++) {
				dp[i][j] = 0;
				for (int k = 0; k <= j / coins[i - 1]; k++) {
					dp[i][j] = dp[i][j] + dp[i - 1][j - k * coins[i - 1]];
				}
			}
		}
		return dp[coins.length][amount];
	}

	/**
	 * DFS 效率不高，但是好处是可以找出具体的组合
	 * 
	 * http://www.cnblogs.com/grandyang/p/4840713.html
	 * 
	 */
	public static int coindChangeDFS(int[] coins, int amount) {
		List<Integer> list = new ArrayList<>();
		helper(coins, amount, list, 0);
		return result;
	}

	static int result = 0;

	public static void helper(int[] coins, int amount, List<Integer> list,
			int pos) {
		if (pos >= coins.length) {
			return;
		}
		if (amount == 0) {
			System.out.println(list);
			result++;
		}

		for (int i = pos; i < coins.length; i++) {
			if (amount < coins[i]) {
				continue;
			}
			list.add(coins[i]);
			helper(coins, amount - coins[i], list, i);
			list.remove(list.size() - 1);
		}
	}

}
