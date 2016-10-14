package HackerRank;

import java.util.Scanner;

public class StringConstruction {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for (int a0 = 0; a0 < n; a0++) {
			String s = in.next();
			System.out.println(constructCost(s));
		}
	}

	public static int constructCost(String s) {
		if (s == null || s.length() == 0)
			return 0;

		boolean[] hash = new boolean[26];
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			int index = s.charAt(i) - 'a';
			if (!hash[index]) {
				hash[index] = true;
				count++;
			}
		}
		return count;
	}

}
