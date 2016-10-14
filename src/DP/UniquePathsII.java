package a.DP;

/**
 * Follow up for "Unique Paths":
 * 
 * Now consider if some obstacles are added to the grids. How many unique paths
 * would there be?
 * 
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 */
public class UniquePathsII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid == null || obstacleGrid.length == 0
				|| obstacleGrid[0].length == 0) {
			return 0;
		}

		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;

		int sum[][] = new int[m][n];

		for (int i = 0; i < m; i++) {
			if (obstacleGrid[i][0] != 1) {
				sum[i][0] = 1;
			} else {
				/**
				 * Note: you must break,该点往后的所有的都不通，将会是默认值0
				 */
				break;
			}
		}

		for (int i = 0; i < n; i++) {
			if (obstacleGrid[0][i] != 1) {
				sum[0][i] = 1;
			} else {
				/**
				 * Note: you must break,该点往后的所有的都不通，将会是默认值0
				 */
				break;
			}
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (obstacleGrid[i][j] != 1) {
					sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
				} else {
					sum[i][j] = 0;
				}
			}
		}
		return sum[m - 1][n - 1];
	}

}
