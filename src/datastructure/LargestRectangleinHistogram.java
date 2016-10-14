package datastructure;

import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleinHistogram {

	public static void main(String[] args) {
		int[] height = { 2, 1, 5, 6, 2, 3 };
		largestRectangleArea(height);
	}

	/**
	 * jiuzhang: O(n)
	 * 
	 * 复杂度分析： 虽然内部有个while loop,当时不是O（n^2）, 那只是最坏的情况下，但是平摊下来还是O(n), 假设，每一层while
	 * loop run n times, poped up all numbers in stack,
	 * 但是在这这之前（stack积攒着n个数）这个while loop都不会被执行到，所以平摊下来就是O(n) 简单的理解就是每个数只是进出stack
	 * one time,
	 * 
	 */

	public static int largestRectangleArea(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}

		Stack<Integer> stack = new Stack<>();
		int maxArea = 0;
		for (int i = 0; i <= height.length; i++) {
			// 为了实现算法思想，即通过某高度，找左右两边第一个比该高度小的，算面积，所有最有多加一个dummy
			// 高度，-1保证，stack里的剩余的高度，能被pop出来算面积
			int curH = (i == height.length) ? -1 : height[i];
			// 维护一个递增stack,并且找到左边第一个比他小的数，所以>= curH， 等于curH的也要被pop出来, 否则不对，
			// 我们需要单调递增stack
			while (!stack.isEmpty() && height[stack.peek()] >= curH) {
				/**
				 * 以每个高度为高的，都算一次面积，其宽度，是该高度以stack左右两边第一个比此高度矮的为界，上面的while
				 * loop就是一旦遇到比栈顶元素矮的，就stack里一个一个高度都pop出来算面积， 完后，再把当前矮的加入stack。
				 * 可以对比题目中的5的高度来理解，当遇到2的时候，就把6为高的pop出来算一遍面积，再把5为高的pop出来算一遍面积
				 * 
				 * 单调递增stack, [1,2 -> push(3) --> [1, 2, 3
				 * 
				 * [1,2 -> push(0) --> [0,
				 * 
				 * [1,2 -> push(2) --> [1,2 (note: first 2 has been poped up,
				 * then add this new 2)
				 * 
				 * 找到左边第一个比他小的数，找到右边第一个比他小的数，下标相减，再减去1就是宽度
				 */
				// 注意，必须先算高度，再算宽度
				int high = height[stack.pop()];
				// stack为空，width就为i,以为我们维护的是单调递增stack, stack为空，
				// 就说明刚被pop出来的high的值比前面所有已经被pop出来的高度都矮，否则，stack不应该为空
				int width = stack.isEmpty() ? i : i - stack.peek() - 1;
				maxArea = Math.max(maxArea, high * width);
			}
			// stack里存的是某高度的下标，这样在算面积的时候，好算高度
			stack.push(i);
		}
		return maxArea;
	}

	/**
	 * Jun's own
	 */
	public int largestRectangleAreaJun(int[] A) {
		Stack<Integer> stack = new Stack<>();
		int[] height = new int[A.length + 1];
		height = Arrays.copyOf(A, A.length + 1);
		height[height.length - 1] = -1;
		int result = 0;
		for (int i = 0; i < height.length; i++) {
			int high = 0;
			int width = 0;
			if (stack.isEmpty()) {
				stack.push(i);
			} else {
				while (!stack.isEmpty() && height[i] < height[stack.peek()]) {
					high = height[stack.peek()];
					stack.pop();
					width = (stack.isEmpty() ? i : i - stack.peek() - 1);
					result = Math.max(result, high * width);
				}
				stack.push(i);
			}
		}
		return result;
	}

	/**
	 * http://www.cnblogs.com/lichen782/p/
	 * leetcode_Largest_Rectangle_in_Histogram.html
	 * 
	 * http://www.cnblogs.com/springfor/p/3869449.html
	 * 
	 * http://www.cnblogs.com/yuzhangcmu/p/4191981.html
	 */

	public static int largestRectangleArea1(int[] height) {
		Stack<Integer> stack = new Stack<Integer>();
		int i = 0;
		int maxArea = 0;
		int[] h = new int[height.length + 1];
		h = Arrays.copyOf(height, height.length + 1);
		while (i < h.length) {
			if (stack.isEmpty() || h[stack.peek()] <= h[i]) {
				stack.push(i++);
			} else {
				int t = stack.pop();
				/**
				 * 栈为空，宽度是从i到最左边（因为这是一个递增栈，如果现在栈为空，表示我们取出的当前直方是最低的直方，
				 * 它的宽度可以一直延展到最左边。）
				 */

				maxArea = Math.max(maxArea, h[t]
						* (stack.isEmpty() ? i : i - stack.peek() - 1));
			}
		}
		return maxArea;
	}
}
