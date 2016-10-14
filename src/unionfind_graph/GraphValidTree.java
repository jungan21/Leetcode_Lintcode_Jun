package unionfind_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 
 * http://nb4799.neu.edu/wordpress/?p=1143
 * 
 * http://www.geeksforgeeks.org/union-find/
 * 
 * https://segmentfault.com/a/1190000003791051
 * 
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to check whether these edges make
 * up a valid tree.
 * 
 * Notice: You can assume that no duplicate edges will appear in edges. Since
 * all edges are undirected, [0, 1] is the same as [1, 0] and thus will not
 * appear together in edges.
 * 
 *
 */

class UnionFindGraphValidTree {
	HashMap<Integer, Integer> rootmap = new HashMap<Integer, Integer>();

	UnionFindGraphValidTree(int n) {
		for (int i = 0; i < n; i++) {
			rootmap.put(i, i);
		}
	}

	public int find(int x) {
		int root = rootmap.get(x);
		while (root != rootmap.get(root)) {
			root = rootmap.get(root);
		}
		return root;
	}

	void union(int x, int y) {
		int fa_x = find(x);
		int fa_y = find(y);
		if (fa_x != fa_y)
			rootmap.put(fa_x, fa_y);
	}
}

/**
 * This algorithm uses an idea called union find. You first initialize each node
 * so that each node itself forms a node set. (We use union_arr to record which
 * set a node belongs to). As we traverse all edges, we will find connected
 * components.
 * 
 * The union find algorithm makes sure that every node in a connected component
 * will point to a same node set by using find_union function. Therefore, if we
 * see a new edge with two points in the same node set, we will return False
 * because the edge makes a cycle in the graph
 * 
 *
 */
public class GraphValidTree {

