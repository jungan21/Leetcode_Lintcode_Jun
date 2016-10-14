package datastructure;

import java.util.ArrayList;
import java.util.Iterator;

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
 * https://segmentfault.com/a/1190000003786218
 * 
 */

public class ZigzagIteratorII {
	ArrayList<ArrayList<Integer>> vecs = null;
	ArrayList<Iterator<Integer>> iters = null;
	static int turn;
	static int size;

	public ZigzagIteratorII(ArrayList<ArrayList<Integer>> vecs) {
		this.vecs = vecs;
		this.iters = new ArrayList<Iterator<Integer>>();
		for (ArrayList<Integer> vec : this.vecs) {
			Iterator<Integer> iter = vec.iterator();
			// 只把非空的iterator加入， 这样 hasNext()方法就可以很容易的通过iters的size来判断
			if (iter.hasNext()) {
				iters.add(iter);
			}
		}
		turn = 0;
		size = vecs.size();
	}

	public int next() {
		if (!hasNext()) {
			return 0;
		}
		int result = 0;
		int pos = turn % iters.size();
		Iterator<Integer> currIter = iters.get(pos);
		result = currIter.next();
		// 如果这个迭代器用完，就将其从列表中移出
		if (!currIter.hasNext()) {
			iters.remove(pos);
			// turns变量更新为上一个下标
			turn = pos - 1;
		}
		turn++;
		return result;
	}

	public boolean hasNext() {
		return iters.size() > 0;
	}

	public static void main(String[] args) {
		ArrayList<Integer> v1 = new ArrayList<>();
		v1.add(1);
		v1.add(2);
		v1.add(3);
		ArrayList<Integer> v2 = new ArrayList<>();
		v2.add(4);
		v2.add(5);
		v2.add(6);
		v2.add(7);
		ArrayList<Integer> v3 = new ArrayList<>();
		v3.add(8);
		v3.add(9);
		ArrayList<ArrayList<Integer>> vecs = new ArrayList<ArrayList<Integer>>();
		vecs.add(v1);
		vecs.add(v2);
		vecs.add(v3);
		ZigzagIteratorII iterator = new ZigzagIteratorII(vecs);
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
