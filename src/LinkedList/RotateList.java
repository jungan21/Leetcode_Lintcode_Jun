package LinkedList;

/**
 * Given a list, rotate the list to the right by k places, where k is
 * non-negative.
 * 
 * Given 1->2->3->4->5 and k = 2, return 4->5->1->2->3.
 */
public class RotateList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Jun's latest
	 */
	public ListNode rotateRight(ListNode head, int k) {
		if (head == null || k < 1) {
			return head;
		}
		int length = getLength(head);
		//不能省略
		if (k >= length) {
			k = k % length;
			if (k <= 0) {
				return head;
			}
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode p1 = head;
		ListNode p2 = head;
		//下面的这部分和  NthToLast.java的意思一样，找从右往左数第Kth node, 结果就是 p1
		for (int i = 0; i < k - 1; i++) {
			p2 = p2.next;
		}

		while (p2.next != null) {
			pre = pre.next;
			p1 = p1.next;
			p2 = p2.next;
		}

		pre.next = null;
		p2.next = head;
		return p1;
	}

	public ListNode rotateRightOld(ListNode head, int k) {
		if (head == null || head.next == null || k <= 0) {
			return head;
		}
		int length = getLength(head);

		if (k >= length) {
			k = k % length;
			// Note: 需要再判断一次，
			if (k <= 0) {
				return head;
			}
		}

		ListNode firstTail = head;
		for (int i = 0; i < (length - k) - 1; i++) {
			firstTail = firstTail.next;
		}
		// secondPart 的head将会是rotated后的总的整体的List的head
		ListNode secondPart = firstTail.next;
		firstTail.next = null;

		/**
		 * Note: 因为secondPart将会是rotated后的总的整体的List的head：
		 * 
		 * 所以，拼接secondPart与之前的的旧的head,必须要重新定义一个变量，让其跑到secondPart的末尾去
		 */
		ListNode cur = secondPart;
		while (cur.next != null) {
			cur = cur.next;
		}
		cur.next = head;
		return secondPart;
	}

	private int getLength(ListNode head) {
		int length = 0;
		while (head != null) {
			length++;
			head = head.next;
		}
		return length;
	}
}
