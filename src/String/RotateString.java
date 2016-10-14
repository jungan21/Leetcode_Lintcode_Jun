package String;

public class RotateString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * param A: A string param offset: Rotate string with offset. return:
	 * Rotated string.
	 */
	public char[] rotateString(char[] A, int offset) {
		if (A == null || A.length == 0) {
			return A;
		}

		offset = offset % A.length;

		// lenghth of left part;
		int leftLen = A.length - offset;

		reverse(A, 0, leftLen - 1);
		reverse(A, leftLen, A.length - 1);
		reverse(A, 0, A.length - 1);
		return A;
	}

	private void reverse(char[] A, int start, int end) {
		while (start < end) {
			char temp = A[start];
			A[start] = A[end];
			A[end] = temp;
			start++;
			end--;
		}
	}
}
