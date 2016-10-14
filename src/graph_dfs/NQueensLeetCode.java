package graph_dfs;

import java.util.ArrayList;

/**
 * 如何能够在8×8的国际象棋棋盘上放置八个皇后，使得任何一个皇后都无法直接吃掉其他的皇后？为了达到此目的，任两个皇后都不能处于同一条横行、纵行或斜线上
 * 
 * （best）http://www.cnblogs.com/springfor/p/3870944.html
 * http://blog.csdn.net/linhuanmars/article/details/20667175
 * http://bangbingsyb.blogspot.ca/2014/11/leetcode-n-queens-i-ii.html
 * 
 * http://www.jiuzhang.com/solutions/n-queens/
 * http://www.jiuzhang.com/solutions/n-queens-ii/
 * 
 */
public class NQueensLeetCode {

	public static void main(String[] args) {

		for (String[] stringArray : solveNQueens(4)) {
			for (String s : stringArray) {
				System.out.println(s);
			}
			System.out.println("===============");
		}
	}

	public static ArrayList<String[]> solveNQueens(int n) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		if (n <= 0) {
			return result;
		}
		int[] columnForRow = new int[n];
		// 0 means row, columnForRow 里存得是每一行，皇后所在的列号， 从第一行开始，，每一行每一行的去放
		DFS_helper(n, columnForRow, result, 0);
		return result;
	}

	private static void DFS_helper(int n, int[] columnForRow,
			ArrayList<String[]> result, int row) {
		/**
		 * 因为是按照每一行去搜索的，当行坐标值等于行数时，说明棋盘上所有行都放好皇后了，这时就把有皇后的位置标为Q，没有的地方标为.。
		 * 按照上面那个一维数组我们就可以遍历整个棋盘，当坐标为（row，columnVal[row]）的时候，就说明这是皇后的位置，标Q就行了。
		 * 下面主要就是打印出结果
		 */
		if (row == n) {
			// 每一种答案，解法对应一个String[]
			result.add(drawCheesBoard(n, columnForRow));
			return;
		}

		// 对每一row, 尝试放在不同的列，判断是否合格，只有在合格的情况下再往下 row+1,考虑下一行
		for (int col = 0; col < n; col++) {
			// (row,columnVal[row])==>(row,i)
			// 针对第row 行， i from 0 to n， 即尝试放在不同的列， 然后check是否合理
			// （columnForRow[row] 的值即使 对应的列数）

			if (!isValidPosition(row, columnForRow, col)) {
				// 如果当前棋盘合法，我们就递归处理下一行，找到正确的棋盘我们就存储到结果集里面。
				// only parameter row keep changing
				continue;
				// 这道题目中不用移除的原因是我们用一个一维数组去存皇后在对应行的哪一列，因为一行只能有一个皇后
			}
			columnForRow[row] = col;
			DFS_helper(n, columnForRow, result, row + 1);
			columnForRow[row] = 0;
		}

	}

	public static String[] drawCheesBoard(int n, int[] columnForRow) {
		String[] board = new String[n];
		for (int i = 0; i < n; i++) {
			StringBuilder strRow = new StringBuilder();
			for (int j = 0; j < n; j++) {
				if (columnForRow[i] == j)
					strRow.append('Q');
				else
					strRow.append('.');
			}
			board[i] = strRow.toString();
		}
		return board;
	}

	private static boolean isValidPosition(int row, int[] columnForRow, int col) {
		// 与之前的row的皇后位置比较
		for (int i = 0; i < row; i++) {
			// columnForRow[row] == columnForRow[i] 表示在同一列上，
			// 检查对角线就是行的差和列的差的绝对值不要相等就可以 Math.abs(columnForRow[row] -
			// columnForRow[i]) == row - i）
			if (columnForRow[i] == col
			// row must be > i, 但是，列数columnForRow[row]不一定比columnForRow[i]的列数大
					|| Math.abs(columnForRow[i] - col) == row - i)
				return false;
		}
		return true;
	}
}
