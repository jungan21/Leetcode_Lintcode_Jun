package a_oa;

/**
 * 
 * 给一个数组, 数字之间最大的差的绝对值是1, 让你找到一个数字n的第一次出现的坐标, 没有就返回-1 很简单, 我们可以证明, 在开始时,
 * 数字n可能出现的地方, 就是n-nums[0], 如果没有, 就是n-nums[1]…..一直找就可以了.@author jungan
 *
 */
public class FindNumberInDiffer1Array {

	public static void main(String[] args) {
		int[] nums = { 4, 3, 4, 5, 6 };
		System.out.println(findNum(nums, 6));
	}

	public static int findNum(int[] nums, int n) {
		int i = 0;
		while (i < nums.length) {
			if (nums[i] == n) {
				return i;
			}
			int diff = Math.abs(nums[i] - n);
			i = i + diff;
		}
		return -1;
	}

}
