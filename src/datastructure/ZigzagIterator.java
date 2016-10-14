package datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given two 1d vectors, implement an iterator to return their elements
 * alternately.
 *
 * Example
 * 
 * Given two 1d vectors:
 * 
 * v1 = [1, 2] v2 = [3, 4, 5, 6]
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements
 * returned by next should be: [1, 3, 2, 4, 5, 6].
 * 
 */

public class ZigzagIterator {
	List<Integer> v1 = null;
	List<Integer> v2 = null;
	Iterator<Integer> iter1;
	Iterator<Integer> iter2;
	static int turn;

	public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
		this.v1 = v1;
		this.v2 = v2;
		this.iter1 = this.v1.iterator();
		this.iter2 = this.v2.iterator();
		turn = 0;
	}

	public int next() {

		if (!hasNext()) {
			return 0;
		}
		if ((turn % 2 == 0 && iter1.hasNext()) || (!iter2.hasNext())) {
			turn++;
			return iter1.next();
		} else if ((turn % 2 == 1 && iter2.hasNext()) || (!iter1.hasNext())) {
			turn++;
			return iter2.next();
		}

		return 0;
	}

	public boolean hasNext() {
		return iter1.hasNext() || iter2.hasNext();
	}

	public static void main(String[] args) {
		List<Integer> v1 = new ArrayList<>();
		v1.add(1);
		v1.add(2);
		List<Integer> v2 = new ArrayList<>();
		v2.add(3);
		v2.add(4);
		v2.add(5);
		v2.add(6);
		ZigzagIterator iterator = new ZigzagIterator(v1, v2);
		ArrayList<Integer> result = new ArrayList<>();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			result.add(next);
		}
		System.out.println(result);
	}
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator solution = new ZigzagIterator(v1, v2); while
 * (solution.hasNext()) result.add(solution.next()); Output result
 */
