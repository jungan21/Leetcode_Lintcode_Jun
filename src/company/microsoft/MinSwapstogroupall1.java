package company.microsoft;

import java.util.ArrayList;
import java.util.List;

/**
 * Input : arr[] = {1, 0, 1, 0, 1}
 * Output : 1
 * Explanation: Only 1 swap is required to
 * group all 1's together. Swapping index 1
 * and 4 will give arr[] = {1, 1, 1, 0, 0}
 *
 * Input : arr[] = {1, 0, 1, 0, 1, 1}
 * Output : 1
 *
 * // https://www.geeksforgeeks.org/minimum-swaps-required-group-1s-together/
 */
public class MinSwapstogroupall1 {

    public static void main (String[] args) {
        int a[] = {1, 0, 1, 0, 1, 1};
        int n = a.length;
        System.out.println( minSwaps(a, n));
    }


    static int minSwaps(int arr[], int n) {
        int totalCount = 0; // To store total number of ones
        int i;
        for (i = 0; i < n; i++)
            totalCount += arr[i]; // Count total no of ones

        int currCount = 0; // To store count of ones in current window
        int maxCount = 0;  // To store maximum count ones out of all windows

        // start of window
        i = 0;
        // end of window
        int j = 0;
        while (j < n) {
            currCount += arr[j];

            // update maxCount when reach window size i.e. total count of ones in array
            if ((j - i + 1) == totalCount) {
                maxCount = Math.max(maxCount, currCount);
                if (arr[i] == 1)
                    currCount--; // decrease current count if first element of window is 1

                i++; // slide window
            }
            j++;
        }
        return totalCount - maxCount;
    }

}
