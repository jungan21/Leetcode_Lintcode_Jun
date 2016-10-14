package math_bit;

import java.util.ArrayList;
import java.util.List;

/**
 * Print numbers from 1 to the largest number with N digits by recursion.
 * 
 * Example Given N = 1, return [1,2,3,4,5,6,7,8,9].
 * 
 * Given N = 2, return [1,2,3,4,5,6,7,8,9,10,11,12,...,99].
 * 
 */
public class PrintNumbersbyRecursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * Jun's
	 * 
	 * https://segmentfault.com/a/1190000004559355
	 * 
	 * https://codesolutiony.wordpress.com/2015/05/21/lintcode-print-numbers-by-recursion/
	 * 
	 * http://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/72994
	 */
	public List<Integer> numbersByRecursion(int n) {
		List<Integer> result = new ArrayList<Integer>();

		if (n < 1) {
			return result;
		}
		helper(1, n, result, 1);
		return result;
	}

	public void helper(int depth, int n, List<Integer> result, int count) {
		if (depth > n) {
			return;
		}
		int max = 0;
		String maxStr = "";
		for (int i = 0; i < depth; i++) {
			maxStr += "9";
		}
		max = Integer.valueOf(maxStr);
		int i = 0;
		for (i = count; i <= max; i++) {
			result.add(i);
		}
		count = i;
		helper(depth + 1, n, result, count);
	}
}
