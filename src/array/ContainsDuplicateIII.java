package array;

import java.util.TreeSet;

/**
 * Given an array of integers, find out whether there are two distinct indices i
 * and j in the array such that the difference between nums[i] and nums[j] is at
 * most t and the difference between i and j is at most k.
 * 
 *
 */
public class ContainsDuplicateIII {

	public static void main(String[] args) {

	}

	/**
	 * http://blog.welkinlan.com/2015/09/11/contains-duplicate-i-ii-iii-leetcode
	 * -java/
	 *
	 * https://segmentfault.com/a/1190000003709386 时间 O(NlogK) 空间 O(K)
	 * 
	 * 要求判断之前是否存在差值小于t的数字，我们需要知道在当前数字x两边的数字，即最大的小于x的数字和最小的大于x的数字。二叉搜索树有也有这样的性质，
	 * 它的左子树的最右节点是最大的较小值，右子树的最左节点是最小的较大值。这里我们用TreeSet这个类，它实现了红黑树，并有集合的性质，非常适合这题。
	 * 我们同样也是维护一个大小为k的TreeSet
	 * ，多余k个元素时把最早加入的给删除。用ceiling()和floor()可以找到最小的较大值和最大的较小值。
	 * 
	 * 
	 * 
	 * The floor(x) method returns the greatest value that is less than x. The
	 * ceiling(x) methods returns the least value that is greater than x. The
	 * following is an example.
	 */
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		TreeSet<Integer> set = new TreeSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			int x = nums[i];
			// 最大的小于x的数字
			if (set.ceiling(x) != null && set.ceiling(x) <= t + x)
				return true;
			// 最小的大于x的数字
			if (set.floor(x) != null && x <= t + set.floor(x))
				return true;
			set.add(x);
			if (set.size() > k)
				set.remove(nums[i - k]);
		}
		return false;
	}

	static int fib2(int n) {
			/* Declare an array to store Fibonacci numbers. */
			int f[] = new int[n + 1];
			int i;
	
			/* 0th and 1st number of the series are 0 and 1 */
			f[0] = 0;
			f[1] = 1;
	
			for (i = 2; i <= n; i++) {
				/*
				 * Add the previous 2 numbers in the series and store it
				 */
				f[i] = f[i - 1] + f[i - 2];
			}
	
			return f[n];
		}

}
