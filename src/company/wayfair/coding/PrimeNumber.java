package company.wayfair.coding;

public class PrimeNumber {
    /**
     * is Prime number
     */
    static boolean isPrime_1(int n)
    {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    static boolean isPrime_2(int n)
    {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i <= n/2; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    static boolean isPrime_3(int n)
    {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;

        return true;
    }


    // Driver Program
    public static void main(String args[]) {
        System.out.println(isPrime_1(11));
        System.out.println(isPrime_1(15));
    }

}
