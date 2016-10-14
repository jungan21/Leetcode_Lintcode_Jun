package graph_dfs;

import java.util.ArrayList;

public class NQueensII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	private static int sum = 0;

	public int totalNQueens(int n) {
		sum = 0;
		ArrayList<Integer> columnForRow = new ArrayList<>();
		placeQueen(n, columnForRow, 0);
		return sum;
	}

	public void placeQueen(int n, ArrayList<Integer> columnForRow, int row) {
		if (row == n) {
			sum++;
			return;
		}
		for (int col = 0; col < n; col++) {
			if (!isValid(columnForRow, row, col)) {
				continue;
			}
			columnForRow.add(col);
			placeQueen(n, columnForRow, row + 1);
			columnForRow.remove(columnForRow.size() - 1);
		}

	}

	public boolean isValid(ArrayList<Integer> columnForRow, int row, int col) {
		for (int i = 0; i < row; i++) {
			if (columnForRow.get(i) == col) {
				return false;
			}
			if ((row - i) == Math.abs(col - columnForRow.get(i))) {
				return false;
			}
		}
		return true;
	}

}
