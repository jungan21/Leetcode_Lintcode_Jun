package LinkedList;

import java.util.Stack;

/**
 * Convert a binary search tree to doubly linked list with in-order traversal.
 * 
 *
 */
public class ConvertBinarySearchTreetoDoublyLinkedList {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(5);
		TreeNode left_right = new TreeNode(3);
		TreeNode left_left = new TreeNode(1);
		root.left = left;
		root.right = right;
		root.left.left = left_left;
		root.left.right = left_right;

		bstToDoublyList1(root);

	}

	class ResultType {
		DoublyListNode first, last;

		public ResultType(DoublyListNode first, DoublyListNode last) {
			this.first = first;
			this.last = last;
		}
	}

	public DoublyListNode bstToDoublyList(TreeNode root) {
		if (root == null) {
			return null;
		}

		ResultType result = helper(root);
		return result.first;
	}

	public ResultType helper(TreeNode root) {
		if (root == null) {
			return null;
		}

		ResultType left = helper(root.left);
		ResultType right = helper(root.right);
		DoublyListNode node = new DoublyListNode(root.val);

		ResultType result = new ResultType(null, null);

		if (left == null) {
			result.first = node;
		} else {
			result.first = left.first;
			left.last.next = node;
			node.prev = left.last;
		}

		if (right == null) {
			result.last = node;
		} else {
			result.last = right.last;
			right.first.prev = node;
			node.next = right.first;
		}

		return result;
	}

	/**
	 * Jun's
	 */
	public static DoublyListNode bstToDoublyList1(TreeNode root) {
		if (root == null) {
			return null;
		}

		DoublyListNode dummy = new DoublyListNode(0);
		DoublyListNode cur = dummy;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode node = stack.peek();
			if (node.left != null) {
				stack.push(node.left);
				node.left = null;
			} else {
				DoublyListNode listNode = new DoublyListNode(node.val);
				listNode.prev = cur;
				cur.next = listNode;
				cur = cur.next;

				stack.pop();
				if (node.right != null) {
					stack.push(node.right);
				}
			}
		}
		return dummy.next;
	}
}
