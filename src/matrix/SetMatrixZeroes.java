package matrix;


// https://leetcode.com/problems/set-matrix-zeroes/ Leetcode: 73
public class SetMatrixZeroes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int b[][] = { { 1, 5, 5, 5 }, { 4, 3, 1, 4 }, { 1, 1, 1, 4 },
				{ 1, 2, 1, 3 }, { 1, 0, 1, 1 } };// 静态初始化一个二维数组
		setZeroes(b);

		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				System.out.print(b[i][j]);
			}
			System.out.println();
		}
	}

	// with extra space
	/**
	 * 
	 * @param matrix
	 */
	public static void setZeroes(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return;
		int[] row = new int[matrix.length];
		int[] column = new int[matrix[0].length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 0) {
					// 注意 必须设置为1， 不能设置为0，因为int数组默认值就是0，否则会出很多问题
					row[i] = 1;
					column[j] = 1;
				}
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (row[i] == 1 || column[j] == 1) {
					matrix[i][j] = 0;
				}
			}
		}

	}

	// method 2, without using extra space
	// http://www.programcreek.com/2012/12/leetcode-set-matrix-zeroes-java/
	// basically use first row and column to track it
	public static void setZeroes1(int[][] matrix) {
		boolean firstRowZero = false;
		boolean firstColumnZero = false;

		// set first row and column zero or not
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				firstColumnZero = true;
				break;
			}
		}

		for (int i = 0; i < matrix[0].length; i++) {
			if (matrix[0][i] == 0) {
				firstRowZero = true;
				break;
			}
		}

		// mark zeros on first row and column
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					/**
					 * 注意这里只能用 matrix[i][0] = 0; 而不能用 matrix[i][0] =
					 * 1，就相当于顺便把第一行和第一列也处理了，
					 * 
					 * 最后，如果firstColumnZero 和 firstRowZero都为true,再把第一行 和第一例
					 * 做特殊处理
					 */
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		// use mark to set elements
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}

		// set first column and row
		if (firstColumnZero) {
			for (int i = 0; i < matrix.length; i++)
				matrix[i][0] = 0;
		}

		if (firstRowZero) {
			for (int i = 0; i < matrix[0].length; i++)
				matrix[0][i] = 0;
		}

	}


	// Jun Method 2023 April 9th not recommend
	public void setZeroesJun(int[][] matrix) {

		int m = matrix.length;
		int n = matrix[0].length;
		boolean [][] visited = new boolean[m][n];

		for (int i = 0; i<m; i++){
			for (int j = 0; j<n; j++){
				if (matrix[i][j] == 0){
					helper(matrix, visited, i, j);
				}
			}
		}

	}

	public void helper(int[][] matrix, boolean [][] visited, int i, int j){

		if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length || visited[i][j]){
			return;
		}

		visited[i][j] = true;
		matrix[i][j] = 0;

		for (int k = 0; k < matrix[i].length; k++){
			// visited[i][k] = true;
			if (!visited[i][k] && matrix[i][k] != 0){
				matrix[i][k] = 0;
				visited[i][k] = true;
			}

		}

		for (int h = 0; h < matrix.length; h++){
			if (!visited[h][j] && matrix[h][j] != 0){
				visited[h][j] = true;
				matrix[h][j] = 0;
			}
		}

	}

}
