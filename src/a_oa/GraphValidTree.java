package a_oa;

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
		System.out.println(validTree(n, edges));
	}

	/**
	 * Union find 时间 O(N^M) 空间 O(1)
	 * 
	 * https://www.youtube.com/watch?v=n_t0a_8H8VY
	 * 
	 * 1. 这些边是否构成环路，如果有环则不能构成树 这些边是否能将所有节点连通，
	 * 
	 * 2. 如果有不能连通的节点则不能构成树
	 * 
	 * https://segmentfault.com/a/1190000003791051
	 */
	public static boolean validTree(int n, int[][] edges) {
		/**
		 * Important. tree should have n nodes with n-1 edges
		 * 
		 * n node tree must have n-1 edges (edges.length即表示边的个数)
		 * 
		 * 这个判断其实就是判断是否联通
		 */
		if (n - 1 != edges.length) {
			return false;
		}

		// 下面的Union Find就是判断是否构成环路，有环路则不行
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
		 * DFS check cycle
		 * 
		 * -1 means parent. 下面的视频中解释了为什么需要pass parent
		 * 
		 * https://www.youtube.com/watch?v=n_t0a_8H8VY
		 */
		if (hasCycle(0, -1, map, visited))
			return false;

		// check connectivity
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

		for (int i : map.get(curr)) {
			// parent 表示DFS时候，记录当前node 是从哪个节点过来的
			if ((i != parent && visited[i])
					|| (!visited[i] && hasCycle(i, curr, map, visited))) {
				// return true means there is a cycle
				return true;
			}
		}

		return false;
	}

	/**
	 * BFS - non recursion with Queue
	 */

	public static boolean validTreeBFS(int n, int[][] edges) {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			map.put(i, new ArrayList<Integer>());
		}

		for (int[] edge : edges) {
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}

		boolean[] visited = new boolean[n];

		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.offer(0);
		// check cycle
		while (!queue.isEmpty()) {
			int top = queue.poll();
			if (visited[top])
				return false;

			visited[top] = true;

			for (int i : map.get(top)) {
				if (!visited[i])
					queue.offer(i);
			}
		}
		// check 连通性
		for (boolean b : visited) {
			if (!b)
				return false;
		}

		return true;
	}

	/**
	 * BFS - non recursion with Queue - Jun
	 */
	public boolean validTreeBFSJun(int n, int[][] edges) {

		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			map.put(i, new ArrayList<Integer>());
		}

		for (int[] edge : edges) {
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}

		boolean[] visited = new boolean[n];

		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.offer(0);
		visited[0] = true;
		while (!queue.isEmpty()) {
			int top = queue.poll();
			if (top != 0 && visited[top]) {
				return false;
			}
			visited[top] = true;
			for (int i : map.get(top)) {
				if (!visited[i]) {
					queue.offer(i);
				}
			}
		}
		for (boolean b : visited) {
			if (!b)
				return false;
		}

		return true;
	}

}
