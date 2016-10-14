package merge;

/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * 
 * Notice You may assume that A has enough space (size that is greater or equal
 * to m + n) to hold additional elements from B. The number of elements
 * initialized in A and B are m and n respectively.
 * 
 */
public class MergeSortedArray {

	public static void main(String[] args) {
		int[] A = new int[5];
		A[0] = 1;
		A[1] = 4;
		A[2] = 6;
		int[] B = { 2, 5 };
		merge(A, 3, B, 2);
	}

	/**
	 * @param A
	 *            : sorted integer array A which has m elements, but size of A
	 *            is m+n
	 * @param B
	 *            : sorted integer array B which has n elements
	 * @return: void
	 */

	// 算法的时间复杂度是O(m+n),m和n分别是两个数组的长度，空间复杂度是O(1)
	// http://www.jiuzhang.com/solutions/merge-sorted-array/
	public static void merge(int[] A, int m, int[] B, int n) {
		int i = m - 1, j = n - 1, index = m + n - 1;
		while (i >= 0 && j >= 0) {
			if (A[i] > B[j]) {
				A[index--] = A[i--];
			} else {
				A[index--] = B[j--];
			}
		}

		/**
		 * Note: 别忘了下面的， 否则会出错， 譬如下面的列子， 除了上面循环， j还指向B数组的最后一个元素
		 * 
		 * A(m=3, size=5)： 4, 5, 6, [], []
		 * 
		 * B(n=2, size=2): 1, 2
		 * 
		 * 上面while 结束后A， i=-1, j=1 是这样的
		 * 
		 * A： 4, 5, 4, 5, 6 (index指向 5的位置)
		 */
		while (i >= 0) {
			A[index--] = A[i--];
		}
		//实际上只要下面的j>=0的while loop就好， 因为i>=0,本身就是自己的值，无需考虑
		while (j >= 0) {
			A[index--] = B[j--];
		}
	}

}
