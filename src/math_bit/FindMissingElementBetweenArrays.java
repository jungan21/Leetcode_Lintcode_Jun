package math_bit;

public class FindMissingElementBetweenArrays {

	public static void main(String[] args) {

		int[] A = { 1, 8, 6 };
		int[] B = { 1, 6, 7, 8 };
		System.out.println(findMiss(A, B));

	}

	public static int findMiss(int[] A, int[] B) {

		int result = 0;
		for (int a : A) {
			result ^= a;
		}
		for (int b : B) {
			result ^= b;
		}
		return result;
	}

}
