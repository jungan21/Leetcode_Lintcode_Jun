package company.walmart;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Leetcode 403: https://leetcode.com/problems/frog-jump/
 * https://www.cnblogs.com/codingEskimo/p/8404523.html
 * https://us.jiuzhang.com/problems/info/622
 */
public class JumpFrog {
    /**
     * 这道题这个题意很难懂，其实是说第一个点是0， 第二个点是1，然后下面开始，每一次都可以跳上一次多跳的步数k／k＋1/k－1步
     * eg. 0 -> 1 k = 0 + 1
     * 　　1 -> 3 k = 1 + 1 (3 - 2 =1)
     * 　　3 -> 5 k = 2 + 0
     * 　　5 -> 8 k = 2 + 1
     * 　　8 -> 12 k = 3 + 1
     * 　　12 -> 17 k ＝ 4 + 1
     * 思路一：
     * 动态规划，想到动态规划是因为这是求状态的问题，同时给定的数据顺序有关系。需要注意的是下一次可以跳的步数，直接与上一次能跳的步数有关。
     * 在这里面我们设计一个map，其实key是stone的值，value是一个set，里面是存了该值可以前进的步数。
     * 然后我们依次循环除了最后的一个stones，每一个stones都拿出来得到它可以前进的步数，在这个stone的基础上加上步数（k/k+1/k-1），
     * 如果可以跳到其中的一个stone，我们需要把这个步数存下来，因为下一次这个点就可以在这个的基础上跳（k/k+1/k-1）
     * 最后就是看最后一个stone的set是不是空的，如果是空的就说明前面没有石头能跳到这一步。
     */
    public boolean canCross(int[] stones) {
        // write your code here
        if (stones == null || stones.length == 0) {
            return false;
        }
        // key是stone的值，value是一个set，里面是存了该值可以前进的步数。
        Map<Integer, Set<Integer>> dp = new HashMap<Integer, Set<Integer>>();
        for (int i = 0; i < stones.length; i++) {
            dp.put(stones[i], new HashSet<Integer>());
        }
        dp.get(0).add(0);
        //依次循环除了最后的一个stones，每一个stones都拿出来得到它可以前进的步数，在这个stone的基础上加上步数（k/k+1/k-1）
        for (int i = 0; i < stones.length - 1; i++) {
            int stone = stones[i];
            for (Integer k : dp.get(stone)) {
                if (k - 1 > 0 && dp.containsKey(stone + k - 1))
                    dp.get(stone + k - 1).add(k - 1);
                if (dp.containsKey(stone + k))
                    dp.get(stone + k).add(k);
                if (dp.containsKey(stone + k + 1))
                    dp.get(stone + k + 1).add(k + 1);
            }
        }
        //最后就是看最后一个stone的set是不是空的，如果是空的就说明前面没有石头能跳到这一步
        return !dp.get(stones[stones.length - 1]).isEmpty();
    }
}
