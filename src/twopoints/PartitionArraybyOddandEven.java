package twopoints;

import java.util.Arrays;

public class PartitionArraybyOddandEven {

	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 4, 5 };
		partitionArray(A);
	}

	public static void partitionArray(int[] nums) {
		int start = 0, end = nums.length - 1;
		while (start < end) {
			while (start < end && nums[start] % 2 == 1) {
				start++;
			}
			while (start < end && nums[end] % 2 == 0) {
				end--;
			}
			if (start < end) {
				int temp = nums[start];
				nums[start] = nums[end];
				nums[end] = temp;
				start++;
				end--;
			}
		}
		System.out.println(Arrays.toString(nums));
	}

}
