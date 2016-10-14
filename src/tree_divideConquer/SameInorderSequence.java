package tree_divideConquer;

import java.util.Stack;

/**
 * Google
 * 
 * Given two binary trees ( not BST) , return true if both of them have same
 * inorder else return false.
 *
 * Both of the trees have same inorder ( A-B-C) hence function will return true
 * 
 * P.S. Please note, we can write inorder method call it once for first tree and
 * then second tree, and finally compare both inorder.
 * 
 * We want to parallely do inorder on both tree, if there is mismatch between
 * inorder nodes of both trees, we can stop the traversal and return false
 * 
 * https://www.careercup.com/question?id=5086938656669696
 */
public class SameInorderSequence {

	public static void main(String[] args) {
		TreeNode root1 = new TreeNode(2);
		TreeNode left1 = new TreeNode(1);
		TreeNode right1 = new TreeNode(3);

		TreeNode root2 = new TreeNode(1);
		TreeNode right = new TreeNode(2);
		TreeNode right_right = new TreeNode(3);

		root1.left = left1;
		root1.right = right1;

		root2.right = right;
		root2.right.right = right_right;
		System.out.println(sameInorder(root1, root2));
	}

	public static boolean sameInorder(TreeNode root1, TreeNode root2) {
		BinarySearchTreeIterator iterator1 = new BinarySearchTreeIterator(root1);
		BinarySearchTreeIterator iterator2 = new BinarySearchTreeIterator(root2);
		while (iterator1.hasNext() && iterator2.hasNext()) {
			if (iterator1.next().val != iterator2.next().val) {
				return false;
			}
		}
		if (iterator1.hasNext() || iterator2.hasNext()){
			return false;
		}
		
		return true;

	}

	// in order traverse
	public static void inOrder(TreeNode root) {

		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root;
		while (!stack.isEmpty() || cur != null) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			cur = stack.peek();
			System.out.println(cur.val);
			stack.pop();
			cur = cur.right;
		}
	}
}
