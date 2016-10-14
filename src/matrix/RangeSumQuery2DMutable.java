package matrix;

/**
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive.
 * 
 * The update(i, val) function modifies nums by updating the element at index i
 * to val.
 * 
 * Example: Given nums = [1, 3, 5]
 * 
 * sumRange(0, 2) -> 9 update(1, 2) sumRange(0, 2) -> 8
 * 
 * Note: The array is only modifiable by the update function. You may assume the
 * number of calls to update and sumRange function is distributed evenly.
 *
 */

/**
 */

public class RangeSumQuery2DMutable {

	public static void main(String[] args) {
		int[] nums = { 1, 3, 5 };
		// RangeSumQuery2DMutable test = new RangeSumQuery2DMutable(nums);

	}

	/**
	 * http://www.cnblogs.com/grandyang/p/5300458.html
	 * 
	 * https://discuss.leetcode.com/topic/30343/java-2d-binary-indexed-tree-
	 * solution-clean-and-short-17ms
	 * 
	 * https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
	 * 
	 * // time should be O(log(m) * log(n))
	 */
	int[][] tree;
	int[][] nums;
	int m;
	int n;

	public RangeSumQuery2DMutable(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0)
			return;
		m = matrix.length;
		n = matrix[0].length;
		tree = new int[m + 1][n + 1];
		nums = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				update(i, j, matrix[i][j]);
			}
		}
	}

	public void update(int row, int col, int val) {
		if (m == 0 || n == 0)
			return;
		int delta = val - nums[row][col];
		nums[row][col] = val;
		for (int i = row + 1; i <= m; i += i & (-i)) {
			for (int j = col + 1; j <= n; j += j & (-j)) {
				tree[i][j] += delta;
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if (m == 0 || n == 0)
			return 0;
		return sum(row2 + 1, col2 + 1) + sum(row1, col1) - sum(row1, col2 + 1)
				- sum(row2 + 1, col1);
	}

	public int sum(int row, int col) {
		int sum = 0;
		for (int i = row; i > 0; i -= i & (-i)) {
			for (int j = col; j > 0; j -= j & (-j)) {
				sum += tree[i][j];
			}
		}
		return sum;
	}
	
	/**
	 * another method: https://segmentfault.com/a/1190000004238792
	 */
}
