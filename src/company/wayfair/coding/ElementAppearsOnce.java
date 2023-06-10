package company.wayfair.coding;


/**
 * The best solution is to use XOR. XOR of all array elements gives us the number with a single occurrence. The idea is based on the following two facts.
 *
 *  XOR of a number with itself is 0.
 * XOR of a number with 0 is number itself.
 *
 * Let ^ be xor operator as in C and C++.
 *
 * res = 7 ^ 3 ^ 5 ^ 4 ^ 5 ^ 3 ^ 4
 *
 * Since XOR is associative and commutative, above
 * expression can be written as:
 * res = 7 ^ (3 ^ 3) ^ (4 ^ 4) ^ (5 ^ 5)
 *     = 7 ^ 0 ^ 0 ^ 0
 *     = 7 ^ 0
 *     = 7
 */
public class ElementAppearsOnce {
    static int findSingle(int ar[], int ar_size) {
        // Do XOR of all elements and return
        int res = ar[0];
        for (int i = 1; i < ar_size; i++)
            res = res ^ ar[i];

        return res;
    }

    // Driver code
    public static void main (String[] args) {
        int ar[] = {2, 3, 5, 4, 5, 3, 4};
        int n = ar.length;
        System.out.println("Element occurring once is " +
                findSingle(ar, n) + " ");
    }
}
