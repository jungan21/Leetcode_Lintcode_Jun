package TreePaths;


/**
 * Leetcode 404: https://leetcode.com/problems/sum-of-left-leaves/
 *
 * https://us.jiuzhang.com/problems/info/1254
 */
public class SumofLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        return helper(root);
    }

    public int helper(TreeNode node) {
        if (node == null) return 0;

        // Note: 每次重置sum
        int sum = 0;
        if (node.left != null) {
            if (isLeafNode(node.left)){
                sum = sum + node.left.val;
            } else {
                sum = sum + helper(node.left);
            }
        }

        if (node.right != null) {
            sum = sum + helper(node.right);
        }
        return sum;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
