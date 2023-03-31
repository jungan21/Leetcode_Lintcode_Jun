package company.microsoft;

import datastructure.Queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * 1306. Jump Game III https://leetcode.com/problems/jump-game-iii/
 * https://algo.monster/problems/jump_game
 *
 * You are given an array of non-negative integers arr and a start index. When you are at an index i, you can move left or right by arr[i]. Your task is to figure out if you can reach value 0.
 *
 * Example 1:
 * Input: arr = [3, 4, 2, 3, 0, 3, 1, 2, 1], start = 7
 * Output: true
 * Explanation:
 * left -> left -> right
 *
 * Example 2:
 * Input: arr = [3, 2, 1, 3, 0, 3, 1, 2, 1], start = 2
 * Output: false
 *
 *
 */
public class JumpGame {

    public boolean canReach(int[] arr, int start) {
        if (arr[start] == 0) return true;

        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        return DFS(arr, visited, start);
    }

    public boolean DFS(int[] arr, Set<Integer> visited, int start) {

        if (arr[start] == 0 ){
            return true;
        }

        if(start + arr[start] < arr.length && !visited.contains(start + arr[start])){
            visited.add(start + arr[start]);
            return DFS(arr, visited, start + arr[start]);

        }

        if(start - arr[start] >= 0 && !visited.contains(start - arr[start])){
            visited.add(start - arr[start]);
            return DFS(arr, visited, start - arr[start]);
        }
        return false;
    }

    public boolean canReachBFS(int[] arr, int start) {
        if (arr[start] == 0) return true;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        HashSet<Integer> visited = new HashSet<>();
        visited.add(start);
        while(!queue.isEmpty()){
            int cur = queue.poll();

            if(arr[cur] == 0 ){
                return true;
            }

            int index = cur - arr[cur];
            if(index >=0 && !visited.contains(index)){
                visited.add(index);
                queue.offer(index);
            }

            index = cur + arr[cur];
            if (index <= arr.length-1 && !visited.contains(index)){
                visited.add(index);
                queue.offer(index);
            }

        }
        return false;
    }
}
