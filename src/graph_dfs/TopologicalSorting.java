package graph_dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//https://sites.google.com/site/indy256/algo/topological_sorting
class DirectedGraphNode {
	int label;
	ArrayList<DirectedGraphNode> neighbors;

	DirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<DirectedGraphNode>();
	}
};

public class TopologicalSorting {

	public static void main(String[] args) {

	}

	// method 1, queue, BFS
	public ArrayList<DirectedGraphNode> topSortBFS(
			ArrayList<DirectedGraphNode> graph) {
		ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
		HashMap<DirectedGraphNode, Integer> indegreeMap = new HashMap<DirectedGraphNode, Integer>();
		for (DirectedGraphNode node : graph) {
			for (DirectedGraphNode neighbor : node.neighbors) {
				if (indegreeMap.containsKey(neighbor)) {
					indegreeMap.put(neighbor, indegreeMap.get(neighbor) + 1);
				} else {
					indegreeMap.put(neighbor, 1);
				}
			}
		}
		Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
		for (DirectedGraphNode node : graph) {
			// find node wihtout incoming edge, i.e.indegree is 0
			// 所有入度为0的放入队列
			if (!indegreeMap.containsKey(node)) {
				queue.offer(node);
				result.add(node);
			}
		}
		while (!queue.isEmpty()) {
			DirectedGraphNode node = queue.poll();
			for (DirectedGraphNode n : node.neighbors) {
				// 每次node-->n，这样每次n的入度减1
				indegreeMap.put(n, indegreeMap.get(n) - 1);
				// 减到直到该点的 入度 indegree 为 0
				if (indegreeMap.get(n) == 0) {
					result.add(n);
					queue.offer(n);
				}
			}
		}
		return result;
	}

	// method2 DFS
	public ArrayList<DirectedGraphNode> topSortDFS(
			ArrayList<DirectedGraphNode> graph) {
		if (graph == null || graph.size() == 0) {
			return null;
		}
		ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
		HashMap<DirectedGraphNode, Integer> map = new HashMap<DirectedGraphNode, Integer>();
		Stack<DirectedGraphNode> stack = new Stack<DirectedGraphNode>();

		for (DirectedGraphNode node : graph) {
			for (DirectedGraphNode neighbor : node.neighbors) {
				if (map.containsKey(neighbor)) {
					map.put(neighbor, map.get(neighbor) + 1);
				} else {
					map.put(neighbor, 1);
				}
			}
		}

		for (DirectedGraphNode node : graph) {
			if (!map.containsKey(node)) {
				stack.push(node);
				result.add(node);
			}
		}

		while (!stack.isEmpty()) {
			DirectedGraphNode node = stack.pop();
			for (DirectedGraphNode neighbor : node.neighbors) {
				map.put(neighbor, map.get(neighbor) - 1);
				if (map.get(neighbor) == 0) {
					result.add(neighbor);
					stack.push(neighbor);
				}
			}
		}
		return result;
	}

}
