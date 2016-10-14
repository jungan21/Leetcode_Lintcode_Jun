package tree_divideConquer;

import java.util.LinkedList;

// follow up question:http://www.cnblogs.com/EdwardLiu/p/4265448.html
public class LowestCommonAncestorBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TreeNode root = new TreeNode(37);

		TreeNode left = new TreeNode(-34);
		TreeNode right = new TreeNode(-48);

		TreeNode left_right = new TreeNode(-100);

		TreeNode right_left = new TreeNode(-100);
		TreeNode right_right = new TreeNode(48);

		TreeNode right_right_left = new TreeNode(54);

		TreeNode right_right_left_left = new TreeNode(-71);
		TreeNode right_right_left_right = new TreeNode(-22);

		TreeNode right_right_left_right_right = new TreeNode(8);

		root.left = left;
		root.right = right;
		left.right = left_right;
		right.left = right_left;
		right.right = right_right;

		right.right.left = right_right_left;

		right.right.left.left = right_right_left_left;
		right.right.left.right = right_right_left_right;
		right.right.left.right.right = right_right_left_right_right;

		System.out.println(lowestCommonAncestorRecursive(root, right.left,
				right.right.left.left).val);

	}

	/**
	 * easier to remember Recursive method 3: from crack code: this applies for
	 * both binary tree and binary search tree T(n)=O(n)
	 */
	public static TreeNode lowestCommonAncestorRecursive(TreeNode root,
			TreeNode p, TreeNode q) {
		if (covers(root.left, p) && covers(root.left, q)) {
			return lowestCommonAncestorRecursive(root.left, p, q);
		}

		if (covers(root.right, p) && covers(root.right, q)) {
			return lowestCommonAncestorRecursive(root.right, p, q);
		}
		// in all other cases, root is the LCA, e.g. (p in left && q in right)
		// OR (p in right, q in left)
		return root;
	}

	public static boolean covers(TreeNode root, TreeNode p) {
		if (root == null) {
			return false;
		}
		if (root == p) {
			return true;
		}
		return covers(root.left, p) || covers(root.right, p);
	}

	/**
	 * O(n) recommend by teacher jiuzhang:
	 * http://www.jiuzhang.com/solutions/lowest-common-ancestor/
	 * 
	 * 如果是最坏的情况下也就是两个节点都是叶子节点，那时间复杂度就应该是O(n)，因为最多最多是把所有节点都访问一次
	 * 
	 * comments from teacher: 1. 在root为根的二叉树中找A,B的LCA 2.如果找到了就返回这个LCA 3.
	 * 如果只能找到A,就返回A， 4，如果只能找到B， 就返回B 5， 如果都没有，就返回null
	 * 
	 * 在root为根的二叉树中找A,B的LCA, 若找不到LCA： 遇到A， 就返回A， 遇到B， 就返回B OR null (if they
	 * don't exist in the tree)
	 */

	public static TreeNode lowestCommonAncestorRecursive1(TreeNode root, TreeNode A,
			TreeNode B) {
		if (root == null) {
			return null;
		}
		// 3. 如果只能找到A,就返回A， 4，如果只能找到B, 就返回B
		if (root == A || root == B) {
			return root;
		}

		// Divide
		TreeNode left = lowestCommonAncestorRecursive1(root.left, A, B);
		TreeNode right = lowestCommonAncestorRecursive1(root.right, A, B);

		// Conquer
		// 上面分治， 碰到谁，返回谁（上面if condition做到的），若都不空，说明一左， 一右
		if (left != null && right != null) {
			return root;
		}
		// means we found LCA in left side
		if (left != null) {
			return left;
		}
		// means we found LCA in right side
		if (right != null) {
			return right;
		}
		// A, B nodes don't exist in this trees
		return null;
	}

	// Jun's own method, deprecated !!!
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

		if ((inLeftSubTree(root, p) && inRightSubTree(root, q))
				|| (inLeftSubTree(root, q) && inRightSubTree(root, p))) {
			return root;
		}
		if (inLeftSubTree(root, p) && inLeftSubTree(root, q)) {
			return lowestCommonAncestor(root.left, p, q);
		}

		if (inRightSubTree(root, p) && inRightSubTree(root, q)) {
			return lowestCommonAncestor(root.right, p, q);
		}
		return result;
	}

	public static boolean inLeftSubTree(TreeNode root, TreeNode p) {
		if (root == null || root.left == null) {
			return false;
		}
		TreeNode leftNode = root.left;
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		list.offer(leftNode);
		while (!list.isEmpty()) {
			TreeNode node = list.poll();
			if (node == p) {
				return true;
			}
			if (node.left != null) {
				list.offer(node.left);
			}

			if (node.right != null) {
				list.offer(node.right);
			}
		}
		return false;
	}

	public static boolean inRightSubTree(TreeNode root, TreeNode p) {
		if (root == null || root.right == null) {
			return false;
		}
		TreeNode leftNode = root.right;
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		list.offer(leftNode);
		while (!list.isEmpty()) {
			TreeNode node = list.poll();
			if (node == p) {
				return true;
			}
			if (node.left != null) {
				list.offer(node.left);
			}

			if (node.right != null) {
				list.offer(node.right);
			}
		}
		return false;
	}

	// Iterative
	// http://www.programcreek.com/2014/07/leetcode-lowest-common-ancestor-of-a-binary-tree-java/
	public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p,
			TreeNode q) {
		return lcaHelper(root, p, q).node;
	}

	public Entity lcaHelper(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null)
			return new Entity(0, null);

		Entity left = lcaHelper(root.left, p, q);
		if (left.count == 2)
			return left;

		Entity right = lcaHelper(root.right, p, q);
		if (right.count == 2)
			return right;

		int numTotal = left.count + right.count;
		if (root == p || root == q) {
			numTotal++;
		}

		return new Entity(numTotal, root);
	}

	class Entity {
		public int count;
		public TreeNode node;

		public Entity(int count, TreeNode node) {
			this.count = count;
			this.node = node;
		}
	}
}
