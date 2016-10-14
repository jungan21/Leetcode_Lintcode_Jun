package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 与JumpGameII.jave类似， 不同的是，每一步步长最多为3， 输出最短的步骤，并且输出最短路径的index
 * 
 * 我先想到的是dp（dp =1+min(dp[i-1], dp[i-2],
 * dp[i-3])，然后他问还有什么solution。经他提醒，貌似可以用三叉树，每一个node存index
 * ，每一分支代表步长。然后用BFS找到最多路经。他说这样可以，让我选一个方式，我当然选dp了。
 */
public class JumpGameIII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = { 2, 3, 1, 1, 4 };
		System.out.println(jump(A));
	}

	/**
	 * DP O(n^2), 坐标类型DP
	 * 
	 * also track index;
	 */

	public static int jump(int[] A) {

		int[] indexTracker = new int[A.length];
		int[] dp = new int[A.length];
		// dp[i], minimun steps need to jump to 0 from i
		dp[0] = 0;
		indexTracker[0] = 0;
		for (int i = 1; i < A.length; i++) {
			// if it can't reach, we will use the largest
			dp[i] = Integer.MAX_VALUE;
			int j = (i >= 3) ? i - 3 : 0;
			for (; j < i; j++) {
				if (dp[j] != Integer.MAX_VALUE && j + A[j] >= i) {
					if (dp[j] + 1 < dp[i]) {
						// 表示从j跳过来的
						indexTracker[i] = j;
						dp[i] = dp[j] + 1;
					}
				}
			}
		}
		//追溯 从哪里跳到最后一个位置的
		List<Integer> jumpIndex = new ArrayList<>();
		int index = indexTracker.length - 1;
		while (index != 0) {
			jumpIndex.add(indexTracker[index]);
			index = indexTracker[index];
		}
		
		System.out.println(Arrays.toString(indexTracker));
		System.out.println(Arrays.toString(dp));
		Collections.sort(jumpIndex);
		System.out.println(jumpIndex);
		return dp[A.length - 1];
	}

	/**
	 * Greedy - O(n)
	 * 
	 * http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
	 */

	public static int canJump1(int[] A) {
		// think it as merging n intervals
		if (A == null || A.length == 0) {
			return -1;
		}
		int start = 0, end = 0, jumps = 0;
		// 即end 到倒数第二个数停止
		// end 是index, 最后一个数字的index 是 A.length-1, 当 end >= A.length-1的时候必须停止
		while (end < A.length - 1) {
			jumps++;
			int farthest = end;
			for (int i = start; i <= end; i++) {
				if (A[i] + i > farthest) {
					farthest = A[i] + i;
				}
			}
			start = end + 1;
			end = farthest;
		}
		return jumps;
	}

	/**
	 * We use "last" to keep track of the maximum distance that has been reached
	 * by using the minimum steps "ret", whereas "curr" is the maximum distance
	 * that can be reached by using "ret+1" steps. Thus, curr = max(i+A[i])
	 * where 0 <= i <= last.
	 */
	public static int canJump2(int[] A) {
		int result = 0;
		int last = 0;
		int curr = 0;
		for (int i = 0; i < A.length; ++i) {
			if (i > last) {
				last = curr;
				++result;
			}
			curr = Math.max(curr, i + A[i]);
		}
		return result;
	}
}
