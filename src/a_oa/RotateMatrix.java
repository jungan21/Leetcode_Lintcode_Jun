package a_oa;

import java.util.Arrays;

/**
 * 第一个是旋转image，可以参见leetcode https://leetcode.com/problems/rotate-image/，
 * 不过不一样的是1.不是正方形的 2.给一个flag参数用来决定是往左还是往右。如果之前的算法不是in
 * space的话应该没有啥差别。我刚开始没怎么看题就做了以为正方形的，结果一直有几个test过不去。。直到重新读题
 * 
 * 把一个m*n的矩阵旋转90度，给一个flag规定是向左转还是向右转
 *
 *
 *
 * rotate matrix好像有变化，flag的值和左旋右旋的对应倒过来了，反正我是从面经里看到的代码，写完后发现要把左旋右选的选择换一下就pass了
 *
 *
 * Rotate by +90:
 * 
 * Transpose Reverse each row Rotate by -90:
 * 
 * Transpose Reverse each column Rotate by +180:
 * 
 * Method 1: Rotate by +90 twice
 * 
 * Method 2: Reverse each row and then reverse each column
 * 
 * Rotate by -180:
 * 
 * Method 1: Rotate by -90 twice
 * 
 * Method 2: Reverse each column and then reverse each row
 */
public class RotateMatrix {

	public static void main(String[] args) {
		int[][] A = { { 1, 2}, {3, 4} };
		int[][] result = rotate(A, 0);
		for (int[] row : result) {
			System.out.println(Arrays.toString(row));
		}

	}

	/**
	 * http://blog.csdn.net/kenden23/article/details/17200067
	 * 
	 * 原矩阵到转置矩阵(行变位列)是很容易计算的，那么从转置矩阵到选择矩阵就只需要reverse每行的元素就可。
	 */
	public static int[][] rotate(int[][] matrix, int flag) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return matrix;
		// int m = matrix.length, n = matrix[0].length;
		int[][] result;
		result = transpose(matrix);
		flip(result, flag);
		return result;
	}

	// 转置， 原来的行变为列，列变为行
	private static int[][] transpose(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		int[][] result = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				result[i][j] = matrix[j][i];
		return result;
	}

	private static void flip(int[][] matrix, int flag) {
		int m = matrix.length, n = matrix[0].length;
		if (flag == 1) { // clock wise 90 degree
			for (int i = 0; i < m; i++)
				for (int j = 0; j < n / 2; j++) {
					int temp = matrix[i][j];
					matrix[i][j] = matrix[i][n - j - 1];
					matrix[i][n - j - 1] = temp;
				}
		} else {
			for (int i = 0; i < m / 2; i++)
				for (int j = 0; j < n; j++) {
					int temp = matrix[i][j];
					matrix[i][j] = matrix[m - i - 1][j];
					matrix[m - i - 1][j] = temp;
				}
		}
	}

}
