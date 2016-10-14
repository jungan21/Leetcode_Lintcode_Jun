package a_oa;

import java.util.Arrays;

/**
 * 第二题是有一个八个元素的int array，值为0或者1，每天晚上变一次.
 * 看两边的元素，如果两边一样，变0，不一样变1。最左边和最右边的左边和右边就算做0
 * 。举个栗子：00111001，第二天变为01101110（应该没错=。=） 两个参数，一是初始状态，二是总共几晚上
 *
 *
 * nt[] dayChange(int[] cells, int cells), cells 数组，有8个元素，每天的变化情况是 当前位置 cell =
 * (cell[i - 1] == cell[i + 1]) ? 0 : 1, 左右相等，当前位置为0， 不等为1， 默认 cells[0]左边 和
 * cells[cells.length - 1]右边的位置元素都是0， 求days天后的变化.
 */
public class DayChange_CellGrowth {

	public static void main(String[] args) {
		int[] cells = { 0, 0, 1, 1, 1, 0, 0, 1 };

		System.out.println(Arrays.toString(dayChange(cells, 200)));
		System.out.println(Arrays.toString(dayChangeJun(cells, 200)));
		System.out.println(Arrays.toString(dayChangeJunWeb(cells, 200)));
	}

	/**
	 * best one: http://www.1point3acres.com/bbs/thread-144488-2-1.html
	 */

	public static int[] dayChangeJunWeb(int[] cells, int days) {
		if (cells == null || days <= 0)
			return cells;
		for (int d = 0; d < days; d++) {
			int prev = 0;
			for (int i = 0; i < cells.length - 1; i++) {
				int cur = cells[i];
				cells[i] = prev ^ cells[i + 1];
				prev = cur;
			}
			cells[cells.length - 1] = prev ^ 0;
		}
		return cells;
	}

	public static int[] dayChangeJun(int[] cells, int days) {
		if (cells == null || days <= 0)
			return cells;
		int len = cells.length;

		for (int i = 0; i < days; i++) {
			int[] temp = new int[len];
			for (int j = 0; j < cells.length; j++) {
				if (j == 0) {
					if (cells[j + 1] == 0) {
						temp[j] = 0;
					} else {
						temp[j] = 1;
					}
				} else if (j == cells.length - 1) {
					if (cells[j - 1] == 0) {
						temp[j] = 0;
					} else {
						temp[j] = 1;
					}
				} else {
					if (cells[j - 1] == cells[j + 1]) {
						temp[j] = 0;
					} else {
						temp[j] = 1;
					}
				}
			}
			cells = temp;
		}
		return cells;
	}

	public static int[] dayChangeJunBit(int[] cells, int days) {
		if (cells == null || days <= 0)
			return cells;
		int len = cells.length;
		for (int i = 0; i < days; i++) {
			int[] temp = new int[len];
			for (int j = 0; j < cells.length; j++) {
				if (j == 0) {
					temp[j] = cells[j + 1] ^ 0;
				} else if (j == cells.length - 1) {
					temp[j] = cells[j - 1] ^ 0;
				} else {
					// 相等为0， 不等为1
					temp[j] = cells[j - 1] ^ cells[j + 1];
				}
			}
			cells = temp;
		}
		return cells;
	}
}