	public static void main(String[] args) {
		int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 1, 3 }, { 1, 4 } };
		int n = 5;
		System.out.println(validTreeBFS(n, edges));
	}

	/**
	 * Union find 时间 O(N^M) 空间 O(1)
	 * 
	 * https://www.youtube.com/watch?v=n_t0a_8H8VY
	 * 
	 * 1. 这些边是否构成环路，如果有环则不能构成树 这些边是否能将所有节点连通，
	 * 
	 * 2. 如果有不能连通的节点则不能构成树
	 */
	public static boolean validTree(int n, int[][] edges) {
		/**
		 * Important. tree should have n nodes with n-1 edges
		 * 
		 * n node tree must have n-1 edges (edges.length即表示边的个数)
		 */
		if (edges.length != n - 1) {
			return false;
		}
		UnionFind uf = new UnionFind();
		for (int i = 0; i < n; i++) {
			uf.father.put(i, i);
		}

		for (int i = 0; i < edges.length; i++) {
			// 相等就表示 两个节点已经在同一个集合中，说明新的边将产生环路
			/**
			 * if we see a new edge with two points in the same node set, we
			 * will return False because the edge makes a cycle in the graph.
			 */
			// For each edge,If both the vertices are in the same subset, a
			// cycle is found.
			if (uf.find(edges[i][0]) == uf.find(edges[i][1])) {
				return false;
			}
			uf.union(edges[i][0], edges[i][1]);
		}
		return true;
	}

	/**
	 * DFS - recursion 在深度遍历图的时候，如果当前节点已访问过，则表示存在回路。
	 * 
	 * https://www.youtube.com/watch?v=n_t0a_8H8VY
	 * 
	 * http://www.cnblogs.com/grandyang/p/5257919.html
	 * 
	 * We start to visit all nodes from node 0. (line 12) If we finish
	 * traversing all reachable nodes but there are still some adjacency matrix
	 * entry left then we know the given edges actually form multiple separate
	 * graphs. Therefore we should return False.
	 */
	public static boolean validTreeDFS(int n, int[][] edges) {

		// 建立存储图的链表结构， key就是每个节点，value 是一个list, 即表示该节点连接的其他点的结合
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			map.put(i, list);
		}
		// edge 表示 edges二维数组的每一行
		for (int[] edge : edges) {
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}

		boolean[] visited = new boolean[n];
		/**
		 * -1 means parent. 下面的视频中解释了为什么需要pass parent
		 * 
		 * https://www.youtube.com/watch?v=n_t0a_8H8VY
		 * 
		 * 0 表示从0节点开始DFS， -1 表示dummy node, 即0节点的parent 是-1
		 */
		if (hasCycle(0, -1, map, visited))
			return false;

		// Note: 重要，不能省略 表示所有的点都是联通的
		for (boolean b : visited) {
			if (!b)
				return false;
		}

		return true;
	}

	// DFS check if an undirected graph has cycle started from vertex curr
	public static boolean hasCycle(int curr, int parent,
			HashMap<Integer, ArrayList<Integer>> map, boolean[] visited) {
		// if current node has been visted, that means there must be cycle

		visited[curr] = true;

		for (int neighbor : map.get(curr)) {
			// parent 表示DFS时候，记录当前node 是从哪个节点过来的
			// 下面视频中7：33 时刻解释了 条件： neighbor != parent && visited[neighbor]
			// https://www.youtube.com/watch?v=n_t0a_8H8VY
			if ((neighbor != parent && visited[neighbor])
					|| (!visited[neighbor] && hasCycle(neighbor, curr, map,
							visited))) {
				// return true means there is a cycle
				return true;
			}
		}

		return false;
	}

	/**
	 * DFS version 2: http://www.cnblogs.com/grandyang/p/5257919.html
	 */

	public static boolean validTreeDFS1(int n, int[][] edges) {

		// 建立存储图的链表结构， key就是每个节点，value 是一个list, 即表示该节点连接的其他点的结合
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			map.put(i, list);
		}
		// edge 表示 edges二维数组的每一行
		for (int[] edge : edges) {
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}

		boolean[] visited = new boolean[n];
		/**
		 * -1 means parent. 下面的视频中解释了为什么需要pass parent
		 * 
		 * https://www.youtube.com/watch?v=n_t0a_8H8VY
		 * 
		 * 0 表示从0节点开始DFS， -1 表示dummy node, 即0节点的parent 是-1
		 */
		if (!isValideTree(0, -1, map, visited))
			return false;

		// Note: 重要，不能省略 说明图不是完全连通的，返回false
		for (boolean b : visited) {
			if (!b)
				return false;
		}

		return true;
	}

	// DFS check if an undirected graph has cycle started from vertex curr
	public static boolean isValideTree(int curr, int parent,
			HashMap<Integer, ArrayList<Integer>> map, boolean[] visited) {
		/**
		 * 当DFS到某个节点，先看当前节点是否被访问过，如果已经被访问过，说明环存在，直接返回false，如果未被访问过，
		 * 我们现在将其状态标记为已访问过
		 */
		if (visited[curr]) {
			return false;
		}

		visited[curr] = true;
		for (int neighbor : map.get(curr)) {
			// 然后我们到邻接链表里去找跟其相邻的节点继续递归遍历，注意我们还需要一个变量pre来记录上一个节点，以免回到上一个节点
			// parent---> neighbor, 因为我们刚才parent 方向遍历到neibhor点，所有需要避免回去
			if (neighbor == parent) {
				continue;
			}
			if (!isValideTree(neighbor, curr, map, visited)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * BFS - non recursion with Queue
	 * 
	 * 其实就是course schedule.java，区别就是这题是无向图， 而courseSchedule是有向图
	 * 
	 * http://www.lintcode.com/en/problem/graph-valid-tree/
	 * 
	 * int[][] edge, 就是n行， 2列的二位数组
	 */

	public static boolean validTreeBFS(int n, int[][] edges) {

		// build graph: adjacent list
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			map.put(i, list);
		}

		for (int[] edge : edges) {
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}

		boolean[] visited = new boolean[n];

		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.offer(0);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			/**
			 * 不能省略 表示有环
			 * 
			 * 对于题目输入， 中间状态queue里： [3, 4, 3]
			 */
			if (visited[cur]) {
				return false;
			}
			visited[cur] = true;

			for (int neighbor : map.get(cur)) {
				if (!visited[neighbor])
					queue.offer(neighbor);
			}
		}
		// 表示有的点节点 是无法联通的， 不能构成Tree
		for (boolean b : visited) {
			if (!b)
				return false;
		}

		return true;
	}
}
