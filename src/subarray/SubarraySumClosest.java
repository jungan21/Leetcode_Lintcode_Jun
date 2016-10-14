package subarray;

import java.util.Arrays;
import java.util.Comparator;

/**
 * http://rafal.io/posts/subsequence-closest-to-t.html
 * 
 * 
 * Given an integer array, find a subarray with sum closest to 0. Return the
 * indexes of the first number and last number.
 *
 * Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4].
 * 
 * sum[i] = nums[0]+....nums[i], also record the index i into sum[i]. Sort array
 * s, and the minimum difference between two consecutive element, is the the
 * subarray.
 * 
 * 分析： 题目的意思是在一个数组中找一段连续的区间，使得这段区间的和的绝对值最小
 * 
 */
public class SubarraySumClosest {

	public static void main(String[] args) {
		int[] nums = { -3, 3, 8, -10, 5 };
		new SubarraySumClosest().subarraySumClosest(nums);
	}

	class Pair {
		int sum;
		int index;

		public Pair(int s, int i) {
			sum = s;
			index = i;
		}
	}

	/**
	 * O(nlogn) 分析： 题目的意思是在一个数组中找一段连续的区间，使得这段区间的和的绝对值最小, 也就是题目中要找的最接近0的意思
	 * 
	 * http://www.cnblogs.com/easonliu/p/4504495.html
	 * 
	 * http://www.cnblogs.com/lishiblog/p/4187961.html
	 * 
	 * @return
	 */
	public int[] subarraySumClosest(int[] nums) {
		int[] result = new int[2];
		if (nums == null || nums.length == 0) {
			return result;
		}
		int len = nums.length;
		if (len == 1) {
			result[0] = result[1] = 0;
			return result;
		}
		/**
		 * 
		 * 为避免对单个子串和是否为最小情形的单独考虑，我们可以采取类似链表 dummy
		 * 节点的方法规避，简化代码实现。故初始化sum_index时需要num_size + 1个
		 * 
		 * sums[1]存着，从原始数字最开始的位置到第一个元素的和 (即-3)
		 * 
		 * sums[2] 存着，从原始数字最开始的位置到第2个元素的和 (即 -2 = -3+1)
		 * 
		 * sums[i] 表示前i个元素的和， 包含第i个数
		 */
		Pair[] sums = new Pair[len + 1];

		int prev = 0;
		/**
		 * 
		 * 这里必须是 Pair(0, 0); Pair(0, -1);不对,
		 * 之所以设置为（0，0）是需要适应大的算法需要，因为对于sums数组反应的原数组下标都需要-1, 这样减去1之后变成-1， 那么下面的
		 * result[0] = temp[0]+1,才能变为0
		 * 
		 * 第一个0是sum, 第二个是index 如果不加的这个dummy,node 的话， 例如下面的列子 只有 -3+3=0，
		 * 那么在你的sums数组里，只有一个0， 而我们是要靠相减，差值最小来判断，那就永远找不到0，而这个0就是最接近0的值
		 * 
		 * 对于数据集： {-3, 3, 8, -10, 5}， 上面第一种方法就会返回正确结果 {0,1},
		 * 而下面这种未加辅助节点的，就会返回{1， 3}
		 * 
		 */
		sums[0] = new Pair(0, 0);
		for (int i = 1; i <= len; i++) {
			sums[i] = new Pair(prev + nums[i - 1], i);
			prev = sums[i].sum;
		}
		// 对sums数组， 按里面元素Pair.sum的值，升序排序
		Arrays.sort(sums, new Comparator<Pair>() {
			public int compare(Pair a, Pair b) {
				return a.sum - b.sum;
			}
		});

		int ans = Integer.MAX_VALUE;
		int[] temp = new int[2];
		for (int i = 1; i <= len; i++) {
			/**
			 * sums[i] = nums[0]+....nums[i], also record the index i into
			 * sums[i]. Sort array sums, and the minimum difference between two
			 * consecutive element, is the the subarray.
			 * 
			 * 那么，我们想要得到nums[i]到nums[j]的和，只要用sum[j] -
			 * sum[i-1]就可以了。如：nums[0]+nums[1] = -3;
			 * nums[0]+nums[1]+nums[2]+nums[3]+nums[4]=-4, 上面两个相减， 等于-1，
			 * 也就是nums[2]+nums[3]+nums[4]=-1 对应原始数组的下标是{1， 2， 3}
			 * 
			 * 但是这里有一点要注意要加一个辅助的节点，那就是[0, 0]，这样就可以确保可以找到以nums[0]开始的区间了
			 * 。剩下的工作就是对acc数组排序，找到排序后相邻的差的绝对值最小的那一对节点。
			 */
			/**
			 * 原理：为什么相邻的两两相减，差值最小的的就是最接近0的sum closet?
			 * 
			 * 由sum[1] <= sum[2] <= sum[3] ===> (sum[1] -sum[1]) < (sum[2]
			 * -sum[1]) - < (sum[3] -sum[1]),
			 * 同样的道理可以推广到整个数组序列,如果不排序，要找出两两相差最小，要做很多这样的判断
			 * ，复杂度为O(n^2),如果按sum大小排好序了，若是要找两两相减最小，就主要比较相邻的就好了，
			 * 排序是O(nlogn)，比较是O(n)， 所以和在一起接近于O(nlogn)
			 * 
			 * 另外一个点要注意：由于找的是最close to 0的值，如果不排序，求相邻的差最小，而是采用brute
			 * force的O(n^2)的方法，则需要，比较绝对值的最小，而排好序了，小得永远在前面（-4， -3， -2） 这样，sum[1]-
			 * sum[0] = -3 -(-4)得到的永远是正数，也就不用考虑绝对值了
			 * 
			 */

			if ((sums[i].sum - sums[i - 1].sum) < ans) {
				// 也可以写成Math.abs(sums[i].sum - sums[i - 1].sum) < ans
				// 不过由于我们这里已经根据sum从大到小排序了，所以这样相减得到的都是正数，不需要求绝对值
				ans = sums[i].sum - sums[i - 1].sum;
				// 容易写错！！！
				temp[0] = sums[i].index - 1;
				temp[1] = sums[i - 1].index - 1;
				// 排序的目的是返回的结果需要按index的升序排序
				Arrays.sort(temp);
				/**
				 * why temp[0]+1?
				 * 
				 * nums[0]+nums[1]+nums[2]+nums[3]+nums[4]=-4
				 * 
				 * nums[0]+nums[1] = -3, 上面两个相减，
				 * 等于-1，也就是nums[2]+nums[3]+nums[4]=-1， 所以下标的起点要加一
				 * 
				 * sums[i].index，表示前i个数(包含i)的和为sums[i].sum,
				 */
				result[0] = temp[0] + 1;
				result[1] = temp[1];
			}
		}

		return result;
	}

