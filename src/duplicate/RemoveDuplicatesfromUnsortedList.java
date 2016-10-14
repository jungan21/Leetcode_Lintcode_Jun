package duplicate;

import java.util.HashSet;

/**
 * 
 * 1->3->2->1->4. ==> 1->3->2->4
 *
 */
public class RemoveDuplicatesfromUnsortedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/**
	 * O(n^2)
	 * 
	 * 1->3->2->1->4. ==> 1->3->2->4
	 */
	public ListNode removeDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode pre = head;
		ListNode cur = head.next;

		while (cur != null) {
			ListNode runner = head;
			while (runner != cur && runner.val != cur.val) {
				runner = runner.next;
			}
			// after while loop, runner == cur OR runner.val == cur.val

			// means no duplicate
			if (runner == cur) {
				pre = pre.next;
				cur = cur.next;
			} else {
				ListNode temp = cur.next;
				pre.next = temp;
				cur = temp;
			}
		}
		return head;
	}

	/**
	 * method 2, O(n) with extra space,
	 * 
	 * 1->3->2->1->4. ==> 1->3->2->4
	 */
	public static ListNode removeDuplicatesJun(ListNode head) {
		if (head == null) {
			return null;
		}
		HashSet<Integer> set = new HashSet<>();
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode cur = head;
		while (cur != null) {
			if (!set.contains(cur.val)) {
				set.add(cur.val);
				pre = pre.next;
				cur = cur.next;
			} else {
				ListNode next = cur.next;
				pre.next = next;
				cur = next;
			}
		}
		return dummy.next;
	}

	/**
	 * O(n) jiuzhang O(n) with extra space,
	 */
	public ListNode removeDuplicatesJiuzhang(ListNode head) {
		HashSet<Integer> hash = new HashSet<Integer>();

		ListNode dummy = new ListNode(0);
		dummy.next = head;
		head = dummy;
		while (head.next != null) {
			if (hash.contains(head.next.val)) {
				head.next = head.next.next;
			} else {
				hash.add(head.next.val);
				head = head.next;
			}
		}

		return dummy.next;
	}

	/**
	 * method 3 : O(nlogn + n) without Space, we can also sort the list and
	 * remove duplicate
	 */
	public ListNode removeDuplicatesWithNoSpace(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode sortedList = mergeSortList(head);
		ListNode pre = sortedList;
		ListNode cur = sortedList.next;
		while (cur != null) {
			if (cur.val == pre.val) {
				while (cur != null && cur.val == pre.val) {
					cur = cur.next;
				}
				// cur = null or cur.val != pre.val
				pre.next = cur;
			} else {
				pre = pre.next;
			}
			cur = cur.next;
		}
		return sortedList;
	}

	private ListNode mergeSortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode mid = findMid(head);

		ListNode secondHead = mid.next;
		mid.next = null;

		// divide
		ListNode left = mergeSortList(head);
		ListNode right = mergeSortList(secondHead);

		// conquer
		ListNode mergedList = merge2List(left, right);
		return mergedList;
	}

	public ListNode merge2List(ListNode head1, ListNode head2) {
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

	private ListNode findMid(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode slow = head;
		ListNode fast = head;

		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
}
