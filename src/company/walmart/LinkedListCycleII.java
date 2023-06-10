package company.walmart;

import LinkedList.ListNode;

/**
 * Given a linked list, return the node where the cycle begins.
 * 
 * If there is no cycle, return null
 * 
 * Given -21->10->4->5, tail connects to node index 1，return 10
 */
public class LinkedListCycleII {

	public static void main(String[] args) {

	}

	// http://www.cnblogs.com/hiddenfox/p/3408931.html
	// http://www.cnblogs.com/springfor/p/3862125.html
	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}

		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (fast == slow) {
				break; // 第一次相遇在Z点
			}
		}

		// means there is cycle
		ListNode find = head;
		if (fast == slow) {
			while (find != fast) { // 二者相遇在Y点，则退出
				find = find.next;
				fast = fast.next;
			}
			return find;
		}

		// means no cycle
		return null;
	}

}
