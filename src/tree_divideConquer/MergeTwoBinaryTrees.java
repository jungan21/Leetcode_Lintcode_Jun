package tree_divideConquer;


import java.util.Stack;

/**
 * https://leetcode.com/problems/merge-two-binary-trees/  Leetcode: 617
 *
 * https://us.jiuzhang.com/problems/info/1126
 *
 *  可以使用深度优先搜索合并两个二叉树。从根节点开始同时遍历两个二叉树，并将对应的节点进行合并。
 *
 * 两个二叉树的对应节点可能存在以下三种情况，对于每种情况使用不同的合并方式。
 *
 * 如果两个二叉树的对应节点都为空，则合并后的二叉树的对应节点也为空；
 *
 * 如果两个二叉树的对应节点只有一个为空，则合并后的二叉树的对应节点为其中的非空节点；
 *
 * 如果两个二叉树的对应节点都不为空，则合并后的二叉树的对应节点的值为两个二叉树的对应节点的值之和，此时需要显性合并两个节点。
 *
 * 对一个节点进行合并之后，还要对该节点的左右子树分别进行合并。这是一个递归的过程。
 */
public class MergeTwoBinaryTrees {
    public TreeNode mergeTreesMethod1(TreeNode root1, TreeNode root2) {

        if (root1 == null) return root2;
        if (root2 == null) return root1;

        TreeNode mergeRoot = new TreeNode(root1.val + root2.val);

        mergeRoot.left = mergeTreesMethod1(root1.left, root2.left);
        mergeRoot.right = mergeTreesMethod1(root1.right, root2.right);

        return mergeRoot;

    }

    // Jun's own version
    public TreeNode mergeTreesMethod2(TreeNode root1, TreeNode root2) {
        Stack<Character> stack = new Stack<>();

        if (root1 == null && root2 == null){
            return null;
        }

        if (root1 == null && root2 != null){
            return root2;
        }

        if (root1 != null && root2 == null){
            return root1;
        }

        TreeNode mergedRoot = new TreeNode(root1.val + root2.val);

        mergedRoot.left = mergeTreesMethod2(root1.left, root2.left);
        mergedRoot.right= mergeTreesMethod2(root1.right, root2.right);

        return mergedRoot;


    }
}
