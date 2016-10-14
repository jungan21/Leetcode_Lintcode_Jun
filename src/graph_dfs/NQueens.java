package graph_dfs;

import java.util.ArrayList;

/**
 * http://blog.csdn.net/u011095253/article/details/9158473
 * 
 * 位运算 http://www.cnblogs.com/tiny656/p/3918367.html
 * 
 * http://blog.csdn.net/kai_wei_zhang/article/details/8033194
 *
 */
public class NQueens {

	public static void main(String[] args) {

		for (ArrayList<String> stringList : new NQueens().solveNQueens(4)) {

			for (String s : stringList) {
				System.out.println(s);
			}
			System.out.println("===============");
		}
	}

	/**
	 * jiuzhang
	 */
	ArrayList<ArrayList<String>> solveNQueens(int n) {
		ArrayList<ArrayList<String>> result = new ArrayList<>();
		if (n <= 0) {
			return result;
		}
		// 每一行 Q所在的列数
		ArrayList<Integer> columnForRowList = new ArrayList<Integer>();
		DFS(n, columnForRowList, result, 0);
		return result;
	}

	private void DFS(int n, ArrayList<Integer> columnForRowList,
			ArrayList<ArrayList<String>> result, int row) {
		if (row == n) {
			result.add(drawChessboard(columnForRowList));
			return;
		}

		for (int col = 0; col < n; col++) {
			if (!isValid(columnForRowList, col)) {
				continue;
			}
			columnForRowList.add(col);
			DFS(n, columnForRowList, result, row + 1);
			columnForRowList.remove(columnForRowList.size() - 1);
		}
	}

	private ArrayList<String> drawChessboard(ArrayList<Integer> columnForRowList) {
		ArrayList<String> chessboard = new ArrayList<>();
		for (int i = 0; i < columnForRowList.size(); i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < columnForRowList.size(); j++) {
				if (j == columnForRowList.get(i)) {
					sb.append('Q');
				} else {
					sb.append('.');
				}
			}
			chessboard.add(sb.toString());
		}

		return chessboard;
	}

	private boolean isValid(ArrayList<Integer> columnForRowList, int col) {
		int row = columnForRowList.size();
		for (int i = 0; i < row; i++) {
			// same column
			if (columnForRowList.get(i) == col) {
				return false;
			}

			if ((row - i) == Math.abs(col - columnForRowList.get(i))) {
				return false;
			}
		}
		return true;
	}

}
