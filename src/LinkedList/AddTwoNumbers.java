package LinkedList;

/**
 * You have two numbers represented by a linked list, where each node contains a
 * single digit. The digits are stored in reverse order, such that the 1's digit
 * is at the head of the list. Write a function that adds the two numbers and
 * returns the sum as a linked list.
 * 
 * Given 7->1->6 + 5->9->2. That is, 617 + 295.
 * 
 * Return 2->1->9. That is 912.
 * 
 * Given 3->1->5 and 5->9->2, return 8->0->8.
 */
public class AddTwoNumbers {

	public ListNode addLists(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return null;
		}
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}

		ListNode newDummyHead = new ListNode(0);
		ListNode cur = newDummyHead;

		int carry = 0;
		// || relation, because, l1, l2的长度可能不同
		// Note: l1= l1.next, l2 = l2.next的位置一定要注意 不能放到 if条件判断的外面
		while (l1 != null || l2 != null) {
			if (l1 != null) {
				carry += l1.val;
				l1 = l1.next;
			}

			if (l2 != null) {
				carry += l2.val;
				l2 = l2.next;
			}

			cur.next = new ListNode(carry % 10);
			carry /= 10;
			cur = cur.next;

		}
		if (carry != 0) {
			cur.next = new ListNode(carry);
		}

		return newDummyHead.next;
	}

	/**
	 * follow up:
	 * 
	 * What if the digits are stored in regular order instead of reversed order?
	 * 
	 * Answer: We can simple reverse each list, then calculate the result, and
	 * reverse the result.
	 */

}
