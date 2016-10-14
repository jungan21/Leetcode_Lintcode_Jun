package array;

import java.util.Arrays;
import java.util.Random;

public class ShuffleArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	int[] origin = null;
	Random r;
	int[] shuffled = null;

	public ShuffleArray(int[] nums) {
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
		for (int i = shuffled.length; i > 1; i--) {
			// nextInt return: j from [0, i), 不包含i, 从0到 i-1
			int j = r.nextInt(i);
			int temp = shuffled[i - 1];
			shuffled[i - 1] = shuffled[j];
			shuffled[j] = temp;
		}
		return shuffled;
	}
}
