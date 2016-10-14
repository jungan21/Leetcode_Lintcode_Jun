package a_oa;

/**
 * 如果是偶数 1->2->3->4 ==> 1->2->4->3
 * 
 * 如果总是为奇数(中间的也要变)，1->2->3->4->5 ==> 1->2->5->4->3
 * 
 */
public class ReverseSecondHalfLinkedList {

	public static void main(String[] args) {

		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;

		ListNode reuslt = reverseSecondHalfList(n1);
		while (reuslt != null) {
			System.out.print(reuslt.val + "->");
			reuslt = reuslt.next;
		}
		System.out.println();
		ListNode reuslt1 = reverseSecondHalf(n1);
		while (reuslt1 != null) {
			System.out.print(reuslt1.val + "->");
			reuslt1 = reuslt1.next;
		}

	}

	/**
	 * http://www.aichengxu.com/view/4330298
	 * 
	 * 如果是偶数 1->2->3->4 ==> 1->2->4->3
	 * 
	 * 如果总是为奇数(中间的也要变)，1->2->3->4->5 ==> 1->2->5->4->3
	 * 
	 */

	public static ListNode reverseSecondHalfList(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		int length = getLength(head);
		int mid = length / 2;
		ListNode preMid = head;

		for (int i = 0; i < mid - 1; i++) {
			preMid = preMid.next;
		}
		System.out.println(preMid.val);
		// pre point to the second half head;
		ListNode cur = preMid.next;
		ListNode pre = null;
		// 切断前半段
		preMid.next = null;
		while (cur != null) {
			ListNode temp = cur.next;
			cur.next = pre;

			pre = cur;
			cur = temp;
		}
		preMid.next = pre;
		return head;
	}

	public static int getLength(ListNode head) {
		if (head == null) {
			return 0;
		}
		int length = 0;
		while (head != null) {
			length++;
			head = head.next;
		}
		return length;
	}

	/**
	 * 面经上的解法 http://wdxtub.com/interview/14520850399861.html
	 * 
	 * * 如果是偶数 1->2->3->4 ==> 1->2->4->3
	 * 
	 * 如果总是为奇数(注意与上面方法区别)，1->2->3->4->5 ==> 1->2->3->5->4
	 */
	public static ListNode reverseSecondHalf(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode fast = head;
		ListNode slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		ListNode pre = slow.next;
		ListNode cur = pre.next;
		while (cur != null) {
			pre.next = cur.next;
			cur.next = slow.next;
			slow.next = cur;
			cur = pre.next;
		}
		return head;
	}
}
