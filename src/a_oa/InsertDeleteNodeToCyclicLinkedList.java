package a_oa;

/**
 * http://www.geeksforgeeks.org/sorted-insert-for-circular-linked-list/
 * 
 * https://www.careercup.com/question?id=9613747
 *
 *
 * 这个我主要说一下。 我遇到的是一道是INSERT INTO CYCLIC LINKED LIST，上面第一个链接的面经里提到过， 再给大家一个链接。
 * http://www.geeksforgeeks.org/sorted-insert-for-circular-linked-list/
 * 实际的题有一点不一样的是它每次给你的HEAD不一定是链表最小那个， 所以稍微改一点就行了。
 * 
 * 注意： insert node 那题的确是返回新插入的节点。
 */

public class InsertDeleteNodeToCyclicLinkedList {

	/**
	 * 插入一个新的节点到一个sorted cycle linkedlist，返回新的节点。给的list节点不一定是最小节点所以先要找到最小的点,
	 * amazon OA2 传入进来的head不一定指向最小的那个节点
	 * 
	 * 
	 * 真题 http://wdxtub.com/interview/14520850399861.html
	 * 
	 * http://www.geeksforgeeks.org/sorted-insert-for-circular-linked-list/
	 */
	public ListNode insert(ListNode head, int val) {
		if (head == null) {
			ListNode newNode = new ListNode(val);
			newNode.next = newNode;
			return newNode;
		}
		ListNode cur = head;
		// 或者直接写成 while(true)也可以
		do {
			if (val >= cur.val && val <= cur.next.val) {
				break;
			}
			// cur.val > cur.next.val 表示 cur.next就是最小的值
			if (cur.val > cur.next.val && (val < cur.next.val || val > cur.val)) {
				break;
			}
			cur = cur.next;
		} while (cur != head);

		ListNode newNode = new ListNode(val);
		newNode.next = cur.next;
		cur.next = newNode;
		return newNode;
	}

	// Utility method to print a linked list
	void printList(ListNode node) {
		if (node != null) {
			ListNode temp = node;
			do {
				System.out.print(temp.val + " ");
				temp = temp.next;
			} while (temp != node);
		}
	}

	public static void main(String[] args) {

		// Creating the linkedlist
		int arr[] = new int[] { 2, 5, 8, 10 };

		ListNode head = new ListNode(2);
		ListNode cur = head;

		// Created linked list will be: 2->5->8->10->2
		for (int i = 1; i < 4; i++) {
			cur.next = new ListNode(arr[i]);
			cur = cur.next;
		}
		cur.next = head;

		InsertDeleteNodeToCyclicLinkedList test = new InsertDeleteNodeToCyclicLinkedList();
		test.printList(test.insert(head.next, 11));
	}

}
