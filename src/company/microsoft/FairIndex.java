package company.microsoft;

import java.util.List;

// https://www.lintcode.com/problem/1882/
public class FairIndex {
    public int countIndexes(List<Integer> A, List<Integer> B) {
        // Write your code here.
        int length = A.size();
        long sumA = 0, sumB = 0;
        for (int i = 0; i < length; i++) {
            sumA += A.get(i);
            sumB += B.get(i);
        }
        if (sumA != sumB) {
            return 0;
        }
        int preA = 0, preB = 0;
        int answer = 0;
        // note:  i < length -1 !!!
        for (int i  = 0; i <length -1; i++){
            preA += A.get(i);
            preB += B.get(i);
            if (preA == preB && sumA - preA == preA){
                answer += 1;
            }
        }
        return answer;
    }
}
