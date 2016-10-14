package matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[][] = generateMatrix(3);

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j]);
			}
			System.out.println();
		}

	}

	public static int[][] generateMatrix(int n) {
		int total = n * n;
		int[][] matrix = new int[n][n];

		int top = 0;
		int bottom = n - 1;
		int left = 0;
		int right = n - 1;
		int number = 1;
		// 注意条件
		while (number <= total) {
			for (int i = left; i <= right; i++) {
				matrix[top][i] = number++;
			}
			top++;

			if (left > right || top > bottom)
				break;

			for (int i = top; i <= bottom; i++) {
				matrix[i][right] = number++;
			}
			right--;

			if (left > right || top > bottom)
				break;

			for (int i = right; i >= left; i--) {
				matrix[bottom][i] = number++;
			}
			bottom--;

			if (left > right || top > bottom)
				break;

			for (int i = bottom; i >= top; i--) {
				matrix[i][left] = number++;
			}
			left++;

			if (left > right || top > bottom)
				break;
		}

		return matrix;
	}

	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<Integer>();
		if (matrix.length == 0 || matrix[0].length == 0)
			return res;

		int top = 0;
		int bottom = matrix.length - 1;
		int left = 0;
		int right = matrix[0].length - 1;

		while (true) {
			for (int i = left; i <= right; i++) {
				res.add(matrix[top][i]);
			}
			top++;

			if (left > right || top > bottom)
				break;

			for (int i = top; i <= bottom; i++) {
				res.add(matrix[i][right]);
			}
			right--;

			if (left > right || top > bottom)
				break;

			for (int i = right; i >= left; i--) {
				res.add(matrix[bottom][i]);
			}
			bottom--;

			if (left > right || top > bottom)
				break;

			for (int i = bottom; i >= top; i--) {
				res.add(matrix[i][left]);
			}
			left++;

			if (left > right || top > bottom)
				break;
		}

		return res;
	}
}
