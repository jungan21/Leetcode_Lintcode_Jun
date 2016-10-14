package math_bit;

import java.util.HashMap;
import java.util.Map;

// roman number explantion: http://blog.csdn.net/wzy_1988/article/details/17057929
/**
 * 计数规则： 相同的数字连写，所表示的数等于这些数字相加得到的数，例如：III = 3
 * 小的数字在大的数字右边，所表示的数等于这些数字相加得到的数，例如：VIII = 8
 * 小的数字，限于（I、X和C）在大的数字左边，所表示的数等于大数减去小数所得的数，例如：IV = 4 正常使用时，连续的数字重复不得超过三次
 * 在一个数的上面画横线，表示这个数扩大1000倍（本题只考虑3999以内的数，所以用不到这条规则）
 * 
 * 其次，罗马数字转阿拉伯数字规则（仅限于3999以内）：
 *
 */

public class RomanToInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(romanToInt("MCMXCVI"));
	}

	public static int romanToInt(String s) {
		int length = s.length();
		if (s == null || length == 0) {
			return 0;
		}
		Map<Character, Integer> m = new HashMap<Character, Integer>();
		m.put('I', 1);
		m.put('V', 5);
		m.put('X', 10);
		m.put('L', 50);
		m.put('C', 100);
		m.put('D', 500);
		m.put('M', 1000);

		int pre = m.get(s.charAt(length - 1));
		int result = m.get(s.charAt(length - 1));
		for (int i = length - 2; i >= 0; i--) {
			int cur = m.get(s.charAt(i));
			if (cur >= pre) {
				result = result + cur;
			} else {
				result = result - cur;
			}
			pre = cur;
		}
		return result;
	}

	// method 2
	// https://leetcode.com/discuss/98994/understand-java-solution-beats-well-explained-with-comments
	// http://blog.csdn.net/wzy_1988/article/details/17057929
	public static int romanToInt2(String s) {
		int length = s.length();
		if (s == null || length == 0) {
			return 0;
		}
		Map<Character, Integer> m = new HashMap<Character, Integer>();
		m.put('I', 1);
		m.put('V', 5);
		m.put('X', 10);
		m.put('L', 50);
		m.put('C', 100);
		m.put('D', 500);
		m.put('M', 1000);

		int i, restul, pre, cur;

		restul = m.get((s.charAt(0)));

		for (i = 1; i < s.length(); i++) {
			pre = m.get((s.charAt(i - 1)));
			cur = m.get((s.charAt(i)));

			if (cur <= pre) {
				restul += cur;
			} else {
				restul = restul - pre * 2 + cur;
			}
		}

		return restul;
	}
}
