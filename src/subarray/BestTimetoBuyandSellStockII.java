package subarray;

/**
 * as many transactions as you like, 当时每天只能有一次交易（买 或者卖）
 * 
 * Given an example [2,1,2,0,1], return 2
 * 
 * http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.
 * html
 */
public class BestTimetoBuyandSellStockII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * 分析：贪心法。从前向后遍历数组，只要当天的价格高于前一天的价格，就算入收益。
	 * 
	 * 代码：时间O(n)，空间O(1)。
	 * http://liangjiabin.com/blog/2015/04/leetcode-best-time-to
	 * -buy-and-sell-stock.html http://www.cnblogs.com/springfor/p/3877065.html
	 * 
	 * 简单的方法就是一旦第二天的价格比前一天的高，就在前一天买入第二天卖出。
	 * 
	 * This problem can be viewed as finding all ascending sequences. For
	 * example, given {5, 1, 2, 3, 4}, buy at 1 & sell at 4 is the same as buy
	 * at 1 &sell at 2 & buy at 2& sell at 3 & buy at 3 & sell at 4. We can scan
	 * the array once, and find all pairs of elements that are in ascending
	 * order.
	 */
	public int maxProfit(int[] prices) {
		// 如果length为1， 就表示只有1天，当天买，当天买，不可能赚钱，因为在同一天股票价格一样
		if (prices == null || prices.length < 2) {
			return 0;
		}
		// 设置为0
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			// diff大于0表示，明天的股票贵，所以今天低价就该买入，等明天卖掉
			int diff = prices[i] - prices[i - 1];
			if (diff > 0) {
				profit = profit + diff;
			}
		}
		return profit;
	}
}
