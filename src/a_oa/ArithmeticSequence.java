package a_oa;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array, return the number of arithmetic sequence, 即有多少段这样的sequence 1.
 * Give array, return the number of possible arithmetic sequence (等差数列)。
 * {-1,1,3,3,3,2,1,0} return 5
 * 
 * 
 * Give array, return the number of possible arithmetic sequence (等差数列)
 * 
 * In mathematics, an arithmetic progression (AP) or arithmetic sequence is a
 * sequence of numbers such that the difference between the consecutive terms is
 * constant. For instance, the sequence 5, 7, 9, 11, 13, 15 … is an arithmetic
 * progression with common difference of 2.
 *
 */
public class ArithmeticSequence {

	public static void main(String[] args) {

		/**
		 * 等差数列 { -1, 1, 3, 3, 3, 2, 1, 0 }==>
		 * 
		 * -1, 1, 3 || 3, 3, 3 || 3, 2, 1 || 3, 2, 1, 0 || 2, 1, 0
		 * 
		 * { 1, 1, 1, 2, 4, 6, 7, 8, 3 }; ==>1, 1,1 || 2, 4, 6 || 6, 7, 8
		 * 
		 */
		int[] A = { -1, 1, 3, 3, 3, 2, 1, 0 };
		// int[] A = { 1, 1, 1, 2, 4, 6, 7, 8, 3 };
		System.out.println(getLAS(A));
		System.out.println(Solution(A));
		System.out.println(computeNumberOfArithmeticSlices(A));
	}

	/**
	 * note: 长度小于3的没法判断，因为我们要判断 A[i] - A[i-1] = A[i+1] - A[i]这样的一段才叫Arithmetic
	 * sequence
	 * 
	 * http://wdxtub.com/interview/14520850399861.html
	 */

	public static int getLAS(int[] A) {
		// Time: O(n)
		// Space: O(1)
		if (A.length < 3)
			return 0;

		int res = 0;
		int diff = Integer.MIN_VALUE;
		int count = 0;
		int start = 0;
		for (int i = 1; i < A.length; i++) {
			int currDiff = A[i] - A[i - 1];
			if (diff == currDiff) {
				count += (i - start - 1 > 0) ? i - start - 1 : 0;
			} else {
				start = i - 1;
				diff = currDiff;
				res += count;
				count = 0;
			}
		}
		res += count;
		return res;
	}

	public static int Solution(int[] array) {
		if (array == null || array.length < 3)
			return 0;
		int rvalue = 0, gap = array[1] - array[0], length = 2;
		for (int i = 1; i < array.length - 1; i++) {
			if (array[i + 1] - array[i] == gap)
				length++;
			else {
				gap = array[i + 1] - array[i];
				if (length >= 3)
					rvalue += (length - 1) * (length - 2) / 2;
				if (rvalue > 1000000000)
					return -1;
				length = 2;
			}
		}
		if (length >= 3)
			rvalue += (length - 1) * (length - 2) / 2;
		return rvalue > 1000000000 ? -1 : rvalue;
	}

	public static int computeNumberOfArithmeticSlices(int[] A) {
		int front = 0, total = 0;
		int result = 0;
		List list;
		for (int back = 0; back < A.length && front < A.length;) {
			list = new ArrayList();
			front = back + 2;
			total = A[front - 1] - A[back];
			list.add(back);
			list.add(front - 1);
			int i = 0;
			boolean ok = false;
			while (front < A.length && A[front] - A[front - 1] == total) {
				ok = true;
				i++;
				list.add(front);
				back = front;
				front++;
				result += i;
				if (result > 1000000000)
					return -1;
			}
			if (!ok)
				back++;
		}
		return result;
	}

}
