package sort;

import java.util.Arrays;

/**
 * Best O(n log(n)), Average O(n log(n)), Worst O(n^2), Space: O(n log(n))
 * 
 * O(1) extra space
 *
 * 整体到局部的思想，因为选一个pivot后， 整体上分流，然后在局部排序
 *
 */
public class QuickSort {
	public static void main(String[] args) {
		int[] x = { 9, 2, 4, 7, 3, 7, 10 };
		System.out.println(Arrays.toString(x));

		int start = 0;
		int end = x.length - 1;

		quickSort(x, start, end);
		System.out.println(Arrays.toString(x));
	}

	public static void quickSort(int[] arr, int start, int end) {
		if (arr == null || arr.length == 0)
			return;
		// ending point
		if (start >= end) {
			return;
		}
		// pick the pivot, before while loop
		int middle = start + (end - start) / 2;
		int pivot = arr[middle];

		// make left < pivot and right > pivot
		int left = start, right = end;
		while (left <= right) {
			while (arr[left] < pivot) {
				left++;
			}

			while (arr[right] > pivot) {
				right--;
			}

			if (left <= right) {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			}
		}

		// after above while loop, left and right crossed the middle, and left >
		// right now
		// recursively sort two sub parts
		if (start < right)
			quickSort(arr, start, right);

		if (left < end)
			quickSort(arr, left, end);
	}

	public static void quickSortDescend(int[] arr, int start, int end) {
		if (arr == null || arr.length == 0)
			return;
		// ending point
		if (start >= end) {
			return;
		}
		// pick the pivot, before while loop
		int middle = start + (end - start) / 2;
		int pivot = arr[middle];

		// make left < pivot and right > pivot
		int left = start, right = end;
		while (left <= right) {
			while (arr[left] > pivot) {
				left++;
			}

			while (arr[right] < pivot) {
				right--;
			}

			if (left <= right) {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			}
		}

		// after above while loop, left and right crossed the middle, and left >
		// right now
		// recursively sort two sub parts
		if (start < right)
			quickSortDescend(arr, start, right);

		if (left < end)
			quickSortDescend(arr, left, end);
	}
}
