package subarray;

/**
 * You may complete at most k transactions.
 * 
 * http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.
 * html
 * 
 * Given prices = [4,4,6,1,1,4,2,5], and k = 2, return 6.
 * 
 * 这道题还有个坑，就是如果k的值远大于prices的天数，比如k是好几百万，而prices的天数就为若干天的话，上面的DP解法就非常的没有效率，
 * 应该直接用Best Time to Buy and Sell Stock II 买股票的最佳时间之二的方法来求解，所以实际上这道题是之前的二和三的综合体，
 */
public class BestTimetoBuyandSellStockIV {

	public static void main(String[] args) {

	}

	/**
	 * 分析：
	 * http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell
	 * -stock.html
	 * 
	 * 分析：特殊动态规划法。传统的动态规划我们会这样想，到第i天时进行j次交易的最大收益，要么等于到第i-1天时进行j次交易的最大收益（
	 * 第i天价格低于第i-1天的价格），要么等于到第i-1天时进行j-1次交易，然后第i天进行一次交易（第i天价格高于第i-1天价格时）。
	 * 于是得到动规方程如下:（其中diff = prices[i] – prices[i – 1]）：
	 * 
	 * profit[i][j] = max(profit[i – 1][j], profit[i – 1][j – 1] + diff)
	 * 看起来很有道理，
	 * 但其实不对，为什么不对呢？因为diff是第i天和第i-1天的差额收益，如果第i-1天当天本身也有交易呢（也就是说第i-1天刚卖出了股票
	 * ，然后又买入等到第i天再卖出），那么这两次交易就可以合为一次交易，这样profit[i – 1][j – 1] +
	 * diff实际上只进行了j-1次交易，而不是最多可以的j次，这样得到的最大收益就小了。
	 * 
	 * 那么怎样计算第i天进行交易的情况的最大收益，才会避免少计算一次交易呢？我们用一个局部最优解和全局最有解表示到第i天进行j次的收益，
	 * 这就是该动态规划的特殊之处
	 * 。用local[i][j]表示到达第i天时，最多进行j次交易的局部最优解；用global[i][j]表示到达第i天时，最多进行j次的全局最优解。
	 * 它们二者的关系如下（其中diff = prices[i] – prices[i – 1]）：
	 * 
	 * local[i][j] = max(global[i – 1][j – 1] , local[i – 1][j] + diff)
	 * global[i][j] = max(global[i – 1][j], local[i][j])
	 * local[i][j]和global[i][j]
	 * 的区别是：local[i][j]意味着在第i天一定有交易（卖出）发生，当第i天的价格高于第i-1天（即diff >
	 * 0）时，那么可以把这次交易（第i
	 * -1天买入第i天卖出）跟第i-1天的交易（卖出）合并为一次交易，即local[i][j]=local[i-1][j]
	 * +diff；当第i天的价格不高于第i
	 * -1天（即diff<=0）时，那么local[i][j]=global[i-1][j-1]+diff，而由于diff
	 * <=0，所以可写成local[
	 * i][j]=global[i-1][j-1]。global[i][j]就是我们所求的前i天最多进行k次交易的最大收益，
	 * 可分为两种情况：如果第i天没有交易
	 * （卖出），那么global[i][j]=global[i-1][j]；如果第i天有交易（卖出），那么global[
	 * i][j]=local[i][j]。
	 * 
	 */

	/**
	 * DP: O(nk) time.二位数组 jiuzhang:
	 * http://www.jiuzhang.com/solutions/best-time-to-buy-and-sell-stock-iv/
	 */

	public int maxProfit(int k, int[] prices) {
		// write your code here
		if (k == 0) {
			return 0;
		}
		if (k >= prices.length / 2) {
			int profit = 0;
			for (int i = 1; i < prices.length; i++) {
				if (prices[i] > prices[i - 1]) {
					profit += prices[i] - prices[i - 1];
				}
			}
			return profit;
		}
		int n = prices.length;
		int[][] mustsell = new int[n + 1][n + 1]; // mustSell[i][j]
													// 表示前i天，至多进行j次交易，第i天必须sell的最大获益
		int[][] globalbest = new int[n + 1][n + 1]; // globalbest[i][j]
													// 表示前i天，至多进行j次交易，第i天可以不sell的最大获益

		mustsell[0][0] = globalbest[0][0] = 0;
		for (int i = 1; i <= k; i++) {
			mustsell[0][i] = globalbest[0][i] = 0;
		}

		for (int i = 1; i < n; i++) {
			int gainorlose = prices[i] - prices[i - 1];
			mustsell[i][0] = 0;
			for (int j = 1; j <= k; j++) {
				mustsell[i][j] = Math.max(globalbest[(i - 1)][j - 1]
						+ gainorlose, mustsell[(i - 1)][j] + gainorlose);
				globalbest[i][j] = Math.max(globalbest[(i - 1)][j],
						mustsell[i][j]);
			}
		}
		return globalbest[(n - 1)][k];
	}

