package LinkedList;

public class InsertionSortList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode n1 = new ListNode(3);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(1);

		ListNode n4 = new ListNode(3);
		ListNode n5 = new ListNode(4);
		ListNode n6 = new ListNode(5);

		n1.next = n2;
		n2.next = n3;

		n1 = insertionSortList(n1);

		printList(n1);

	}

	/**
	 * better one, method 2:, 为了方便理解， 可以把helper 也就是dummy的head在纸上竖着画出来，
	 * 从顶往下慢慢插入新的节点 http://blog.csdn.net/linhuanmars/article/details/21144553
	 */
	public static ListNode insertionSortList(ListNode head) {
		if (head == null)
			return null;
		ListNode dummy = new ListNode(0);
		ListNode pre = dummy;
		
		
		ListNode cur = head;
		while (cur != null) {
			ListNode next = cur.next;
			// pre have to go back to the start point, evertime, when there is
			// new number to be added
			pre = dummy;
			// pre.next.val, 从pre.next 来判断，因为,pre每次开始之前最前面的dummy node
			while (pre.next != null && pre.next.val <= cur.val) {
				pre = pre.next;
			}
			// 即，当前处理的节点从原始链表中去除
			cur.next = pre.next;
			pre.next = cur;
			cur = next;
		}
		return dummy.next;
	}

	/**
	 * Jun's, works, but not good, 没有很好的体现insertion sort的意思
	 */
	public static ListNode insertionSortList1(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode p1 = head;
		ListNode p2 = head.next;

		ListNode runnerPre = null;
		ListNode runner = null;
		while (p2 != null) {
			if (p2.val < p1.val) {
				runnerPre = dummy;
				/**
				 * 有下面的操作，我们得知dummy里维护的是一个之前已经排好序的列表，需要用dummy.next来做runner去逐个判断，
				 * 
				 * 不能用：runner = head,
				 * 因为下面的链表操作设计删除，你会发现，下一轮循环的时候，head里面有些元素不见了，因为你每insert一个数的时候
				 * ，你其实把该元素从原始head链表的位置中上除了
				 */
				runner = dummy.next;
				while (runner != p2) {
					if (runner.val < p2.val) {
						runnerPre = runnerPre.next;
						runner = runner.next;
					} else {
						break;
					}
				}
				ListNode temp = p2.next;
				// 如果不把p1指向p2的point断掉，会形成环
				p1.next = temp;
				runnerPre.next = p2;
				p2.next = runner;
				p2 = temp;
			} else {
				p1 = p2;
				p2 = p2.next;
			}
		}
		return dummy.next;
	}

	public static void printList(ListNode x) {
		if (x != null) {
			System.out.print(x.val + " ");
			while (x.next != null) {
				System.out.print(x.next.val + " ");
				x = x.next;
			}
			System.out.println();
		}

	}

}
