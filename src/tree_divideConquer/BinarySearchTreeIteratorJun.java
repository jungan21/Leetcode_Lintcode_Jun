package tree_divideConquer;

/**
 * 

 Design an iterator over a binary search tree with the following rules:

 Elements are visited in ascending order (i.e. an in-order traversal)
 next() and hasNext() queries run in O(1) time in average.


 */
import java.util.Stack;

/**
 * Your BSTIterator will be called like this: BSTIterator i = new
 * BSTIterator(root); while (i.hasNext()) v[f()] = i.next();
 */

// Calling next() will return the next smallest number in the BST.
/**
 * 
 * inorder trasver can be used here
 *
 */
public class BinarySearchTreeIteratorJun {
	Stack<TreeNode> stack;

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
		root.right = right;

		BinarySearchTreeIteratorJun i = new BinarySearchTreeIteratorJun(root);
		while (i.hasNext()) {
			System.out.println(i.next());

		}
	}

	// inorder trasver initiliaztion
	public BinarySearchTreeIteratorJun(TreeNode root) {
		stack = new Stack<TreeNode>();
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}

	// O(1) time and uses O(h) memory, where h is the height
	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	// O(1) time and uses O(h) memory, where h is the height
	/** @return the next smallest number */
	public int next() {
		TreeNode node = stack.pop();
		int result = node.val;
		if (node.right != null) {
			node = node.right;
			// while loop is key point
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
		}

		return result;
	}

}
