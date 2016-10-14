package graph_dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

class UndirectedGraphNode {
	int label;
	ArrayList<UndirectedGraphNode> neighbors;

	UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
};

// http://www.cnblogs.com/springfor/p/3874591.html

public class CloneGraph {

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode head) {
		if (head == null)
			return null;

		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

		// BFS get all nodes
		ArrayList<UndirectedGraphNode> nodeList = getAllNodesBFS(head);

		// clone nodes
		for (UndirectedGraphNode node : nodeList) {
			UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
			map.put(node, newNode);
		}

		// clone neighbors/edges, clone edges也可以用下面的方法
		for (UndirectedGraphNode node : nodeList) {
			UndirectedGraphNode newNode = map.get(node);
			for (UndirectedGraphNode neighbor : node.neighbors) {
				newNode.neighbors.add(map.get(neighbor));
			}
		}
		// !!! return new head;
		return map.get(head);
	}

	public UndirectedGraphNode cloneGraph1(UndirectedGraphNode head) {
		if (head == null)
			return null;

		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

		// BFS get all nodes
		ArrayList<UndirectedGraphNode> nodeList = getAllNodesBFS(head);

		// clone nodes
		for (UndirectedGraphNode node : nodeList) {
			UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
			map.put(node, newNode);
		}

		// clones edeges， 与上面方法的区别， 这个更好理解
		for (Map.Entry<UndirectedGraphNode, UndirectedGraphNode> entry : map
				.entrySet()) {
			for (UndirectedGraphNode neighbor : entry.getKey().neighbors) {
				// 下面的写法错误， 这里仅仅是build edge,关系，不能再new 一个node出来了， 都已经存在于map里了，
				// 再new,节点就多了
				// entry.getValue().neighbors.add(new
				// UndirectedGraphNode(neighbor.label));
				entry.getValue().neighbors.add(map.get(neighbor));
			}
		}

		// !!! return new head;
		return map.get(head);
	}

	public ArrayList<UndirectedGraphNode> getAllNodesBFS(
			UndirectedGraphNode head) {
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		HashSet<UndirectedGraphNode> set = new HashSet<>();

		queue.offer(head);
		set.add(head);

		while (!queue.isEmpty()) {
			UndirectedGraphNode node = queue.poll();
			for (UndirectedGraphNode neighbor : node.neighbors) {
				if (set.contains(neighbor)) {
					continue;
				}
				set.add(neighbor);
				queue.offer(neighbor);
			}
		}
		/**
		 * !!! copy set directly to ArrayList
		 * 
		 * 如果不这么写， 就要 ArrayList<UndirectedGraphNode> result = new
		 * ArrayList<UndirectedGraphNode>(); 然后每次把 queue.poll();的node加入到result里面
		 */
		return new ArrayList<UndirectedGraphNode>(set);
	}

	// ======================================
	// DFS - Recursive
	public UndirectedGraphNode cloneGraphDFS1(UndirectedGraphNode node) {
		if (node == null)
			return null;

		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

		UndirectedGraphNode head = new UndirectedGraphNode(node.label);
		map.put(node, head);

		DFS(map, node);
		return head;
	}

	public void DFS(HashMap<UndirectedGraphNode, UndirectedGraphNode> map,
			UndirectedGraphNode node) {
		if (node == null) {
			return;
		}
		for (UndirectedGraphNode neighbor : node.neighbors) {
			if (!map.containsKey(neighbor)) {
				UndirectedGraphNode newNeighbor = new UndirectedGraphNode(
						neighbor.label);
				map.put(neighbor, newNeighbor);
				DFS(map, neighbor);

			}
			map.get(node).neighbors.add(map.get(neighbor));
		}
	}

	// DFS - Non Recursive
	/**
	 * One starts at the root (selecting some arbitrary node as the root in the
	 * case of a graph) and explores as far as possible
	 * 
	 */
	public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
		if (node == null)
			return null;

		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		Stack<UndirectedGraphNode> stack = new Stack<>();

		UndirectedGraphNode head = new UndirectedGraphNode(node.label);
		map.put(node, head);
		stack.push(node);

		while (!stack.isEmpty()) {
			UndirectedGraphNode cur = stack.pop();
			for (UndirectedGraphNode neighbor : cur.neighbors) {
				if (!map.containsKey(neighbor)) {
					stack.push(neighbor);
					UndirectedGraphNode newNeighbor = new UndirectedGraphNode(
							neighbor.label);
					map.put(neighbor, newNeighbor);
				}
				// Note, 这里不是if/else 条件而是if完后，接着执行
				map.get(cur).neighbors.add(map.get(neighbor));
			}
		}
		return head;
	}

	// BFS - Non recursive
	/**
	 * 第一种实现方法是BFS的，就是先将头节点入queue，每一次queue出列一个node，然后检查这个node的所有的neighbors，
	 * 如果没visited过
	 * ，就入队，并更新neighbor。对于一个节点来说先把所有neighbors都检查一遍，再从第一个neighbor开始，循环往复。
	 * 
	 */
	public UndirectedGraphNode cloneGraphBFS3(UndirectedGraphNode node) {
		if (node == null)
			return null;

		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		Queue<UndirectedGraphNode> queue = new LinkedList<>();

		UndirectedGraphNode head = new UndirectedGraphNode(node.label);
		map.put(node, head);
		queue.offer(node);

		while (!queue.isEmpty()) {
			UndirectedGraphNode cur = queue.poll();
			for (UndirectedGraphNode neighbor : cur.neighbors) {
				if (!map.containsKey(neighbor)) {
					queue.offer(neighbor);
					UndirectedGraphNode newNeighbor = new UndirectedGraphNode(
							neighbor.label);
					map.put(neighbor, newNeighbor);
				}
				map.get(cur).neighbors.add(map.get(neighbor));
			}
		}
		return head;
	}

}
