package HackerRank;

import java.util.Scanner;

public class Pangrams {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.println(isPangram(str));
		//String str1 = "We promptly judged antique ivory buckles for the next prize";
	}
	
	
	public static String isPangram(String str) {
		str = str.toLowerCase();
		int[] hash = new int[26];
		for (char c : str.toCharArray()) {
			if (c == ' ') {
				continue;
			}
			hash[c - 'a']++;
		}
		for (int i = 0; i < 26; i++) {
			if (hash[i] == 0) {
				return "not pangram";
			}
		}
		return "pangram";
	}

}
