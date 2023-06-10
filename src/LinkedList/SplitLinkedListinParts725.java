package LinkedList;

/**
 * Leetcode 725 https://leetcode.com/problems/split-linked-list-in-parts/
 */
public class SplitLinkedListinParts725 {
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode cur = head;
        int size = 0;
        while(cur != null) {
            size++;
            cur = cur.next;
        }

        ListNode result[] = new ListNode[k];

        // s 数组里每个元素 表示 每个链表里元素个数
        // example 1: [1,2,3], k = 5,  s = {1, 1, 1, 0, 0}
        // example 2: head = [1,2,3,4,5,6,7,8,9,10], k = 3,  s = {4, 3, 3}
        int s[] = new int[k];
        int c = 0;
        // 给S数组赋值逻辑很关键，分k组，相当于k个bucket, 原始链表里所有元素，挨个逐个的放入这些bucket里
        while(size > 0) {
            size--;
            s[c % k]++;
            c++;

        }

        cur = head;
        int index = 0;
        // num代表每个链表里元素个数
        for(int num : s) {
            ListNode subListHead = null;
            ListNode curNodeofSubList = null;
            //build each sub-List
            for (int i = 0; i < num; i++) {
                if(subListHead == null) {
                    subListHead = new ListNode(cur.val);
                    curNodeofSubList = subListHead;
                } else {
                    curNodeofSubList.next = new ListNode(cur.val);
                    curNodeofSubList = curNodeofSubList.next;
                }
                cur = cur.next;
            }

            result[index++] = subListHead;
        }

        return result;

    }
}
