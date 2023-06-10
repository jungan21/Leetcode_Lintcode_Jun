package LinkedList;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * leetcode 2487: https://leetcode.com/problems/remove-nodes-from-linked-list/
 *
 * https://hackmd.io/@Zihao0917/BkO_dOZwi?utm_source=preview-mode&utm_medium=rec
 */
public class RemoveNodesFromLinkedList2487 {

    //单调栈方法
    public ListNode removeNodesMethod1(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }
        Deque<Integer> stk = new ArrayDeque<>();
        for (int v : nums) {
            while (!stk.isEmpty() && stk.peek() < v) {
                stk.pop();
            }
            stk.push(v);
        }
        ListNode dummy = new ListNode(0);
        head = dummy;
        while (!stk.isEmpty()) {
            head.next = new ListNode(stk.pollLast());
            head = head.next;
        }
        return dummy.next;
    }

    public ListNode removeNodesMethod2(ListNode head) {
        head = ReverseLinkedList.reverse(head);
        ListNode prev = head, curr = head.next;
        int maxValue = head.val;
        while(curr!=null){
            if(curr.val<maxValue) prev.next = curr.next;
            else{
                maxValue = Math.max(maxValue, curr.val);
                prev = prev.next;
            }
            curr = curr.next;
        }
        return ReverseLinkedList.reverse(head);
    }



    public ListNode removeNodesRecursiveMethod3(ListNode head) {
        if(head==null) return null;
        head.next = removeNodesRecursiveMethod3(head.next);
        return head.next!=null && head.next.val>head.val ? head.next : head;
    }
}
