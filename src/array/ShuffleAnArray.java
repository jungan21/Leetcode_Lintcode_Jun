package array;

import java.util.Arrays;
import java.util.Random;

public class ShuffleAnArray {

	int[] origin = null;
	Random r;
	int[] shuffled = null;

	public ShuffleAnArray(int[] nums) {
		origin = nums;
		shuffled = Arrays.copyOf(nums, nums.length);
		r = new Random();
	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
		shuffled = Arrays.copyOf(origin, origin.length);
		return shuffled;
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		// 如果从 i = shffled.length-1开始，结果就不对, 因为j = r.nextInt(shuffled.length-1),
		// 0<=0j<shuffled.length-1, 也就是最后一个小标取不到
		for (int i = shuffled.length; i > 1; i--) {
			// j [0, i), including 0, but don't include i, 0<=j<i
			int j = r.nextInt(i);
			int temp = shuffled[i - 1];
			shuffled[i - 1] = shuffled[j];
			shuffled[j] = temp;
		}
		return shuffled;
	}

	public int[] shuffle2() {
		int len = shuffled.length;

		for (int i = 0; i < len; i++) {
			int si = r.nextInt(len - i);
			int temp = shuffled[i];
			shuffled[i] = shuffled[si + i];
			shuffled[si + i] = temp;
		}
		return shuffled;
	}

	public static void main(String[] args) {
		int[] nums = {};
		ShuffleAnArray test = new ShuffleAnArray(nums);
		System.out.println(Arrays.toString(test.shuffled));

	}

}
