package a_oa;

import java.util.Arrays;
import java.util.Scanner;

public class StockMaximize {

	public static void main1(String[] args) {

	}

	/**
	 * method 1:
	 */
	public static void main(String[] args) {
		System.out.println(2163790409L > Integer.MAX_VALUE);

		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		int total = sc.nextInt();
		long[] prices;
		for (int i = 0; i < total; i++) {
			int len = sc.nextInt();
			prices = new long[len];
			for (int j = 0; j < len; j++) {
				prices[j] = sc.nextInt();
			}
			System.out.println(maxProfit(prices));
		}
	}

	/**
	 * method 2: input as sc.nextLine();
	 */
	public static void main2(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		int total = Integer.valueOf(sc.nextLine());
		long[] prices;
		for (int i = 0; i < total; i++) {
			int len = Integer.valueOf(sc.nextLine());
			prices = new long[len];
			String[] temp = sc.nextLine().split(" ");
			for (int j = 0; j < len; j++) {
				prices[j] = Integer.valueOf(temp[j]);
			}
			System.out.println(Arrays.toString(prices));
			System.out.println(maxProfit(prices));
		}
	}

	// Best
	// https://krzychusan.wordpress.com/2014/03/29/hackerrank-stock-maximize/
	// https://www.hackerrank.com/challenges/stockmax
	public static long maxProfit(long[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		long result = 0;
		long maxFuturePrice = 0;
		for (int i = prices.length - 1; i >= 0; i--) {
			maxFuturePrice = Math.max(maxFuturePrice, prices[i]);
			result += maxFuturePrice - prices[i];
		}
		return result;
	}

}
