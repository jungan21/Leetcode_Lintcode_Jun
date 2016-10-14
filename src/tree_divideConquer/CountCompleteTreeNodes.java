package tree_divideConquer;


public class CountCompleteTreeNodes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		TreeNode left_left = new TreeNode(4);
		TreeNode left_right = new TreeNode(5);
		TreeNode right_left = new TreeNode(6);

		root.left = left;
		root.right = right;
		root.left.left = left_left;
		// root.left.right = left_right;
		// root.right.left = right_left;

		System.out.println(countNodes(root));
	}

	// recursive
	public static int countNodes(TreeNode root) {
		if (root == null)
			return 0;

		int left_most_height = getLeftMostHeight(root) + 1;
		int right_most_heght = getRightMostHeight(root) + 1;
		// int height = heightOfBinaryTree(root);

		if (left_most_height == right_most_heght) {
			return (int) Math.pow(2, left_most_height) - 1;
			// return (2<<(left_most_height-1)) - 1;
			// note: 2<<(n-1) = 2^n
		} else {
			return countNodes(root.left) + countNodes(root.right) + 1;
		}
	}

	// get the height of left-most part
	public static int getLeftMostHeight(TreeNode n) {
		if (n == null)
			return 0;

		int height = 0;
		while (n.left != null) {
			height++;
			n = n.left;
		}
		return height;
	}

	// get the height of right-most part
	public static int getRightMostHeight(TreeNode n) {
		if (n == null)
			return 0;

		int height = 0;
		while (n.right != null) {
			height++;
			n = n.right;
		}
		return height;
	}

	// Iteratve
	/**
	 * 用迭代法的思路稍微有点不同，因为不再是递归中那样的分治法，我们迭代只有一条正确的前进方向。
	 * 所以，我们判断的时左节点的最左边的深度，和右节点的最左边深度。 因为最后一层结束的地方肯定在靠左的那侧，所以我们要找的就是这个结束点。
	 * 如果两个深度相同,说明左子树和右子树都是满，结束点在右侧， ，如果右子树算出的最左深度要小一点,说明结束点在左边，右边已经是残缺的了
	 * ，。根据这个大小关系，我们可以确定每一层的走向
	 * ，最后找到结束点。另外，还要累加每一层的节点数，最后如果找到结束点，如果结束点是左节点，就多加1个，如果结束点是右节点，就多加2个。
	 * 
	 * @param root
	 * @return
	 */
	public static int countNodes2(TreeNode root) {
		int res = 0;
		while (root != null) {
			// 得到左节点的最左深度
			int leftH = getH(root.left);
			System.out.print("leftH: " + leftH);
			// 得到右节点的最左深度
			int rightH = getH(root.right);
			System.out.println("  rightH: " + rightH);
			// 左节点的最左深度大，说明右边已经残缺，结束点在左边
			if (leftH > rightH) {
				if (rightH != 0) {
					// res += 1 << rightH;
					// get the nodes in right side first (include root node)
					res = res + (1 << rightH);
					System.out.print("res: " + res);
					System.out.println("  rightH: " + leftH);
				} else {
					return res + 2;
				}
				root = root.left;
				// 否则说明结束点在右边
			} else if (leftH <= rightH) {
				if (leftH != 0) {
					// res += 1 << leftH;
					// 0001 = 2^0=1, move 1 to left for leftH position
					// get the nodes in left side first (i.e. count 1, 2, 4, 5
					// first)
					res = res + (1 << leftH);
					System.out.print("res: " + res);
					System.out.println("  leftH: " + leftH);
				} else {
					return res + 1;
				}
				root = root.right;
			}
		}
		return res;
	}

	private static int getH(TreeNode root) {
		int h = 0;
		while (root != null) {
			++h;
			root = root.left;
		}
		return h;
	}

}
