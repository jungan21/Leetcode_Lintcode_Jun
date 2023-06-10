package TreePaths;

import LinkedList.ListNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Leetcode 1367 https://leetcode.com/problems/linked-list-in-binary-tree/
 *
 * https://levelup.gitconnected.com/java-algorithms-linked-list-in-binary-tree-leetcode-942daa409b3f
 */
public class LinkedListinBinaryTree1367 {
    public boolean isSubPath(ListNode head, TreeNode root) {
        return traverse(root, head);
    }

    private boolean traverse(TreeNode root, ListNode head) {
        // 必须这么写
        if (root == null) {
            return head == null;
        }
        if (root.val == head.val && findPath(root, head)) {
            return true;
        }
        return traverse(root.left, head) || traverse(root.right, head);
    }

    private boolean findPath(TreeNode root, ListNode head) {
        //head == null表示ListNode里的节点都遍历到了
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return root.val == head.val && (findPath(root.left, head.next) || findPath(root.right, head.next));
    }
    // Method 2 非递归方法
    public boolean isSubPathNonRecursive(ListNode head, TreeNode root) {
        int headVal = head.val;
        Queue<TreeNode> listQueue = new LinkedList<TreeNode>();
        Queue<TreeNode> treeQueue = new LinkedList<TreeNode>();
        treeQueue.offer(root);
        while (!treeQueue.isEmpty()) {
            TreeNode node = treeQueue.poll();
            if (node.val == headVal)
                // trverse tree, 把Tree里所有与ListNode头结点相等的节点 加入listQueue
                // head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
                // 上面的例子，tree里面有2个节点等于4（ListNode的head节点）所有listQueue理由有2个元素，都是4
                listQueue.offer(node);

            if (node.left != null)
                treeQueue.offer(node.left );
            if (node.right != null)
                treeQueue.offer(node.right);
        }

        //上面while循环时，已经把ListNode第一个元素比较过了，这里直接从ListNode的下一个元素开始比较
        ListNode curr = head.next;
        while (curr != null && !listQueue.isEmpty()) {
            int nextNodeVal = curr.val;
            int size = listQueue.size();
            // for 循环不能少，有分层的概念
            for (int i = 0; i < size; i++) {
                TreeNode node = listQueue.poll();
                if (node.left != null && node.left.val == nextNodeVal)
                    listQueue.offer(node.left);
                if (node.right != null && node.right.val == nextNodeVal)
                    listQueue.offer(node.right);
            }
            curr = curr.next;
        }
        return !listQueue.isEmpty();
    }
}
