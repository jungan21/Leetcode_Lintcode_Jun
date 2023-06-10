package LinkedList;

/**
 * You have two numbers represented by a linked list, where each node contains a
 * single digit. The digits are stored in *forward* order, such that the 1's
 * digit is at the head of the list. Write a function that adds the two numbers
 * and returns the sum as a linked li
 * 
 * Given 6->1->7 + 2->9->5. That is, 617 + 295.
 * 
 * Return 9->1->2. That is, 912.
 */
public class AddTwoNumbersII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ListNode addLists2(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return null;
		}
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}

		ListNode reversedL1 = reverse(l1);
		ListNode reversedL2 = reverse(l2);

		ListNode newDummyHead = new ListNode(0);
		ListNode cur = newDummyHead;

		int carry = 0;
		// || relation, because, l1, l2的长度可能不同
		while (reversedL1 != null || reversedL2 != null) {
			if (reversedL1 != null) {
				carry += reversedL1.val;
				reversedL1 = reversedL1.next;
			}

			if (reversedL2 != null) {
				carry += reversedL2.val;
				reversedL2 = reversedL2.next;
			}

			cur.next = new ListNode(carry % 10);
			carry /= 10;
			cur = cur.next;

		}
		if (carry != 0) {
			cur.next = new ListNode(carry);
		}
		return reverse(newDummyHead.next);
	}

	// jiuzhang 老师课堂提到 推荐
	private static ListNode reverse(ListNode head) {
		// key point
		ListNode pre = null;
		ListNode curt = head;
		while (curt != null) {
			ListNode temp = curt.next;
			curt.next = pre;

			pre = curt;
			curt = temp;
		}
		return pre;
	}

}
