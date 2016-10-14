package HackerRank;

import java.util.Scanner;

public class TwoStrings {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nums = sc.nextInt();

		for (int i = 0; i < nums; i++) {
			//这里不能用 sc.nextLine();
			String str1 = sc.next();
			String str2 = sc.next();
			
			System.out.println(hasCommon(str1, str2));
		}
	}

	public static String hasCommon(String str1, String str2) {
		int[] hash = new int[26];
		for (char c : str1.toCharArray()) {
			hash[c - 'a']++;
		}
		for (char c : str2.toCharArray()) {
			if (hash[c - 'a'] != 0) {
				return "YES";
			}
		}
		return "NO";
	}

}
