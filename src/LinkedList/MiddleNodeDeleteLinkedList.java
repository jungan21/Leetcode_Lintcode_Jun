package LinkedList;


/**
 * Leetcode 2095 https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 *  Input: head = [1,3,4,7,1,2,6]  Output: [1,3,4,1,2,6]
 *  Input: head = [1,2,3,4] Output: [1,2,4] (偶数时，2个middle, delete 2nd middle i.e. 3)
 */
public class MiddleNodeDeleteLinkedList {
    public ListNode deleteMiddle(ListNode head) {

        // to delete the 2nd middle, need to find the previous one node before 2nd middle node
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode cur = dummy;

        ListNode slow = head;
        ListNode fast = head;
        while (fast!= null && fast.next != null) {
            cur = cur.next; // used to find the previous node the middle node
            slow = slow.next;
            fast = fast.next.next;
        }

        cur.next = slow.next;

        return dummy.next;

    }
}
