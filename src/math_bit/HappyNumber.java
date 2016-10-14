package math_bit;

import java.util.HashSet;

/**
 * A happy number is a number defined by the following process: Starting with
 * any positive integer, replace the number by the sum of the squares of its
 * digits, and repeat the process until the number equals 1 (where it will
 * stay), or it loops endlessly in a cycle which does not include 1. Those
 * numbers for which this process ends in 1 are happy numbers.
 * 
 * Example: 19 is a happy number
 * 
 *
 */
public class HappyNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isHappy3(2));
	}

	static HashSet<Integer> set = new HashSet<Integer>();

	// recursive
	public static boolean isHappy(int n) {
		if (!set.contains(n)) {
			set.add(n);
			n = squareSum(n);
			if (n == 1) {
				return true;
			} else {
				return isHappy(n);
			}
		}
		return false;
	}

	// method 2: non-recursive
	// http://www.programcreek.com/2014/04/leetcode-happy-number-java/
	// 最好维护一个HashSet，假如遇见重复，则返回false
	// Time Complexity - O(n)， Space Complexity - O(n)
	public static boolean isHappy2(int n) {
		while (!set.contains(n)) {
			set.add(n);
			n = squareSum(n);
			if (n == 1) {
				return true;
			}
		}
		return false;
	}

	// method 3 faster algorithm (method 1 & method 2,all use extra space
	// hashset)
	// Space Complexity 简化到 O(1),应为，没有用hashset了，使用 fast / slow pointer进行Cycle
	// Detection的思路，很巧妙。 更奇妙的是运行时间也减少了
	public static boolean isHappy3(int n) {
		if (n < 1)
			return false;
		int slow = n, fast = squareSum(n);
		while (slow != fast) {
			slow = squareSum(slow);
			// if(slow ==1){
			// return true;
			// }
			fast = squareSum(squareSum(fast));
		}
		return slow == 1;
	}

	public static boolean isHappy4(int n) {
		if (n < 1)
			return false;
		if (n == 1)
			return true;
		int slow = n, fast = squareSum(n);
		while (slow != fast) {
			slow = squareSum(slow);
			fast = squareSum(squareSum(fast));
			if (slow == 1) {
				return true;
			}
		}
		return false;
	}

	public boolean isHappy5(int n) {
		if (n < 1)
			return false;
		int slow = n, fast = squareSum(n);
		// 这样写循环，n ==1的情况，就不用单独考虑
		do {
			slow = squareSum(slow);
			fast = squareSum(squareSum(fast));
			if (slow == 1) {
				return true;
			}
		} while (slow != fast);

		return false;
	}

	public static int squareSum(int n) {
		int sum = 0;
		while (n > 0) {
			int mod = n % 10;
			n = n / 10;
			sum = sum + (int) Math.pow(mod, 2);
		}
		return sum;
	}

}
