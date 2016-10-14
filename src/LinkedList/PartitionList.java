package LinkedList;

/**
 * Given a linked list and a value x, partition it such that all nodes less than
 * x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the
 * two partitions.
 * 
 *
 * Given 1->4->3->2->5->2->null and x = 3, return 1->2->2->4->3->5->null.
 *
 */
public class PartitionList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(4);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(2);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(2);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;

		ListNode node = partition(n1, 2);
		while (node != null && node.next != null) {
			System.out.print(node.val + "->");
			node = node.next;
		}
		System.out.print(node.val);
	}

	public static ListNode partition(ListNode head, int x) {
		if (head == null) {
			return null;
		}

		// create two dummy list: left and right
		ListNode leftDummy = new ListNode(0);
		ListNode rightDummy = new ListNode(0);
		// left, right are nodes to moving
		ListNode left = leftDummy, right = rightDummy;

		while (head != null) {
			// Note: x is int val
			if (head.val < x) {
				left.next = head;
				// left = head can also be replaced by left = left.next;
				left = head;
			} else {
				right.next = head;
				// right = head can also be replaced by right = right.next;
				right = head;
			}
			head = head.next;
		}

		right.next = null;
		// rightDummy.next.. not right.next..because we keep moving right
		left.next = rightDummy.next;
		return leftDummy.next;
	}

}
