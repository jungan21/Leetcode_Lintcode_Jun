package duplicate;

public class RemoveDuplicatesfromSortedListII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * jiuzhang, 老师课堂推荐的
	 * 
	 * 1, 2, 3, 3, 4, 4 , 5 => 1, 2, 5
	 */

	public static ListNode deleteDuplicates(ListNode head) {
		if (head == null)
			return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode pre = dummy;
		ListNode cur = head;
		while (cur != null) {
			// cur.next != null 必须要做次判断，因为如果cur.next 是null的话， cur.next.val 将报错
			if (cur.next != null && cur.val == cur.next.val) {
				int val = cur.val;
				while (cur != null && cur.val == val) {
					cur = cur.next;
				}
				// after while loop, cur = null OR cur.val != val;
				pre.next = cur;
			} else {
				pre = cur;
				cur = cur.next;
			}
		}
		return dummy.next;
	}

	/**
	 * method 2： 根据上面的方法稍作改动
	 */
	public static ListNode deleteDuplicates1(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode pre = dummy;
		ListNode cur = head;

		while (cur != null) {
			if (cur.next != null && cur.val == cur.next.val) {
				while (cur.next != null && cur.val == cur.next.val) {
					cur = cur.next;
				}
				// cur.next = null or cur.val != cur.next.val
				pre.next = cur.next;
				cur = cur.next;
			} else {
				pre = pre.next;
				cur = cur.next;
			}
		}
		return dummy.next;
	}

	/**
	 * Jun's old way
	 * 
	 * 1, 2, 3, 3, 4, 4 , 5 => 1, 2, 5
	 */
	public static ListNode deleteDuplicatesOld(ListNode head) {
		if (head == null)
			return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode pre = dummy;
		ListNode cur = head;
		while (cur != null) {
			/**
			 * 关键是pre始终固定住，不管cur有多少重复，最后while loop后，都会指向最后一个重复数字
			 * 
			 * Note: 不能用 cur.next=cur.next.next来判断，
			 * 
			 * 因为在loop里，cur本身也是一直移动的，只有依靠pre固定位置才能判断
			 * 
			 * cur.next != null必须做判断
			 */
			while (cur.next != null && pre.next.val == cur.next.val) {
				cur = cur.next;
			}
			// that means above while loop doesn't get chance to run
			if (pre.next == cur) {
				pre = pre.next;
				cur = cur.next;
			} else {
				// 如果有重复，cur出来内循环后，指向最后一个重复的数字
				pre.next = cur.next;
				cur = cur.next;
			}
		}
		return dummy.next;
	}

}
