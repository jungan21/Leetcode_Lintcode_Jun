package TreePaths;

/**
 * 
 Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 Notice
 Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
import java.util.ArrayList;
import java.util.List;

public class Triangle {

	public static void main(String[] args) {

		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(-1);
		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(2);
		l2.add(3);
		List<Integer> l3 = new ArrayList<Integer>();
		l3.add(1);
		l3.add(-1);
		l3.add(-3);
		List<List<Integer>> list = new ArrayList<List<Integer>>();

		list.add(l1);
		list.add(l2);
		list.add(l3);

		System.out.println("DP: " + minimumTotal(list));

		int[][] triangle = { { 2 }, { 3, 4 }, { 6, 5, 7 }, { 4, 1, 8, 3 } };

		System.out.println("Traverser: " + minimumTotalTraverser(triangle));
		System.out.println("DivideConquer: "
				+ minimumTotalDivideConquer(triangle));
		System.out.println("DivideConquer+Memory: "
				+ minimumTotalDivideConquerWithMemory(triangle));
		System.out.println("DPTop2Bottom: "
				+ minimumTotalDPTop2Bottom(triangle));
		System.out.println("DPBottom2Top2: "
				+ minimumTotalDPBottom2Top(triangle));

	}

	public static int minimumTotal(List<List<Integer>> triangle) {

		int[] dp = new int[triangle.size()];
		// get index
		int l = triangle.size() - 1;

		// initial by last row, because last row's length must equal to the
		// levels of the triangle
		// dp[i]初始化值是最后一行的值
		for (int i = 0; i < triangle.get(l).size(); i++) {
			dp[i] = triangle.get(l).get(i);
		}

		// iterate from last second row
		for (int i = triangle.size() - 2; i >= 0; i--) {
			for (int j = 0; j < triangle.get(i).size(); j++) {
				dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
			}
		}
		// 因为我们从倒数第二层往顶层运行， 当到达顶层时， dp[0]即表示要找的值
		return dp[0];

	}

	/**
	 * Lintcode 提交 会 Time limit Exceeded, 就功能而言是正确的
	 * 
	 * O(2^n) Traverser(从上往下算，即从root节点，到最底层) - DFS - jiu zhang
	 * 
	 * 类似于组合， 每次分2个方向， n层(n就是triangle.length)
	 * 
	 */
	// Traverser 需要全局变量
	static int result = Integer.MAX_VALUE;

	public static int minimumTotalTraverser(int[][] triangle) {
		if (triangle == null || triangle.length == 0) {
			return 0;
		}
		// 中间两个（0，0）表示x,y坐标，最后一个0 表示累加和
		dfs(triangle, 0, 0, 0);
		return result;
	}

	// return minumum path from root to (x, y);
	public static void dfs(int[][] triangle, int x, int y, int sum) {
		// x == triangle.length, 类似于树结构，到叶子节点了，题目要求到bottom,所以必须等于
		if (x == triangle.length) {
			result = Math.min(sum, result);
			// 这个return 不能省略
			return;
		}
		// 关于如何决定，x+1,
		// y+1,在纸上画出三角形，并对每个节点表上坐标，root节点坐标为（0，0），由于题目要求从上层往bottom移动的时候，you can
		// ONLY move to adjacent numbers
		dfs(triangle, x + 1, y, sum + triangle[x][y]);
		dfs(triangle, x + 1, y + 1, sum + triangle[x][y]);
	}

	/**
	 * Lintcode 提交 会 Time limit Exceeded, 就功能而言是正确的
	 * 
	 * O(2^n) Divide-Conquer（从最底层往上算）- jiu zhang
	 * 
	 * 类似于组合， 每次分两个方向，n层(n就是triangle.length)
	 * 
	 */
	public static int minimumTotalDivideConquer(int[][] triangle) {
		if (triangle == null || triangle.length == 0) {
			return 0;
		}
		// （0，0）表示x,y坐标
		return divideConquer(triangle, 0, 0);
	}

	// return minumum path from (x, y) to bottom
	public static int divideConquer(int[][] triangle, int x, int y) {
		// 当到达叶子节点时，minumum path from (x, y) to bottom 就是0，表示从自己到自己的
		if (x == triangle.length) {
			return 0;
		}

		// 注意和traverser的区别，不能传入sum, 根据函数的定义，这里返回的是minumum path sum from (x, y)
		// to bottom, 可以按分治的思想去理解，要求(x,y)到bottom的最小path
		// sum,我们分成两个小的子问题，由于从（x,y）往下有两个分支，问题自然分解成从（x+1,y)到bottom的最小path
		// sume以及从(x+1,y+1)到bottom的最小path sum，子问题计算返回后，我们记得加上 (x,y)就是我们要求的结果，
		// 而Travers是自顶向下，遍历，不是分治的思想
		int left = divideConquer(triangle, x + 1, y);
		int right = divideConquer(triangle, x + 1, y + 1);
		// 因为上面分治的时候，是直接从"下一层的左右方向分流了"，所以这里记得加上root节点的值
		return triangle[x][y] + Math.min(left, right);
	}

	/**
	 * O(n^2) 带有记忆功能的 Divide-Conquer+ Memorization（从最底层往上算）其本质就是DP- jiu zhang
	 * 
	 * 这种本质就是：在从低往上计算的时候，如果需要某个值，不要去亲自计算，因为有很多重复的计算，我们预先把这些值存入数组中，用的时候取一下就好，
	 * 大大提高效率
	 * 
	 */

	static int[][] memory = null;

	public static int minimumTotalDivideConquerWithMemory(int[][] triangle) {
		if (triangle == null || triangle.length == 0) {
			return 0;
		}
		memory = new int[triangle.length][triangle.length];
		// init
		for (int i = 0; i < triangle.length; i++) {
			for (int j = 0; j <= i; j++) {
				memory[i][j] = Integer.MAX_VALUE;
			}
		}
		return divideConquerWithMemory(triangle, 0, 0);
	}

	// return minumum path from (x, y) to bottom
	public static int divideConquerWithMemory(int[][] triangle, int x, int y) {
		// minumum path from (x, y) to bottom is 0
		if (x == triangle.length) {
			return 0;
		}
		if (memory[x][y] != Integer.MAX_VALUE) {
			return memory[x][y];
		}

		memory[x][y] = triangle[x][y]
				+ Math.min(divideConquerWithMemory(triangle, x + 1, y),
						divideConquerWithMemory(triangle, x + 1, y + 1));
		return memory[x][y];
	}

	/**
	 * O(n^2) DP（自顶向下）- jiuzhang recommended by teacher
	 * 
	 */

	public static int minimumTotalDPTop2Bottom(int[][] triangle) {
		if (triangle == null || triangle.length == 0) {
			return 0;
		}

		// 表示从 (i, j)坐标， 出发走到最后一层的最小路径长度
		int[][] dp = new int[triangle.length][];
		for (int i = 0; i < triangle.length; i++) {
			// 每一个是一个一维数组
			dp[i] = new int[i + 1];
		}

		dp[0][0] = triangle[0][0];

		// 初始化三角形， 即，沿着最左边，和沿着最右边的两条线走到底的path sum
		for (int i = 1; i < triangle.length; i++) {
			dp[i][0] = dp[i - 1][0] + triangle[i][0];
			dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
		}

		// top down
		for (int i = 1; i < triangle.length; i++) {
			// 因为是三角形，所以，每一行最有一个元素的y坐标就是行数
			for (int j = 1; j < i; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1])
						+ triangle[i][j];
			}
		}

		// Note:上面只是算出了每条路径到每个bottom节点的最短路径，由于最后一行很多节点还需要比较
		int result = Integer.MAX_VALUE;
		for (int j = 0; j < triangle.length; j++) {
			result = Math.min(result, dp[triangle.length - 1][j]);
		}
		return result;
	}

	/**
	 * O(n^2) DP（自底向上）- jiuzhang
	 */

	public static int minimumTotalDPBottom2Top(int[][] triangle) {
		if (triangle == null || triangle.length == 0) {
			return 0;
		}

		// 表示从 (i, j)坐标， 出发走到最后一层的最小路径长度
		int[][] dp = new int[triangle.length][triangle.length];

		// 初始化, 终点先有值, 也就是最后一行,每个节点本身的值
		for (int i = 0; i < triangle.length; i++) {
			dp[triangle.length - 1][i] = triangle[triangle.length - 1][i];
		}
		// bottom ---> up ,最后一行往上推， 由于上面初始化时， 最后一行已经赋值了，现在只要从倒数第二行算起
		for (int i = triangle.length - 2; i >= 0; i--) {
			// 因为是三角形，所以，每一行最有一个元素的y坐标就是行数
			for (int j = 0; j <= i; j++) {
				dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1])
						+ triangle[i][j];
			}
		}

		// bottom ---> up, 所以结果就是最上面的点
		return dp[0][0];
	}

	/**
	 * 上面方法的空间复杂度优化 O(n^2) DP（自底向上）- jiuzhang
	 */

	public int minimumTotalDPBottom2Top2(int[][] triangle) {
		if (triangle == null || triangle.length == 0) {
			return 0;
		}
		int[] dp = new int[triangle.length];
		for (int i = 0; i < triangle.length; i++) {
			dp[i] = triangle[triangle.length - 1][i];
		}

		// 根绝上面的方法，我们知道，当前的行，仅仅依赖下面一行的值，所有可以优化成一唯
		for (int i = triangle.length - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				dp[j] = Math.min(dp[j], dp[j + 1]) + triangle[i][j];
			}
		}
		return dp[0];

	}
}
