package subarray;

import java.util.Arrays;

/**
 * at most two transactions.
 *
 * Leetcode:123: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * Given an example [4,4,6,1,1,4,2,5], return 6.
 * 
 * http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.
 * html
 * 
 */
public class BestTimetoBuyandSellStockIII {

	public static void main(String[] args) {
		int nums[] = { 1, 2 };
		System.out.println(maxProfit2(nums));
	}

	/**
	 * 思路： http://www.cnblogs.com/springfor/p/3877068.html
	 * 
	 * 根据题目要求，最多进行两次买卖股票，而且手中不能有2只股票，就是不能连续两次买入操作。
	 * 
	 * 所以，两次交易必须是分布在2各区间内，也就是动作为：买入卖出，买入卖出。
	 * 
	 * 进而，我们可以划分为2个区间[0,i]和[i,len-1]，i可以取0~len-1。
	 * 
	 * 那么两次买卖的最大利润为：在两个区间的最大利益和的最大利润。
	 * 
	 * 一次划分的最大利益为：Profit[i] = MaxProfit(区间[0,i]) + MaxProfit(区间[i,len-1]);
	 * 
	 * 最终的最大利润为：MaxProfit(Profit[0], Profit[1], Profit[2], ... , Profit[len-1])。
	 * 即，表示不同的分割点
	 * 
	 */

	/**
	 * 
	 * 动态规划法。以第i天为分界线，计算第i天之前进行一次交易的最大收益preProfit[i]，和第i天之后进行一次交易的最大收益postProfit
	 * [i]，最后遍历一遍，max{preProfit[i] + postProfit[i]}
	 * (0≤i≤n-1)就是最大收益。第i天之前和第i天之后进行一次的最大收益求法同Best Time to Buy and Sell Stock I。
	 * 
	 * 
	 * http://www.programcreek.com/2014/02/leetcode-best-time-to-buy-and-sell-
	 * stock-iii-java/
	 * 
	 * ONLY 2 transactions to 2. This can be solve by "devide and conquer".
	 * 
	 * left[i] to track the maximum profit for transactions before i 天
	 * 
	 * right[i] to track the maximum profit for transactions after i 天. You can
	 * use the following example to understand the Java solution:
	 * 
	 * Prices: 1 4 5 7 6 3 2 9
	 * 
	 * left = [0, 3, 4, 6, 6, 6, 6, 8]
	 * 
	 * right= [8, 7, 7, 7, 7, 7, 7, 0]
	 * 
	 * The maximum profit = 13 (7+6)
	 */
	public static int maxProfit2(int[] prices) {
		/**
		 * 注意： prices.length < 2
		 */
		if (prices == null || prices.length < 2) {
			return 0;
		}

		// highest profit in 0 ... i，record max profit before i
		int[] left = new int[prices.length];

		// highest profit in i.... len-1, record max profit after i
		int[] right = new int[prices.length];

		// DP from left to right,
		/**
		 * 注意：left[0] = 0; 之前写错了， 第一天的profit只能为0
		 * 
		 * 该子过程就是BestTimetoBuyandSellStockI里面的DP方法， 这不过这里要保留整个数组计算，最后要尝试不同的组合
		 */
		left[0] = 0;
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			left[i] = Math.max(left[i - 1], prices[i] - min);
		}

		// DP from right to left
		/**
		 * 注意：right[prices.length - 1] = 0;; 之前写错了
		 */
		right[prices.length - 1] = 0;
		int max = prices[prices.length - 1];
		for (int i = prices.length - 2; i >= 0; i--) {
			max = Math.max(max, prices[i]);
			right[i] = Math.max(right[i + 1], max - prices[i]);
		}

		/**
		 * 注意最后处理时下标时候，和MaximumSubarrayII.java不同
		 * 
		 * 在第i天时， 对于left, right两个方向上，有一个方向是没有transaction的,
		 * 虽然对应的数组[i]不为0，体现在，那个值是从前一天挪过来的，(在填DP数组时候)
		 * 
		 * 之所以写成 profit = Math.max(profit, left[i] + right[i]);
		 * 是因为,在第i天有可能有操作，但是我不知道，在哪个方向是没有操作的
		 * 
		 * DP 数组，只保存到目前为止天数的最大收益，而没有保存某天是否有transaction, 所以对于题目要求求最大值，这么加起来不影响
		 * 
		 * 比如3 2 1 2 3 4 5 6 5
		 * 4，这种情况肯定是只取1到6这一段收益最大，任何其他的方法收益都比这个小，而这时的left和right在前后两段递减序列里都是0
		 * 
		 */
		System.out.println(Arrays.toString(left));
		System.out.println(Arrays.toString(right));
		int profit = 0;
		for (int i = 0; i < prices.length; i++) {
			profit = Math.max(profit, left[i] + right[i]);
		}

		return profit;
	}
}
