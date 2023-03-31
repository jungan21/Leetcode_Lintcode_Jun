package tree_divideConquer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);

		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);

		TreeNode left_left = new TreeNode(4);
		TreeNode left_right = new TreeNode(5);
		TreeNode right_left = new TreeNode(6);
		TreeNode right_right = new TreeNode(7);

		root.left = left;
		root.right = right;

		left.left = left_left;
		left.right = left_right;

		right.left = right_left;
		right.right = right_right;
		System.out.println(levelOrderDFS(root));
	}

	/**
	 * jiuzhang: recommened by teacher:
	 * 
	 * http://www.jiuzhang.com/solutions/binary-tree-level-order-traversal/
	 * 
	 * BFS
	 * 
	 */

	public static ArrayList<ArrayList<Integer>> levelOrderBSF(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			ArrayList<Integer> currentLevelNodes = new ArrayList<Integer>();
			// NOTE: size is changing for each loop
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				currentLevelNodes.add(node.val);

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			result.add(currentLevelNodes);
		}

		/**
		 * as a follow up, if they ask you to do do level order from left to
		 * root, just do: Collections.reverse(result);
		 */
		return result;
	}

	/**
	 * DFS:
	 * 
	 * jiuzhang:
	 * http://www.jiuzhang.com/solutions/binary-tree-level-order-traversal/
	 */
	public static List<List<Integer>> levelOrderDFS(TreeNode root) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();

		if (root == null) {
			return results;
		}

		int maxLevel = 0;
		while (true) {
			ArrayList<Integer> levelValueList = new ArrayList<Integer>();
			// 0 means current level
			dfs(root, levelValueList, 0, maxLevel);
			// ==0, means reached out to the last level
			System.out.println(levelValueList);
			if (levelValueList.size() == 0) {
				break;
			}

			results.add(levelValueList);
			maxLevel++;
		}

		return results;
	}

	private static void dfs(TreeNode root, ArrayList<Integer> levelValueList,
			int curtLevel, int maxLevel) {
		if (root == null || curtLevel > maxLevel) {
			return;
		}

		if (curtLevel == maxLevel) {
			levelValueList.add(root.val);
			return;
		}

		dfs(root.left, levelValueList, curtLevel + 1, maxLevel);
		dfs(root.right, levelValueList, curtLevel + 1, maxLevel);
	}

	// method 3, easy to understand
	/**
	 * It is obvious that this problem can be solve by using a queue. However,
	 * if we use one queue we can not track when each level starts. So we use
	 * two queues to track the current level and the next level.
	 */
	public static ArrayList<ArrayList<Integer>> levelOrder2(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> levelNodeValues = new ArrayList<Integer>();
		if (root == null)
			return result;

		LinkedList<TreeNode> current = new LinkedList<TreeNode>();
		LinkedList<TreeNode> next = new LinkedList<TreeNode>();
		current.add(root);

		while (!current.isEmpty()) {
			// remove method: retrieve and remove the head(first) node in the
			// list
			TreeNode node = current.remove();

			if (node.left != null)
				next.add(node.left);
			if (node.right != null)
				next.add(node.right);

			levelNodeValues.add(node.val);
			if (current.isEmpty()) {
				current = next;
				next = new LinkedList<TreeNode>();
				result.add(levelNodeValues);
				levelNodeValues = new ArrayList<Integer>();
			}

		}
		return result;
	}

}
