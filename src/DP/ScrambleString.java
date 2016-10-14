package a.DP;

import java.util.Arrays;

/**
 * Given a string s1, we may represent it as a binary tree by partitioning it to
 * two non-empty substrings recursively.
 * 
 * Below is one possible representation of s1 = "great":
 * 
 *
 */
public class ScrambleString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean isScramble(String s1, String s2) {
		// Write your code here

		return true;
	}

	/**
	 * http://blog.csdn.net/fightforyourdream/article/details/17707187
	 */

	/**
	 * http://www.programcreek.com/2014/05/leetcode-scramble-string-java/
	 * 
	 * Recursive
	 * 
	 */

	public boolean isScrambleProgramCreek(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;

		if (s1.length() == 0 || s1.equals(s2))
			return true;

		if (!isValid(s1, s2)) {
			return false;
		}
		// Base Cases

		for (int i = 1; i < s1.length(); i++) {
			String s11 = s1.substring(0, i);
			String s12 = s1.substring(i, s1.length());
			String s21 = s2.substring(0, i);
			String s22 = s2.substring(i, s2.length());
			String s23 = s2.substring(0, s2.length() - i);
			String s24 = s2.substring(s2.length() - i, s2.length());

			if (isScramble(s11, s21) && isScramble(s12, s22))
				return true;
			if (isScramble(s11, s24) && isScramble(s12, s23))
				return true;
		}
		return false;
	}

	/**
	 * memorized search
	 * 
	 * dp[x][y][k] 表示是从s1串x开始,s2串y开始,他们后面k个字符组成的substr是Scramble String
	 * 
	 * • Function:
	 * 
	 * 对于所有i属于{1,k}
	 * 
	 * s11 = s1.substring(0, i); s12 = s1.substring(i, s1.length());
	 * 
	 * s21 = s2.substring(0, i); s22 = s2.substring(i, s2.length());
	 * 
	 * s23 = s2.substring(0, s2.length() - i); s24 = s2.substring(s2.length() -
	 * i, s2.length());
	 * 
	 * for i = x -> x+k
	 * 
	 * dp[x][y][k] = (dp[x][y][i] && dp[x+i][y+i][k-i]) || dp[x][y+k-i][i] &&
	 * dp[x+i][y][k-i])
	 * 
	 * Intialize: • dp[i][j][1] = s1[i]==s[j]. • Answer: dp[0][0][len]
	 */
	public boolean isScrambleMemorizedSearch(String s1, String s2) {
		int len = s1.length();
		// 第三个参数， len+1, 表示 前 i 个数
		int[][][] visit = new int[len][len][len + 1];
		return checkScramble(s1, 0, s2, 0, len, visit);
	}

	private boolean checkScramble(String s1, int start1, String s2, int start2,
			int k, int[][][] visit) {
		// k = s1.length
		if (visit[start1][start2][k] == 1)
			return true;
		if (visit[start1][start2][k] == -1)
			return false;

		if (s1.length() != s2.length()) {
			visit[start1][start2][k] = -1;
			return false;
		}

		if (s1.length() == 0 || s1.equals(s2)) {
			visit[start1][start2][k] = 1;
			return true;
		}

		if (!isValid(s1, s2)) {
			visit[start1][start2][k] = -1;
			return false;
		}// Base Cases

		for (int i = 1; i < s1.length(); i++) {
			String s11 = s1.substring(0, i);
			String s12 = s1.substring(i, s1.length());

			String s21 = s2.substring(0, i);
			String s22 = s2.substring(i, s2.length());

			String s23 = s2.substring(0, s2.length() - i);
			String s24 = s2.substring(s2.length() - i, s2.length());

			if (checkScramble(s11, start1, s21, start2, i, visit)
					&& checkScramble(s12, start1 + i, s22, start2 + i, k - i,
							visit)) {
				visit[start1][start2][k] = 1;
				return true;
			}

			if (checkScramble(s11, start1, s24, start2 + k - i, i, visit)
					&& checkScramble(s12, start1 + i, s23, start2, k - i, visit)) {
				visit[start1][start2][k] = 1;
				return true;
			}
		}
		visit[start1][start2][k] = -1;
		return false;
	}

	private boolean isValid(String s1, String s2) {
		char[] arr1 = s1.toCharArray();
		char[] arr2 = s2.toCharArray();
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		if (!(new String(arr1)).equals(new String(arr2))) {
			return false;
		}
		return true;
	}

}
