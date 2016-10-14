package subarray;

/**
 * 
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * If you were only permitted to complete at most one transaction (ie, buy one
 * and sell one share of the stock), design an algorithm to find the maximum
 * profit.
 * 
 * Given array [3,2,3,1,2], return 1.
 * 
 */
public class BestTimetoBuyandSellStock {

	public static void main(String[] args) {

	}

	/**
	 * DP 思想：
	 * 
	 * 分析：动态规划法。从前向后遍历数组，记录当前出现过的最低价格，作为买入价格，并计算以当天价格出售的收益，作为可能的最大收益，整个遍历过程中，
	 * 出现过的最大收益就是所求。即，最低买入，最高卖出
	 * 
	 * 代码：O(n)时间，O(1)空间。
	 * 
	 * 这道题只让做一次transaction，那么就需要找到价格最低的时候买，价格最高的时候卖（买价的日期早于卖价的日期）。从而转化为在最便宜的时候买入
	 * ，卖价与买价最高的卖出价最大时，就是我们要得到的结果。
	 * 
	 * 因为我们需要买价日期早于卖价日期，所以不用担心后面有一天价格特别低，而之前有一天价格特别高而错过了（这样操作是错误的）。
	 * 
	 * 所以，只许一次遍历数组，维护一个最小买价，和一个最大利润(保证了买在卖前面）即可。
	 * 
	 */
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}

		int profit = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < prices.length; i++) {
			min = Math.min(prices[i], min);
			profit = Math.max(profit, prices[i] - min);
		}
		return profit;
	}

	/**
	 * real DP
	 */
	public int maxProfitDP(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int len = prices.length;
		int[] leftMax = new int[len];

		int min = prices[0];
		// 第一天刚开始利润必须为0
		leftMax[0] = 0;

		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			leftMax[i] = Math.max(leftMax[i - 1], prices[i] - min);
		}
		return leftMax[len - 1];
	}

	/**
	 * real DP - optimized space
	 */
	public int maxProfitDP1(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		// 第一天刚开始利润必须为0
		int leftMax = 0;
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			leftMax = Math.max(leftMax, prices[i] - min);
		}
		return leftMax;
	}

}
