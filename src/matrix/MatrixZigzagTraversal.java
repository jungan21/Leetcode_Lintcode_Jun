package matrix;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in ZigZag-order.
 * 
 * 
 * Example Given a matrix:
 * 
 * [ [1, 2, 3, 4], [5, 6, 7, 8], [9,10, 11, 12] ] return [1, 2, 5, 9, 6, 3, 4,
 * 7, 10, 11, 8, 12]
 *
 */
public class MatrixZigzagTraversal {

	public static void main(String[] args) {

	}

	// http://algorithm.yuanbin.me/zh-hans/problem_misc/matrix_zigzag_traversal.html
	public int[] printZMatrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return null;

		int m = matrix.length - 1, n = matrix[0].length - 1;
		int[] result = new int[(m + 1) * (n + 1)];
		int index = 0;
		for (int i = 0; i <= m + n; i++) {
			if (i % 2 == 0) {
				for (int x = i; x >= 0; x--) {
					// valid matrix index
					if ((x <= m) && (i - x <= n)) {
						result[index] = matrix[x][i - x];
						index++;
					}
				}
			} else {
				for (int x = 0; x <= i; x++) {
					if ((x <= m) && (i - x <= n)) {
						result[index] = matrix[x][i - x];
						index++;
					}
				}
			}
		}

		return result;
	}

}
