package HackerRank;

import java.util.Scanner;

// http://www.coderegister.co.in/2015/07/anagram-hackerrank-problem-solution.html
public class Anagram {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			String s = in.next();
			System.out.println(anagramCost(s));
		}
	}

	public static int anagramCost(String s) {
		int len = s.length();
		if (len % 2 == 1) {
			return -1;
		}
		int mid = len / 2;
		int[] hash = new int[26];

		for (int i = 0; i < mid; i++) {
			hash[s.charAt(i) - 'a']++;
		}
		int count = 0;
		for (int i = mid; i < len; i++) {
			if (hash[s.charAt(i) - 'a'] != 0) {
				hash[s.charAt(i) - 'a']--;
			} else {
				count++;
			}
		}

		return count;
	}

}
