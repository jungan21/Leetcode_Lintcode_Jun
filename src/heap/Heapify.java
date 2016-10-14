package heap;

/**
 * Given an integer array, heapify it into a min-heap array.
 * 
 * For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1]
 * is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].
 * 
 */
public class Heapify {

	public static void main(String[] args) {
		int A[] = { 16, 7, 3, 20, 17, 8 };
		new Heapify().heapify(A);

	}

	/**
	 * http://blog.csdn.net/cdnight/article/details/11650983、
	 * 
	 * https://www.youtube.com/watch?v=d3qd_wQdYqg
	 * 
	 * http://www.jiuzhang.com/solutions/heapify/
	 * 
	 * O(n)
	 * 
	 * 
	 * http://www.importnew.com/20169.html
	 * 
	 */

	// iterative method
	private void siftdown(int[] A, int k) {
		while (k < A.length) {
			int smallest = k;
			// k*2+1 is the left child index;
			if (k * 2 + 1 < A.length && A[k * 2 + 1] < A[smallest]) {
				smallest = k * 2 + 1;
			}
			// k*2+2 is the right child index
			if (k * 2 + 2 < A.length && A[k * 2 + 2] < A[smallest]) {
				smallest = k * 2 + 2;
			}
			// have to break,no need to adjust heap
			if (smallest == k) {
				break;
			}
			int temp = A[smallest];
			A[smallest] = A[k];
			A[k] = temp;

			k = smallest;
		}
	}

	// recursive

	public void siftdownRecursive(int[] A, int k) {
		if (k >= A.length) {
			return;
		}

		int smallest = k;
		int lson = 2 * k + 1;
		int rson = 2 * k + 2;
		if (lson < A.length && A[lson] < A[smallest]) {
			smallest = lson;
		}
		if (rson < A.length && A[rson] < A[smallest]) {
			smallest = rson;
		}
		if (smallest == k) {
			return;
		}
		int temp = A[smallest];
		A[smallest] = A[k];
		A[k] = temp;
		k = smallest;
		// recursive call;
		siftdown(A, k);
	}

	// A.length/2 就是最后一个非叶子节点的下一个
	public void heapify(int[] A) {
		for (int i = A.length / 2; i >= 0; i--) {
			siftdown(A, i);
		} // for
	}

}
