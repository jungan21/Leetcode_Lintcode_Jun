package matrix;

/**
 * Given a 2D matrix, print all elements of the given matrix in diagonal order.
 * For example, consider the following 5 X 4 input matrix.
 * 
 * 1 2 3 4
 * 
 * 5 6 7 8
 * 
 * 9 10 11 12
 * 
 * 13 14 15 16
 * 
 * 17 18 19 20
 * 
 * ==>
 * 
 * 1
 * 
 * 5 2
 * 
 * 9 6 3
 * 
 * 13 10 7 4
 * 
 * 17 14 11 8
 * 
 * 18 15 12
 * 
 * 19 16
 * 
 * 20
 * 
 * 
 */
public class PrintMatrixDiagonally {

	public static void main(String[] args) {
		int[][] A = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		printDiagonally(A);
	}

	// 算法仅仅适用于 行列相等的matrix
	public static void printDiagonally(int[][] A) {
		for (int row = 0; row <= 2 * (A.length - 1); row++) {
			for (int x = 0; x < A.length; x++) {
				int y = row - x;
				if (y < 0 || y >= A.length) {
					// Coordinates are out of bounds; skip.
					continue;
				} else {
					System.out.print(A[x][y] + ",");
				}
			}
			System.out.println();
		}
	}

	/**
	 * 行列不相等的情况
	 * 
	 * http://www.geeksforgeeks.org/print-matrix-diagonally/
	 * 
	 * http://www.techcrashcourse.com/2015/03/c-program-to-print-matrix-
	 * diagonally.html
	 */
}
