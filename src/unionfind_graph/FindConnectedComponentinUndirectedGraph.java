package unionfind_graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Find the number connected component in the undirected graph. Each node in the
 * graph contains a label and a list of its neighbors. (a connected component
 * (or just component) of an undirected graph is a subgraph in which any two
 * vertices are connected to each other by paths, and which is connected to no
 * additional vertices in the supergraph.)
 * 
 *
 * 对于无向图，没有强/弱 联通component的概念，只有有向图才有，强/弱连图component 的概念
 *
 */
class UndirectedGraphNode {
	int label;
	ArrayList<UndirectedGraphNode> neighbors;

	UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
}

public class FindConnectedComponentinUndirectedGraph {

	public static void main(String[] args) {

	}

	/**
	 * method 3: Union Find
	 */
	public List<List<Integer>> connectedSetUnionFind(
			ArrayList<UndirectedGraphNode> nodes) {

		/**
		 * put all node.lable in hashset, for remove duplicate) nodes (nodes
		 * with same lable)
		 * 
		 * and build the Union Find structure
		 */
		HashSet<Integer> hashSet = new HashSet<Integer>();
		for (UndirectedGraphNode now : nodes) {
			hashSet.add(now.label);
			for (UndirectedGraphNode neighbour : now.neighbors) {
				hashSet.add(neighbour.label);
			}
		}
		// init UnionFind map
		UnionFind uf = new UnionFind();
		for (Integer now : hashSet) {
			uf.father.put(now, now);
		}

		// iterator each node and its neighbors, each node and it's neighbors
		// are supposed to be in same 集合
		for (UndirectedGraphNode now : nodes) {
			int fnow = uf.find(now.label);
			for (UndirectedGraphNode neighbour : now.neighbors) {
				int fneighbour = uf.find(neighbour.label);
				if (fnow != fneighbour) {
					uf.union(now.label, neighbour.label);
				}
			}
		}
		return print(hashSet, uf);
	}

	public List<List<Integer>> print(HashSet<Integer> hashSet, UnionFind uf) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		HashMap<Integer, List<Integer>> hashMap = new HashMap<Integer, List<Integer>>();
		for (int i : hashSet) {
			// find就是找 root
			int fa = uf.find(i);
			// 以每个集合的老大作为map的key
			if (!hashMap.containsKey(fa)) {
				hashMap.put(fa, new ArrayList<Integer>());
			}
			List<Integer> now = hashMap.get(fa);
			now.add(i);
			// 放回去
			hashMap.put(fa, now);
		}

		// 将答案里的每个list集合，顺序排序
		for (List<Integer> now : hashMap.values()) {
			Collections.sort(now);
			result.add(now);
		}
		return result;
	}

	/**
	 * method 1: BFS Non Recursive
	 */
	public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		Map<UndirectedGraphNode, Boolean> visited = new HashMap<>();

		for (UndirectedGraphNode node : nodes) {
			if (visited.get(node) == false) {
				BFS(node, visited, result);
			}
		}

		return result;

	}

	public void BFS(UndirectedGraphNode node,
			Map<UndirectedGraphNode, Boolean> visited,
			List<List<Integer>> result) {

		List<Integer> row = new ArrayList<>();
		Queue<UndirectedGraphNode> queue = new LinkedList<>();
		visited.put(node, true);
		queue.offer(node);
		while (!queue.isEmpty()) {
			UndirectedGraphNode u = queue.poll();
			row.add(u.label);
			for (UndirectedGraphNode v : u.neighbors) {
				if (visited.get(v) == false) {
					visited.put(v, true);
					queue.offer(v);
				}
			}
		}
		Collections.sort(row);
		result.add(row);

	}

	/**
	 * method 2: BFS Recursive
	 */
	public List<List<Integer>> connectedSetRecursive(
			ArrayList<UndirectedGraphNode> nodes) {

		Map<UndirectedGraphNode, Boolean> visited = new HashMap<>();
		// 初始化， 所有node都是没被访问过的
		for (UndirectedGraphNode node : nodes) {
			visited.put(node, false);
		}

		List<List<Integer>> result = new ArrayList<>();
		List<Integer> list = null;

		for (UndirectedGraphNode node : nodes) {
			// 每一个 node 要开一个list
			list = new ArrayList<Integer>();
			if (visited.get(node) == false) {
				BFSRecursive(node, visited, result, list);
			}
			Collections.sort(list);
			// 空的集合 不用加到结果里面去
			if (list.size() != 0) {
				result.add(list);
			}
		}
		return result;
	}

	public void BFSRecursive(UndirectedGraphNode node,
			Map<UndirectedGraphNode, Boolean> visited,
			List<List<Integer>> result, List<Integer> list) {

		if (visited.get(node)) {
			return;
		}
		list.add(node.label);
		visited.put(node, true);

		for (UndirectedGraphNode v : node.neighbors) {
			BFSRecursive(v, visited, result, list);
		}
	}

}