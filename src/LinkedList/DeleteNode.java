package LinkedList;

public class DeleteNode {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
	}

	// you are ONLY given access to the node to be deleted
	public void deleteNode(ListNode node) {

		node.val = node.next.val;
		node.next = node.next.next;

	}

}
