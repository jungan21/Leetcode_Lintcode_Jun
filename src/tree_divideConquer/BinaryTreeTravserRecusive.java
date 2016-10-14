package tree_divideConquer;

import java.util.Arrays;

/**
 * T(n)=O(n)
 */
public class BinaryTreeTravserRecusive {
	// Root of Binary Tree
	static TreeNode root;

	BinaryTreeTravserRecusive() {
		root = null;
	}

	/*
	 * Given a binary tree, print its nodes according to the "bottom-up"
	 * postorder traversal.
	 */
	void printPostorder(TreeNode node) {
		if (node == null)
			return;
		// first recur on left subtree
		printPostorder(node.left);

		// then recur on right subtree
		printPostorder(node.right);

		// now deal with the node
		System.out.print(node.val + " ");
	}

	/* Given a binary tree, print its nodes in inorder */
	void printInorder(TreeNode node) {
		if (node == null)
			return;

		/* first recur on left child */
		printInorder(node.left);

		/* then print the data of node */
		System.out.print(node.val + " ");

		/* now recur on right child */
		printInorder(node.right);
	}

	void printInorder2(TreeNode node, int[] array, int index) {
		if (node == null)
			return;

		/* first recur on left child */
		printInorder(node.left);

		/* then print the data of node */
		System.out.print(node.val + " ");
		array[index++] = node.val;
		/* now recur on right child */
		printInorder(node.right);
	}

	/* Given a binary tree, print its nodes in preorder */
	void printPreorder(TreeNode node) {
		if (node == null)
			return;

		/* first print data of node */
		System.out.print(node.val + " ");

		/* then recur on left sutree */
		printPreorder(node.left);

		/* now recur on right subtree */
		printPreorder(node.right);
	}

	void printPreorderIterative(TreeNode node) {
		if (node == null)
			return;

		/* first print data of node */
		System.out.print(node.val + " ");

		/* then recur on left sutree */
		printPreorder(node.left);

		/* now recur on right subtree */
		printPreorder(node.right);
	}

	// Driver method
	public static void main(String[] args) {
		BinaryTreeTravserRecusive tree = new BinaryTreeTravserRecusive();
		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.right = new TreeNode(3);
		tree.root.left.left = new TreeNode(4);
		tree.root.left.right = new TreeNode(5);

		int[] array = new int[15];
		int index = 0;
		System.out.println("inorder traversal of binary tree is ");
		tree.printInorder2(root, array, index);
		System.out.println(Arrays.toString(array));
		//
		// System.out.println("\nInorder traversal of binary tree is ");
		// tree.printInorder(root);
		//
		// System.out.println("\nPostorder traversal of binary tree is ");
		// tree.printPostorder(root);
	}
}
