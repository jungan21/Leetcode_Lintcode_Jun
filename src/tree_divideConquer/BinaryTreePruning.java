package tree_divideConquer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 814. Binary Tree Pruning
 *  https://leetcode.com/problems/binary-tree-pruning/
 *  https://us.jiuzhang.com/problems/info/1003
 */
public class BinaryTreePruning {


    public TreeNode pruneTree(TreeNode root) {
        return containsOne(root) ? root : null;
    }

    public boolean containsOne(TreeNode node) {
        if (node == null) return false;
        boolean leftContainsOne = containsOne(node.left);
        boolean rightContainsOne = containsOne(node.right);
        if (!leftContainsOne) node.left = null;
        if (!rightContainsOne) node.right = null;
        return node.val == 1 || leftContainsOne || rightContainsOne;
    }

    //========================
    public TreeNode pruneTreeMethod2(TreeNode root) {
        return helper(root);
    }

    public TreeNode helper(TreeNode root){
        if (root == null) return null;

        if(root.left != null){
            if(isZeroTree(root.left)){
                root.left = null;
            } else {
                helper(root.left);
            }
        }

        if(root.right != null){
            if(isZeroTree(root.right)){
                root.right = null;
            } else {
                helper(root.right);
            }
        }
        return root;
    }

    // 层序遍历
    public boolean isZeroTree(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.val == 1) return false;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return true;
    }

}
