package merge;

import java.util.*;

public class MergeKSortedArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * PriorityQueue:9856 ms
	 * 
	 * http://www.jiuzhang.com/solutions/merge-k-sorted-arrays/
	 */

	class Element {
		public int row, col, val;

		Element(int row, int col, int val) {
			this.row = row;
			this.col = col;
			this.val = val;
		}
	}

	public List<Integer> mergekSortedArrays(int[][] arrays) {
		List<Integer> result = new ArrayList<Integer>();
		if (arrays == null || arrays.length == 0) {
			return result;
		}

		// minHeap
		PriorityQueue<Element> minHeap = new PriorityQueue<Element>(
				arrays.length, new Comparator<Element>() {
					public int compare(Element left, Element right) {
						return left.val - right.val;
					}
				});

		// 如果返回对象不是list,而是一个数组，那么，就需要这个total_size去track新的数组的大小
		// int total_size = 0;
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i].length > 0) {
				Element elem = new Element(i, 0, arrays[i][0]);
				minHeap.offer(elem);
				// total_size += arrays[i].length;
			}
		}

		while (!minHeap.isEmpty()) {
			Element elem = minHeap.poll();
			result.add(elem.val);
			// 这一步需要用到当前num的坐标（x,y）所以想到创建一个新的对象Element
			if (elem.col + 1 < arrays[elem.row].length) {
				// 必须这样一个一个property赋值，这样elem相当于一个新的elem对象，否则就要每次new一个Element对象
				elem.col = elem.col + 1;
				elem.val = arrays[elem.row][elem.col];
				minHeap.offer(elem);
			}
		}

		return result;
	}

	/**
	 * divide conquer: 10265ms
	 */

	public List<Integer> mergekSortedArraysDivideConquer(int[][] arrays) {
		List<Integer> result = new ArrayList<Integer>();
		if (arrays == null || arrays.length == 0) {
			return result;
		}
		int start = 0;
		int end = arrays.length - 1;

		return helper(arrays, start, end);

	}

	public List<Integer> helper(int[][] arrays, int start, int end) {
		if (start == end) {
			List<Integer> list = new ArrayList<Integer>();
			for (int a : arrays[start]) {
				list.add(a);
			}
			return list;
		}
		int mid = start + (end - start) / 2;

		List<Integer> left = helper(arrays, start, mid);
		List<Integer> right = helper(arrays, mid + 1, end);

		return merge2Arrays(left, right);

	}

	public List<Integer> merge2Arrays(List<Integer> A, List<Integer> B) {
		if (A == null && B == null) {
			return null;
		} else if (A == null || A.size() == 0) {
			return B;
		} else if (B == null || B.size() == 0) {
			return A;
		}

		List<Integer> list = new ArrayList<Integer>();
		int i = 0;
		int j = 0;
		while (i < A.size() && j < B.size()) {
			if (A.get(i) <= B.get(j)) {
				list.add(A.get(i));
				i++;
			} else {
				list.add(B.get(j));
				j++;
			}
		}

		while (i < A.size()) {
			list.add(A.get(i++));
		}

		while (j < B.size()) {
			list.add(B.get(j++));
		}

		return list;
	}

}
