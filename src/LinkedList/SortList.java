package LinkedList;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 */
public class SortList {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(-1);
		n1.next = n2;
		sortList1(n1);
	}

	/**
	 * PriorityQueue 该方法，在Lintcode 和 Leetcode上都过不了
	 */
	public static ListNode sortList1(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(1,
				new Comparator<ListNode>() {
					public int compare(ListNode n1, ListNode n2) {
						return n1.val - n2.val;
					}
				});

		while (head != null) {
			minHeap.offer(head);
			head = head.next;
		}

		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while (!minHeap.isEmpty()) {
			cur.next = minHeap.poll();
			cur = cur.next;
		}
		return dummy.next;
	}

	/**
	 * merge sort - Jun 老师推荐
	 * 
	 * method 1
	 * 
	 */
	public static ListNode sortList(ListNode head) {
		// base case
		if (head == null || head.next == null) {
			return head;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode secondHead = slow.next;
		slow.next = null;

		// divide
		ListNode left = sortList(head);
		ListNode right = sortList(secondHead);

		// conquer
		ListNode mergedList = merge2List(left, right);
		return mergedList;
	}

	public static ListNode merge2List(ListNode head1, ListNode head2) {
		if (head1 == null && head2 == null) {
			return null;
		} else if (head1 == null) {
			return head2;
		} else if (head2 == null) {
			return head1;
		}

		ListNode newDummyHead = new ListNode(0);
		ListNode cur = newDummyHead;
		while (head1 != null && head2 != null) {
			if (head1.val < head2.val) {
				cur.next = head1;
				head1 = head1.next;
			} else {
				cur.next = head2;
				head2 = head2.next;
			}
			cur = cur.next;
		}

		if (head1 != null) {
			cur.next = head1;
		}
		if (head2 != null) {
			cur.next = head2;
		}
		return newDummyHead.next;
	}

	/**
	 * quick sort: http://www.jiuzhang.com/solutions/sort-list/
	 */
}
