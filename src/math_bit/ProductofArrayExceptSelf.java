package math_bit;

import java.util.ArrayList;

public class ProductofArrayExceptSelf {

	public static void main(String[] args) {
		ArrayList<Integer> A = new ArrayList<>();
		A.add(1);
		A.add(2);
		A.add(3);
		System.out.println(productExcludeItselfJun(A));
	}

	public static ArrayList<Long> productExcludeItself(ArrayList<Integer> A) {
		ArrayList<Long> result = new ArrayList<>();
		if (A == null || A.size() == 0) {
			return result;
		}
		int n = A.size();
		long[] f = new long[n];
		f[n - 1] = A.get(n - 1);
		for (int i = n - 2; i >= 0; i--) {
			f[i] = f[i + 1] * A.get(i);
		}

		long temp = 1;
		for (int i = 0; i < n; i++) {
			if (i + 1 < n) {
				result.add(temp * f[i + 1]);
			} else {
				result.add(temp);
			}
			// 记录所有i位置之前的数字的乘积
			temp = temp * A.get(i);
		}
		return result;
	}

	public static ArrayList<Long> productExcludeItself1(ArrayList<Integer> A) {
		int len = A.size();
		ArrayList<Long> B = new ArrayList<Long>();
		long[] t1 = new long[len];
		long[] t2 = new long[len];

		t1[0] = 1;
		t2[len - 1] = 1;

		// scan from left to right
		for (int i = 0; i < len - 1; i++) {
			t1[i + 1] = A.get(i) * t1[i];
		}

		// scan from right to left
		for (int i = len - 1; i > 0; i--) {
			t2[i - 1] = t2[i] * A.get(i);
		}

		// multiply
		for (int i = 0; i < len; i++) {
			B.add(t1[i] * t2[i]);
		}

		return B;
	}

	public static ArrayList<Long> productExcludeItselfJun(ArrayList<Integer> A) {

		long product = 1;

		int size = A.size();
		ArrayList<Long> result = new ArrayList<Long>();
		if (size == 1) {
			result.add(1l);
			return result;
		}

		long[] forwardPro = new long[size];
		long[] backPro = new long[size];

		for (int i = 0; i < size; i++) {
			forwardPro[i] = product * A.get(i);
			product = forwardPro[i];
		}

		product = 1;
		for (int i = size - 1; i >= 0; i--) {
			backPro[i] = product * A.get(i);
			product = backPro[i];
		}

		for (int i = 0; i < size; i++) {
			if (i == 0) {
				result.add(backPro[i + 1]);
			} else if (i == size - 1) {
				result.add(forwardPro[i - 1]);
			} else {
				result.add(forwardPro[i - 1] * backPro[i + 1]);
			}
		}
		return result;
	}
}
