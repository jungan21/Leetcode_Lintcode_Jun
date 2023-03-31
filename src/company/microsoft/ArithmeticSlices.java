package company.microsoft;

/**
 * https://us.jiuzhang.com/problem/arithmetic-slices/
 * https://leetcode.com/problems/arithmetic-slices/ 413
 */
public class ArithmeticSlices {

    public int numberOfArithmeticSlicesDP(int[] A) {
        /*
            option 2 DP
            use f(i) to represent the number of arithmetic slice ending with A[i]
            f(i) = f(i - 1) + 1 if A[i] - A[i-1] == A[i-1] - A[i - 2]
            total nummber of arithmetic slices to current elment
            res[i] = sum(f(k))  where 0 <= k <= i
            time complexity O(n) where n is the length of A
        */
        if(A == null || A.length == 0  || A.length < 2) {
            return 0;
        }
        int total = 0;
        int[] f = new int[A.length];
        f[0] = 0;
        f[1] = 0;
        for(int i = 2; i < A.length; i++) {
            if(A[i] - A[i-1] == A[i-1] - A[i-2]) {
                f[i] = f[i - 1] + 1;
                total += f[i];
            }
        }
        return total;
    }

//=============================================
    private int count = 0;
    public int numberOfArithmeticSlicesBF(int[] A) {
        /*
            option 1 brute force,
            loop though all the element and find next element that
            can be combined together with current element and become a arithmetic sequence
            count++ only if this arithetic sequence length >=3
            time complexity O(n^2)
        */
        for(int i = 1; i < A.length; i++) {
            numberOfArithmetic(A, i-1, i, A[i] - A[i-1], 2);
        }
        return count;
    }

    private void numberOfArithmetic(int[] A, int prev, int current, int diff, int len) {
        while(current < A.length){
            if(A[current] - A[prev] != diff) {
                return;
            }
            if(len >= 3) {
                count++;
            }
            prev = current;
            current += 1;
            len++;
        }
    }
}
