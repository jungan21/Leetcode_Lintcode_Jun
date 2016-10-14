package capital;
import java.util.Scanner;

public class FizzBuzz { // Everything in Java is a class
	public static void main(String[] args) { // Every program must have main()

		/* Enter your code here. Read input from STDIN. Print output to STDOUT */

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		if (N <= 0 || N >= Math.pow(10, 7)) {
			System.out.println("N constraints: 0 < N < 10^7");
			System.exit(0);
		} else {
			for (int i = 1; i <= N; i++) { // count from 1 to 100
				if (((i % 3) == 0) && ((i % 5) == 0)) // A multiple of both
					System.out.println("FizzBuzz");
				else if ((i % 3) == 0)
					System.out.println("Fizz"); // else a multiple of 3
				else if ((i % 5) == 0)
					System.out.println("Buzz"); // else a multiple of 5
				else
					System.out.println(i); // else just print it
			}
		}
	}
}