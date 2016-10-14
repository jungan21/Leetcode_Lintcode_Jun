package tree_divideConquer;

import java.util.Stack;

/**
 * Flatten a binary tree to a fake "linked list" in pre-order traversal.
 * 
 * Here we use the right pointer in TreeNode as the next pointer in ListNode.
 *
 * Notice
 * 
 * Don't forget to mark the left child of each node to null. Or you will get
 * Time Limit Exceeded or Memory Limit Exceeded.
 */
public class FlattenBinaryTreetoLinkedList {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(5);
		TreeNode left_right = new TreeNode(4);
		TreeNode left_left = new TreeNode(3);
		TreeNode right_right = new TreeNode(6);

		root.left = left;
		root.right = right;
		root.left.left = left_left;
		root.left.right = left_right;
		root.right.right = right_right;
		flattenRecursive(root);
	}

	/**
	 * Recursive: http://www.cnblogs.com/grandyang/p/4293853.html
	 * 
	 * 思路是先利用DFS的思路找到最左子节点，然后回到其父节点，把其父节点和右子节点断开，将原左子结点连上父节点的右子节点上，
	 * 然后再把原右子节点连到新右子节点的右子节点上，然后再回到上一父节点做相同操作
	 * 
	 */
	public static void flattenRecursive(TreeNode root) {
		if (root == null) {
			return;
		}

		flattenRecursive(root.left);
		flattenRecursive(root.right);
		// Note: 上面的两句递归调用，和下面的顺序不能换
		TreeNode temp = root.right;
		root.right = root.left;
		root.left = null;

		while (root.right != null) {
			root = root.right;
		}
		root.right = temp;
	}

	/**
	 * Jun's
	 * 
	 * 其实就是preorder遍历
	 */
	public void flattenNonRecursive(TreeNode root) {
		if (root == null) {
			return;
		}
		TreeNode cur = root;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			// 因为cur已经指向root,了，在构造flatten树时，要将root ignore掉
			if (node != root) {
				cur.right = node;
				cur = cur.right;
			}
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
				// 与先序遍历的不同，根据题目提示，必须要将left置空，否则time/memory limit 不通过
				node.left = null;
			}
		}

	}

	// old way =========================================================

	/**
	 * Traverse - recursive;
	 * 
	 * http://www.jiuzhang.com/solutions/flatten-binary-tree-to-linked-list/
	 * 
	 * 如hint所给出，这道题就是使用先序遍历，遍历到的值作为新的右孩子存起来，左孩子变为空。
	 * 注意的是，因为右孩子会更新，所以为了递归右子树，要在更新之前提前保存右孩子。 整个程序需要维护一个全局变量，保存当前所遍历的节点。
	 */
	private TreeNode lastNode = null;

	public void flatten(TreeNode root) {
		if (root == null) {
			return;
		}

		if (lastNode != null) {
			lastNode.left = null;
			lastNode.right = root;
		}

		lastNode = root;
		// 必须提前把right拿去来，否则结果不对
		TreeNode realRight = root.right;
		flatten(root.left);
		flatten(realRight);
	}

	/**
	 * Divide Conquer
	 * 
	 * http://www.jiuzhang.com/solutions/flatten-binary-tree-to-linked-list/
	 */
	public void flatten1(TreeNode root) {
		flattenHelper(root);
	}

	private TreeNode flattenHelper(TreeNode root) {
		if (root == null) {
			return null;
		}

		if (root.left == null && root.right == null) {
			return root;
		}

		if (root.left == null) {
			return flattenHelper(root.right);
		}

		if (root.right == null) {
			root.right = root.left;
			root.left = null; // important!
			return flattenHelper(root.right);
		}

		// Divide
		TreeNode leftLastNode = flattenHelper(root.left);
		TreeNode rightLastNode = flattenHelper(root.right);

		// Conquer
		leftLastNode.right = root.right;
		root.right = root.left;
		root.left = null; // important!
		return rightLastNode;
	}

}
