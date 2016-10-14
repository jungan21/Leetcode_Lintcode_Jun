package LinkedList;

public class ReverseDoubleLinkedList {

	public static void main(String[] args) {
		DoubleListNode n1 = new DoubleListNode(10);
		DoubleListNode n2 = new DoubleListNode(8);
		DoubleListNode n3 = new DoubleListNode(4);
		DoubleListNode n4 = new DoubleListNode(2);
		n1.next = n2;
		n2.prev = n1;

		n2.next = n3;
		n3.prev = n2;

		n3.next = n4;
		n4.prev = n3;

		reverse(n1);

	}

	public static void reverse(DoubleListNode head) {

		DoubleListNode temp = null;
		DoubleListNode current = head;

		/*
		 * swap next and prev for all nodes of doubly linked list
		 */
		while (current != null) {
			temp = current.prev;
			current.prev = current.next;
			current.next = temp;
			current = current.prev;
		}

		/*
		 * Before changing head, check for the cases like empty list and list
		 * with only one node
		 */
		if (temp != null) {
			head = temp.prev;
		}

	}

}
