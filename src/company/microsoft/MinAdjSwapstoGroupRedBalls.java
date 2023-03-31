package company.microsoft;

/**
 * Input : arr[] = {1, 0, 1, 0, 1}
 * Output : 1
 * Explanation: Only 1 swap is required to
 * group all 1's together. Swapping index 1
 * and 4 will give arr[] = {1, 1, 1, 0, 0}
 *
 * Input : arr[] = {1, 0, 1, 0, 1, 1}
 * Output : 1
 */
//https://www.geeksforgeeks.org/minimum-swaps-required-group-1s-together/
public class MinAdjSwapstoGroupRedBalls {
    public static int minSwaps(int arr[], int n) {
        int noOfOnes = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] == 1)
                noOfOnes++;
        }
        return 0;
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine();
//        scanner.close();
//        int res = minSwaps(s);
//        System.out.println(res);
//    }
}
