package LinkedList;

/**
 * Given a singly linked list L: L0 → L1 → … → Ln-1 → Ln
 * 
 * reorder it to: L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 *
 * Given 0->1->2->3->4, reorder it to 0->4->1->3->2
 *
 */
public class ReorderList {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(0);
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(2);
		ListNode n4 = new ListNode(3);
		ListNode n5 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		new ReorderList().reorderList(n1);

	}

	/**
	 * Jun's own - Recursive 单向链表的话，递归一般时间复杂度太高
	 * 
	 * Given 0->1->2->3->4, reorder it to 0->4->1->3->2
	 * 
	 * 规律：就是把最后一个节点，挪到第一个节点后面，
	 * 
	 * 思路： 先第一步： (0->4)->1->2->3, 然后把剩下的1->2->3当成一个子问题去解决，把3再挪到1的后面
	 * (0->4->1->3)->2,把2当成子问题再递归调用
	 */
	public void reorderListRecursive(ListNode head) {
		if (head == null || head.next == null
				|| (head.next != null && head.next.next == null)) {
			return;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode secondLast = dummy;
		ListNode last = head;

		while (last.next != null) {
			secondLast = secondLast.next;
			last = last.next;
		}
		// after while loop, last point to last node， secondLast是last的前一个node
		secondLast.next = null;

		ListNode temp = head.next;
		head.next = last;
		// recursive call
		reorderListRecursive(temp);
		// Note: 因为last就一个node, 所以可以直接拼接，否则要循环last到last的最后一个node的时候， 才可以拼接
		last.next = temp;
	}

	/**
	 * NON-recursive, much faster
	 * 
	 * jiuzhang: http://www.jiuzhang.com/solutions/reorder-list/
	 */
	public void reorderList(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}

		ListNode mid = findMiddle(head);
		// revers second half
		ListNode tail = reverse(mid.next);
		mid.next = null;

		merge(head, tail);
	}

	private ListNode reverse(ListNode head) {
		ListNode pre = null;
		ListNode cur = head;
		while (cur != null) {
			ListNode temp = cur.next;
			cur.next = pre;
			// moving on
			pre = cur;
			cur = temp;
		}
		return pre;
	}

	// 按奇偶性合并
	private void merge(ListNode head1, ListNode head2) {
		int index = 0;
		ListNode dummy = new ListNode(0);
		while (head1 != null && head2 != null) {
			if (index % 2 == 0) {
				dummy.next = head1;
				head1 = head1.next;
			} else {
				dummy.next = head2;
				head2 = head2.next;
			}
			dummy = dummy.next;
			index++;
		}
		if (head1 != null) {
			dummy.next = head1;
		} else {
			dummy.next = head2;
		}
	}

	private ListNode findMiddle(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

}
