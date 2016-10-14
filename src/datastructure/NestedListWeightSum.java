package datastructure;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedListWeightSum {
	// http://www.programcreek.com/2014/05/leetcode-nested-list-weight-sum-java/
	// method DFS - recursive
	static int sum = 0;

	public int depthSumDFS(List<NestedInteger> nestedList) {
		helper(nestedList, 1);
		return sum;
	}

	private void helper(List<NestedInteger> nestedList, int depth) {
		if (nestedList == null || nestedList.size() == 0) {
			return;
		}
		for (NestedInteger elem : nestedList) {
			if (elem.isInteger()) {
				sum += elem.getInteger() * depth;
			} else {
				helper(elem.getList(), depth + 1);
			}
		}
	}

	// BFS - jiuzhang
	// http://www.jiuzhang.com/solutions/nested-list-weight-sum/

	public int depthSum(List<NestedInteger> nestedList) {
		// Write your code here
		if (nestedList == null || nestedList.size() == 0) {
			return 0;
		}
		int sum = 0;
		Queue<NestedInteger> queue = new LinkedList<NestedInteger>();
		for (NestedInteger nestedInt : nestedList) {
			queue.offer(nestedInt);
		}
		// level by level traverse,
		int depth = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			depth++;
			for (int i = 0; i < size; i++) {
				NestedInteger nestedInt = queue.poll();
				if (nestedInt.isInteger()) {
					sum += nestedInt.getInteger() * depth;
				} else {
					for (NestedInteger innerInt : nestedInt.getList()) {
						queue.offer(innerInt);
					}
				}
			}
		}
		return sum;
	}
}
