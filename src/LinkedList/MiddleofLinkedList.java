package LinkedList;


/**
 * Leetcode 876: https://leetcode.com/problems/middle-of-the-linked-list/
 */
public class MiddleofLinkedList {

	// while循环后，fast 要么指向最后一个节点，要么指向倒数第1个节点.
	// slow 肯定指向第1个中点  Input: head = [1,2,3,4,5,6] Output: [3,4,5,6]
	public ListNode firstMiddleNode(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode slow = head;
		ListNode fast = head;

		while (fast.next != null && fast.next.next != null){
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

	 // while循环后，fast 要么指向最后一个节点，要么指向倒数第1个节点.
	 // slow 肯定指向第2个中点  Input: head = [1,2,3,4,5,6] Output: [4,5,6]
	public ListNode SecondMiddleNode(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode slow = head;
		ListNode fast = head;
		while (fast!= null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}


	 // jiuzhang 推荐， 和detect cycle写法一致
	 // while循环后，fast 要么指向最后一个节点，要么指向null也就是跑出去了
	 // slow 肯定指向第1个中点  Input: head = [1,2,3,4,5,6] Output: [3,4,5,6]
	public ListNode firstMiddleNodeMethod2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode slow = head;
		ListNode fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

}
