package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Find the kth smallest number in at row and column sorted matrix.
 * 
 */
public class KthSmallestNumberinSortedMatrix {

	public static void main(String[] args) {
		int[][] matrix = { { 1, 5, 7 }, { 3, 7, 8 }, { 4, 8, 9 } };

		new KthSmallestNumberinSortedMatrix().kthSmallest(matrix, 3);
	}

	/**
	 * O(k logk), n is the maximal number in width and height.
	 * 
	 * 这里用heap,只需要遍历前k个元素，即只要弹出k个元素，由于本题算法，每弹一个元素的同时最多加入2个元素
	 * 
	 * klog(2k) + klog(2k) = klog(n) 弹k次，每次耗时：log(n)
	 * 
	 * 注： 从min/max Heap里取min/max的时间复杂福： O(1)
	 * 
	 * 加入或删除一个到min/max Heap元素时间复杂度： O(logn),n是堆里元素个数
	 * 
	 * http://www.cnblogs.com/deepblueme/p/4780676.html
	 */

	class Node {
		public int row;
		public int col;
		public int value;

		public Node(int row, int col, int value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}
	}

	public int kthSmallest(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		if (k > matrix.length * matrix[0].length) {
			return 0;
		}

		Comparator<Node> comparator = new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				return n1.value - n2.value;
			}
		};
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		// minHeap
		PriorityQueue<Node> minHeap = new PriorityQueue<Node>(k, comparator);
		minHeap.offer(new Node(0, 0, matrix[0][0]));
		int result = 0;
		visited[0][0] = true;
		for (int i = 0; i < k; i++) {
			// from 0 to k-1, poll k 次， 出来的就是第kth smallest的值
			Node node = minHeap.poll();
			result = node.value;
			// 两个方向上都有顺序
			put2Heap(node.row + 1, node.col, visited, matrix, minHeap);// down
			put2Heap(node.row, node.col + 1, visited, matrix, minHeap);// right
		}
		return result;
	}

	private void put2Heap(int row, int col, boolean[][] visited,
			int[][] matrix, PriorityQueue<Node> heap) {
		int m = matrix.length;
		// matrix 行列相等，所以可以用直接用matrix[0].length，
		// 注意这里不能用matrix[x].length,因为在这个函数被调用之前有put2Heap(node.row + 1,
		// node.col）操作，row+1,也就是这里的row,有越界的可能如果越界了就报错

		int n = Integer.MIN_VALUE;
		if (row < matrix.length && matrix[row] != null) {
			n = matrix[row].length;
		}

		if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col]) {
			return;
		}
		// 通过上面的if判断， 被访问过得，以及下标越界的就不考虑了
		heap.offer(new Node(row, col, matrix[row][col]));
		visited[row][col] = true;
	}

}
