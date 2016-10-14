package datastructure;

import java.util.Stack;

// crack code: 3.6 you can ONLY use pop, push, isEmpty functions 
public class SortStackInAscendingOrder {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(10);
		stack.push(8);
		stack.push(20);
		System.out.println(SortStackInAscendingOrder.sort(stack));
	}

	// 栈底元素最小，越往上， 越大
	public static Stack<Integer> sort(Stack<Integer> stack) {
		Stack<Integer> result = new Stack<Integer>();

		while (!stack.isEmpty()) {
			int temp = stack.pop();

			while (!result.isEmpty() && result.peek() > temp) {
				stack.push(result.pop());
			}
			result.push(temp);
		}

		return result;
	}

	/**
	 * Lintcode ,与上面的区别是， 这个不能反会不同的stack, 而只能对已有的stack进行排序
	 */

	public void stackSorting(Stack<Integer> stack) {
		Stack<Integer> temp = new Stack<>();

		while (!stack.isEmpty()) {
			int insert = stack.pop();
			if (temp.isEmpty() || temp.peek() >= insert) {
				temp.push(insert);
			} else {
				/**
				 * Note: 需要把条件放在while条件判断里，如果放在if条件语句里，必须加break, 你想一下万一if没满足,
				 * 就一直退不出外层循环了 dead loop,
				 * 
				 * if (temp.peek() < insert){
				 * 
				 * stack.push(temp.pop());
				 * 
				 * }else {
				 * 
				 * break;
				 * 
				 * }
				 * 
				 */
				while (!temp.isEmpty() && temp.peek() < insert) {
					// if (temp.peek() < insert) {
					stack.push(temp.pop());
					// }
				}
				temp.push(insert);
			}

		}

		while (!temp.isEmpty()) {
			stack.push(temp.pop());
		}
	}
}
