package tree_divideConquer;

/**
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST
 * 
 *
 */
public class ConvertSortedListtoBST {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		sortedListToBST1(n1);
	}

	/**
	 * Jun's own methodO(N*lgN) 因为找到中间节点需要O(n)
	 * 
	 * each level of recursive call requires a total of N/2 traversal steps in
	 * the list, and there are a total of lg N number of levels (ie, the height
	 * of the balanced tree).
	 * 
	 * Note: convert sorted array to BST 是O(n),因为对于数组，O(1)的时间get mid
	 * 
	 * 自顶向下的方法，先找到root然后对左右子树分别递归调用。
	 * 
	 * similar to construct BST fron Array, find mid node as root, then
	 * recursivey build and left and righ tree
	 */

	public static TreeNode sortedListToBST(ListNode head) {
		if (head == null) {
			return null;
		}

		// for case 1->null (i.e. we only have 1 node)
		if (head.next == null) {
			return new TreeNode(head.val);
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;
		// slow, fast have same starting point i.e. head node
		ListNode slow = head;
		ListNode fast = head;

		while (fast.next != null && fast.next.next != null) {
			// 需要track slow的前一个点，用以断开left与mid(slow) node
			dummy = dummy.next;
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode mid = slow;
		ListNode secondHead = slow.next;

		slow.next = null;

		// mid点将会被用作root，所以要从left一半（将作为left child）的list中移除
		dummy.next = null;

		// 1->2 (i.e only two nodes, slow(1) will be root, left tree is empty
		if (slow == head) {
			head = null;
		}

		TreeNode root = new TreeNode(mid.val);
		root.left = sortedListToBST(head);
		root.right = sortedListToBST(secondHead);

		return root;
	}

	/**
	 * Better one O(n) . another way, is to convert the List to array, then
	 * convert array to tree
	 * 
	 * http://articles.leetcode.com/convert-sorted-list-to-balanced-binary/
	 * 
	 * 自底向上的方法，算法复杂度为O(N)。
	 * 
	 * 1. 先递归构建左子树，在构建左子树的同时不断移动链表的头指针，链表的头指针永远是对应当前子树位置的。
	 * 一直到左叶子节点，左叶子节点对应的就是链表的第一个元素，生成左叶子节点之后移动链表当前指针。
	 * 
	 * 2. create root
	 * 
	 * 3. create right subtree
	 */
	// 必须设置为null,否则Leetcode通不过
	private static ListNode current = null;

	public static TreeNode sortedListToBST1(ListNode head) {
		if (head == null) {
			return null;
		}
		// 赋值必须在求length之前，否则getListLength方法运行完后，head的位置改变了
		current = head;
		int length = getListLength(head);
		return helper(0, length - 1);
	}

	public static TreeNode helper(int start, int end) {
		// 必须 > 不能>=
		if (start > end) {
			return null;
		}
		/**
		 * 1. take left n/2 nodes and recursively construct the left subtree,
		 * 
		 * 2.After left subtree is constructed, we allocate memory for root and
		 * link the left subtree with rootFinally, we recursively construct the
		 * right subtree and link it with root.
		 * 
		 * While constructing the BST, we also keep moving the list head pointer
		 * to next so that we have the appropriate pointer in each recursive
		 * call.
		 */
		// divide
		int mid = start + (end - start) / 2;
		TreeNode left = helper(start, mid - 1);
		
		// 下面两句顺序不能乱，相当于conquer
		TreeNode root = new TreeNode(current.val);
		current = current.next;
		root.left = left;

		TreeNode right = helper(mid + 1, end);
		root.right = right;
		return root;
	}

	private static int getListLength(ListNode head) {
		int length = 0;
		while (head != null) {
			length++;
			head = head.next;
		}
		return length;
	}
}
