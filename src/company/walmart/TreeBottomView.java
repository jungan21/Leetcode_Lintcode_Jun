package company.walmart;

import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;

// https://www.geeksforgeeks.org/bottom-view-binary-tree/
// diagram: https://blog.csdn.net/cumtb2009/article/details/107792628

// Tree node class
class TreeNodeWithHD {
    int data; //data of the node
    int hd; //horizontal distance of the node, root node is 0
    TreeNodeWithHD left, right; //left and right references

    public TreeNodeWithHD(int key) {
        data = key;
        hd = Integer.MAX_VALUE;
        left = right = null;
    }
}

public class TreeBottomView {

    // Method that prints the bottom view.
    public static void bottomView(TreeNodeWithHD root) {
        if (root == null)
            return;
        int hd = 0; // root node horizontal distance

        // TreeMap Key: node horizontal distance (root is 0)
        // TreeMap Value: actual node value, we need it to print the final output result
        Map<Integer, Integer> map = new TreeMap<>();

        // Queue to store tree nodes in level order traversal
        Queue<TreeNodeWithHD> queue = new LinkedList<TreeNodeWithHD>();
        root.hd = hd;
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNodeWithHD cur = queue.poll();
            hd = cur.hd;

            // Diagram clearly explain why: https://blog.csdn.net/cumtb2009/article/details/107792628
            // key is horizontal distance.
            // Trick part: Every time we find a node having same horizontal distance we need to replace the data in the map.
            map.put(hd, cur.data);

            // If cur node has a left child add it to the queue with a horizontal distance hd-1.
            if (cur.left != null) {
                cur.left.hd = hd-1;
                queue.add(cur.left);
            }
            // If cur  node has a right child add it to the queue with a horizontal distance hd+1.
            if (cur.right != null) {
                cur.right.hd = hd+1;
                queue.add(cur.right);
            }
        }

        for (Entry<Integer, Integer> entry : map.entrySet()){
            System.out.print(entry.getValue()+" ");
        }
    }

    public static void main(String[] args)
    {
        TreeNodeWithHD root = new TreeNodeWithHD(20);
        root.left = new TreeNodeWithHD(8);
        root.right = new TreeNodeWithHD(22);
        root.left.left = new TreeNodeWithHD(5);
        root.left.right = new TreeNodeWithHD(3);
        root.right.left = new TreeNodeWithHD(4);
        root.right.right = new TreeNodeWithHD(25);
        root.left.right.left = new TreeNodeWithHD(10);
        root.left.right.right = new TreeNodeWithHD(14);

        System.out.println("Bottom view of the given binary tree:");
        TreeBottomView.bottomView(root);

    }
}