	/**
	 * DP: 代码：时间O(nk)，空间O(nk)。 MaximumSubarrayIII 思路类似
	 * http://blog.csdn.net/fightforyourdream/article/details/14503469
	 * http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-
	 * stock.html
	 * 
	 * 具体 local, global的公式，参见上面一大段分析
	 */
	public int maxProfit1(int k, int[] prices) {
		// len表示天数
		int len = prices.length;

		if (len < 2 || k <= 0) {
			return 0;
		}

		// 即k的值， 大于或等于天数， 问题就转换为了BestTimetoBuyandSellStockII的情况,也就是可以进行任意次数交易
		if (k >= len) {
			return new BestTimetoBuyandSellStockII().maxProfit(prices);
		}

		// local[i][j]表示到前i天，最多进行j次交易的最大利润， 并且最后一次交易必须发生在第i天（sell）。
		int[][] local = new int[len + 1][k + 1];
		// global[i][j]表示前i天，最多进行j次的全局最优解， 在第i天不一定有交易，可以有，也可以没有交易
		int[][] global = new int[len + 1][k + 1];

		// i==0，表示前面0天，不可能有收益，
		// i==1, 表示前一天， 由于每天当中只能买或买，profit也为0， 所以i从2开始，表示前面2天
		for (int i = 2; i <= len; i++) {
			int diff = prices[i - 1] - prices[i - 2];
			for (int j = 1; j <= k; j++) {
				/**
				 * Math.max(diff, 0)，可省略，因为diff小于0
				 * 
				 * local一定要加diff,是因为local表示的就是一定要在第i天有交易，所以没得选择，必须加上，
				 * 
				 * 而global[i-1][j-1] + Math.max(0, diff);表示对于全局的max数组global
				 * 它有选择，第i天可以交易，也可以不交易，如果diff>0就交易，否则不交易
				 * 
				 * 1. local[i - 1][j] + diff
				 * (因为local[i-1][j]比如包含第i-1天卖出的交易，所以现在变成第i天卖出
				 * ，并不会增加交易次数，而且这里无论diff是不是大于0都一定要加上
				 * ，因为否则就不满足local[i][j]必须在最后一天卖出的条件了,原理有点类似算max
				 * subarry,加上当前数组元素num[i]一样)
				 * 
				 * 2. global[i - 1][j - 1] + Math.max(diff, 0):
				 * 第i-1天后，共交易了j-1次（global
				 * [i-1][j-1]），因此为了满足“第i天过后共进行了j次交易，且第i天必须进行交易
				 * ”的条件：我们可以选择1：在第i-1天买入 ，然后再第i天卖出 diff
				 * (这就是1的情况，已考虑过了)，或者选择在第i天买入，然后同样在第i天卖出（收益为0）。
				 */

				local[i][j] = Math.max(
						global[i - 1][j - 1] + Math.max(diff, 0),
						local[i - 1][j] + diff);
				// 也就是取当前局部最好的，和过往全局最好的中大的那个
				// 全局（第i天进行j次交易的最大收益） =
				// max{局部（在第i天交易后，恰好满足j次交易），全局（到达第i-1天时已经满足j次交易）}
				global[i][j] = Math.max(global[i - 1][j], local[i][j]);
			}
		}

		return global[len][k];
	}

	/**
	 * DP - 一维数组: 时间O(nk)，空间O(k)。
	 * 
	 * 动规所用的二维辅助数组可以降为一维的，即只用大小为k的一维数组记录到达第i天时的局部最优解和全局最优解。需要注意的是，
	 * 由于第i天时交易k次的最优解依赖于第i-1天时交易k-1次的最优解，所以数组更新应当从后往前（即从k到1）更新。
	 */
	public int maxProfit2(int k, int[] prices) {
		if (prices.length < 2 || k <= 0)
			return 0;

		if (k >= prices.length)
			return new BestTimetoBuyandSellStockII().maxProfit(prices);

		int[] local = new int[k + 1];
		int[] global = new int[k + 1];

		for (int i = 1; i < prices.length; i++) {
			int diff = prices[i] - prices[i - 1];
			// 数组更新必须从后往前（即从k到1）更新，具体见上面解释
			for (int j = k; j >= 1; j--) {
				// Math.max(diff, 0)，可省略，因为diff小于0
				local[j] = Math.max(global[j - 1], local[j] + diff);
				global[j] = Math.max(global[j], local[j]);
			}
		}

		return global[k];
	}
}
