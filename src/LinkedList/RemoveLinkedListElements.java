package LinkedList;

/**
 * Remove all elements from a linked list of integers that have value val.
 * 
 * 
 * Given 1->2->3->3->4->5->3, val = 3, you should return the list as 1->2->4->5
 */
public class RemoveLinkedListElements {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(1);
		n1.next = n2;
		removeElements(n1, 1);

	}

	public static ListNode removeElements(ListNode head, int val) {
		if (head == null || (head.next == null && head.val == val)) {
			return null;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode pre = dummy;
		ListNode cur = head;

		while (cur != null) {
			ListNode temp = cur.next;

			if (cur.val == val) {
				pre.next = temp;
				cur = temp;
				// note: else relation
			} else {
				pre = pre.next;
				cur = cur.next;
			}
		}
		return dummy.next;
	}

}
