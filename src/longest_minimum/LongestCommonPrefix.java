package longest_minimum;

/**
 * Write a function to find the longest common prefix string amongst an array of
 * strings.
 * 
 */
public class LongestCommonPrefix {

	public static void main(String[] args) {
		String[] str = { "ABCD", "ABEF", "ACEF" };
		System.out.println(longestCommonPrefix(str));
	}

	public static String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}

		if (strs.length == 1)
			return strs[0];

		// int minLen = strs.length + 1;

		int minLen = Integer.MAX_VALUE;

		for (String str : strs) {
			if (str.length() < minLen) {
				minLen = str.length();
			}
		}

		/**
		 * To solve this problem, we need to find the two loop conditions. One
		 * is the length of the shortest string. The other is iteration over
		 * every element of the string array.
		 */
		for (int i = 0; i < minLen; i++) {
			for (int j = 0; j < strs.length - 1; j++) {
				String s1 = strs[j];
				String s2 = strs[j + 1];
				if (s1.charAt(i) != s2.charAt(i)) {
					// for subString(i, j): return i to j-1
					return s1.substring(0, i);
				}
			}
		}

		return strs[0].substring(0, minLen);
	}

	// method2 http://www.jiuzhang.com/solutions/longest-common-prefix/
	// 1. Method 1, start from the first one, compare prefix with next string,
	// until end;
	// 2. Method 2, start from the first char, compare it with all string, and
	// then the second char
	// I am using method 1 here

	/**
	 * 思路，就是用第一个与第二个的prefix, 再去与第三个找
	 * 
	 */
	public String longestCommonPrefix2(String[] strs) {
		if (strs == null || strs.length == 0) {
			// return "", 而不是null
			return "";
		}
		String prefix = strs[0];
		int j = 0;
		for (int i = 1; i < strs.length; i++) {
			j = 0;
			while (j < strs[i].length() && j < prefix.length()
					&& strs[i].charAt(j) == prefix.charAt(j)) {
				j++;
			}
			if (j == 0) {
				return "";
			}
			// 0 to j-1 is the common prefix, reason we use (0,j) is we got j++
			// in while loop
			prefix = prefix.substring(0, j);
		}
		return prefix;
	}

}
