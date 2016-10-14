package tree_divideConquer;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTrees {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// generateTrees(3);
		numTrees(3);
	}

	// http://www.programcreek.com/2014/05/leetcode-unique-binary-search-trees-java/

	/**
	 * Let dp[i] be the number of unique binary search trees for i. The number
	 * of trees are determined by the number of subtrees which have different
	 * root node. For example,
	 */

	/**
	 * 这是很有意思的一个题。刚拿到这题的时候，完全不知道从那下手，因为对于BST是否Unique，很难判断。最后引入了一个条件以后，立即就清晰了，即
	 * 当数组为 1，2，3，4，.. i，.. n时，基于以下原则的BST建树具有唯一性： 以i为根节点的树，其左子树由[0, i-1]构成，
	 * 其右子树由[i+1, n]构成。
	 * http://fisherlei.blogspot.ca/2013/03/leetcode-unique-binary
	 * -search-trees.html
	 * 
	 * 定义Count[i] 为以[0,i]能产生的Unique Binary Tree的数目，
	 * 
	 * http://www.jiuzhang.com/solutions/unique-binary-search-trees/
	 * 
	 * 
	 * The case for 3 elements example
	 * 
	 * Count[3] = Count[0]*Count[2] (1 as root) + Count[1]*Count[1] (2 as root)
	 * + Count[2]*Count[0] (3 as root)
	 * 
	 * Therefore, we can get the equation: Count[i] = ∑ Count[0...k] * [
	 * k+1....i] 0<=k<i-1
	 */
	// http://www.programcreek.com/2014/05/leetcode-unique-binary-search-trees-java/
	public static int numTrees(int n) {
		// n = 0, n=1 return 1
		if (n <= 1) {
			return 1;
		}
		int[] dp = new int[n + 1];
		// dp[0] =1. 因为下面乘法需要，所以不能设置为0， 也表示empty tree
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				// dp
				// i - j -1: (- 1 means minus root node)
				// dp[j] 和 dp[i-j-1]分别表示左右字数个数
				dp[i] = dp[i] + dp[j] * dp[i - j - 1];
			}
		}

		return dp[n];
	}

	// generate trees
	/**
	 * 要求生成所有的unique BST，类似combination/permutation的题目，可以递归构造。
	 * 
	 * 1. 根节点可以任取min ~ max (例如min = 1, max = n)，假如取定为i。 2. 则left subtree由min ~
	 * i-1组成，假设可以有L种可能。right subtree由i+1 ~ max组成，假设有R种可能。生成所有可能的left/right
	 * subtree。 3 对于每个生成的left subtree/right subtree组合<T_left(p), T_right(q)>，p =
	 * 1...L，q = 1...R，添加上根节点i而组成一颗新树。
	 */
	// http://bangbingsyb.blogspot.ca/2014/11/leetcode-unique-binary-search-trees-i-ii.html
	public static List<TreeNode> generateTrees(int n) {
		List<TreeNode> result = new ArrayList<TreeNode>();
		if (n <= 0) {
			// 不能省略：n=0的时候，正确的输出是[{}]， 如果不加这一句，结果就是[]
			result.add(null);
			return result;
		}
		return helper(1, n);
	}

	// m start, n end
	public static List<TreeNode> helper(int m, int n) {
		List<TreeNode> result = new ArrayList<TreeNode>();
		if (m > n) {
			result.add(null);
			return result;
		}

		for (int i = m; i <= n; i++) {
			// 枚举以每个i为 root, nodeLis
			List<TreeNode> ls = helper(m, i - 1);
			List<TreeNode> rs = helper(i + 1, n);
			for (TreeNode l : ls) {
				for (TreeNode r : rs) {
					// should new a root here because it need to
					// be different for each tree
					TreeNode curr = new TreeNode(i);
					curr.left = l;
					curr.right = r;
					result.add(curr);
				}
			}
		}

		return result;
	}
}
