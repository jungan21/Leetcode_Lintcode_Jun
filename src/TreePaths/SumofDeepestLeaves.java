package TreePaths;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Leetcode 1302: https://leetcode.com/problems/deepest-leaves-sum/
 *
 * https://dev.to/seanpgallivan/solution-deepest-leaves-sum-1936
 */
public class SumofDeepestLeaves {

    public int deepestLeavesSumBFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int sum = 0, qlen = 0;
        while (queue.size() > 0) {
            // Note: sum inside of while loop, 不停的重置sum, 最后一次while循环，就是计算的最深的一层的节点
            sum = 0;
            for (int i = 0; i < queue.size(); i++) {
                TreeNode curr = queue.poll();
                sum += curr.val;
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }
        return sum;
    }


    public int deepestLeavesSumDFS1(TreeNode root) {
        List<List<Integer>> levelList = new ArrayList<>();
        dfs1(root, 0, levelList);
        System.out.println(levelList);
        int sum = 0;
        for (Integer number : levelList.get(levelList.size()-1)){
            sum += number;
        }
        return sum;
    }
    public void dfs1(TreeNode node, int level, List<List<Integer>> levelList) {
        // e.g. node 为root 的时候， level = 0 , levelList.size()=0
        if (level == levelList.size()) {
            levelList.add(new ArrayList<>());
        }
        levelList.get(level).add(node.val);

        if (node.left != null) dfs1(node.left, level+1, levelList);
        if (node.right != null) dfs1(node.right, level+1, levelList);
    }

    public int deepestLeavesSumDFS2(TreeNode root) {
        // sum of each level
        List<Integer> sums = new ArrayList<>();
        dfs2(root, 0, sums);
        return sums.get(sums.size()-1);
    }
    public void dfs2(TreeNode node, int level, List<Integer> sums) {
        if (level == sums.size()) {
            sums.add(node.val);
        } else {
            sums.set(level, sums.get(level) + node.val);
        }

        if (node.left != null) dfs2(node.left, level+1, sums);
        if (node.right != null) dfs2(node.right, level+1, sums);
    }
}
