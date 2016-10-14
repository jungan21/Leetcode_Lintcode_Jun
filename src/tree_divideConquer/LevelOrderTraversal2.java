package tree_divideConquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

// i.e from leaf level to  to root level

/**
 * iven a binary tree, return the bottom-up level order traversal of its nodes'
 * values.
 * 
 * For example, given binary tree {3,9,20,#,#,15,7},
 * 
 * 3 / \ 9 20 / \ 15 7 return its level order traversal as [[15,7], [9,20],[3]]
 * 
 * @author jungan
 *
 */
public class LevelOrderTraversal2 {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);

		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);

		TreeNode left_left = new TreeNode(4);
		TreeNode left_right = null;
		TreeNode right_left = null;
		TreeNode right_right = new TreeNode(5);

		root.left = left;
		root.right = right;

		left.left = left_left;
		left.right = left_right;

		right.left = right_left;
		right.right = right_right;
		System.out.println(levelOrderFromLeafToRoot(root));
	}

	/**
	 * It is obvious that this problem can be solve by using a queue. However,
	 * if we use one queue we can not track when each level starts. So we use
	 * two queues to track the current level and the next level.
	 */
	public static ArrayList<ArrayList<Integer>> levelOrderFromLeafToRoot(
			TreeNode root) {
		// write your code here
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
		Collections.reverse(result);
		return result;
	}
}
