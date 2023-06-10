package company.walmart;

import java.util.HashSet;

/**
 * Determine whether a Sudoku is valid.
 * 
 * The Sudoku board could be partially filled, where empty cells are filled with
 * the character ..
 * 
 * Notice
 * 
 * A valid Sudoku board (partially filled) is not necessarily solvable. Only the
 * filled cells need to be validated.
 *
 *
 * What is Sudoku?
 * 
 * http://sudoku.com.au/TheRules.aspx
 * https://zh.wikipedia.org/wiki/%E6%95%B8%E7%8D%A8
 * https://en.wikipedia.org/wiki/Sudoku
 * http://baike.baidu.com/subview/961/10842669.htm
 */
public class ValidSudoku {

	public static void main(String[] args) {

	}

	public boolean isValidSudoku(char[][] board) {
		HashSet<Character> set = new HashSet<Character>();
		// Check for each row
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.')
					continue;
				if (set.contains(board[i][j]))
					return false;
				set.add(board[i][j]);
			}
			set.clear();
		}

		// Check for each column
		// 注意 board[j][i]， j, i顺序
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[j][i] == '.')
					continue;
				if (set.contains(board[j][i]))
					return false;
				set.add(board[j][i]);
			}
			set.clear();
		}

		// Check for each sub-grid
		for (int i = 0; i < 9; i += 3) {
			// i = 0,时候， j=0, 3, 6 i.e. (0,0),(0,3),(0,6)
			// 即，i=0,时候 遍历了 (0,0) 到 （2，8）的所有点，然后i+3,去遍历下一个3行
			for (int j = 0; j < 9; j += 3) {
				// 有点类似于Search2DMatrix.java里 一维坐标算二位坐标的方式
				for (int k = 0; k < 9; k++) {
					int x = i + k / 3;
					int y = j + k % 3;
					if (board[x][y] == '.')
						continue;
					if (set.contains(board[x][y]))
						return false;
					set.add(board[x][y]);
				}
				set.clear();
			}
		}
		return true;
	}

}
