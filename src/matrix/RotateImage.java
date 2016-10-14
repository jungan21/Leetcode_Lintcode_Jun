package matrix;

/**
 * You are given an len x len 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Follow up: Could you do this in-place?
 * 
 * http://stackoverflow.com/questions/42519/how-do-you-rotate-a-two-dimensional-array
 * 
 *
 */
public class RotateImage {

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		rotate(matrix);
	}

	/**
	 * http://www.cnblogs.com/grandyang/p/4389572.html
	 */

	public static void rotate(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}

		int len = matrix.length;

		for (int i = 0; i < len / 2; ++i) {
			for (int j = i; j < len - 1 - i; ++j) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[len - 1 - j][i];
				matrix[len - 1 - j][i] = matrix[len - 1 - i][len - 1 - j];
				matrix[len - 1 - i][len - 1 - j] = matrix[j][len - 1 - i];
				matrix[j][len - 1 - i] = tmp;
			}
		}
	}

	/**
	 * 
	 * jiuzhang
	 */
	public void rotate1(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}

		int len = matrix.length;

		for (int i = 0; i < len / 2; i++) {
			for (int j = 0; j < (len + 1) / 2; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[len - j - 1][i];
				matrix[len - j - 1][i] = matrix[len - i - 1][len - j - 1];
				matrix[len - i - 1][len - j - 1] = matrix[j][len - i - 1];
				matrix[j][len - i - 1] = tmp;
			}
		}
	}

	/**
	 * 
	 * 先按对角线反转，再按水平中轴线反转。注意此处不能调用swap的method，因为Java是pass by value的。
	 */
	public void rotate2(int[][] matrix) {
		int len = matrix[0].length;
		int temp;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len / 2; j++) {
				temp = matrix[i][j];
				matrix[i][j] = matrix[i][len - 1 - j];
				matrix[i][len - 1 - j] = temp;
			}
		}
	}
}
