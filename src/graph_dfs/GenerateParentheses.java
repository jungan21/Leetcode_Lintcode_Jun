package graph_dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement an algorithm to print all valid (e g , properly opened and closed)
 * combi- nations of n-pairs of parentheses
 * 
 * EXAMPLE: input: 3 (e g , 3 pairs of parentheses) output: ()()(), ()(()),
 * (())(), ((()))
 * 
 *
 */
public class GenerateParentheses {

	public static void main(String[] args) {
		// System.out.println(generateParenthesis(3));
		System.out.println(generateParenthesisBest1(2));
	}

	/**
	 * recommend method, 上面的方法是permution 即所有的( 和 ）都要用到，而这个方法，类似于subset
	 * 
	 * 不能用 StirngBuilder
	 */

	public static ArrayList<String> generateParenthesisBest(int n) {
		ArrayList<String> result = new ArrayList<String>();
		// for (int i = 1; i <= n; i++) {
		// Brackets("", 0, 0, i, result);
		// }
		// StringBuilder sb = new StringBuilder();
		Brackets("", 0, 0, n, result);
		return result;
	}

	private static void Brackets(String output, int open, int close, int pairs,
			ArrayList<String> result) {
		if ((open == pairs) && (close == pairs)) {
			result.add(output);
			return;
		}

		if (open < pairs)
			Brackets(output + "(", open + 1, close, pairs, result);
		// 只有 ) 比 (少的时候，在加上 )
		if (close < pairs && open > close)
			Brackets(output + ")", open, close + 1, pairs, result);
	}

	// 上面的方法也可以改写成
	private static void Brackets1(String output, int open, int close,
			int pairs, ArrayList<String> result) {
		if (open < close) {
			return;
		}

		if ((open == pairs) && (close == pairs)) {
			result.add(output);
			return;
		}

		if (open < pairs)
			Brackets(output + "(", open + 1, close, pairs, result);
		// 只有 ) 比 (少的时候，在加上 )
		if (close < pairs)
			Brackets(output + ")", open, close + 1, pairs, result);
	}

	/**
	 * 上面的方法用String, 这里用StringBuilder
	 */
	public static ArrayList<String> generateParenthesisBest1(int n) {
		ArrayList<String> result = new ArrayList<String>();
		if (n < 1) {
			return result;
		}
		StringBuilder sb = new StringBuilder();
		Brackets(n, 0, 0, sb, result);
		return result;
	}

	public static void Brackets(int n, int left, int right, StringBuilder sb,
			ArrayList<String> result) {

		if (left == n && right == n) {
			result.add(new String(sb));
			return;
		}
		if (left < n) {
			sb.append("(");
			Brackets(n, left + 1, right, sb, result);
			sb.deleteCharAt(sb.length() - 1);
		}

		if (right < n && right < left) {
			sb.append(")");
			Brackets(n, left, right + 1, sb, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	/**
	 * Another method: NOT recommended
	 */
	public static List<String> generateParenthesis(int n) {
		ArrayList<String> result = new ArrayList<String>();
		dfs(result, "", n, n);
		return result;
	}

	/*
	 * left and right represents the remaining number of ( and ) that need to be
	 * added. When left > right, there are more ")" placed than "(". Such cases
	 * are wrong and the method stops.
	 */
	public static void dfs(ArrayList<String> result, String s, int left,
			int right) {
		// 表示 剩下的 如果'('多余')'的个数，就表示不合格
		// base case 不能省
		if (left > right)
			return;

		if (left == 0 && right == 0) {
			result.add(s);
			return;
		}

		if (left > 0) {
			dfs(result, s + "(", left - 1, right);
		}

		if (right > 0) {
			dfs(result, s + ")", left, right - 1);
		}
	}

	/**
	 * jiuzhang: http://www.jiuzhang.com/solutions/generate-parentheses/
	 */

	public ArrayList<String> generateParenthesis2(int n) {
		ArrayList<String> result = new ArrayList<String>();
		if (n <= 0) {
			return result;
		}
		helper(result, "", n, n);
		return result;
	}

	public void helper(ArrayList<String> result, String paren, // current paren
			int left, // how many left paren we need to add
			int right) { // how many right paren we need to add
		if (left == 0 && right == 0) {
			result.add(paren);
			return;
		}

		if (left > 0) {
			helper(result, paren + "(", left - 1, right);
		}
		// left < right, 表示 剩下的 ( 比 ) 多
		if (right > 0 && left < right) {
			helper(result, paren + ")", left, right - 1);
		}
	}

}
