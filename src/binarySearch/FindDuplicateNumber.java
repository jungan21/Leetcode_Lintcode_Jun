package binarySearch;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1
 * and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * 
 * Note: You must not modify the array (assume the array is read only). You must
 * use only constant, O(1) extra space. Your runtime complexity should be less
 * than O(n2). There is only one duplicate number in the array, but it could be
 * repeated more than once.
 * 
 *
 */

/**
 * 目要求我们不能改变原数组，即不能给原数组排序，又不能用多余空间，那么哈希表神马的也就不用考虑了， 又说时间小于O(n2)，也就不能用brute
 * force的方法，那我们也就只能考虑用二分搜索法了，我们在区别[1,
 * n]中搜索，首先求出中点mid，然后遍历整个数组，统计所有小于等于mid的数的个数，如果个数大于mid，则说明重复值在[mid+1,
 * n]之间，反之，重复值应在[1, mid-1]之间，然后依次类推，直到搜索完成，此时的low就是我们要求的重复值，参见代码如下：
 * 
 *
 */

public class FindDuplicateNumber {

	public static void main(String[] args) {
		int[] nums = { 2, 1, 3, 1 };
		System.out.println(findDuplicate2(nums));
	}

	// best explanation
	// https://segmentfault.com/a/1190000003817671
	/**
	 * 按照抽屉原理，整个数组中如果小于等于n/2的数的数量大于n/2，说明1到n/2这个区间是肯定有重复数字的 时间 O(NlogN) 空间 O(1)
	 * 本题目是对于非排序的， 如果面试官说是排序的，用RemoveDuplicateFromStortedArray.java 的方法 O(n)即可
	 */
	public static int findDuplicate(int[] nums) {
		int low = 0, high = nums.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			int cnt = 0;
			// 计算总数组中有多少个数小于等于中间数
			for (int a : nums) {
				if (a <= mid)
					++cnt;
			}
			// 如果小于等于中间数的数量大于中间数，说明前半部分必有重复
			if (cnt > mid)
				high = mid - 1;
			else
				low = mid + 1;
		}
		// Note return low
		return low;
	}

	// 时间 O(N) 空间 O(1)
	/**
	 * 假设数组中没有重复，那我们可以做到这么一点，就是将数组的下标和1到n每一个数一对一的映射起来。比如数组是213,则映射关系为0->2, 1->1,
	 * 2->3。假设这个一对一映射关系是一个函数f(n)，其中n是下标，f(n)是映射到的数。如果我们从下标为0出发，根据这个函数计算出一个值，
	 * 以这个值为新的下标
	 * ，再用这个函数计算，以此类推，直到下标超界。实际上可以产生一个类似链表一样的序列。比如在这个例子中有两个下标的序列，0->2->3。
	 * 
	 * 但如果有重复的话，这中间就会产生多对一的映射，比如数组2131,则映射关系为0->2, {1，3}->1,
	 * 2->3。这样，我们推演的序列就一定会有环路了，这里下标的序列是0->2->3->1->1->1->1->...，而环的起点就是重复的数。
	 * 
	 * 所以该题实际上就是找环路起点的题，和Linked List Cycle
	 * II一样。我们先用快慢两个下标都从0开始，快下标每轮映射两次，慢下标每轮映射一次
	 * ，直到两个下标再次相同。这时候保持慢下标位置不变，再用一个新的下标从0开始
	 * ，这两个下标都继续每轮映射一次，当这两个下标相遇时，就是环的起点，也就是重复的数。对这个找环起点算法不懂的，请参考Floyd's
	 * 
	 * http://bookshadow.com/weblog/2015/09/28/leetcode-find-duplicate-number/
	 * Algorithm。 https://segmentfault.com/a/1190000003817671
	 */
	public static int findDuplicate2(int[] nums) {
		int slow = 0;
		int fast = 0;
		// 找到快慢指针相遇的地方
		// In fact, they meet in a circle, the duplicate number must be the
		// entry point of the circle when visiting the array from nums[0].
		do {
			slow = nums[slow];
			fast = nums[nums[fast]];
		} while (slow != fast);

		// next while loop to loop for the index of the duplicate number
		int find = 0;
		// 用一个新指针从头开始，直到和慢指针相遇
		/**
		 * Next we just need to find the entry point. We use a point(finder ) to
		 * visit form begining with one step each time, do the same job to slow.
		 * When finder==slow, they meet at the entry point of the circle.
		 */
		// you can also user find to look for fast
		while (find != fast) {
			fast = nums[fast];
			find = nums[find];
		}
		return find;
	}
	/**
	 * https://leetcode.com/problems/linked-list-cycle-ii/. Use two pointers the
	 * fast and the slow. The fast one goes forward two steps each time, while
	 * the slow one goes only step each time. They must meet the same item when
	 * slow==fast. In fact, they meet in a circle, the duplicate number must be
	 * the entry point of the circle when visiting the array from nums[0]. Next
	 * we just need to find the entry point. We use a point(we can use the fast
	 * one before) to visit form begining with one step each time, do the same
	 * job to slow. When fast==slow, they meet at the entry point of the circle.
	 * The easy understood code is as follows.
	 */
}
