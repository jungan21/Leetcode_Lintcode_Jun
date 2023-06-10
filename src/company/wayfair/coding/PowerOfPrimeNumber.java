package company.wayfair.coding;

import java.util.ArrayList;

//https://www.geeksforgeeks.org/check-if-the-number-is-a-prime-power-number/
public class PowerOfPrimeNumber {

    /// Array to store the prime numbers
    static ArrayList<Integer> primes = new ArrayList<Integer>();

    // Function to check if the number can be represented as a power of prime
    public static int[] power_of_prime(int n) {
        for(int prime : primes) {
            if (n % prime == 0) {
                int c = 0;
                while(n % prime == 0) {
                    n /= prime;
                    c += 1;
                }
                // important condition
                if(n == 1)
                    return new int[]{prime, c};
                else
                    return new int[]{-1, 1};
            }
        }
        return new int[]{-1, 1};
    }


    static boolean isPrime(int n){
        // Corner case
        if (n <= 1)
            return false;

        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;

        return true;
    }

    // Driver code
    public static void main(String args[])
    {
        int n = 49;

        for (int i = 2; i < n; i++){
            if (isPrime(i)){
                primes.add(i);
            }
        }


        // Function call
        int arr[] = power_of_prime(n);

        if (arr[0] > 1)
            System.out.println(arr[0] + " ^ " + arr[1]);
        else
            System.out.println("-1");
    }
}
