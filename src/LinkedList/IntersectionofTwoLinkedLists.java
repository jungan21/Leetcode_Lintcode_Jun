package LinkedList;

/**
 * Write a program to find the node at which the intersection of two singly
 * linked lists begins.
 * 
 * Your code should preferably run in O(n) time and use only O(1) memory.
 */
public class IntersectionofTwoLinkedLists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Best method 2, cycle 思路
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}

		// get the tail of list A.
		ListNode node = headA;
		while (node.next != null) {
			node = node.next;
		}

		node.next = headB;
		ListNode result = listCycleII(headA);
		// 上边为了方便算法计算，人工造了个环，现在去掉，因为结果要求不能改变原来链表的结构
		node.next = null;
		return result;
	}

	private ListNode listCycleII(ListNode head) {
		ListNode slow = head, fast = head.next;

		while (slow != fast) {
			if (fast == null || fast.next == null) {
				return null;
			}

			slow = slow.next;
			fast = fast.next.next;
		}

		slow = head;
		fast = fast.next;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow;
	}

	/**
	 * method 1:
	 * 
	 * https://www.youtube.com/watch?v=gE0GopCq378
	 * 
	 */
	public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
		if (headA == null || headA == null)
			return null;

		ListNode p1 = headA, p2 = headB;
		int lenA = 0, lenB = 0;
		while (p1 != null) {
			lenA++;
			p1 = p1.next;
		}
		while (p2 != null) {
			lenB++;
			p2 = p2.next;
		}

		int diff = (lenA > lenB) ? lenA - lenB : lenB - lenA;
		p1 = headA;
		p2 = headB;
		if (lenA > lenB) {
			for (int i = 0; i < diff; i++) {
				p1 = p1.next;
			}
		} else {
			for (int i = 0; i < diff; i++) {
				p2 = p2.next;
			}
		}

		while (p1 != null && p2 != null) {
			if (p1.val == p2.val) {
				return p1;
			}
			p1 = p1.next;
			p2 = p2.next;
		}
		return null;
	}

	/**
	 * method 3, Wrong, Jun's
	 */
	public ListNode getIntersectionNodeJun(ListNode headA, ListNode headB) {
		if (headA == null && headB == null) {
			return null;
		} else if (headA == null) {
			return null;
		} else if (headB == null) {
			return null;
		}

		// corner case
		if (headA == headB) {
			return headA;
		}

		ListNode A = reverse(headA);
		ListNode B = reverse(headB);

		ListNode dummy = new ListNode(0);
		dummy.next = A;

		ListNode pre = dummy;

		while (A != null && B != null && A == B) {
			A = A.next;
			B = B.next;
			pre = pre.next;
		}
		return pre;
	}

	private ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode pre = null;
		ListNode cur = head;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = pre;

			pre = cur;
			cur = next;
		}
		return pre;
	}

}
