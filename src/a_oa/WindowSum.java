package a_oa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WindowSum {

	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 4, 5, 6 };

		ArrayList<Integer> B = new ArrayList<>();
		for (int i = 1; i <= 6; i++) {
			B.add(i);
		}

		System.out.println(Arrays.toString(getSumJun(A, 3)));

		System.out.println(getSumJun(B, 3));
	}

	/**
	 * Jun's 注意(arraylist == null || arraylist.size() == 0
	 * 要return一个已经初始化的arrayList而不是null，否则会有一个test case过不去
	 */
	public static List<Integer> getSumJun(List<Integer> A, int k) {
		ArrayList<Integer> result = new ArrayList<>();
		if (A == null || A.size() == 0 || k <= 0) {
			return result;
		}
		int sum = 0;
		for (int i = 0; i < A.size(); i++) {
			sum = sum + A.get(i);
			if (i >= k) {
				sum = sum - A.get(i - k);
			}
			if (i + 1 >= k) {
				result.add(sum);
			}
		}
		return result;
	}

	// another method
	public static int[] getSumJun(int[] array, int k) {
		if (array == null || array.length < k || k <= 0)
			return new int[0];

		int[] result = new int[array.length - k + 1];
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum = sum + array[i];
			if (i >= k) {
				sum = sum - array[i - k];
			}
			if (i + 1 >= k) {
				result[i - k + 1] = sum;
			}
		}
		return result;
	}

	/**
	 * from website
	 * 
	 * @param A
	 * @param k
	 * @return
	 */

	public static List<Integer> getSum(List<Integer> A, int k) {
		ArrayList<Integer> result = new ArrayList<>();
		if (A == null || A.size() == 0 || k <= 0) {
			return result;
		}
		int count = 0;
		for (int i = 0; i < A.size(); i++) {
			count++;
			if (count >= k) {
				int sum = 0;
				for (int j = i; j >= i - k + 1; j--) {
					sum += A.get(j);
				}
				result.add(sum);
			}
		}
		return result;
	}

	// another method
	public static int[] getSum(int[] array, int k) {
		if (array == null || array.length < k || k <= 0)
			return null;
		int[] rvalue = new int[array.length - k + 1];
		for (int i = 0; i < k; i++)
			rvalue[0] += array[i];

		for (int i = 1; i < rvalue.length; i++) {
			rvalue[i] = rvalue[i - 1] - array[i - 1] + array[i + k - 1];
		}
		return rvalue;
	}

}
