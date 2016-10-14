package array;

/**
 * 中位数定义：计算有限个数的数据的中位数的方法是：把所有的同类数据按照大小的顺序排列。如果数据的个数是奇数，则中间那个数据就是这群数据的中位数；
 * 如果数据的个数是偶数，则中间那2个数据的算术平均值就是这群数据的中位数。
 * 
 * There are two sorted arrays A and B of size m and n respectively. Find the
 * median of the two sorted arrays.
 * 
 * Example Given A=[1,2,3,4,5,6] and B=[2,3,4,5], the median is 3.5.
 * 
 * Given A=[1,2,3] and B=[4,5], the median is 3.
 * 
 * The overall run time complexity should be O(log (m+n)).
 * 
 * 其实就是两个数字merge到一起后(merge 后还是有序的)， 如果个数是偶数个，就是中间两个数的平均值， 如果个数是奇数个，就是中间的那个数字
 * 
 * http://www.geeksforgeeks.org/median-of-two-sorted-arrays/
 */
public class MedianofTwoSortedArrays {

	public static void main(String[] args) {
		int[] A = { 1, 3, 9, 10 };
		int[] B = { 1, 4, 6, 6, 10, 11 };
		System.out.println(new MedianofTwoSortedArrays()
				.findMedianSortedArrays(A, B));
	}
	//http://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/
	public double findMedianSortedArrays(int A[], int B[]) {
		int len = A.length + B.length;
		if (len % 2 == 1) {
			// 如果合并后是奇数， 第（len/2 + 1)th 大的数字就正好是中间的数字
			// 这里的len/2+1 表示的不是index,而是第几个数，
			return findKth(A, 0, B, 0, len / 2 + 1);
		}
		// 如果合并后是偶数，就是最中间两个数的平均值
		return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0;
	}

	/**
	 * find kth number of two sorted array， int k, k means kth (its index: k-1)
	 * 
	 * Best explaination: http://www.cnblogs.com/springfor/p/3861890.html
	 * 
	 * 
	 * jiuzhang: http://www.jiuzhang.com/solutions/median-of-two-sorted-arrays/
	 *
	 * http://www.cnblogs.com/yuzhangcmu/p/4138184.html
	 * 
	 * http://fisherlei.blogspot.ca/2012/12/leetcode-median-of-two-sorted-arrays
	 * .html
	 */
	public static int findKth(int[] A, int A_start, int[] B, int B_start, int k) {
		// 判断A是否为空，A里面没有数据可提供，因为为空时候， A_start = 0 && A.length=0
		if (A_start >= A.length) {
			// B[B_start + k - 1], 不能写成 B[k-1]
			return B[B_start + k - 1];
		}
		if (B_start >= B.length) {
			return A[A_start + k - 1];
		}
		// Base Case,终止条件 在这里必须要退出。因为k = 1的时候，不可能再分了（k一直在减小）。
		// 即表示找合并后的第一个元素,这里如果不退出，后面会无限循环
		if (k == 1) {
			return Math.min(A[A_start], B[B_start]);
		}

		/**
		 * if A[k/2-1]==B[k/2-1]，这个数就是两个数组中第k大的数。
		 * 
		 * if A[k/2-1]<B[k/2-1], =>
		 * A[0]到A[k/2-1]都不可能是第k大的数，所以需要舍弃这一半，继续从A[k/2]到A
		 * [A.length-1]继续找。当然，因为这里舍弃了A[0]到A[k/2-1]这k/2个数，那么第k大也就变成了，第k-k/2个大的数了
		 * 
		 * 如果 A[k/2-1]>B[k/2-1]，就做之前对称的操作就好。
		 * 
		 * 上面的原理，这个网址有证明： http://www.cnblogs.com/yuzhangcmu/p/4138184.html
		 */

		// 减1是因为索引本身是从0开始的。而前k大元素含有k个元素。
		int mid = k / 2 - 1;
		/**
		 * 若下标比A.length小，取此下标对应的元素，否则置为int的最大值，便于后面进行比较，免去了诸多边界条件的判断,
		 * 当下标（A_start + mid） >= A.length时候，该小标越界了，将A_key 设置为Max, 保证A_key
		 * >=B_key， 这样，A 的前K/2个元素就不会drop掉，
		 * 
		 * 总之： A的元素不够k/2,我们没法得知A的第K/2数以及之前数的大小，所有不能乱丢， 这样我们可以丢弃B前k/2
		 * 
		 * 因为A都不到 K/2个数字，假设 A 只有 K/2-1个数字，那么A前总的长度，加上B的前K/2个数字，还是比K小， （K/2 - 1）
		 * + （K/2） < K，
		 * 
		 * 这样可以放心的去掉B的前K/2个数字
		 * 
		 * A = [1, 2, 3, MAX, MAX, MAX,]
		 * 
		 * B = [4, 5, 6, 7, 8, 9, 10, MAX, MAX]
		 * 
		 * k = 8 ,k / 2 = 4;
		 * 
		 */
		// A_start + mid
		int A_key = A_start + mid < A.length ? A[A_start + mid]
				: Integer.MAX_VALUE;
		int B_key = B_start + mid < B.length ? B[B_start + mid]
				: Integer.MAX_VALUE;

		// 因为接下来的recursive call ,我们要丢弃k / 2个不是Kth的数字，这样K会一直缩小，直到k=1也就是终止条件
		int newKth = k - k / 2;
		if (A_key < B_key) {
			// A_start + k/2
			return findKth(A, A_start + k / 2, B, B_start, newKth);
		} else {
			return findKth(A, A_start, B, B_start + k / 2, newKth);
		}
	}

}
