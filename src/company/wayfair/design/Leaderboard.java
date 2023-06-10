package company.wayfair.design;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Leetcode 1244  similar to TopKFrequentWordsII.java
 */
public class Leaderboard {
    private Map<Integer, Integer> idToScoreMap = new HashMap<>();
    public void addScore(int playerId, int score) {
        idToScoreMap.merge(playerId, score, Integer::sum);
    }

    public int top(int K) {
        int ans = 0;
        Queue<Integer> minHeap = new PriorityQueue<>();

        for (final int score : idToScoreMap.values()) {
            minHeap.offer(score);
            if (minHeap.size() > K)
                minHeap.poll();
        }

        while (!minHeap.isEmpty())
            ans += minHeap.poll();

        return ans;
    }

    public void reset(int playerId) {
        idToScoreMap.remove(playerId);
    }


}
