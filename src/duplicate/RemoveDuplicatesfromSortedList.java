package duplicate;

public class RemoveDuplicatesfromSortedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * method 1:
	 * 
	 * 1, 2, 3, 3, 4, 4 , 5 => 1,2,3, 4, 5
	 */
	public static ListNode deleteDuplicatesWithOneCopy(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode pre = head;
		ListNode cur = head.next;

		while (cur != null) {
			if (cur.val == pre.val) {
				pre.next = cur.next;
				cur = cur.next;
			} else {
				// NOTE: can NOT set pre = pre.next
				pre = cur;
				cur = cur.next;
			}
		}
		// Note:最后返回的是head不是p1 或 p2
		return head;
	}

	// method 2: 1, 2, 3, 3, 4, 4 , 5 => 1,2,3, 4, 5
	public static ListNode deleteDuplicatesWithOneCopyJun(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode pre = head;
		ListNode cur = head.next;

		while (cur != null) {
			if (cur.val == pre.val) {
				while (cur != null && cur.val == pre.val) {
					cur = cur.next;
				}
				// cur = null or cur.val != pre.val
				pre.next = cur;
			} else {
				pre = pre.next;
				cur = cur.next;
			}
		}
		return head;
	}

}
