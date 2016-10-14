package math_bit;

import java.util.Arrays;

/**
 * Given an array contains N numbers of 0 .. N, find which number doesn't exist
 * in the array.
 * 
 * Example Given N = 3 and the array [0, 1, 3], return 2.
 */
public class FindtheMissingNumber {

	public static void main(String[] args) {
		int[] nums = { 0, 2 };

		System.out.println(new FindtheMissingNumber().missingNumber(nums));
	}

	// 思路是既然0到n之间少了一个数，我们将这个少了一个数的数组和0到n之间完整的数组异或一下，那么相同的数字都变为0了，剩下的就是少了的那个数字了，参加代码如下：
	/**
	 * http://www.cnblogs.com/grandyang/p/4756677.html
	 * 
	 * 譬如 输入 0， 1，3 （其下标分别为0， 1， 2） 下标各自加1就变成了，1， 2， 3，
	 * 将输入数组和这个下标加1后的数组求异或就能找到miss number,其他的都对消了 x^x = 0
	 * 
	 * 譬如 输入 1,2 （其下标分别为0， 1） 下标各自加1就变成了，1， 2, 这样两个数组求异或就变成0，说明0就是下标
	 */
	public static int missingNumber(int[] nums) {
		int miss = 0;
		for (int i = 0; i < nums.length; i++) {
			miss = miss ^ ((i + 1) ^ nums[i]);
		}
		return miss;
	}

	public int findMissing(int[] nums) {
		int len = nums.length;
		int sum = 0;
		for (int num : nums) {
			sum = sum + num;
		}
		// 没丢失数字的数组长度，应该是len+1,相当于项数，首部是0， 尾部肯定是len
		// 在没有丢失任何数的情况下， 末尾的数字应该是len-1, (0... N)
		return (int) ((0 + len) * (len + 1) * 0.5 - sum);
	}

	/**
	 * Amazon 重要 https://segmentfault.com/a/1190000004614168
	 * 
	 * ，用二分法找到第一个和A[i]不相等的数i就可以了。
	 */
	public int findMissing2(int[] A) {
		int len = A.length;
		// 注意 这题目输入不一定是排好序的，所以要排序, 如果是输入时排好序的，这个算法效率最高logn
		Arrays.sort(A);
		int left = 0, right = len - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (A[mid] > mid) {
				right = mid;
			} else {
				left = mid;
			}
		}
		if (A[left] != left) {
			return left;
		} else {
			/**
			 * 注意，不能return right, 否则 如果输入，[0]就会输出0，而正确答案是1
			 */
			return left + 1;
		}
	}
}
