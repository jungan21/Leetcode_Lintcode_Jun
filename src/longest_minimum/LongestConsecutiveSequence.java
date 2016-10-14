package longest_minimum;

import java.util.HashSet;

/**
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 *
 *
 * Clarification Your algorithm should run in O(n) complexity.
 * 
 * Example Given [100, 4, 200, 1, 3, 2], The longest consecutive elements
 * sequence is [1, 2, 3, 4]. Return its length: 4.
 */
public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		//int[] nums = { 1, 2, 0, 1 };
		int[] nums = {0};
		System.out.println(longestConsecutive1(nums));
	}

	public static int longestConsecutive(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
		}

		int longest = 0;
		for (int i = 0; i < nums.length; i++) {
			int down = nums[i] - 1;
			while (set.contains(down)) {
				set.remove(down);
				down--;
			}

			int up = nums[i] + 1;
			while (set.contains(up)) {
				set.remove(up);
				up++;
			}
			longest = Math.max(longest, up - down - 1);
		}

		return longest;
	}
	
	/**
	 * antoher method: user count variable to count
	 */
	public static int longestConsecutive1(int[] num) {
		HashSet<Integer> set = new HashSet<>();

		for (int n : num) {
			set.add(n);
		}

		int maxLen = 0;
		for (int n : num) {
			int count = 1;
			int down = n - 1;
			while (set.contains(down)) {
				count++;
				// 一定要remove 否则数组过大会超时
				set.remove(down);
				down--;
			}

			int up = n + 1;
			while (set.contains(up)) {
				count++;
				set.remove(up);
				up++;
			}
			maxLen = Math.max(maxLen, count);
		}
		return maxLen;
	}
	

	// wrong method  !!!
	public static int longestConsecutive2(int[] num) {
		if (num == null || num.length == 0) {
			return 0;
		}
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < num.length; i++) {
			set.add(num[i]);
		}

		int max = 1;
		for (int i = 0; i < num.length; i++) {
			int down = num[i];
			while (set.contains(down)) {
				set.remove(down);
				down--;
			}
			// 这样写不对， 否则如果上面down的时候，remove掉了nums[i],即使有up，这里while loop也不会被执行到了
			int up = num[i];
			while (set.contains(up)) {
				set.remove(up);
				up++;
			}
			max = Math.max(max, up - down);
		}

		return max;
	}

}
