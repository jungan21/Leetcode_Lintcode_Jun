package duplicate;

import java.util.Arrays;

/**
 * Given a sorted array, remove the duplicates in place such that each element
 * appear only once and return the new length.
 * 
 * Do not allocate extra space for another array, you must do this in place with
 * constant memory.
 * 
 * Given input array A = [1,1,2],
 * 
 * Your function should return length = 2, and A is now [1,2].
 *
 */
public class RemoveDuplicatesfromSortedArray {

	// If we only want to count the number of unique elements, the following
	// method is good enough.
	public static int countUnique(int[] A) {
		int count = 0;
		for (int i = 0; i < A.length - 1; i++) {
			if (A[i] == A[i + 1]) {
				count++;
			}
		}
		// System.out.println("count: " + count);
		return (A.length - count);
	}

	/**
	 * follow up: return the array with all unique elements
	 */
	public static int[] removeDuplicates(int[] A) {
		if (A == null || A.length < 2)
			return A;
		
		/**
		 * pre 这么设置是有技巧的，类似RemoveDuplicatesfromSortedArrayII， pre的位置要hold住，保证0..pre的位置是满足要求的
		 */
		int pre = 0;
		int cur = 1;

		while (cur < A.length) {
			if (A[cur] == A[pre]) {
				cur++;
			} else {
				/**
				 * 由于pre之前（包含pre）,都是unique的, 上面cur++一直跑到下一个不重复的数,
				 * 这里pre++(往前挪一位的意思是，题目要求重复的数要保留1份copy)
				 */
				pre++;
				// 可以按链表来理解，pre之前的都是不重复的, 如果pre, cur,相邻，pre++后，相当于自己赋值给自己，不影响结果
				A[pre] = A[cur];
				cur++;
			}
		}

		/**
		 * 出了while loop, pre之前（包含pre）,都是unique的, pre+1 is the length of the new
		 * unique array
		 */
		int[] B = Arrays.copyOf(A, pre + 1);

		return B;
	}

	/**
	 * jiuzhang
	 * 
	 * return the unique array length
	 */
	public int removeDuplicates1(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int size = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] != A[size]) {
				A[++size] = A[i];
			} else {
				// 其实就是等于的时候， i 接着往前跑
			}
		}
		return size + 1;
	}

}
