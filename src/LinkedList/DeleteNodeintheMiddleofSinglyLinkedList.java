package LinkedList;

/**
 * 
 * Implement an algorithm to delete a node in the middle of a singly linked
 * list, given only access to that node.
 * 
 * Given 1->2->3->4, and node 3. return 1->2->4
 */
public class DeleteNodeintheMiddleofSinglyLinkedList {

	public void deleteNode(ListNode node) {
		if (node == null || node.next == null)
			return;

		ListNode  next = node.next;
		node.val = next.val;
		node.next = next.next;
		return;
	}

}
