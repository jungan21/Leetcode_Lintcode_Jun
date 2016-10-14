package tree_divideConquer;

import java.util.Stack;

public class RecoverBinarySearchTree {
	TreeNode first;
	TreeNode second;
	TreeNode pre;

	/**
	 * 解决方法是利用中序遍历找顺序不对的两个点，最后swap一下就好。
	 * 
	 * 因为这中间的错误是两个点进行了交换，所以就是大的跑前面来了，小的跑后面去了。
	 * 
	 * 所以在中序便利时，遇见的第一个顺序为抵减的两个node，大的那个肯定就是要被recovery的其中之一，要记录。
	 * 
	 * 另外一个，要遍历完整棵树，记录最后一个逆序的node。
	 * 
	 * 简单而言，第一个逆序点要记录，最后一个逆序点要记录，最后swap一下。
	 * 
	 * 因为Inorder用了递归来解决，所以为了能存储这两个逆序点，这里用了全局变量，用其他引用型遍历解决也可以。
	 * 
	 */

	public static void main(String[] args) {
		TreeNode root = new TreeNode(0);
		TreeNode left = new TreeNode(1);
		TreeNode right = new TreeNode(15);
		TreeNode right_left = new TreeNode(16);
		TreeNode right_right = new TreeNode(18);

		root.left = left;

		new RecoverBinarySearchTree().recoverTree(root);

	}

	// http://www.cnblogs.com/springfor/p/3891390.html
	// http://huntfor.iteye.com/blog/2077665
	/**
	 * inorder 1,4,3,2,5,6
	 * 
	 * 1.当我们读到4的时候，发现是正序的，不做处理, 但是遇到3时，发现逆序，将4存为第一个错误节点，3存为第二个错误节点,
	 * 
	 * 
	 * 2. 继续往后，发现3，2又是逆序了，那么将第2个错误节点更新为2
	 * 
	 * 其实就是inorder 递归遍历： 参考： BinaryTreeTravserRecusive.printInorder
	 */
	public void inorderRecurisve(TreeNode root) {
		if (root == null)
			return;

		inorderRecurisve(root.left);

		if (pre != null && root.val < pre.val) {
			if (first == null) {
				// 第一个逆序点, 只有在firt ==
				// null,时才会执行，所以pre只会被赋值一次，中序便利时，遇见的第一个顺序为抵减的两个node，大的那个肯定就是要被recovery的其中之一，要记录。
				first = pre;
			}
			/**
			 * 不断寻找最后一个逆序点
			 * 
			 * 注意： 不可以将 second = root 放在 else{}字句里， 否则如果只有两个节点的树，处理不了，如 (0,1)
			 * 根为0，左字树为1，如果放在else 里面，
			 * 当first=pre执行完，下面second就不会被赋值了，而这时候stack已经为空了
			 * 
			 */
			second = root;
		}
		// this pre keep moving on each node
		pre = root;
		inorderRecurisve(root.right);
	}

	// O(n) space , The time complexity is O(n)
	public void recoverTree(TreeNode root) {
		if (root == null)
			return;
		// traverse and get two elements
		inorderRecurisve(root);
		System.out.println("first" + first.val);
		System.out.println("second" + second.val);
		// swap
		if (second != null && first != null) {
			int temp = first.val;
			first.val = second.val;
			second.val = temp;
		}
	}

	/**
	 * Jiuzhang's inorder template
	 * 
	 * @param root
	 */
	private void inorderFindNodesJiuzhang(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root;
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			cur = stack.pop();
			if (pre != null && pre.val > cur.val) {
				if (first == null) {
					first = pre;
				}
				second = cur;
			}
			pre = cur;
			cur = cur.right;
		}
	}

	/**
	 * jun's inorder
	 */

	private void inorderFindNodesJun(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.peek();
			if (cur.left != null) {
				stack.push(cur.left);
				cur.left = null;
			} else {
				if (pre != null && pre.val > cur.val) {
					if (first == null) {
						first = pre;
					}
					second = cur;
				}
				stack.pop();
				pre = cur;
				if (cur.right != null) {
					stack.push(cur.right);
				}
			}
		}
	}

}
