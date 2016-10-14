package math_bit;

/**
 * Given 3*n + 1 numbers, every numbers occurs triple times except one, find it.
 *
 * Example
 * 
 * Given [1,1,2,3,3,3,2,2,4,1] return 4
 */
public class SingleNumberII {

	public static void main(String[] args) {
		int[] A = { 14, 14, 14, 9 };
		System.out.println(singleNumber(A));

	}

	/**
	 *
	 * 由于x^x^x = x，无法直接利用I的方法来解。但可以应用类似的思路，即利用位运算来消除重复3次的数。以一个数组[14 14 14
	 * 9]为例，将每个数字以二进制表达：
	 * 
	 * 1110
	 * 
	 * 1110
	 * 
	 * 1110
	 * 
	 * 1001
	 * 
	 * _____
	 * 
	 * 4331 对每一位进行求和 1001 对每一位的和做%3运算，来消去所有重复3次的数
	 * 
	 * 
	 * 计算机是怎么存储数字的。考虑全部用二进制表示，如果我们把 第 ith 个位置上所有数字的和对3取余，那么只会有两个结果 0 或 1
	 * (根据题意，3个0或3个1相加余数都为0). 因此取余的结果就是那个 “Single Number”.
	 * 
	 * 一个直接的实现就是用大小为 32的数组来记录所有 位上的和。
	 * 
	 * 创建一个长度为32的数组a，a[i]表示所有数字在i位出现的次数。 假如a[i]是3的整数倍，则忽略；否则就把该位取出来组成答案
	 * 
	 * O(n) time, O(1) space因为 32是常量
	 */
	public static int singleNumberJiuzhang(int[] A) {
		if (A == null || A.length == 0) {
			return -1;
		}
		int result = 0;
		/**
		 * 基本思想是每个数都可以表示成二进制形式，进而统计每个数在每一位出现的次数
		 * 
		 * 平时计算二进制都是从右边往左边(i.e. 左边是高位，右边是低位)，由于这里用数组表示，左边是低位，右边是高位
		 */
		int[] bits = new int[32];
		for (int i = 0; i < 32; i++) {
			// 统计每个数在每一位出现的次数, bit[i]表示A中所有数字在i位出现的次数
			bits[i] = 0;
			for (int j = 0; j < A.length; j++) {
				// &i, 取二进制的右数第i位
				// 要判断某个数(如14, 1110)的二进制表示，在第i位(最右边是第0位) 是否为1， A[j] >> i, 右移i位，
				// &1(0001), 因为1的二进制最后一位是1，两者求&就可以判断出第i未
				if ((A[j] >> i & 1) == 1) {
					bits[i]++;
				}
			}
			// | 是 OR 运算符,在位运算里 | (i.e. OR) 也就是加的意思
			// | (bits[i] << i); 将二进制 右数第i位(i是index,从0开始) 强制变为 bits[i]
			result = result | (bits[i] % 3 << i);
			// result = result + (bits[i]%3 << i);
		}
		return result;
	}

	/**
	 * method 2: http://www.cnblogs.com/springfor/p/3870863.html
	 */
	public static int singleNumber(int[] A) {
		if (A.length == 0 || A == null)
			return 0;

		int[] cnt = new int[32];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < 32; j++) {
				if ((A[i] >> j & 1) == 1) {
					cnt[j]++;
				}
			}
		}
		int res = 0;
		// 对于输入{14， 14， 14， 9}, cnt数组是{1， 3， 3， 4， 0...0}
		for (int i = 0; i < 32; i++) {
			// 之所以要<< i， 因为我们的cnt数组，左边是低位，右边是高位， 而实际的二进制表示法是，左边是高位，右边是高位，（i.e.
			// 4, 3, 3, 1）
			res += (cnt[i] % 3 << i);
			// res |= (cnt[i]%3 << i);
		}
		cnt = null;
		return res;
	}

	/**
	 * ，用3个整数来表示INT的各位的出现次数情况，one表示出现 了1次，two表示出现了2次。当出现3次的时候该位清零。最后答案就是one的值。
	 * 
	 * ones 代表第ith 位只出现一次的掩码变量 twos 代表第ith 位只出现两次次的掩码变量 threes 代表第ith
	 * 位只出现三次的掩码变量 假设在数组的开头连续出现3次5，则变化如下：
	 * 
	 * http://www.acmerblog.com/leetcode-single-number-ii-5394.html
	 */
	public int singleNumberII(int[] A) {
		int one = 0, two = 0, three = 0;
		for (int a : A) {
			two |= one & a;
			// 异或3次 和 异或 1次的结果是一样的
			one ^= a;
			three = one & two;
			// 对于ones 和 twos 把出现了3次的位置设置为0 （取反之后1的位置为0）
			one &= ~three;
			two &= ~three;
		}
		return one;
	}

}
