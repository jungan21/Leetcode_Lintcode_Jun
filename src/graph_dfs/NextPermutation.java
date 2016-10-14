package graph_dfs;

import java.util.Arrays;

/**
 * Given a list of integers, which denote a permutation.
 * 
 * Find the next permutation in ascending order.
 * 
 * Notice: The list may contains duplicate integers.
 * 
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place, do not allocate extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column. 1,2,3 → 1,3,2 3,2,1 →
 * 1,2,3 1,1,5 → 1,5,1
 *
 */
public class NextPermutation {

	public static void main(String[] args) {
		NextPermutation np = new NextPermutation();
		int[] num = { 1, 2, 3 };
		System.out.println(Arrays.toString(np.nextPermutation(num)));
	}

	/**
	 * http://www.jiuzhang.com/solutions/next-permutation/ 的 Solution 2
	 * 
	 * http://www.cnblogs.com/grandyang/p/4428207.html
	 * 
	 * 看下面一个例子，有如下的一个数组
	 * 
	 * 1　　2　　7　　4　　3　　1
	 * 
	 * 下一个排列为：
	 * 
	 * 1　　3　　1　　2　　4　　7
	 * 
	 * 那么是如何得到的呢，我们通过观察原数组可以发现，如果从末尾往前看，数字逐渐变大，到了2时才减小的，然后我们再从后往前找第一个比2大的数字，是3，
	 * 那么我们交换2和3，再把此时3后面的所有数字转置一下即可，步骤如下：
	 * 
	 * 1　　2　　7　　4　　3　　1
	 * 
	 * 1　　2　　7　　4　　3　　1
	 * 
	 * 1　　3　　7　　4　　2　　1
	 * 
	 * 1　　3　　1　　2　　4　　7
	 * 
	 * 
	 */
	// O(n)
	public int[] nextPermutation(int[] num) {
		// find the last increase index
		// 1　　2　　7　　4　　3　　1, index就是 1
		int index = -1;
		for (int i = num.length - 2; i >= 0; i--) {
			if (num[i] < num[i + 1]) {
				index = i;
				break;
			}
		}
		// index = -1,表示序列整体是降序排列，next permutation就是reverse成整体升序排列
		if (index == -1) {
			reverse(num, 0, num.length - 1);
			return num;
		}

		// find the first num > num[index] (也就是从后往找第一个大于2的数字，这里就是3 其index 为4)
		int biggerIndex = index + 1;
		for (int i = num.length - 1; i > index; i--) {
			if (num[i] > num[index]) {
				biggerIndex = i;
				break;
			}
		}

		// swap them to make the permutation bigger
		int temp = num[index];
		num[index] = num[biggerIndex];
		num[biggerIndex] = temp;

		// reverse the last part
		reverse(num, index + 1, num.length - 1);
		return num;
	}

	public void reverse(int[] num, int start, int end) {
		while (start < end) {
			int temp = num[start];
			num[start] = num[end];
			num[end] = temp;
			start++;
			end--;
		}
	}
}
