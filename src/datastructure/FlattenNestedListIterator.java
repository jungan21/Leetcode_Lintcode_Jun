package datastructure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * Given a nested list of integers, implement an iterator to flatten it.
 * 
 * Each element is either an integer, or a list -- whose elements may also be
 * integers or other lists.
 * 
 * Example
 * 
 * Given the list [[1,1],2,[1,1]], By calling next repeatedly until hasNext
 * returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * 
 * Given the list [1,[4,[6]]], By calling next repeatedly until hasNext returns
 * false, the order of elements returned by next should be: [1,4,6].
 * 
 * 
 * 
 */

public class FlattenNestedListIterator implements Iterator<Integer> {

	/**
	 * method 1
	 */

	LinkedList<Integer> queue = new LinkedList<Integer>();

	public FlattenNestedListIterator(List<NestedInteger> nestedList) {
		helper(nestedList);
	}

	// @return {int} the next element in the iteration
	@Override
	public Integer next() {
		return queue.poll();
	}

	// @return {boolean} true if the iteration has more element or false
	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}

	@Override
	public void remove() {

	}

	public void helper(List<NestedInteger> nestedList) {
		for (NestedInteger elem : nestedList) {
			if (elem.isInteger()) {
				queue.offer(elem.getInteger());
			} else {
				helper(elem.getList());
			}
		}
	}

	/**
	 * 
	 * method 2
	 * 
	 * 只能有stack,如果第一个元素不是integer, 把该元素化为integer,再逆序push到栈顶，这样就保证了原来的顺序
	 * 
	 * 如果用queue [[1,1],2,[1,1]],只要第一个元素不是integer,就要把[1,1]放到最后去了，这样就打破了原来的顺序
	 */
	Stack<NestedInteger> stack = new Stack<NestedInteger>();

	public FlattenNestedListIterator(List<NestedInteger> nestedList) {
		if (nestedList == null)
			return;

		for (int i = nestedList.size() - 1; i >= 0; i--) {
			stack.push(nestedList.get(i));
		}
	}

	// @return {int} the next element in the iteration
	@Override
	public Integer next() {
		return stack.pop().getInteger();
	}

	// @return {boolean} true if the iteration has more element or false
	@Override
	public boolean hasNext() {
		while (!stack.isEmpty()) {
			NestedInteger top = stack.peek();
			if (top.isInteger()) {
				return true;
			} else {
				stack.pop();
				for (int i = top.getList().size() - 1; i >= 0; i--) {
					stack.push(top.getList().get(i));
				}
			}
		}

		return false;
	}

	@Override
	public void remove1() {
	}

}
