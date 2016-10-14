package graph_dfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SixDegree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * http://www.jiuzhang.com/qa/1164/
	 * 
	 * 最短距离肯定BFS更合适啊
	 * 
	 */
	public int sixDegrees(List<UndirectedGraphNode> graph,
			UndirectedGraphNode s, UndirectedGraphNode t) {
		// Write your code here
		if (s == t)
			return 0;
		int step = 0;
		HashMap<UndirectedGraphNode, Integer> visited = new HashMap<>();
		Queue<UndirectedGraphNode> queue = new LinkedList<>();

		queue.offer(s);
		// from s to s, it needs 0 step
		visited.put(s, 0);
		while (!queue.isEmpty()) {
			UndirectedGraphNode node = queue.poll();
			// 到当前node的步骤是 step
			step = visited.get(node);
			for (int i = 0; i < node.neighbors.size(); i++) {
				if (visited.containsKey(node.neighbors.get(i))) {
					continue;
				}
				// 因为到当前node的步骤是 step， 到node的任何一个neighbor的步骤是step+1
				visited.put(node.neighbors.get(i), step + 1);
				queue.offer(node.neighbors.get(i));
				if (node.neighbors.get(i) == t) {
					return step + 1;
				}
			}

		}
		return -1;
	}

}
