package LinkedList;

/**
 * Given a linked list, remove the nth node from the end of list and return its
 * head.
 * 
 * Given linked list: 1->2->3->4->5->null, and n = 2.
 * 
 * After removing the second node from the end, the linked list becomes
 * 1->2->3->5->null.
 * 
 * speical case: 1->null and n=1; return null
 *
 */
public class RemoveNthNodeFromEndofList {

	public static void main(String[] args) {

	}

	/**
	 * 反牵涉到删除node在链表里，要想到加一个dummy node， 否则special case， 无法处理
	 * 
	 * 如： 1-
	 */
	ListNode removeNthFromEnd(ListNode head, int n) {
		if (n <= 0) {
			return null;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode p1 = dummy;
		ListNode p2 = head;

		for (int i = 0; i < n - 1; i++) {
			if (p2.next == null) {
				// the size of list < n
				return head;
			} else {
				p2 = p2.next;
			}
		}
		// NOTE: condition is p2.next != null, 如果写成p2!=null, p2最后指向最后的null节点
		while (p2.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		/**
		 * NOTE: do NOT use p1.next=p2 it doesn't work for special case: 1->null
		 * and n=1, 着这种情况下，上面所有for, while loop都没有被执行，p2还指向head（即1），p1还指向dummy
		 * node 如果你用p1.next=p2，1不会被删除的
		 */
		p1.next = p1.next.next;
		return dummy.next;
	}

}
