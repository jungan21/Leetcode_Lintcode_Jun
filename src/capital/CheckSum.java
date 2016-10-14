package capital;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CheckSum {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		/* Enter your code here. Read input from STDIN. Print output to STDOUT */
		Scanner in = new Scanner(System.in);

		Long[] input = new Long[in.nextInt()];
		in.nextLine(); // consuming the <enter> from input above

		for (int i = 0; i < input.length; i++) {
			input[i] = Long.parseLong(in.nextLine());
		}
		
		System.out.println("No");
		for (long s : input) {
			// System.out.println("No");
			int total = sumOfEvenPlaces(s) + sumOfOddPlaces(s);
			if (isValid(total)) {
				System.out.println("Yes");
			} else if (!isValid(total)) {
				System.out.println("No");
			}
		}

	}

	public static boolean isValid(long total) {
		if (total % 10 == 0) {
			return true;
		}
		return false;
	}

	public static int getDigit(int number) {
		if (number <= 9) {
			return number;
		} else if (number > 9)
			return (number % 10 + number / 10);

		return number;
	}

	public static int sumOfEvenPlaces(long number) {
		int sum = 0;
		int remainder;
		number %= 10;
		while (number % 10 != 0 || number / 10 != 0) {
			remainder = (int) (number % 10);
			sum = sum + getDigit(remainder * 2);
			number /= 100;
		}
		return sum;

	}

	public static int sumOfOddPlaces(long number) {
		int sum = 0;
		int remainder;
		number /= 10;
		while (number % 10 != 0 || number / 10 != 0) {
			remainder = (int) (number % 10);
			sum = sum + getDigit(remainder * 2);
			number /= 100;
		}
		// System.out.println(sum);
		return sum;
	}

}
