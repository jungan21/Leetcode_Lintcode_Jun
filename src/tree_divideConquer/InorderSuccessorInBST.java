package tree_divideConquer;

import java.util.Stack;

/**
 * Given a binary search tree (See Definition) and a node in it, find the
 * in-order successor of that node in the BST.
 * 
 * If the given node has no in-order successor in the tree, return null.
 * 
 * It's guaranteed p is one node in the given tree. (You can directly compare
 * the memory address to find p)
 *
 */
public class InorderSuccessorInBST {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(2);
		TreeNode left = new TreeNode(1);
		TreeNode right = new TreeNode(3);
		TreeNode left_left = new TreeNode(0);

		root.left = left;
		root.right = right;
		left.left = left_left;

		inorderSuccessorBest(root, left.left);

	}

	// http://www.cnblogs.com/grandyang/p/5306162.html
	// Time is O(log(n)) and space is O(1).
	public static TreeNode inorderSuccessorBest(TreeNode root, TreeNode p) {
		TreeNode successor = null;
		while (root != null) {
			// 必须是 >
			if (root.val > p.val) {
				successor = root;
				root = root.left;
			} else {
				// 这里会一直move right, 直到找打root.val > p.val然后对successor赋值
				root = root.right;
			}
		}
		return successor;
	}

	/**
	 * jun's inorder traverse , 不符合要求，题目要求O(h)time h 是数的高度，而这里是 O(n), n是树的节点数
	 * 
	 * Time is O(n), Space is O(n).
	 */
	public TreeNode inorderSuccessorJun(TreeNode root, TreeNode p) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode curt = root;
		TreeNode pre = null;
		while (curt != null || !stack.isEmpty()) {
			while (curt != null) {
				stack.push(curt);
				curt = curt.left;
			}
			curt = stack.pop();
			if (pre != null && pre == p) {
				return curt;
			}
			pre = curt;
			curt = curt.right;
		}
		return null;
	}

	/**
	 * 递归中序遍历
	 * 
	 * {4,3,5,2,#,#,6} node with value 2, output 4, lintcode answer is 3
	 */

	TreeNode succ = null;
	TreeNode pre = null;

	public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
		helper(root, p);
		return succ;
	}

	public void helper(TreeNode root, TreeNode p) {
		if (root == null) {
			return;
		}
		helper(root.left, p);
		if (pre != null && pre == p) {
			succ = root;
			// return;
			// 即使找到了也不能return, 否则结果不对，必须要让helper运行完
		}
		pre = root;
		helper(root.right, p);
	}

}
