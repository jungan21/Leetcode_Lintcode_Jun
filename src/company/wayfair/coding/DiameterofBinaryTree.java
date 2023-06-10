package company.wayfair.coding;

import TreePaths.TreeNode;

/**
 *
 * Leetcode: 543
 * https://leetcode.com/problems/diameter-of-binary-tree/
 */

// 与LongestPathAny2Any类似，不过这里Diameter算的是边数，LongestPathAny2Any算的是节点数
public class DiameterofBinaryTree {

    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;

        int lheight = height(root.left);
        int rheight = height(root.right);

        int ldiameter = diameterOfBinaryTree(root.left);
        int rdiameter = diameterOfBinaryTree(root.right);

        /* Return max of following three
          1) Diameter of left subtree
          2) Diameter of right subtree
          3) Height of left subtree + height of right subtree + 1
         */
        return Math.max(lheight + rheight + 1, Math.max(ldiameter, rdiameter)) -1 ;
    }
    static int height(TreeNode node){
        if (node == null)
            return 0;

        // If tree is not empty then height = 1 + max of left height and right heights
        return (1  + Math.max(height(node.left), height(node.right)));
    }

}