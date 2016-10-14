package LinkedList;

// Given 1->2->3->4, you should return the list as 2->1->4->3.
public class SwapNodeInPair {

	// http://www.jiuzhang.com/solutions/swap-nodes-in-pairs/
	public static ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode p = dummy;
		// 先要确定，接下来要swap的两个node都不是null
		while (p.next != null && p.next.next != null) {
			// NOTE: 循环里面赋值给n1, n2
			ListNode n1 = p.next;
			ListNode n2 = p.next.next;

			// p->n1->n2->...
			// => p->n2->n1->...
			ListNode temp = n2.next;

			p.next = n2;
			n2.next = n1;
			n1.next = temp;

			// move to next pair
			p = n1;
		}
		return dummy.next;
	}

	/**
	 * method 2 - Jun
	 * 
	 * @param args
	 */
	public ListNode swapPairs2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode p = dummy;
		ListNode n1 = head;
		ListNode n2 = head.next;

		while (n2 != null) {
			ListNode temp = n2.next;
			n2.next = n1;
			n1.next = temp;
			p.next = n2;

			p = n1;
			if (p.next == null || p.next.next == null) {
				break;
			} else if (p.next != null && p.next.next != null) {
				n1 = p.next;
				n2 = p.next.next;
			}
		}
		return dummy.next;
	}

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;

		ListNode n = swapPairs(n1);
		while (n != null) {
			System.out.println(n.val);
			n = n.next;
		}

	}

}
