package HackerRank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MissingNumbers {
	// http://www.cnblogs.com/sunshineatnoon/p/3914611.html
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] CountA = new int[10001];
		int[] CountB = new int[10001];
		// nextDouble
		int n = in.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
			CountA[a[i]]++;
		}

		int m = in.nextInt();
		int[] b = new int[m];
		for (int i = 0; i < m; i++) {
			b[i] = in.nextInt();
			CountB[b[i]]++;
		}

		for (int i = 1; i <= 10000; i++) {
			if (CountB[i] > CountA[i])
				System.out.print(String.valueOf(i) + " ");
		}
	}

	// 最后一个test case过不了，，Time out,数据太大
	public static void main2(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> A = new ArrayList<Integer>();
		ArrayList<Integer> B = new ArrayList<Integer>();

		int sizeA = sc.nextInt();
		for (int i = 0; i < sizeA; i++) {
			A.add(sc.nextInt());
		}

		int sizeB = sc.nextInt();
		for (int i = 0; i < sizeB; i++) {
			B.add(sc.nextInt());
		}

		for (Integer num : A) {
			// 必须是Integer
			if (B.contains(num)) {
				B.remove(num);
			}
		}

		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int num : B) {
			if (!result.contains(num)) {
				result.add(num);
			}
		}
		Collections.sort(result);
		for (int num : result) {
			System.out.print(num + " ");
		}
	}

	/**
	 * read from BufferedReader
	 * 
	 * 输入文件里的四行 也可以用下面的方法
	 * 
	 * Scanner in = new Scanner(System.in);
	 * 
	 * String line1 = in.nextLine();
	 * 
	 * String line2 = in.nextLine();
	 * 
	 * String line3 = in.nextLine();
	 * 
	 * String line4 = in.nextLine();
	 * 
	 */
	public static void main3(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		List<Integer> A = new ArrayList<Integer>();
		List<Integer> B = new ArrayList<Integer>();

		ArrayList<Integer> result = new ArrayList<Integer>();
		// get all the numbers in A list
		String[] ANumbers = in.readLine().split(" ");
		for (int i = 0; i < ANumbers.length; i++) {
			A.add(Integer.parseInt(ANumbers[i]));
		}
		// sort the A List
		Collections.sort(A);
		// get all the numbers in B list
		int m = Integer.parseInt(in.readLine());
		String[] BNumbers = in.readLine().split(" ");
		for (int i = 0; i < BNumbers.length; i++) {
			B.add(Integer.parseInt(BNumbers[i]));

		}
		// sort the A List
		Collections.sort(B);

		int i = 0, j = 0;
		while (i < A.size() && j < B.size()) {
			int numA = A.get(i);
			int numB = B.get(j);
			if (numA == numB) {
				i++;
				j++;
			} else {
				if (!result.contains(numB)) {
					result.add(numB);
				}
				j++;
			}
		}
		while (j < B.size()) {
			int numB = B.get(j);
			if (!result.contains(numB)) {
				result.add(numB);
			}
			j++;
		}
		for (Integer num : result) {
			System.out.print(num + " ");
		}
	}

	public static void main1(String[] args) {
		Scanner in = new Scanner(System.in);
		String line1 = in.nextLine();
		String line2 = in.nextLine();
		String line3 = in.nextLine();
		String line4 = in.nextLine();
		System.out.println(line1);
		System.out.println(line2);
		System.out.println(line3);
		System.out.println(line4);

	}
}
