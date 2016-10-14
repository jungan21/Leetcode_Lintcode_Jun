package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Given an array of integers, find the first repeating element in it. We need
 * to find the element that occurs more than once and whose index of first
 * occurrence is smallest.
 * 
 * Examples:
 * 
 * Input: arr[] = {10, 5, 3, 4, 3, 5, 6} Output: 5 [5 is the first element that
 * repeats]
 * 
 * Array {7,2,2,3,7} first repeated Number : 7
 * 
 * @author jungan
 *
 */
public class FindFirstRepeatingNumberinArray {

	public static void main(String[] args) {
		int[] A = { 6, 10, 5, 4, 9, 120, 4, 6, 10 };
		System.out.println(findFirstRepated3(A));
	}

	// with HashMap
	public static int findFirstRepated(int[] A) {

		// HashSet<Integer> set = new HashSet<>();
		// key: A[i], value: index i.e. i
		HashMap<Integer, Integer> map = new HashMap<>();

		int resultIndex = A.length;
		for (int i = 0; i < A.length; i++) {
			if (!map.containsKey(A[i])) {
				map.put(A[i], i);
			} else {
				resultIndex = Math.min(resultIndex, map.get(A[i]));
			}
		}
		return A[resultIndex];
	}

	// with HashSet
	public static int findFirstRepated2(int[] A) {

		HashSet<Integer> set = new HashSet<>();
		int resultIndex = A.length;
		for (int i = A.length - 1; i >= 0; i--) {
			if (!set.contains(A[i])) {
				set.add(A[i]);
			} else {
				resultIndex = i;
			}
		}
		return A[resultIndex];
	}

	// with HashSet 2
	public static int findFirstRepated4(int[] A) {

		HashSet<Integer> set = new HashSet<>();
		int resultIndex = A.length;
		for (int i = A.length - 1; i >= 0; i--) {
			if (!set.contains(A[i])) {
				set.add(A[i]);
			} else {
				resultIndex = i;
			}
		}
		return A[resultIndex];
	}

	// sort + binary search;
	/**
	 * ) Copy the given array to an auxiliary array temp[].
	 * 
	 * 2) Sort the temp array using a O(nLogn) time sorting algorithm.
	 * 
	 * 3) Scan the input array from left to right. For every element, count its
	 * occurrences in temp[] using binary search. As soon as we find an element
	 * that occurs more than once, we return the element. This step can be done
	 * in O(nLogn) time.
	 * 
	 */
	public static int findFirstRepated3(int[] A) {

		int resultIndex = -1;
		int[] B = Arrays.copyOf(A, A.length);
		Arrays.sort(B);
		for (int i = 0; i < A.length; i++) {
			if (binarySearchCount(B, A[i]) > 1) {
				resultIndex = i;
				break;
			}
		}
		return A[resultIndex];
	}

	// find the occurance of a specifc number in a sorted array
	private static int binarySearchCount(int[] A, int target) {

		// find the first occurance
		int firstOccurIndex = -1;
		int lastOccurIndex = -1;
		int start = 0;
		int end = A.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (A[mid] < target) {
				start = mid;
			} else if (A[mid] > target) {
				end = mid;
			} else {
				end = mid;
			}
		}

		if (A[start] == target) {
			firstOccurIndex = start;
		}
		if (A[end] == target) {
			firstOccurIndex = end;
		}

		// find the last occurance index
		start = 0;
		end = A.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (A[mid] < target) {
				start = mid;
			} else if (A[mid] > target) {
				end = mid;
			} else {
				start = mid;
			}
		}

		if (A[end] == target) {
			lastOccurIndex = end;
		}

		if (A[start] == target) {
			lastOccurIndex = start;
		}

		return lastOccurIndex - firstOccurIndex + 1;
	}
}
