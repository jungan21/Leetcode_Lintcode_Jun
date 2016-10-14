package longest_minimum;

/**
 * Given an array of n POSITIVE integers and a positive integer s, find the
 * minimal length of a subarray of which the sum ≥ s. If there isn't one, return
 * -1 instead.
 *
 *
 * Given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal
 * length under the problem constraint.
 */
public class MinimumSizeSubarraySum {

	public static void main(String[] args) {
		int nums[] = { 2, 3, 1, 2, 4, 3 };
		int s = 7;
		minimumSize(nums, 7);
	}

	/**
	 * O(n) jiuzhang template
	 * 
	 * 虽然内部有个while loop, 但是由于, i, j两个指针各自都只是遍历一遍数组O(2n)= O(n)
	 * 
	 * 因为j并不是每次都回退到起点， 如果两层 for loop(i, 0 to n), (j, i to n),
	 * 这样，每次j都要回退，这样，每次j其实都重复扫描和很多元素，这种才是O(n^2)
	 * 
	 * 这种题目和sliding window的区别是，sliding window的窗口大小是固定的
	 * 
	 * 思路： 对于每一个i, 将j边移动 直到sum>=s,后面的j就暂时不用看了，因为都是正数，如果加上去，所以肯定大于s
	 * 
	 */
	public static int minimumSize(int[] nums, int s) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int i = 0;
		int j = 0;
		int sum = 0;
		// i左窗口， j右窗口
		// 和 minimum window substring类似，不能设置为nums.length, 而只能设置为MAX_VALUE;
		int minLen = Integer.MAX_VALUE;
		for (i = 0; i < nums.length; i++) {
			while (j < nums.length && sum < s) {
				sum = sum + nums[j];
				j++;
			}
			// 出了while loop的时候，sum>=s, 但是j已经停留在了下一个位置，所有j-i就行
			// 条件判断不能省略
			if (sum >= s) {
				minLen = Math.min(minLen, j - i);
			}
			// i是左窗口，由于for loop接下来i要往前移动，所以要减去nums[i],对于下一个i
			sum = sum - nums[i];
		}

		return minLen == Integer.MAX_VALUE ? -1 : minLen;
	}

	/**
	 * O(n) Jun
	 */
	public int minimumSizeJun(int[] nums, int s) {
		if (nums == null || nums.length == 0) {
			return -1;
		}

		int pre = 0;
		int sum = 0;

		int minLen = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			sum = sum + nums[i];
			while (sum >= s) {
				minLen = Math.min(minLen, i - pre + 1);
				sum = sum - nums[pre];
				pre++;
			}
		}

		return minLen == Integer.MAX_VALUE ? -1 : minLen;
	}

}
