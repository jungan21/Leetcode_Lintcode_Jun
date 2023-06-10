package company.wayfair.coding;

/**
 * Given an array of integers (positive and negative), sort so all negative numbers are on the left, and positive are on the right while retaining their original order.
 *     Example:
 *     Input: [-2, 4, -19, 4, 0]
 *     Output: [-2, -19, 0, 4, 4]
 */
public class NegativePositiveNumbers {

    public static void main(String[] args) {
        int[] arr = {-13, -5, 6, -7, 5, -3, 11 };

        rearrangePosNegWithOrderJun(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    // https://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers/
    // int[] arr = {12， 11, -13, -5, 6, -7, 5, -3, 11 };
    public static void rearrangePosNegWithOrderJun(int[] arr)
    {
        //int i = 0, j = 0;
        int l= 0;
        for(int r = 0; r < arr.length; r++){
            if (arr[r] < 0){
                for (int k = r; k > l; k--) {
                    int temp = arr[k];
                    arr[k] = arr[k - 1];
                    arr[k - 1] = temp;
                }
                l++;
            }
        }
    }


    // int[] arr = {12， 11, -13, -5, 6, -7, 5, -3, 11 };
    public static void rearrangePosNegWithOrder(int[] arr)
    {
        int i = 0, j = 0;
        while (j < arr.length) {
            if (arr[j] >= 0) {
                j++;
            }
            else {
                for (int k = j; k > i; k--) {
                    int temp = arr[k];
                    arr[k] = arr[k - 1];
                    arr[k - 1] = temp;
                }
                i++;
                j++;
            }

        }
    }



}



