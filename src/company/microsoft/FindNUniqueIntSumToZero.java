package company.microsoft;

public class FindNUniqueIntSumToZero {
    public int[] sumZero(int n) {

        int[] result = new int[n];

        int index = 0;
        for (int i = 1; i <= n /2; i++){
            result[index] = i;
            result[index+1] = -i;
            index = index + 2;
        }

        if (n % 2 == 1){
            result[n-1] = 0;
        }
        return result;
    }
}
