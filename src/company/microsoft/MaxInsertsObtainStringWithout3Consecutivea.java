package company.microsoft;

import java.util.Scanner;

/**
 * https://algo.monster/problems/max_inserts_to_obtain_string_without_3_consecutive_a
 *
 * Example 1:
 * Input: aabab
 * Output: 3
 * Explanation:
 * A string aabaabaa can be made
 *
 * Example 2:
 * Input: dog
 * Output: 8
 * Explanation:
 * A string aadaaoaagaa can be made
 */
public class MaxInsertsObtainStringWithout3Consecutivea {
    public static int maxInserts(String s) {
        int count_As = 0;
        int count_others = 0;
        int s_len = s.length();
        for (int i = 0; i < s_len; ++i) {
            if (s.charAt(i) == 'a') {
                count_As++;
            } else {
                count_others++;
                count_As = 0;
            }
            if (count_As == 3) {
                return -1;
            }
        }
        return 2 * (count_others + 1) - (s_len - count_others);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();
        int res = maxInserts(s);
        System.out.println(res);
    }
}
