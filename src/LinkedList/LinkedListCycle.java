package LinkedList;

/**
 * Given a linked list, determine if it has a cycle in it.
 * 
 * Given -21->10->4->5, tail connects to node index 1，return 10
 */
public class LinkedListCycle {

	public static void main(String[] args) {

	}

	/**
	 * 
	 * http://www.cnblogs.com/hiddenfox/p/3408931.html
	 * http://www.cnblogs.com/wuyuegb2312/p/3183214.html
	 * 
	 * 复杂度O(n)的方法，使用两个指针slow,fast。两个指针都从表头开始走，slow每次走一步，fast每次走两步，
	 * 
	 * 如果fast遇到null，则说明没有环，返回false；
	 * 
	 * 如果slow==fast，说明有环(因为如果没有环的情况下，由于fast和slow的起点相同，fast的速度比slow快，两者是永远不可能相遇的，
	 * 只有有环的情况，才有可能相等)，并且此时fast超了slow一圈，返回true。
	 * 
	 * 为什么有环的情况下二者一定会相遇呢
	 * ？因为fast先进入环，在slow进入之后，如果把slow看作在前面，fast在后面每次循环都向slow靠近1，所以一定会相遇
	 * ，而不会出现fast直接跳过slow的情况。
	 * 
	 */

	// http://www.programcreek.com/2012/12/leetcode-linked-list-cycle/
	public boolean detectCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}
		ListNode fast = head;
		ListNode slow = head;

		// you can also write this way: while (fast.next!= null && fast.next.next != null)
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast)
				return true;
		}
		return false;
	}

	/**
	 * jiuzhang: http://www.jiuzhang.com/solutions/middle-of-linked-list/
	 */
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}

		ListNode slow = head;
		ListNode fast = head.next;

		while (slow != fast) {
			if (fast == null || fast.next == null) {
				return false;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		return true;
	}

}