	/**
	 * 错误的方法： !!!
	 * 
	 * 与上面方法区别 Pair[] sums = new Pair[len];
	 * ，上面方法的数组初始化长度是len+!（加了个辅助节点在最开始，类似链表的dummy head node）
	 * 
	 * 但是这里有一点要注意要加一个辅助的节点，那就是[0,0]，这样就可以确保可以找到以nums[0]开始的区间了,如果不加辅助节点，就会或略掉，
	 * nums[0]开始的区间了
	 * 
	 * 如果不加的这个dummy,node 的话， 例如下面的列子 只有 -3+3=0， 那么在你的sums数组里，只有一个0，
	 * 而我们是要考相减，差值最小来判断，那就永远找不到0，而这个0就是最接近0的值
	 * 
	 * 对于数据集： {-3, 3, 8, -10, 5}， 上面第一种方法就会返回正确结果 {0,1}, 而下面这种未加辅助节点的，就会返回{1， 3}
	 * 
	 */
	public int[] subarraySumClosestTest(int[] nums) {
		int[] result = new int[2];
		if (nums == null || nums.length == 0) {
			return result;
		}
		int len = nums.length;
		if (len == 1) {
			result[0] = result[1] = 0;
			return result;
		}

		Pair[] sums = new Pair[len];
		int temp = nums[0];
		sums[0] = new Pair(nums[0], 0);
		for (int i = 1; i < len; i++) {
			temp = nums[i] + temp;
			sums[i] = new Pair(temp, i);
		}

		Arrays.sort(sums, new Comparator<Pair>() {
			public int compare(Pair a, Pair b) {
				return a.sum - b.sum;
			}
		});

		int minDiff = Integer.MAX_VALUE;
		int[] tempResult = new int[2];
		for (int i = 1; i < len; i++) {
			int diff = sums[i].sum - sums[i - 1].sum;
			if (diff < minDiff) {
				minDiff = diff;
				tempResult[0] = sums[i].index;
				tempResult[1] = sums[i - 1].index;
			}
		}

		Arrays.sort(tempResult);
		result[0] = tempResult[0] + 1;
		result[1] = tempResult[1];
		return result;
	}
}
