package tree_divideConquer;

/**
 * Leetcode 654: https://leetcode.com/problems/maximum-binary-tree/
 */
public class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // note: end = nums.length -1
        return helper(nums, 0, nums.length-1);
    }

    public TreeNode helper(int[] nums, int start, int end){
        // note this condition, has to be ">". "=" doesn't work
        if (start > end){
            return null;
        }

        int maxIndex = findMaxIndex(nums, start, end);
        TreeNode root = new TreeNode(nums[maxIndex]);

        root.left = helper(nums, start, maxIndex - 1);
        root.right = helper(nums, maxIndex+1, end);

        return root;
    }

    public int findMaxIndex (int[] nums, int start, int end){
        int maxIndex = start;
        for (int i = start + 1;  i <= end; i++){
            if (nums[i] > nums[maxIndex]){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
