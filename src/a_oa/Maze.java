package a_oa;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 给一个2D数组，用maze[0][0]出发找到终点，我用bfs和dfs都写过，dfs代码量少一些
 * 
 * http://www.geeksforgeeks.org/backttracking-set-2-rat-in-a-maze/
 * 
 * http://123.57.208.150/bbs/forum.php?mod=viewthread&tid=154566&fromguid=hot&
 * extra=&mobile=2
 * 
 * http://wdxtub.com/interview/14520850399861.html
 * 
 * MAZE是个01矩阵，0代表路，1代表墙，好像要找一个是9的元素，
 * 
 * 这题呢，用故事性强一点的说法就是，一只可怜的饥饿的小老鼠在一个N乘以N的迷宫里面（其实就一个二维数组……），它从（0，0）点开始出发，要寻找奶酪来吃（
 * 似乎是它闻到有奶酪的味了？）。 然后呢，这个二维数组表示的迷宫里面，1是路，0是墙（如果没记错的话，大家还是认真看看这个），值为9的地方是终点！
 * 题目给出的就是这个二维数组，问你小老鼠最后能不能吃到奶酪（到达9）呢？能的话就返回true，不(e)能(si)的话就返回false。
 * 我留意到了之前做过这个题的楼主提醒，老鼠在（0，0）开始，然后要测（0，0）就是终点（==9）的情况， 多么坑的一个corner case。
 * 
 * 楼主没想到起点为0（墙）的情况是直接返回不能(false)的
 * 
 * 
 * ind Path in 2D matrix 输入一个2D的int array，其中有0，1，9。 0为墙壁 1为可以通过，9
 * 为需要找的结果，这道题如果真是1是路
 * ，找9比较好，可以直接用大于符号。一亩三分地里面也有人说0是可以通过，1是墙壁，只能看着来了。出生点我定为（0,0）因为我不确定OA中给的是不是（0,0）
 * 返回true or false，表示可以找到9或者没有办法找到。9不一定只有一个。
 * 
 * 需要注意的特殊情况是第一个就是需要找的9，eg. {{9}}
 * 想了一下，应该是上下左右都可以活动才对，所以又加了两个方向。感觉可以把Korsh原来写的炫酷的迷宫游戏写出来了。还是做面试准备先。
 * 
 * http://blog.csdn.net/lycorislqy/article/details/49202651
 * 
 *
 */
public class Maze {

	public static void main(String[] args) {
		int[][] A = { { 1, 1, 1 }, { 1, 0, 0 }, { 1, 0, 9 } };
		// int[][] A = { { 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1
		// },{ 0, 0, 9, 1, 1 } };
		// int[][] A = { { 1, 0, 0, 0 }, { 1, 1, 1, 1 }, { 1, 9, 0, 0 } };
		// int[][] A= {{9}};
		// int[][] A = { { 1, 1, 1, 1, 1, 1 } };
		int[][] B = { { 1, 1, 1, 1, 0, 0 }, { 0, 0, 1, 0, 0, 0 },
				{ 1, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 0, 9, 0 } };
		System.out.println(DFS(A));
		System.out.println(BFS(A, 0, 0));
		System.out.println(BFS2(A, 0, 0));
	}

	// 0表示墙， 1 表示通路
	public static int DFS(int[][] A) {

		boolean[][] visited = new boolean[A.length][];
		for (int i = 0; i < A.length; i++) {
			visited[i] = new boolean[A[i].length];
		}
		if (A[0][0] == 0) {
			return -1;
		}
		if (A[0][0] == 9) {
			return 1;
		}
		return helper(A, visited, 0, 0) == 1 ? 1 : -1;
	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	/**
	 * http://123.57.208.150/bbs/forum.php?mod=viewthread&tid=154566&fromguid=
	 * hot&extra=&mobile=2
	 * 
	 * 0表示墙， 1 表示通路
	 * 
	 * Note:也可以不用visited 数组，而用题目中的方法int temp = A[x][y]
	 */
	public static int helper(int[][] A, boolean[][] visited, int x, int y) {

		if (x < 0 || y < 0 || x >= A.length || y >= A[x].length || A[x][y] == 0
				|| visited[x][y]) {
			return -1;
		}

		if (A[x][y] == 9) {
			return 1;
		}

		int result = -1;
		visited[x][y] = true;
		// int temp = A[x][y];
		// A[x][y] = 0;
		for (int k = 0; k < 4; k++) {
			int newX = x + dx[k];
			int newY = y + dy[k];
			if (helper(A, visited, newX, newY) == 1) {
				result = 1;
			}
		}
		visited[x][y] = false;
		// A[x][y] = temp;
		return result;
	}

	// 0 表示墙， 1 表示通路
	private static boolean BFS(int[][] maze, int x, int y) {
		if (maze == null || maze.length == 0 || maze[0].length == 0) {
			return false;
		}
		if (maze[0][0] == 0) {
			return false;
		}
		if (maze[0][0] == 9) {
			return true;
		}

		HashSet<String> set = new HashSet<>();
		Queue<Point> queue = new LinkedList<Point>();
		Point p1 = new Point(0, 0, maze[0][0]);
		queue.offer(p1);
		set.add(0 + "," + 0);

		int m = maze.length;
		int n = maze[0].length;

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			if (p.val == 9) {
				return true;
			}
			for (int i = 0; i < 4; i++) {
				int newX = p.x + dx[i];
				int newY = p.y + dy[i];
				if (newX < 0 || newY < 0 || newX >= m || newY >= n
						|| maze[newX][newY] == 0
						|| set.contains(newX + "," + newY)) {
					continue;
				}
				Point newP = new Point(newX, newY, maze[newX][newY]);
				queue.offer(newP);
				// 也可以不用set, 而直接将maze[newX][newY] = 0
				set.add(newX + "," + newY);
			}
		}
		return false;
	}

	private static boolean BFS2(int[][] maze, int x, int y) {

		if (maze == null || maze.length == 0 || maze[0].length == 0) {
			return false;
		}

		if (maze[0][0] == 0) {
			return false;
		}
		if (maze[0][0] == 9) {
			return true;
		}
		int m = maze.length;
		int n = maze[0].length;

		boolean visited[] = new boolean[m * n];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(x);
		visited[x * n + y] = true;

		while (!queue.isEmpty()) {
			Integer index = queue.poll();
			if (maze[index / n][index % n] == 9) {
				return true;
			}
			for (int i = 0; i < 4; i++) {
				int newX = index / n + dx[i];
				int newY = index % n + dy[i];
				int newIndex = newX * n + newY;
				if (newX < 0 || newY < 0 || newX >= m || newY >= n
						|| maze[newX][newY] == 0 || visited[newIndex]) {
					continue;
				}
				queue.offer(newIndex);
				visited[newIndex] = true;
			}
		}
		return false;
	}
}

class Point {
	int x;
	int y;
	int val;

	public Point(int x, int y, int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}
}
