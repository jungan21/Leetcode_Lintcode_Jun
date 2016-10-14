package a_oa;

import java.util.ArrayList;
import java.util.List;

// dynamic programming, 若使用贪心算法是不可行的，因为有可能会错过全局最优解
/**
 * e.g.   7, 8, 9 三种面额， 我们找15, 组成15 最少需要几个硬币
 * if greedy, we pick 9 first .. then? 无解
 * if DP, we can get 8+ 7
 */

/**
 * Note: 除了DP， 我们也可以用DFS解决，本质就是subset每个元素可以取多次， 和CombinationSum.java一样
 * 
 * Let dp[v] to be the minimum number of coins required to get the amount v.
 * dp[i+a_coin] = min(dp[i+a_coin], dp[i]+1) if dp[i] is reachable. dp[i+a_coin]
 * = dp[i+a_coin] is dp[i] is not reachable. We initially set dp[i] to be
 * MAX_VALUE.
 *
 * http://www.acmerblog.com/dp6-coin-change-4973.html dp[i + coins[j] ] =
 * min(dp[i + coins[j] ] , dp[i] + 1）
 * 
 * 
 * http://www.cnblogs.com/grandyang/p/5138186.html
 */

// best artical describe dynamic programming
// http://www.hawstein.com/posts/dp-novice-to-advanced.html
public class CoinChange {

	public static void main(String[] args) {

		// int[] coins = { 2 };
		// int[] coins = { 2, 1, 4, 7, 4, 8, 3, 6, 4, 7 };
		int[] coins = { 1 };
		System.out.println(coinChange(coins, 0));
	}

	/**
	 * 
	 * 最少需要几个硬币
	 * 
	 * http://www.cnblogs.com/grandyang/p/5138186.html
	 * 
	 * best explanation:
	 * http://www.hawstein.com/posts/dp-novice-to-advanced.html
	 */
	public static int coinChange(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			// Integer.MAX_VALUE = 0x7fff_fffe
			dp[i] = Integer.MAX_VALUE;
		}
		// 类似背包问题
		for (int i = 1; i <= amount; i++) {
			for (int coin : coins) {
				if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], dp[i - coin] + 1);
				}
			}
		}
		return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
	}

	/**
	 * DFS时间复杂度高，Leetcode提交不过 上面是DP, 接下来用DFS： 本质就是subset每个元素可以取多次，
	 * 和CombinationSum.java一样
	 * 
	 * http://www.cnblogs.com/grandyang/p/5138186.html
	 */

	public static int coindChangeDFS(int[] coins, int amount) {
		List<Integer> list = new ArrayList<>();
		helper(coins, amount, list, 0);
		System.out.println(result);
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	static int min = Integer.MAX_VALUE;
	static List<Integer> result = null;

	public static void helper(int[] coins, int amount, List<Integer> list,
			int pos) {
		if (pos >= coins.length) {
			return;
		}
		if (amount == 0) {
			min = Math.min(min, list.size());
			if (result == null
					|| (result != null && result.size() > list.size())) {
				result = new ArrayList<Integer>(list);
			}
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

	/**
	 * Memorized DFS 时间复杂度高， 上面是DP, 接下来用DFS： 本质就是subset每个元素可以取多次，
	 * 和CombinationSum.java一样
	 * 
	 * http://www.cnblogs.com/grandyang/p/5138186.html
	 */

	public static int coinChangeMemorizedDFS(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		dp[0] = 0;
		for (int i = 1; i < dp.length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		for (int i = 1; i <= amount; i++) {
			dp[i] = helper(coins, i, dp);
		}
		return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
	}

	public static int helper(int[] coins, int amount, int[] dp) {
		if (dp[amount] != Integer.MAX_VALUE) {
			return dp[amount];
		}

		for (int i = 0; i < coins.length; i++) {
			if (amount >= coins[i]
					&& dp[amount - coins[i]] != Integer.MAX_VALUE) {
				dp[amount] = Math.min(dp[amount],
						helper(coins, amount - coins[i], dp) + 1);
			}
		}
		return dp[amount];
	}
}
