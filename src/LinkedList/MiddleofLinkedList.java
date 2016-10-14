package LinkedList;

public class MiddleofLinkedList {

	/**
	 * 这种写法可以保证 while循环后，fast 要么指向最后一个节点，要么指向倒数第二个节点
	 * 
	 * slow 肯定指向中点
	 */
	public ListNode middleNode(ListNode head) {
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

	/**
	 * jiuzhang 推荐， 和detect cycle写法一致
	 * 
	 * 这种写法 while循环后，fast 要么指向最后一个节点，要么指向null也就是跑出去了
	 * 
	 * slow 肯定指向中点
	 */
	public ListNode middleNode2(ListNode head) {
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
