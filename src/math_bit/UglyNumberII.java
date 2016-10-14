package math_bit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class UglyNumberII {

	public static void main(String[] args) {
		System.out.println(nthUglyNumberHeap(9));
	}

	// O(n2), can't be AC by Linkedcode
	public static int nthUglyNumber(int n) {
		if (n < 1) {
			return -1;
		}
		Stack<Integer> stack = new Stack<>();

		int i = 1;
		while (stack.size() < n) {
			if (isUgly(i)) {
				stack.push(i);
			}
			i++;
		}
		return stack.pop();
	}

	public static boolean isUgly(int num) {
		if (num <= 0) {
			return false;
		}
		while (num != 1) {
			if (num % 5 == 0) {
				num /= 5;
			} else if (num % 3 == 0) {
				num /= 3;
			} else if (num % 2 == 0) {
				num /= 2;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * jiuzhang
	 */
	public boolean isUglyJiuzhang(int num) {
		// 0, 和 1 的 edge case必须提前考虑
		if (num <= 0)
			return false;
		if (num == 1)
			return true;
		while (num >= 2 && num % 2 == 0)
			num /= 2;
		while (num >= 3 && num % 3 == 0)
			num /= 3;
		while (num >= 5 && num % 5 == 0)
			num /= 5;

		return num == 1;
	}

	/**
	 * 
	 * O(n) http://www.programcreek.com/2014/05/leetcode-ugly-number-ii-java/
	 * 
	 * http://www.cnblogs.com/grandyang/p/4743837.html
	 * 
	 * 
	 * 这道题是之前那道Ugly Number
	 * 丑陋数的延伸，这里让我们找到第n个丑陋数，还好题目中给了很多提示，基本上相当于告诉我们解法了，根据提示中的信息
	 * ，我们知道丑陋数序列可以拆分为下面3个子列表：
	 * 
	 * (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
	 * 
	 * (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
	 * 
	 * (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
	 * 
	 * 仔细观察上述三个列表，我们可以发现每个子列表都是一个丑陋数分别乘以2,3,5，而要求的丑陋数就是从已经生成的序列中取出来的，
	 * 我们每次都从三个列表中取出当前最小的那个加入序列，
	 * 
	 */
	public int nthUglyNumber2(int n) {
		if (n <= 0)
			return 0;

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);

		// i means index of list
		int i = 0;
		int j = 0;
		int k = 0;

		while (list.size() < n) {
			int m2 = list.get(i) * 2, m3 = list.get(j) * 3, m5 = list.get(k) * 5;

			int min = Math.min(m2, Math.min(m3, m5));
			list.add(min);

			if (min == m2)
				i++;

			if (min == m3)
				j++;

			if (min == m5)
				k++;
		}
		return list.get(list.size() - 1);
	}

	/**
	 * heap 老师课堂上说要掌握
	 */
	public static int nthUglyNumberHeap(int n) {
		if (n < 1) {
			return -1;
		}
		// Long
		HashSet<Long> set = new HashSet<>();
		PriorityQueue<Long> minHeap = new PriorityQueue<Long>();
		// 把int 1转换为 long型, 1L
		minHeap.offer(1L);
		set.add(1L);
		long result = 0;
		for (int i = 0; i < n; i++) {
			result = minHeap.poll();
			// System.out.println(result);
			long n1 = result * 2;
			long n2 = result * 3;
			long n3 = result * 5;

			if (!set.contains(n1)) {
				minHeap.offer(n1);
				set.add(n1);
			}
			if (!set.contains(n2)) {
				minHeap.offer(n2);
				set.add(n2);
			}
			if (!set.contains(n3)) {
				minHeap.offer(n3);
				set.add(n3);
			}
		}
		return (int) result;
	}
}
