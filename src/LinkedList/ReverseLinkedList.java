package LinkedList;

public class ReverseLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		n1.next = n2;
		n2.next = n3;
		reverse(n1);
		System.out.println(new ReverseLinkedList().reverseList(n1).val);

	}

	/**
	 * jiuzhang 老师课堂提到 推荐
	 */

	public static ListNode reverse(ListNode head) {
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

	/**
	 * Jun's old way... not recommended
	 */
	public static ListNode reverseList(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode p1 = head;
		ListNode p2 = head.next;

		/**
		 * NOTE: if you don't add *head.next= null*, you will hit
		 * "memory limit exceed"exception
		 * 
		 * head = null doesn't work
		 */
		head.next = null;
		while (p1 != null && p2 != null) {
			ListNode temp = p2.next;
			p2.next = p1;
			// 注意不能加上下面的部分
			// p1.next = temp;
			p1 = p2;
			p2 = temp;
		}
		// after above while loop, p2=null, p1指向最后的节点
		return p1;
	}

	/**
	 * recursive
	 * http://www.programcreek.com/2014/05/leetcode-reverse-linked-list-java/
	 */
	public ListNode reverseListRecursive(ListNode head) {
		if (head == null || head.next == null)
			return head;

		// we grab the second element (which will be the last after we
		// reverse it)
		ListNode second = head.next;
		// Divide: we reverse everything from the second element on
		ListNode result = reverseListRecursive(second);

		// Conquer: then we join the two lists
		second.next = head;
		head.next = null;
		/**
		 * need to unlink list from the rest or you will get a cycle
		 * 
		 * you can also put this sentence after the recursive call, doesn't
		 * matter
		 */
		// result is the node actually point to the end of the LinkedList
		return result;
	}

}
