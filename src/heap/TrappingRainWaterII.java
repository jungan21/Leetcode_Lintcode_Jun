package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

class Cell {
	public int x, y, h;

	Cell() {
	}

	Cell(int x, int y, int h) {
		this.x = x;
		this.y = y;
		this.h = h;
	}
}

public class TrappingRainWaterII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * Time:
	 * 
	 * 初始化heap: 最外围一共有2n+2m个元素，要放入heap 所以是 O(log(2m+2n))
	 * 
	 * 一共m*n个点，每个点都要遍历一次(上下左右遍历时) ，而每个点都要经历进出heap(size为m+n)的操作，log(m+n)
	 * 
	 * ==> 总的时间复杂度是 O(log(2m+2n)) + O(m*n*log(m+n2)) = O(mn*log(m+n))
	 * 
	 * 
	 * 
	 * http://www.jiuzhang.com/solutions/trapping-rain-water-ii/
	 * 
	 * 对于matrix的能够灌水的高度，取决于最外围一圈的高度中的最小值
	 * 
	 * 1. 用heap保存最外围的点，并且快速求得最小值 用一个visited matrix保存visit过的点
	 * 
	 * 2. 每次得到最外围的最小值，然后由外围往内灌水（四个方向）
	 * 
	 * 由于水是往低处流的， 所以对于这一类trapping water 问题，我们只用从最外层开始往内接雨水就可以。
	 * 首先对矩阵外层需要找到最小的柱子，那么就想到用堆， 从最矮的柱子往里面灌水，其本质和
	 * TrappingRainWater.java里从两头开始往中间灌水的意思是一样的
	 * 
	 * 
	 * 维护一个最小堆，保存最外面一圈的高度，因为最矮的格子决定了水能存放多少 每次取最小高度 h，与周围4个中没有被访问过的元素进行比较
	 * 如果该元素的高度小于 h，则注水到其中，并将其加入到最小堆中，设置该元素被访问过 如果该元素的高度大于
	 * h，则直接将其加入到最小堆中，设置改元素被访问过
	 * 
	 * 创建一个cell类，封装x, y坐标以及高度h，还有定义compare规则 定义一个dx = [1, -1, 0, 0]和dy = [0, 0,
	 * 1, -1]用于生成坐标(x, y)上下左右的点
	 * 
	 */
	int result = 0;

	public int trapRainWater(int[][] heights) {
		// write your code here
		if (heights.length == 0)
			return 0;
		PriorityQueue<Cell> q = new PriorityQueue<Cell>(1,
				new Comparator<Cell>() {
					public int compare(Cell x, Cell y) {
						return x.h - y.h;
					}

				});

		int n = heights.length;
		int m = heights[0].length;
		int[][] visit = new int[n][m];

		// =================最外围四周 start=========================
		// n 是行数
		for (int i = 0; i < n; i++) {
			// 第一列
			q.offer(new Cell(i, 0, heights[i][0]));
			visit[i][0] = 1;
			// 最后一列
			q.offer(new Cell(i, m - 1, heights[i][m - 1]));
			visit[i][m - 1] = 1;
		}

		for (int i = 0; i < m; i++) {
			// 第一行
			q.offer(new Cell(0, i, heights[0][i]));
			visit[0][i] = 1;
			// 最后一行
			q.offer(new Cell(n - 1, i, heights[n - 1][i]));
			visit[n - 1][i] = 1;

		}
		// =================最外围四周 end=========================

		while (!q.isEmpty()) {
			// 每次取一个最外围的点， minHeap每次poll出来的是最外围点中最小的值， 然后往内灌水（四个方向）
			Cell now = q.poll();
			trapRainWaterHelper(heights, now.x + 1, now.y, visit, q, now);
			trapRainWaterHelper(heights, now.x - 1, now.y, visit, q, now);
			trapRainWaterHelper(heights, now.x, now.y + 1, visit, q, now);
			trapRainWaterHelper(heights, now.x, now.y - 1, visit, q, now);
		}
		return result;
	}

	public void trapRainWaterHelper(int[][] heights, int x, int y,
			int[][] visit, PriorityQueue<Cell> q, Cell now) {
		if (x < 0 || x >= heights.length || y < 0 || y >= heights[0].length
				|| visit[x][y] == 1) {
			return;
		}
		visit[x][y] = 1;
		// 注意在往堆里放新元素的时候，要同时更新位置的水位高度
		q.offer(new Cell(x, y, Math.max(now.h, heights[x][y])));
		// 如果外围的柱子水位高度，比里面的高，则加入，如果低就不加水
		result = result + Math.max(0, now.h - heights[x][y]);
		/**
		 * 其实上面就等于 if (now.h - heights[x][y]) { result = result + (now.h -
		 * heights[x][y]) }
		 */
	}

}
