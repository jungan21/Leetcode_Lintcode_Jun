package binarySearch;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KclosestNumbersinSortedArray {

	public static void main(String[] args) {
		// int[] A = { 1, 4, 6, 8 };
		int[] A = { 1, 2, 3 };
		System.out.println(kClosestNumbers(A, 2, 3));

	}

	/**
	 * jiuzhang: O(logn + k)
	 */
	public static int[] kClosestNumbers(int[] A, int target, int k) {
		if (A == null || A.length == 0) {
			return A;
		}
		int[] result = new int[k];
		// 只有k > A.length才return A, k = A.length不能直接return A,因为k== A.length, 要把A中的元素按与A的diff大小排序
		if (k > A.length) {
			return A;
		}
		if (k < 1) {
			return new int[0];
		}

		// return first index that >= target
		int index = firstIndex(A, target);
		// two points
		int start = index - 1;
		int end = index;
		int i = 0;
		// || 的关系
		while (i < k && (start >= 0 || end < A.length)) {
			int diff1 = start >= 0 ? target - A[start] : Integer.MAX_VALUE;
			int diff2 = end < A.length ? A[end] - target : Integer.MAX_VALUE;

			if (diff1 <= diff2) {
				result[i++] = A[start--];
			} else {
				result[i++] = A[end++];
			}
		}
		return result;
	}

	// find first index that >= target
	private static int firstIndex(int[] A, int target) {
		int start = 0, end = A.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (A[mid] < target) {
				start = mid;
			} else if (A[mid] >= target) {
				end = mid;
			}
		}

		if (A[start] >= target) {
			return start;
		}
		if (A[end] >= target) {
			return end;
		}
		return A.length;
	}

	/**
	 * method2: nlogn 时间复杂度太高， Lintcode过不了
	 */
	public int[] kClosestNumbers2(int[] A, int target, int k) {
		int[] result = new int[k];
		if (A == null || A.length == 0) {
			return A;
		}

		if (k <= 0) {
			return new int[0];
		}
		// k == A.length的时候，不能直接return A, 因为题目对输出顺序有要求
		if (k > A.length) {
			return A;
		}
		// 注意 因为 内部类 Comparator要用，所以必须是要final的
		final int targetFinal = target;

		// Comparator<Integer> comparator = new Comparator<Integer>() {
		// public int compare(Integer a, Integer b) {
		// if (Math.abs(a - targetFinal) == Math.abs(b - targetFinal)) {
		// return a - b;
		// } else {
		// return Math.abs(a - targetFinal)
		// - Math.abs(b - targetFinal);
		// }
		//
		// }
		// };

		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k,
				new Comparator<Integer>() {
					public int compare(Integer a, Integer b) {
						if (Math.abs(a - targetFinal) == Math.abs(b
								- targetFinal)) {
							return a - b;
						} else {
							return Math.abs(a - targetFinal)
									- Math.abs(b - targetFinal);
						}

					}
				});

		for (int a : A) {
			minHeap.offer(a);
		}

		for (int i = 0; i < k; i++) {
			result[i] = minHeap.poll();
		}
		return result;

	}

}
