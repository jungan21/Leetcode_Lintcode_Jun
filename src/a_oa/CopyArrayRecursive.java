package a_oa;

public class CopyArrayRecursive {

	public static void main(String[] args) {
		int[] A = { 3, 4, 5 };
		int[] B = new int[A.length];
		copyArray(A, B, 0);
		for (int n : B) {
			System.out.println(n);
		}
	}

	public static void copyArray(int[] A, int[] B, int index) {
		if (index >= A.length) {
			return;
		}
		B[index] = A[index];
		copyArray(A, B, index + 1);
	}

}
