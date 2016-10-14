package a_oa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * 例如数组[1,2,3,2,1,4,5,6], window的大小是5，第一个window 是[1,2,3,2,1],
 * 那么重复个数是2，第二个window是[2,3,2,1,4], 重复个数是1.
 */
public class DuplicateNumberInArrayWindow {

	public static void main(String[] args) {
		int[] A = { 1, 2, 2, 2, 1, 5, 5, 6 };

		System.out.println(repeatedNumbersInWindow(A, 5));
	}

	public static List<Integer> repeatedNumbersInWindow(int[] A, int k) {
		List<Integer> result = new ArrayList<Integer>();
		if (A == null || A.length == 0 || k <= 0) {
			return result;
		}
		Queue<Integer> queue = new LinkedList<>();
		HashSet<Integer> tempSet = new HashSet<>();
		for (int i = 0; i < A.length; i++) {
			if (queue.contains(A[i])) {
				tempSet.add(A[i]);
			}
			queue.add(A[i]);

			if (i >= k) {
				if (tempSet.contains(A[i - k])) {
					tempSet.remove(A[i - k]);
				}
				queue.remove(0);
			}

			if (i + 1 >= k) {
				result.add(tempSet.size());
			}
		}
		return result;
	}
}
