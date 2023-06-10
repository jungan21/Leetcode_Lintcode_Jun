package tree_divideConquer;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Leetcode 655 https://leetcode.com/problems/print-binary-tree/
 * Lintcode: 1105
 * https://us.jiuzhang.com/problems/info/1105
 * https://walkccc.me/LeetCode/problems/0655/
 */
public class PrintBinaryTree {
    public List<List<String>> printTree_DFS(TreeNode root) {
        final int row = getHeight(root);
        final int column = (int) Math.pow(2, row) - 1;
        List<List<String>> result = new ArrayList<>();
        List<String> eachRow = new ArrayList<>();

        for (int i = 0; i < column; ++i)
            eachRow.add("");

        for (int i = 0; i < row; ++i)
            result.add(new ArrayList<>(eachRow));

        DFS(root, 0, 0, column - 1, result);
        return result;
    }
    private void DFS(TreeNode root, int row, int left, int right, List<List<String>> result) {
        if (root == null)
            return;

        final int mid = (left + right) / 2;
        result.get(row).set(mid, Integer.toString(root.val));
        DFS(root.left, row + 1, left, mid - 1, result);
        DFS(root.right, row + 1, mid + 1, right, result);
    }
//=====================================================================================
    public List<List<String>> printTree_BFS(TreeNode root) {
        final int row = getHeight(root);
        final int column = (int) Math.pow(2, row) - 1;
        List<List<String>> result = new ArrayList<>();
        List<String> eachRow = new ArrayList<>();

        for (int i = 0; i < column; ++i)
            eachRow.add("");

        for (int i = 0; i < row; ++i)
            result.add(new ArrayList<>(eachRow));

        BFS(root, 0, 0, column - 1, result);
        return result;
    }

    public void BFS(TreeNode root, int row, int l, int r, List<List<String>> result) {
        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(root, 0, 0, result.get(0).size()));
        while (!queue.isEmpty()) {
            Element p  = queue.poll();
            result.get(p.row).set((p.l + p.r) / 2, ""  + p.node.val);
            if (p.node.left != null)
                queue.add(new Element(p.node.left, p.row + 1, p.l, (p.l + p.r) / 2));
            if (p.node.right != null)
                queue.add(new Element(p.node.right, p.row + 1, (p.l + p.r + 1) / 2, p.r));
        }
    }

    class Element {
        Element(TreeNode n, int rowNum, int left, int right) {
            node = n;
            row = rowNum;
            l = left;
            r = right;
        }
        TreeNode node;
        int row, l, r;
    }

    public int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
