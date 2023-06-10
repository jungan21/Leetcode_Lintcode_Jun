package company.walmart;

class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int data) {
        this.data = data;
        left = right = null;
    }
}
// https://www.geeksforgeeks.org/check-if-two-trees-are-mirror/
    public class TreeMirror {
        TreeNode a, b;

        /* Given two trees, return true if they are irror of each other */
        boolean areMirror(TreeNode a, TreeNode b) {
            /* Base case : Both empty */
            if (a == null && b == null)
                return true;

            // If only one is empty
            if (a == null || b == null)
                return false;

            return a.data == b.data
                    && areMirror(a.left, b.right)
                    && areMirror(a.right, b.left);
        }

        // Driver code to test above methods
        public static void main(String[] args)
        {
            TreeMirror tree = new TreeMirror();
            TreeNode a = new TreeNode(1);
            TreeNode b = new TreeNode(1);
            a.left = new TreeNode(2);
            a.right = new TreeNode(3);
            a.left.left = new TreeNode(4);
            a.left.right = new TreeNode(5);

            b.left = new TreeNode(3);
            b.right = new TreeNode(2);
            b.right.left = new TreeNode(5);
            b.right.right = new TreeNode(4);

            if (tree.areMirror(a, b) == true)
                System.out.println("Yes");
            else
                System.out.println("No");

        }
    }
