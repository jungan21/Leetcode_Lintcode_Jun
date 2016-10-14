package a_oa;

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

public class IntegerToRoman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(intToRoman(659));
		System.out.println(intToRomanBinarySearch(659));
		System.out.println(findIndex(659));
	}

	public static String intToRoman2(int num) {
		if (num <= 0) {
			return "";
		}
		int[] nums = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
				"IX", "V", "IV", "I" };
		StringBuilder res = new StringBuilder();
		int index = 0;
		while (num > 0) {
			int times = num / nums[index];
			num = num - nums[index] * times;
			for (; times > 0; times--) {
				res.append(symbols[index]);
			}
			index++;
		}
		return res.toString();
	}

	// method 2; easy to understand
	/**
	 * amazon follow up:
	 * 
	 * 给一个整数,返回这个整数对应的罗马数字中最大的单位代表的整数,比如8表示为罗马数字为VIII,最大单位为V,即5。
	 * 我用一次遍历实现的,面试官问怎么优化,我说可以二分搜索,复杂度降到O(logn)
	 */
	static String symbol[] = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
			"IX", "V", "IV", "I" };
	static int value[] = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

	public static String intToRoman(int num) {
		StringBuffer sb = new StringBuffer();

		// num > 0 as condition also works
		int i = 0;
		while (num != 0) {
			while (num >= value[i]) {
				num -= value[i];
				sb = sb.append(symbol[i]);
			}
			i++;
		}

		return sb.toString();
	}

	/**
	 * 上面的方法，优化，zai Value数组里找第一个>= num的数字
	 */
	public static String intToRomanBinarySearch(int num) {
		StringBuffer sb = new StringBuffer();
		// find first index with value <= num
		int index = 0;
		while (num != 0) {
			index = findIndex(num);
			num -= value[index];
			sb = sb.append(symbol[index]);
		}

		return sb.toString();
	}

	// first index <= num
	public static int findIndex(int num) {
		int start = 0;
		int end = value.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (value[mid] <= num) {
				end = mid;
			} else {
				start = mid;
			}
		}
		if (value[start] <= num) {
			return start;
		}
		if (value[end] <= num) {
			return end;
		}
		return -1;
	}

	/**
	 * recursive version
	 * 
	 * http://www.cnblogs.com/springfor/p/3886459.html
	 */

	public String intToRomanRecursive(int num) {
		if (num >= 1000)
			return "M" + intToRoman(num - 1000);
		if (num >= 900)
			return "CM" + intToRoman(num - 900);
		if (num >= 500)
			return "D" + intToRoman(num - 500);
		if (num >= 400)
			return "CD" + intToRoman(num - 400);
		if (num >= 100)
			return "C" + intToRoman(num - 100);
		if (num >= 90)
			return "XC" + intToRoman(num - 90);
		if (num >= 50)
			return "L" + intToRoman(num - 50);
		if (num >= 40)
			return "XL" + intToRoman(num - 40);
		if (num >= 10)
			return "X" + intToRoman(num - 10);
		if (num >= 9)
			return "IX" + intToRoman(num - 9);
		if (num >= 5)
			return "V" + intToRoman(num - 5);
		if (num >= 4)
			return "IV" + intToRoman(num - 4);
		if (num >= 1)
			return "I" + intToRoman(num - 1);
		return "";
	}
}
