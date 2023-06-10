package company.walmart;

import LinkedList.ListNode;

/**
 * Reverse a linked list from position m to n.
 * 
 * Notice Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of
 * list.
 * 
 * Example Given 1->2->3->4->5->NULL, m = 2 and n = 4, return
 * 1->4->3->2->5->NULL.
 *
 */
public class LinkedListReverseII {

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

		ListNode node = reverseBetween1(n1, 2, 4);
		while (node != null) {
			System.out.println(node.val);
			node = node.next;
		}
	}

	/**
	 * jiuzhang 老师课堂推荐的解法, only one pass, from first node to mth node, then from
	 * mth node to nths node
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (m >= n || head == null) {
			return head;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode preMNode = dummy;

		// m-1 position i.e. preMNode position
		for (int i = 0; i < m - 1; i++) {
			if (preMNode == null) {
				return null;
			}
			preMNode = preMNode.next;
		}

		ListNode mNode = preMNode.next;
		preMNode.next = null;

		// reverse m ~ n in place
		// nNode相当于pre, postN相当于cur;
		/**
		 * 也可以模范，Reverse Linked List的写法
		 * 
		 * ListNode nNode = null; ListNode postN = mNode;
		 * 
		 * for (int i = m; i <=n; i++) , 其他部分不变
		 */
		ListNode nNode = mNode;
		ListNode postN = mNode.next;
		for (int i = m; i < n; i++) {
			if (postN == null) {
				return null;
			}
			ListNode temp = postN.next;
			postN.next = nNode;

			nNode = postN;
			postN = temp;
		}

		/**
		 * after above forloop, nNode points to Nth Node position, postN points
		 * to the nth next node;
		 */

		// connect m-1 -> n, m ->n+1
		preMNode.next = nNode;
		mNode.next = postN;
		return dummy.next;
	}

	/**
	 * Jun's solution 结合了jiuzhang的思想，不过还是没有达到题目要求（one pass）
	 * 
	 */
	public static ListNode reverseBetween1(ListNode head, int m, int n) {
		if (m >= n || head == null) {
			return null;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode preMNode = dummy;
		ListNode mNode = head;

		ListNode nNode = head;

		// find m, n position
		for (int i = 0; i < n - 1; i++) {
			if (i < m - 1) {
				preMNode = preMNode.next;
				mNode = mNode.next;
			}
			nNode = nNode.next;
		}

		ListNode postN = nNode.next;
		nNode.next = null;
		preMNode.next = null;

		// in place reverse
		ListNode pre = postN;
		ListNode cur = mNode;
		while (cur != null) {
			ListNode temp = cur.next;
			cur.next = pre;

			pre = cur;
			cur = temp;
		}
		// put together
		preMNode.next = pre;
		return dummy.next;

	}

	/**
	 * Jun's Old way - NOT recommend
	 */
	public static ListNode reverseBetween2(ListNode head, int m, int n) {
		// write your code
		if (head == null) {
			return null;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode pre = dummy;
		ListNode post = null;

		ListNode p1 = head;
		ListNode p2 = head;

		for (int i = 0; i < n - 1; i++) {
			if (i < m - 1) {
				pre = pre.next;
				p1 = p1.next;
			}
			p2 = p2.next;
		}
		post = p2.next;
		p2.next = null;

		ListNode reversedPart = LinkedListReverse.reverseList(p1);
		/**
		 * Note: pre.next = reversedPart， 是可以把reversedPart拼接到pre部分去，
		 * 但是由于，reverse函数返回来的只是反转后的头指针（4），pre+reversedPart变成了(1, 4,),
		 * 如果这时候，想要把post部分再拼接上去，你需要得到reversedPart的最末尾的指针，否则你直接拼接就会出现（1，4,5）
		 * 
		 * 
		 * 简单的说法就是，想把这段链表(1)加在别的链表(2)后面，需要有1的头指针和2的尾指针
		 */

		pre.next = reversedPart;
		// 上面的pre.next =reversedPart
		// 执行完后，链表变成(1->4->3->2)，不过指针依然指向数字1，如果要想再拼接后面的post，你需要把该指针移到末尾
		ListNode currentTailPoint = pre;
		while (currentTailPoint.next != null) {
			currentTailPoint = currentTailPoint.next;
		}
		// 上面while循环过后，currentTailPoint指向了数字2，这时候，你才能把后面的post部分凭借上去
		currentTailPoint.next = post;
		return dummy.next;
	}

}
