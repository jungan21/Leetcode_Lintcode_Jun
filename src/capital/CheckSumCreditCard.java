//package capital;
//
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//public class CheckSumCreditCard {
//
//	public static void main(String[] args) throws FileNotFoundException {
//		/* Enter your code here. Read input from STDIN. Print output to STDOUT */
//		//Scanner sc = new Scanner(System.in);
//		// long cardNumber = sc.nextInt();
//		// VALID: 4388576018410707
//		// INVALID: 4388576018402626
//		long cardNumber = 4388576018402626l;
//		if (isValid(cardNumber)) {
//			System.out.println("Yes");
//		} else {
//			System.out.println("No");
//		}
//	}
//	public static boolean isValid(long cardNumber) {
//		long sum = checkSumAlgorithm(cardNumber);
//		if (sum % 10 == 0) {
//			return true;
//		}
//		return false;
//	}
//
//	public static long checkSumAlgorithm(long number) {
//		long tempSum = 0;
//		// long evenSum = 0;
//		String num = number + "";
//		char[] c = num.toCharArray();
//		
//		if (c.length % 2 !=0){
//			for (int i = (c.length) - 1; i >= 0; i--) {
//				// odd position
//				// i is the array index, i+1 is the actual position
//				if ((i + 1) % 2 != 0) {
//					tempSum = tempSum + Character.getNumericValue(c[i]);
//					// even position
//				} else if ((i + 1) % 2 == 0) {
//					int tempValue = Character.getNumericValue(c[i]) * 2;
//					tempSum = tempSum + getDigitSum4EvenPosition(tempValue);
//				}
//			}
//		} else if (c.length % 2 ==0){
//			for (int i = (c.length) - 1; i >= 0; i--) {
//				// odd position
//				// i is the array index, i+1 is the actual position
//				if ((i + 1) % 2 == 0) {
//					oddSum = oddSum + Character.getNumericValue(c[i]);
//					System.out.print(Character.getNumericValue(c[i]) + ",");
//					// even position
//				} else if ((i + 1) % 2 != 0) {
//					int tempValue = Character.getNumericValue(c[i]) * 2;
//					evenSum = evenSum + getDigitSum4EvenPosition(tempValue);
//				}
//			}
//		}
//		
//		System.out.println("oddSum: " + oddSum);
//		System.out.println("evenSum: " + evenSum);
//		return oddSum + evenSum;
//	}
//	public static int getDigitSum4EvenPosition(int number) {
//		if (number <= 9) {
//			return number;
//		} else if (number > 9)
//			return (number % 10 + number / 10);
//		return number;
//	}
//
//}
