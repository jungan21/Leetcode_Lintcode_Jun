package company.walmart;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * Empty cells are indicated by the character '.'.
 * 
 * You may assume that there will be only one unique solution.
 *
 */
public class SudokuSolver {

	public static void main(String[] args) {
		int k = 3;
		System.out.println((char) (k + '0'));
		char k1 = '3';
		System.out.println((int) (k1 - '0'));
		System.out.println(k1);
		for (char num = '1'; num <= '9'; num++) {
			System.out.print(num + ", ");
		}
	}

	/**
	 * method1 推荐 和N Queen思路一样 http://www.cnblogs.com/springfor/p/3884252.html
	 */
	public void solveSudoku(char[][] board) {
		if (board == null || board.length == 0) {
			return;
		}
		helper(board);
	}

	/**
	 * 不能用 (char) (k - '0');,只能用加号,
	 * 
	 * int -> char(用 + '0'), int k =3, (char) (k +'0') = '3'
	 * 
	 * char -> int (用 - '0'), char k=3, (int) (k-'0') = 3;
	 * 
	 * 为了方便处理，也可以像下面的写法：
	 * 
	 * for (char num = '1'; num <= '9'; num++)
	 */
	private boolean helper(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// 根据题目要求，不等于‘.’的地方不需要赋值
				if (board[i][j] != '.') {
					continue;
				}
				// 枚举尝试赋予不同的值
				for (int k = 1; k <= 9; k++) {
					char ch = (char) (k + '0');
					// 如果不合理，就不赋值，尝试下一个数
					if (!isValid(board, i, j, ch)) {
						continue;
					}
					// 只有合理的情况下，才赋值，并做DFS和NQueue思路一样
					board[i][j] = ch;
					if (helper(board)) {
						return true;
					}
					board[i][j] = '.';
				}
				// 本格不符合规则，那么就可以马上返回了，所以不用到所有循环外。
				// 就是不用等到之后的循环完了。
				return false;
			}
		}
		// 别忘记了最后，i==9&&j==9的时候跳出循环，这个时候就是成功了，要返回true
		return true;
	}

	private boolean isValid(char[][] board, int i, int j, char ch) {
		// check column
		for (int row = 0; row < 9; row++)
			if (board[row][j] == ch)
				return false;
		// check row
		for (int col = 0; col < 9; col++)
			if (board[i][col] == ch)
				return false;
		// check block
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				/**
				 * 计算(i,j)所在的小区域内 譬如i=1， j=4(1, 4)
				 * 
				 * 区间左上角是(0,3), 右下角(2, 5)
				 */
				int x = i / 3 * 3 + row;
				int y = j / 3 * 3 + col;
				if (board[x][y] == ch) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * method 2
	 * 
	 * To do:
	 * 
	 * http://www.cnblogs.com/TenosDoIt/p/3800485.html
	 * 
	 * http://blog.csdn.net/kenden23/article/details/17080375
	 * 
	 * 
	 * http://blog.csdn.net/linhuanmars/article/details/20748761
	 * 
	 * http://www.cnblogs.com/grandyang/p/4421852.html
	 */

	public void solveSudoku1(char[][] board) {
		if (board == null || board.length == 0) {
			return;
		}
		helper(board, 0, 0);
	}

	public boolean helper(char[][] board, int i, int j) {

		if (i == 9) {
			return true;
		}
		if (j >= 9) {
			return helper(board, i + 1, 0);
		}

		if (board[i][j] == '.') {
			for (char k = '1'; k <= '9'; k++) {
				char ch = k;
				if (!isValid(board, i, j, ch)) {
					continue;
				}
				board[i][j] = ch;
				if (helper(board, i, j + 1)) {
					return true;
				}
				board[i][j] = '.';
			}
		} else {
			return helper(board, i, j + 1);
		}
		return false;
	}

}
