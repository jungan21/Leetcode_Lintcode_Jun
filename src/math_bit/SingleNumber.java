package math_bit;

import java.util.Arrays;

/**
 * Given 2*n + 1 numbers, every numbers occurs twice except one, find it.
 * 
 * Given [1,2,2,1,3,4,3], return 4
 *
 */
public class SingleNumber {

	public static void main(String[] args) {
		int a = 2;
		int b = 1;
		System.out.println(a | b);
	}

	/**
	 * bit manipulation. XOR(^) will return 1 only on two different bits.
	 * 
	 * if two numbers are the same, XOR will return 0. Finally only one number
	 * left, that's the single number
	 * 
	 * 
	 * result = 0000; result ^ 1 = 0001, 0001 ^ 0001 = 0
	 * 
	 * XOR(^)异或 ：  A XOR A = 0;    A XOR B XOR A = B;
	 * 
	 */
	public int singleNumber(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int result = 0;
		for (int a : A) {
			// 用到异或的性质：对于任何数x，都有x^x=0，x^0=x
			// 位操作符： https://zh.wikipedia.org/wiki/%E4%BD%8D%E6%93%8D%E4%BD%9C
			// ^ ,两个不等的时候，返回 1
			result = result ^ a;
			// System.out.println("test" + String.valueOf(rst));
		}
		return result;
	}

	// jun
	public int singleNumberJun(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		if (A.length == 1) {
			return A[0];
		}
		int len = A.length;
		int n = (len - 1) / 2;

		Arrays.sort(A);

		int pre = 0;
		int cur = 1;

		int count = 1;
		while (cur < len) {
			if (A[cur] == A[pre]) {
				count += 1;
			}
			if (count == 2) {
				pre += 2;
				cur += 2;
				count = 1;
			} else {
				break;
			}
		}
		return A[pre];
	}
}
