package HackerRank;

import java.util.Scanner;

public class FunnyString {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		int testNum = sc.nextInt();
		for (int i = 0; i < testNum; i++) {
			String result = isFunny(sc.next()) ? "Funny" : "Not Funny";
			System.out.println(result);
		}

	}

	private static boolean isFunny(String s) {
		for (int i = 1; i < s.length(); i++) {
			int diff1 = (int) (s.charAt(i) - s.charAt(i - 1));
			int diff2 = (int) (s.charAt(s.length() - 1 - i) - s.charAt(s
					.length() - 1 - (i - 1)));
			if (Math.abs(diff1) != Math.abs(diff2)) {
				return false;
			}
		}
		return true;
	}
}
