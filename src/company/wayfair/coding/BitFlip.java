package company.wayfair.coding;

public class BitFlip {
    // Find the total number of bits needed to be flipped to convert `x` to `y`
    public static int findBits(int x, int y) {
        // take XOR of `x` and `y` and store in `n`
        int n = x ^ y;

        System.out.println("n=" + Integer.toBinaryString(n));

        // Using Brian Kernighan’s algorithm to count set bits

        // `count` stores the total bits set in `n`
        int count = 0;

        while (n != 0)
        {
            //解释：https://blog.csdn.net/qq_26233899/article/details/104635692
            // 求某一个数的二进制表示中1的个数（无论正数还是补码负数）
            n = n & (n - 1);    // clear the least significant bit set
            count++;
        }

        return count;
    }

    public static void main(String[] args)
    {
        int x = 65;
        int y = 80;

        System.out.println(x + " in binary is " + Integer.toBinaryString(x));
        System.out.println(y + " in binary is " + Integer.toBinaryString(y));

        System.out.println("\nThe total number of bits to be flipped is " + findBits(x, y));
    }
}
