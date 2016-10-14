package a_oa;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *  Wrong !!!
 * given a binary tree where each node has some weight. You have to return the
 * max weight in the binary tree.
 * 
 * Maxweight = value of root node + value in its left subtree and right subtree.
 * 
 * weight of a node = node.val + weight(node.leftChild) +
 * weight(node.rightChild);
 * 
 * 可以用post-order遍历二叉树，每次得到叶子节点的权值，加上本节点value然后返回给父节点
 *
 */
public class WeightSubTree {

	public static void main(String[] args) {
		// TreeNode root = new TreeNode(1);
		// TreeNode left = new TreeNode(-2);
		// TreeNode right = new TreeNode(-3);
		// root.left = left;
		// root.right = right;
		TreeNode root = new TreeNode(-5);

		TreeNode left = new TreeNode(15);
		TreeNode right = new TreeNode(8);

		TreeNode left_left = new TreeNode(-2);
		TreeNode left_right = new TreeNode(-3);
		TreeNode right_left = new TreeNode(-6);

		root.left = left;
		root.right = right;
		left.left = left_left;
		left.right = left_right;
		right.left = right_left;

		// postOrder(root);
		// System.out.println(postOrder(root));
		System.out.println(maxWeight(root));
		// System.out.println(sum);
		// postOrder(root);
	}

	/**
	 * https://gist.github.com/vinay13/c102c68247786ef2e033
	 */
	public static int maxWeight(TreeNode root) {
		if (root == null) {
			return 0;
		}

		return Math.max(maxWeight, root.val + maxWeight(root.left)
				+ maxWeight(root.right));
	}

	public static void postOrder(TreeNode root) {

		if (root.left != null) {
			root.val = root.val + root.left.val;
			postOrder(root.left);
		}
		if (root.right != null) {
			root.val = root.val + root.right.val;
			postOrder(root.right);
		}
		System.out.print(root.val + ", ");

	}

	static int maxWeight = Integer.MIN_VALUE;
	static int sum = 0;

	public static List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null) {
			return res;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode cur = stack.peek();
			// 因为left->right->root, 只有没有子树时，才能轮到root
			if (cur.left == null && cur.right == null) {
				TreeNode pop = stack.pop();
				System.out.print(pop.val + ",  ");
				sum += pop.val;
				// if (sum < 0) {
				// sum = 0;
				// }
				maxWeight = Math.max(maxWeight, sum);
				res.add(pop.val);
			} else {
				if (cur.right != null) {
					stack.push(cur.right);
					cur.val += cur.right.val;
					// can NOT ignore
					cur.right = null;
				}

				if (cur.left != null) {
					stack.push(cur.left);
					cur.val += cur.left.val;
					// can NOT ignore
					cur.left = null;
				}
			}
		}

		return res;
	}

}
