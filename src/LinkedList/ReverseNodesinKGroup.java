package LinkedList;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 * 
 * If the number of nodes is not a multiple of k then left-out nodes in the end
 * should remain as it is.
 * 
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * Only constant memory is allowed
 * 
 * Given this linked list: 1->2->3->4->5
 * 
 * For k = 2, you should return: 2->1->4->3->5
 * 
 * For k = 3, you should return: 3->2->1->4->5
 *
 */
public class ReverseNodesinKGroup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReverseNodesinKGroup test = new ReverseNodesinKGroup();

		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;

		test.reverseKGroup(n1, 2);
	}

	/**
	 * http://www.cnblogs.com/springfor/p/3864530.html
	 */

	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || k <= 1)
			return head;

		ListNode dummy = new ListNode(0);
		dummy.next = head;
		int count = 0;

		ListNode pre = dummy;
		ListNode cur = head;
		while (cur != null) {
			count++;
			// 当count==k的时候，cur已经跑到下一个k group node 的第一个节点了
			cur = cur.next;
			if (count == k) {
				pre = reverse(pre, cur);
				count = 0;
			}
		}
		return dummy.next;
	}

	private ListNode reverse(ListNode pre, ListNode next) {
		ListNode p1 = pre.next;
		ListNode p2 = pre.next.next;

		ListNode nextPre = pre.next;
		pre.next = null;
		while (p2 != next) {
			ListNode temp = p2.next;
			p2.next = p1;

			p1 = p2;
			p2 = temp;
		}
		pre.next = p1;
		nextPre.next = next;
		// newPre is the node pointing to next k group
		return nextPre;
	}

	/**
	 * Reverse a link list between pre and next exclusively an example: a linked
	 * list: 0->1->2->3->4->5->6 | | pre next after call, pre = reverse(pre,
	 * next)
	 * 
	 * 0->3->2->1->4->5->6 | | pre next
	 * 
	 * @return the reversed list's last node, which is the precedence of
	 *         parameter next
	 */
	// reverse nodes between pre.next and next.previous
	private ListNode reverseOld(ListNode pre, ListNode next) {
		ListNode p1 = pre.next;// where first will be the "last" of reversed
								// part
		ListNode p2 = pre.next.next;
		while (p2 != next) {
			p1.next = p2.next;
			// Note: do NOT set p2.next = p1; 这种只有在K为2时候才工作，我们不是做两两反转
			p2.next = pre.next;
			pre.next = p2;
			p2 = p1.next;
		}
		return p1;
	}

	/**
	 * Jun's
	 */
	public ListNode reverseKGroup1(ListNode head, int k) {
		if (head == null || k == 1) {
			return head;
		}
		int length = getLength(head);

		if (k >= length) {
			return reverseList(head);
		}
		ListNode dummy = new ListNode(0);
		ListNode p = dummy;

		ListNode cur = head;
		int count = 0;
		ListNode temp = null;
		ListNode subHead = head;
		while (cur != null) {
			count++;
			if (count == k) {
				temp = cur.next;
				cur.next = null;
				// need to track starting point of each sublist
				p.next = reverseList(subHead);
				while (p.next != null) {
					p = p.next;
				}
				count = 0;
			}
			// if you don't set count==0 condition, cur will go back (wil get
			// infinite loop)
			if (temp != null && count == 0) {
				cur = temp;
				count++;
				cur = cur.next;
				subHead = temp;
			} else {
				cur = cur.next;
			}
		}
		return dummy.next;
	}

	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode p1 = head;
		ListNode p2 = head.next;

		head.next = null;
		while (p1 != null && p2 != null) {
			ListNode temp = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = temp;
		}
		return p1;
	}

	public int getLength(ListNode head) {
		if (head == null) {
			return 0;
		}
		int length = 0;
		while (head != null) {
			length++;
			head = head.next;
		}
		return length;
	}

}
