package graph_dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 常以XXX.XXX.XXX.XXX形式表现，每组XXX代表小于或等于255的10进制数。所以说IP地址总共有四段，每一段可能有一位，两位或者三位，范围是[
 * 0, 255]，题目明确指出输入字符串只含有数字，所以当某段是三位时，我们要判断其是否越界（>255)，还有一点很重要的是，当只有一位时，0可以成某一段，
 * 如果有两位或三位时，像 00， 01， 001， 011， 000等都是不合法的，所以我们还是需要有一个判定函数来判断某个字符串是否合法。
 * 
 * 基本思路就是取出一个合法的数字，作为IP地址的一项，然后递归处理剩下的项。可以想象出一颗树，每个结点有三个可能的分支（因为范围是0-255，
 * 所以可以由一位两位或者三位组成
 * ）。并且这里树的层数不会超过四层，因为IP地址由四段组成，到了之后我们就没必要再递归下去，可以结束了。这里除了上述的结束条件外
 * ，另一个就是字符串读完了。可以看出这棵树的规模是固定的
 * ，不会像平常的NP问题那样，时间复杂度取决于输入的规模，是指数量级的，所以这道题并不是NP问题，因为他的分支是四段，有限制。代码如下：
 *
 */
public class RestoreIPAddress {

	public static void main(String[] args) {

	}

	// 原理 和 PalindromePartitioning.java 一样, DecodeWays.java
	public ArrayList<String> restoreIpAddresses(String s) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();

		if (s.length() < 4 || s.length() > 12)
			return result;
		// 0 track position
		helper(result, list, s, 0);
		return result;
	}

	public void helper(ArrayList<String> result, ArrayList<String> list,
			String s, int pos) {
		// list里有4段String对象，
		if (list.size() == 4 && pos == s.length()) {
			StringBuffer sb = new StringBuffer();
			for (String tmp : list) {
				sb.append(tmp);
				sb.append(".");
			}
			// remove last "."
			sb.deleteCharAt(sb.length() - 1);
			result.add(sb.toString());
			return;
		}
		// i < pos +3, 因为每一段IP 最多只有3位
		for (int i = pos; i < s.length() && i < pos + 3; i++) {
			String tmp = s.substring(pos, i + 1);
			if (!isvalid(tmp)) {
				continue;
			}
			list.add(tmp);
			helper(result, list, s, i + 1);
			list.remove(list.size() - 1);
		}
	}

	private boolean isvalid(String s) {
		if (s.charAt(0) == '0')
			return s.equals("0"); // to eliminate cases like "00", "10"
		int digit = Integer.valueOf(s);
		return digit >= 0 && digit <= 255;
	}

	/**
	 * non-recursive
	 * 
	 * http://www.cnblogs.com/grandyang/p/4305572.html
	 */

	public List<String> restoreIpAddresses1(String s) {
		List<String> res = new ArrayList<String>();
		for (int a = 1; a < 4; ++a)
			for (int b = 1; b < 4; ++b)
				for (int c = 1; c < 4; ++c)
					for (int d = 1; d < 4; ++d)
						if (a + b + c + d == s.length()) {
							int A = Integer.parseInt(s.substring(0, a));
							int B = Integer.parseInt(s.substring(a, a + b));
							int C = Integer.parseInt(s.substring(a + b, a + b
									+ c));
							int D = Integer.parseInt(s.substring(a + b + c));
							if (A <= 255 && B <= 255 && C <= 255 && D <= 255) {
								String t = String.valueOf(A) + "."
										+ String.valueOf(B) + "."
										+ String.valueOf(C) + "."
										+ String.valueOf(D);
								// 不能省略，因为string 转换为Integer的时候，前置的0会被去掉
								if (t.length() == s.length() + 3)
									res.add(t);
							}
						}
		return res;
	}

	/**
	 * https://segmentfault.com/a/1190000003704558
	 */
		public List<String> restoreIpAddresses2(String s) {
			List<String> res = new LinkedList<String>();
			int len = s.length();
			// 第一个分割点
			for (int i = 1; i < 4 && i < len - 2; i++) {
				// 第二个分割点
				for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
					// 第三个分割点
					for (int k = j + 1; k < j + 4 && k < len; k++) {
						String s1 = s.substring(0, i), s2 = s.substring(i, j), s3 = s
								.substring(j, k), s4 = s.substring(k,
								s.length());
						if (isValid(s1) && isValid(s2) && isValid(s3)
								&& isValid(s4))
							res.add(s1 + "." + s2 + "." + s3 + "." + s4);
					}
				}
			}
			return res;
		}

		private boolean isValid(String sub) {
			return sub.length() <= 3
					&& ((sub.charAt(0) != '0' && Integer.valueOf(sub) <= 255) || sub
							.equals("0"));
		}

}
