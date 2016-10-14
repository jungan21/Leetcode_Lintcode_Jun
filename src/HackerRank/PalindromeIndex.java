package HackerRank;

import java.util.Scanner;
//http://juliachencoding.blogspot.ca/2016/03/hackerrank-string-algorithm-palindrome.html
public class PalindromeIndex {

	/**
	 * method 1;
	 */
	public static int palindromeIndex(String str, int start, int end) {
		if (start >= end) {
			return -1;
		}

		if (str.charAt(start) == str.charAt(end)) {
			return palindromeIndex(str, start + 1, end - 1);
		} else {
			// remove end
			String temp = str.substring(start, end);
			// means after we remove "end", it becomes palindrome
			if (isPallindrome(temp)) {
				return end;
			} else {
				return start;
			}
		}

	}

	public static boolean isPallindrome(String str) {
		int i = 0;
		int j = str.length() - 1;
		while (i < j) {
			if (str.charAt(i) != str.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	/**
	 * method 2
	 */

	public static int findIndex(String str, int i, int j) {
		while (i < j) {
			if (str.charAt(i) != str.charAt(j)) {
				// 如果 i+1表示 remove i reutrn -1表示 remove i后成为了palindrome, 所以i就是结果
				return findIndex(str, i + 1, j) == -1 ? i : j;
			}
			i++;
			j--;
		}
		return -1;
	}

	/**
	 * method 3
	 */

	public static int findIndex1(String str, int i, int j) {
		int remove_pos = -1;
		while (i < j) {
			if (str.charAt(i) != str.charAt(j)) {
				// detect if we need to remove i or j
				remove_pos = str.charAt(i + 1) == str.charAt(j)
						&& str.charAt(i + 2) == str.charAt(j - 1) ? i : j;
				break;
			}
			i++;
			j--;
		}
		return remove_pos;
	}

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		for (int i = 0; i < K; i++) {
			String string = sc.next();
			int index = palindromeIndex(string, 0, string.length() - 1);
			System.out.println(index);
		}
	}

}
