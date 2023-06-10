package company.walmart;

import java.util.*;

// https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/
public class MinNumberWorkSessionsFinishTasks {

    int minSession = Integer.MAX_VALUE;
    public int minSessions(int[] tasks, int sessionTime) {
        helper(tasks, sessionTime, 0, new ArrayList<Integer>());
        return minSession;
    }

    public void helper(int[] tasks, int sessionTime, int index, List<Integer> sessions){

        //there has already been a setting when less buckets could satisfy requirements
        if(sessions.size() >= minSession)
            return;

        if(index == tasks.length){
            minSession = Math.min(minSession, sessions.size());
            return;
        }

        //now iterate over all the sessions and if there is space, put and backtrack again
        for(int i=0; i<sessions.size(); i++){
            if(tasks[index] + sessions.get(i) <= sessionTime){
                sessions.set(i, sessions.get(i) + tasks[index]);
                helper(tasks, sessionTime, index+1, sessions);
                sessions.set(i, sessions.get(i) - tasks[index]);
            }
        }

        sessions.add(tasks[index]);
        helper(tasks, sessionTime, index+1, sessions);
        sessions.remove(sessions.size() - 1);


    }


    //Method 2
    // https://hsiyinl.medium.com/minimum-number-of-work-sessions-to-finish-the-tasks-leetcode-1986-2a25474d9703

    // https://hackmd.io/@Zihao0917/HyqhyrFiq
    Map<Integer, Integer> cache;
    public int minSessions_Method2(int[] tasks, int sessionTime) {
        cache = new HashMap<>();
        return backtrack(tasks, sessionTime,  0, new ArrayList<>());
    }

    private int backtrack(int[] tasks, int sessionTime, int index, List<Integer> sessions) {
        if (index == tasks.length) {
            return sessions.size();
        }

        if (cache.containsKey(index) && sessions.size() >= cache.get(index)) {
            return cache.get(index);
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i) + tasks[index] <= sessionTime) {
                sessions.set(i, sessions.get(i) + tasks[index]);
                result = Math.min(result, backtrack(tasks, sessionTime, index + 1, sessions));
                sessions.set(i, sessions.get(i) - tasks[index]);
            }
        }

        sessions.add(tasks[index]);
        result = Math.min(result, backtrack(tasks, sessionTime, index + 1, sessions));
        sessions.remove(sessions.size()-1);
        cache.put(index, result);
        return result;
    }
}
