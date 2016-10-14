package tree_divideConquer;

public class LowestCommonAncestorBinarySearchTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TreeNode root = new TreeNode(5);

		TreeNode left = new TreeNode(3);
		TreeNode right = new TreeNode(6);

		TreeNode left_left = new TreeNode(2);
		TreeNode left_right = new TreeNode(4);

		TreeNode left_left_left = new TreeNode(1);

		root.left = left;
		root.right = right;
		left.left = left_left;
		left.right = left_right;
		left.left.left = left_left_left;

		System.out
				.println(lowestCommonAncestorRecursive(root, left, left.left).val);

	}

	// recursive
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p,
			TreeNode q) {
		TreeNode result = null;
		if (root == null) {
			return null;
		}
		if ((root.left == p && root.right == q)
				|| (root.left == q && root.right == p) || q == root
				|| p == root) {
			return root;
		}

		if (p.left == q || p.right == q) {
			return p;
		}
		if (q.left == p || q.right == p) {
			return q;
		}

		if ((p.val < root.val && q.val > root.val)
				|| (q.val < root.val && p.val > root.val)) {
			return root;
		}

		if (p.val < root.val && q.val < root.val) {
			return lowestCommonAncestor(root.left, p, q);
		}

		if (p.val > root.val && q.val > root.val) {
			return lowestCommonAncestor(root.right, p, q);
		}
		return result;
	}

	// method 2;
	public static TreeNode lowestCommonAncestorRecursive(TreeNode root,
			TreeNode p, TreeNode q) {
		TreeNode m = root;

		if (m.val > p.val && m.val < q.val) {
			return m;
		} else if (m.val > p.val && m.val > q.val) {
			return lowestCommonAncestorRecursive(root.left, p, q);
		} else if (m.val < p.val && m.val < q.val) {
			return lowestCommonAncestorRecursive(root.right, p, q);
		}

		return root;
	}

}
