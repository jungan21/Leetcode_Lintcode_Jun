package company.microsoft;

import java.util.*;
import java.util.stream.Collectors;

public class LargestKbothKandKExist {

    public static int largestK(List<Integer> nums) {
        HashSet<Integer> set = new HashSet<>();
        int curMax = 0;
        for (int a : nums) {
            if (set.contains(-a))
                curMax = Math.max(curMax, Math.abs(a));
            else
                set.add(a);
        }
        return curMax;
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        int res = largestK(nums);
        System.out.println(res);
    }

//    public static List<String> splitWords(String s) {
//        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
//        scanner.close();
//        int res = largestK(nums);
//        System.out.println(res);
//    }
}
