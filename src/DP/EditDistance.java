package DP;

/**
 * Given two words word1 and word2, find the minimum number of steps required to
 * convert word1 to word2. (each operation is counted as 1 step.)
 * 
 * You have the following 3 operations permitted on a word:
 * 
 * Insert a character, Delete a character, Replace a characte
 * 
 * Given word1 = "mart" and word2 = "karma", return 3 (replace t with m, add a
 * at the end, add k at the begaining)
 *
 */
public class EditDistance {

	public static void main(String[] args) {
		String word1 = "ma";
		String word2 = "kar";
		System.out.println(minDistance(word1, word2));
	}

	/**
	 * Best: http://www.programcreek.com/2013/12/edit-distance-in-java/
	 * 
	 * http://www.cnblogs.com/springfor/p/3896167.html
	 * 
	 * http://www.jiuzhang.com/solutions/edit-distance/
	 * 
	 * http://blog.csdn.net/linhuanmars/article/details/24213795
	 * 
	 */

	/**
	 * Let dp[i][j] stands for the edit distance between two strings with length
	 * i and j, i.e., word1[0,...,i-1] and word2[0,...,j-1]. There is a relation
	 * between dp[i][j] and dp[i-1][j-1]. here is a relation between dp[i][j]
	 * and dp[i-1][j-1]. Let's say we transform from one string to another. The
	 * first string has length i and it's last character is "x"; the second
	 * string has length j and its last character is "y". The following diagram
	 * shows the relation
	 * 
	 * @param word2
	 */
	public static int minDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();

		/**
		 * dp[i][j]表示从word1前i个字符转换到word2前j个字符最少的步骤数。
		 * 
		 * dp[len1][len2] 表示从word1前len1个字符转换到word2前len2个字符最少的步骤数。
		 * 
		 * len1+1,len2+1, because finally return dp[len1][len2]
		 */
		int[][] dp = new int[len1 + 1][len2 + 1];

		// i次delete
		for (int i = 0; i < len1 + 1; i++)
			dp[i][0] = i;

		// i次insert
		for (int j = 0; j < len2 + 1; j++)
			dp[0][j] = j;

		// iterate though, and check last char
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				/**
				 * if last two chars equal
				 * 
				 * when i = j =1时， dp[1][1]
				 * 表示从word1前1个字符转换到word2前1个字符最少的步骤数，前一个数字即表示第一个字母， 即下标为i-1 =0
				 * 
				 * length is i and j, however, the index would be i-1, j -1
				 */
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					// update dp value for +1 length
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					/**
					 * if word1.charAt(i - 1) != word2.charAt(j - 1)
					 */
					int replace = dp[i - 1][j - 1] + 1;
					// delete 表示， word1的前 i 个， 与word2前 j-1个
					// 是相等的，现在只需要在word1的等i个字符后面加一个字符就好
					int delete = dp[i][j - 1] + 1;
					// insert 表示， word1的前 i-1 个， 与word2前 j个
					// 是相等的，现在只需要在word1的等i个字符删除掉就好
					int insert = dp[i - 1][j] + 1;
					// dp[i][j]等于 上面三个变量里最小的那个，即看哪一种情况需要最少的步骤
					dp[i][j] = Math.min(replace, Math.min(delete, insert));
				}
			}
		}

		return dp[len1][len2];
	}

}
