package HackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MergeKUnsortedArray {

	public static void mainTest(String[] args) {
		int[][] A = { { 8, 7, 54 }, { 25, 84, 65 }, { 75, 24, 14 } };
		// int[][] A = { { 7, 8, 54 }, { 25, 65, 84 }, { 14, 24, 75 } };
		System.out.println(mergeNArrays(A, 0, A.length - 1));

	}

	// read one by one
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		int[][] A = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = sc.nextInt();
			}
			Arrays.sort(A[i]);
		}
		System.out.println(mergeNArrays(A, 0, m - 1));
	}

	// read line by line. recommended
	public static void main1(String[] args) {
		// note: nextLine和nextInt不能混合用，要用哪一种，就指定一种用
		Scanner sc = new Scanner(System.in);
		String[] rowCol = sc.nextLine().split(" ");
		int m = Integer.valueOf(rowCol[0]);
		int n = Integer.valueOf(rowCol[1]);
		int[][] A = new int[m][n];
		for (int i = 0; i < m; i++) {
			String[] rowStr = sc.nextLine().split(" ");
			for (int j = 0; j < n; j++) {
				A[i][j] = Integer.valueOf(rowStr[j]);
			}
			Arrays.sort(A[i]);
		}
		System.out.println(mergeNArrays(A, 0, m - 1));
	}

	public static List<Integer> mergeNArrays(int[][] arrays, int start, int end) {
		if (start == end) {
			List<Integer> list = new ArrayList<Integer>();
			for (int a : arrays[start]) {
				list.add(a);
			}
			return list;
		}
		int mid = start + (end - start) / 2;
		List<Integer> left = mergeNArrays(arrays, start, mid);
		List<Integer> right = mergeNArrays(arrays, mid + 1, end);
		return merge2Arrays(left, right);
	}

	public static List<Integer> merge2Arrays(List<Integer> A, List<Integer> B) {
		List<Integer> result = new ArrayList<>();
		if (A == null && B == null) {
			return result;
		} else if (A == null || A.size() == 0) {
			return B;
		} else if (B == null || B.size() == 0) {
			return A;
		}

		int i = 0;
		int j = 0;
		while (i < A.size() && j < B.size()) {
			if (A.get(i) <= B.get(j)) {
				result.add(A.get(i));
				i++;
			} else {
				result.add(B.get(j));
				j++;
			}
		}
		while (i < A.size()) {
			result.add(A.get(i++));
		}

		while (j < B.size()) {
			result.add(B.get(j++));
		}
		return result;
	}

}
