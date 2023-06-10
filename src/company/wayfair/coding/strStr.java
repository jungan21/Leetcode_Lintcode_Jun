package company.wayfair.coding;

/**
 * Returns a index to the first occurrence of target in source,
 * 
 * or -1 if target is not part of source.
 */
public class strStr {

	public static void main(String[] args) {
		String str1 = "";
		String str2 = "";
		str2.contains(str1);
		// System.out.println(strStr("mississippi", "issip"));
		System.out.println(strStr(str1, str2));
	}

	// Jiuzhang: http://www.jiuzhang.com/solutions/implement-strstr/
	// 这种写法的好处是无需特别考虑 当 str1 和 str2 "", ""的情况
	public static int strStr(String source, String target) {
		/**
		 * 不能加上 source.length() == 0， 因为["", ""],的正确结果应该是0
		 * 
		 * target == null 条件不能少， ["abc", null] 正确结果应该是-1
		 */
		if (source == null || target == null
				|| source.length() < target.length()) {
			return -1;
		}

		/**
		 * 也可以写成 i <= source.length() - target.length()
		 */
		for (int i = 0; i < source.length() - target.length() + 1; i++) {
			// every time, j starts from 0
			int j = 0;
			for (j = 0; j < target.length(); j++) {
				// source.charAt(i + j) by using i+j, we don't need another
				// variable, just use j as the incremenal variable to make i
				// increase
				if (source.charAt(i + j) != target.charAt(j)) {
					break;
				}
			}
			// finished internal loop, target found
			if (j == target.length()) {
				return i;
			}
		}
		return -1;
	}

	// http://www.programcreek.com/2012/12/leetcode-implement-strstr-java/
	public static int strStr1(String str1, String str2) {
		if (str1 == null || str2 == null)
			return -1;

		if (str2.length() == 0)
			return 0;

		for (int i = 0; i < str1.length(); i++) {
			// critical
			if (i + str2.length() > str1.length()) {
				return -1;
			}

			int m = i;
			for (int j = 0; j < str2.length(); j++) {
				if (str2.charAt(j) == str1.charAt(m)) {
					if (j == str2.length() - 1) {
						// str1[i] = str2[0]
						return i;
					}
					m++;
				} else {
					// break internal for loop, starting from next i using
					// external loop
					break;
				}

			}
		}

		return -1;
	}

	public int strStr2(String source, String target) {
		if (source == null || target == null) {
			return -1;
		}

		if (target.length() > source.length()) {
			return -1;
		}
		int m = 0;
		for (int i = 0; i < source.length(); i++) {
			int j = 0;
			m = i;
			while (j < target.length() && source.charAt(m) == target.charAt(j)) {
				m++;
				j++;
			}

			if (j == target.length()) {
				return i;
			}
		}

		return -1;
	}
}
