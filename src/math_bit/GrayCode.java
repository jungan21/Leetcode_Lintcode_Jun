package math_bit;

import java.util.ArrayList;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ
 * in only one bit.
 * 
 * Given a non-negative integer n representing the total number of bits in the
 * code, find the sequence of gray code. A gray code sequence must begin with 0
 * and with cover all 2^n integers.
 * 
 * Notice
 * 
 * For a given n, a gray code sequence is not uniquely defined.
 * 
 * [0,2,3,1] is also a valid gray code sequence according to the above
 * definition.
 * 
 * Example Given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 
 * 00 - 0, 01 - 1, 11 - 3, 10 - 2;
 */
public class GrayCode {

	public static void main(String[] args) {
		int n = 3;
		System.out.println(grayCode(2));
		String a = "ab";
		char c = 'c';
		String d = a + c;
		System.out.println(d);
	}

	/**
	 * https://zh.wikipedia.org/wiki/%E6%A0%BC%E9%9B%B7%E7%A0%81
	 * 
	 * http://www.cnblogs.com/yuzhangcmu/p/4121804.html
	 * 
	 * http://www.jiuzhang.com/solutions/gray-code/
	 * 
	 */
	// amazon OA: 给两个byte，判断它们是否互为graycode
	// 就是判断不一样的位数是否为1, 参考 FlipBits.java
	public static List<Integer> grayCode(int n) {
		List<Integer> result = new ArrayList<Integer>();
		// n =1, 0, 1
		if (n == 0) {
			result.add(0);
			return result;
		}
		// divide
		result = grayCode(n - 1);
		// i必须从后往前，倒着来，至于为什么看一下下面网页中的镜像图就知道了
		// https://zh.wikipedia.org/wiki/%E6%A0%BC%E9%9B%B7%E7%A0%81
		for (int i = result.size() - 1; i >= 0; i--) {
			int num = result.get(i);
			num = num + (1 << (n - 1));
			result.add(num);
		}

		return result;
	}

}
