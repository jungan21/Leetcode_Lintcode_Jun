package math_bit;

import java.util.ArrayList;

/**
 * Given an array of integers, the majority number is the number that occurs
 * (more than 1/3 of the size) of the array.
 * 
 * Find it.
 * 
 * Notice
 * 
 * There is only one majority number in the array.
 *
 */
public class MajorityNumberII {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(4);
		list.add(4);
		System.out.println(majorityNumber(list));
	}

	/**
	 * 思路： 三三抵销法，但是也有需要注意的地方, 如果出现3个不一样的数，就抵消掉, 最后小于n/3个的元素都被抵消了
	 * 
	 * 1. 我们对cnt1,cnt2减数时，相当于丢弃了3个数字（当前数字，candidate1,
	 * candidate2）。也就是说，每一次丢弃数字，我们是丢弃3个不同的数字。
	 * 
	 * 而Majority number超过了1/3所以它最后一定会留下来。
	 * 
	 * 设定总数为N, majority number次数为m。丢弃的次数是x。则majority 被扔的次数是x
	 * 
	 * 而m > N/3, N - 3x > 0.
	 * 
	 * 3m > N, N > 3x 所以 3m > 3x, m > x 也就是说 m一定没有被扔完
	 * 
	 * 最坏的情况，Majority number每次都被扔掉了，但它一定会在n1,n2中。
	 * 
	 * 2. 为什么最后要再检查2个数字呢(从头开始统计，而不用剩下的count1, count2)？因为数字的编排可以让majority
	 * 数被过度消耗，使其计数反而小于n2，或者等于n2.前面举的例子即是。
	 * 
	 * 另一个例子：
	 * 
	 * 1 1 1 1 2 3 2 3 4 4 4 这个 1就会被消耗过多，最后余下的反而比4少。
	 * 
	 * http://www.cnblogs.com/EdwardLiu/p/4331430.html
	 * 
	 */

	/**
	 * 
	 * http://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/72996
	 * 
	 * 
	 * http://www.cnblogs.com/EdwardLiu/p/4331430.html
	 */

	public static int majorityNumber(ArrayList<Integer> nums) {
		if (nums == null || nums.isEmpty())
			return -1;

		int key1 = -1, key2 = -1;
		int count1 = 0, count2 = 0;
		for (int num : nums) {
			if (count1 == 0) {
				key1 = num;
				count1 = 1;
				continue;
			}
			if (count2 == 0 && key1 != num) {
				key2 = num;
				count2 = 1;
				continue;
			}

			if (num == key1) {
				count1++;
			} else if (num == key2) {
				count2++;
			} else {
				// 该数及不等于key1 也不等于key2, 如果出现3个不一样的数，就抵消掉
				// 抵消之后，剩下来的数中，主元素一定仍然超过1/3, 1/k。对于1/k的情况（
				count1--;
				count2--;
			}
		}

		count1 = 0;
		count2 = 0;
		for (int num : nums) {
			if (key1 == num) {
				count1++;
			} else if (key2 == num) {
				count2++;
			}
		}
		return count1 > count2 ? key1 : key2;
	}
}
