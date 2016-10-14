package UnitTest;

import java.util.Scanner;

public class SannnerTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int arr[] = new int[n];
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += in.nextInt();
		}
		System.out.println(sum);
	}

}
