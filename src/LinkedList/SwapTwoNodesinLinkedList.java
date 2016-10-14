package LinkedList;

/**
 * 
 * Given a linked list and two values v1 and v2. Swap the two nodes in the
 * linked list with values v1 and v2. It's guaranteed there is no duplicate
 * values in the linked list. If v1 or v2 does not exist in the given linked
 * list, do nothing.
 *
 *
 * Given 1->2->3->4->null and v1 = 2, v2 = 4.
 * 
 * Return 1->4->3->2->null.
 */
public class SwapTwoNodesinLinkedList {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(10);
		ListNode n2 = new ListNode(8);
		ListNode n3 = new ListNode(7);
		ListNode n4 = new ListNode(6);
		ListNode n5 = new ListNode(4);
		ListNode n6 = new ListNode(3);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		swapNodes(n1, 8, 10);
	}

	public static ListNode swapNodes(ListNode head, int v1, int v2) {
		if (head == null) {
			return null;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode pre = dummy;
		ListNode p = head;

		ListNode preV1 = null;
		ListNode pV1 = null;
		ListNode preV2 = null;
		ListNode pV2 = null;

		while (p != null) {
			if (p.val == v1) {
				preV1 = pre;
				pV1 = p;
			}
			if (p.val == v2) {
				preV2 = pre;
				pV2 = p;
			}
			pre = pre.next;
			p = p.next;
		}

		if (pV1 != null && pV2 != null) {
			ListNode pV1Next = pV1.next;
			ListNode pV2Next = pV2.next;
			pV1.next = null;
			pV2.next = null;

			// 如果不做此判断， 会形成环
			if (pV2 != preV1 && pV1 != preV2) {
				preV1.next = pV2;
				pV2.next = pV1Next;

				preV2.next = pV1;
				pV1.next = pV2Next;
				// 下面else里面是考虑, v1, v2两个节点相邻的时候
			} else {
				if (pV2 == preV1) {
					pV1.next = pV2;
					pV2.next = pV1Next;

					preV2.next = pV1;
				}
				if (pV1 == preV2) {
					pV2.next = pV1;
					pV1.next = pV2Next;

					preV1.next = pV2;
				}
			}
		}
		return dummy.next;
	}
}
