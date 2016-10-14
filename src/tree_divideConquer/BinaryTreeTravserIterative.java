package tree_divideConquer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeTravserIterative {

	// root, left ,right
	public static List<Integer> preorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null)
			return result;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		while (!stack.empty()) {
			TreeNode n = stack.pop();
			result.add(n.val);

			if (n.right != null) {
				stack.push(n.right);
				/**
				 * you can remove n.right = null;
				 * 
				 * because, eveytime, you process the root node first, it won't
				 * re-visit root.left/right again
				 */
				// n.right = null;
			}
			if (n.left != null) {
				stack.push(n.left);
				/**
				 * you can remove n.right = null;
				 * 
				 * because, eveytime, you process the root node first, it won't
				 * re-vist root.left/right again
				 */
				// n.left = null;
			}

		}
		return result;
	}

	// left, root, right
	public static List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if (root == null)
			return result;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode top = stack.peek();
			/**
			 * 思路是：in order: left -> root > right
			 * 
			 * 一直把左边的压栈，直到没有left child,然后从最左边的node开始pop
			 */
			if (top.left != null) {
				stack.push(top.left);
				// NOTE: can NOT ignore (top.left=null;),
				top.left = null;
			} else {
				result.add(top.val);
				stack.pop();
				/**
				 * 如果这个最左下角的node, 没有右孩子， 该node就相当于是最左边的叶子节点.就从他pop，然后访问上一个次left
				 * most的node
				 * 
				 * 如果这个最左下角的node,
				 * 有右孩子，该node就相当在最左边小的子树里扮演root的角色，他出来后接下来就要访问他的右孩子.
				 * ("root"->right)
				 * 
				 */
				if (top.right != null) {
					stack.push(top.right);
					// NOTE: you can ignore (top.right=null;), because you
					// already
					// processed root before right node
					// top.right = null;
				}
			}
		}

		return result;
	}

	// left, right , root
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
				res.add(pop.val);
			} else {
				if (cur.right != null) {
					stack.push(cur.right);
					// can NOT ignore
					cur.right = null;
				}

				if (cur.left != null) {
					stack.push(cur.left);
					// can NOT ignore
					cur.left = null;
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(8);
		TreeNode left = new TreeNode(6);
		TreeNode right = new TreeNode(10);
		TreeNode left_right = new TreeNode(7);
		TreeNode left_left = new TreeNode(5);
		root.left = left;
		root.right = right;
		root.left.left = left_left;
		root.left.right = left_right;
		System.out.println(postorderTraversal(root));

	}

}
