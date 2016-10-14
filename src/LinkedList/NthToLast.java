package LinkedList;

public class NthToLast {
	// assume the minimum number of nodes in list is n

	// Iteration from the last element to count n element
	private static LinkedListNode nthToLastIteration(LinkedListNode head, int n) {
		LinkedListNode p1 = head;
		LinkedListNode p2 = head;

		// NOTE: n < 1
		if (n < 1 || head == null) {
			return null;
		}

		for (int i = 0; i < n - 1; ++i) {
			// below condition is key point
			if (p2.next == null) {
				System.out.println("the size of the list < n");
				break;
			} else {
				p2 = p2.next;
			}
		}
		// Note: p2.next != null,
		while (p2.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
	}

	// Recursive
	static int i = 0;

	public static LinkedListNode nthToLastRecursion(LinkedListNode head, int n) {
		/*
		 * this has to be static so that it holds it's value from call to call
		 * because of the fact that a non-static variable will not hold it's
		 * value since the variable will just be local to the function if it's
		 * not static and will not be preserved across call stack:
		 */

		LinkedListNode result = head;
		// base case when we reach the end of linked list:
		if (head == null) {
			return null;
		}

		result = nthToLastRecursion(head.next, n);
		if (++i == n) {
			result = head;
		}
		return result;
	}

	public static void main(String[] args) {
		LinkedListNode node4 = new LinkedListNode(6, null);
		LinkedListNode node3 = new LinkedListNode(8, node4);
		LinkedListNode node2 = new LinkedListNode(4, node3);
		LinkedListNode node1 = new LinkedListNode(1, node2);
		System.out.println(nthToLastIteration(node1, 2).data);
	}
}

class LinkedListNode {
	int data;
	LinkedListNode next;

	LinkedListNode(int data, LinkedListNode next) {
		this.data = data;
		this.next = next;
	}
}