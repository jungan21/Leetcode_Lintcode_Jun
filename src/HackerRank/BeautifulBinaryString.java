package HackerRank;

// https://www.hackerrank.com/contests/hourrank-8/challenges/beautiful-binary-string/forum/comments/130733
// https://www.hackerrank.com/contests/hourrank-8/challenges/beautiful-binary-string/submissions/code/5799648
import java.util.Scanner;

public class BeautifulBinaryString {

	public static void main(String[] args) {
		String str = "0100101010100010110100100110110100011100111110101001011001110111110000101011011111011001111100011101";
		String test = "abc";
		// System.out.println(countSteps(str));
		System.out.println(test.substring(2, 4));
	}

	public static void main1(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String B = in.next();
	}

	public static int countSteps(String str) {
		int cnt = 0;
		for (int i = 0; i <= str.length() - 3; i++) {
			if (str.substring(i, i + 3).equals("010")) {
				cnt++;
				i += 2;
			}
		}
		return cnt;
	}
}
