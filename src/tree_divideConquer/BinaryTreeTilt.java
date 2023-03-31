package tree_divideConquer;


/**
 * https://leetcode.com/problems/binary-tree-tilt/
 * leetcode: 563
 *
 * https://us.jiuzhang.com/problems/info/1172
 *
 * 
 */
public class BinaryTreeTilt {

    int ans = 0;

    public int findTiltMethod1(TreeNode root) {
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int sumLeft = dfs(node.left);
        int sumRight = dfs(node.right);
        ans += Math.abs(sumLeft - sumRight);
        return sumLeft + sumRight + node.val;
    }

    public int findTiltMethod2(TreeNode root) {
        // Write your code here
        return countTree(root)[1];
    }

    public int[] countTree(TreeNode root) {
        if(root == null) return new int[] {0,0};
        int[] l = countTree(root.left);
        int[] r = countTree(root.right);
        return new int[] {root.val + l[0] + r[0], l[1] + r[1] + Math.abs(l[0] - r[0])};
    }
}
