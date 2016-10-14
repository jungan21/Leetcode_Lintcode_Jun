package a_oa;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 第二道题我遇到的是SHORTEST JOB FIRST。 给你的INPUT是两个ARRAY，一个是每个JOB的START
 * TIME，一个是每个JOB的EXECUTION TIME。 叫你求AVERAGE WAITING TIME。 学完OS课忘了，或者没学过的，都不用急，
 * 它的描述里讲的很清楚怎么求，认识读几遍就行了
 * 
 * 一个处理器要处理一堆request，一次只能处理一条，如果它有几个积压着的requests，它会先执行持续时间短的那个；对于持续时间相等的requests
 * ，先执行最早到达处理器的request。问平均每个request要等多久才能被处理。input：requestTimes[]，
 * 每个request到达处理器的时间; durations[] 每个request要处理的持续时间。
 * 两个数组是一一对应的，并已按requestTimes[] 从小到大排序过
 * 
 * public double CalWaitingTime(int requestTimes[], int[] durations[]){}
 * 
 * 用priorityqueue做，地里有一个两层循环的答案，没仔细看，做完round
 * robin以后发现思路很相似。注意用priorityqueue写comparator的时候，要先判断两者的execute time,如果execute
 * time相同，则返回arrival time之差，即先按执行时间排序，若执行时间相同则按到达的时间排。
 * 
 */
public class ShortestJobFirst {

	public static void main(String[] args) {
		int[] arrive = { 0, 1, 4 };
		int[] execute = { 5, 2, 3 };
		System.out.println(aveWaitingTime(arrive, execute));
	}

	public static float aveWaitingTime(int[] arrive, int[] execute) {
		if (arrive == null || execute == null
				|| arrive.length != execute.length)
			return 0;
		int index = 0, length = arrive.length;
		int waitTime = 0, curTime = 0;
		PriorityQueue<Process> minHeap = new PriorityQueue<Process>(
				arrive.length, new Comparator<Process>() {
					public int compare(Process p1, Process p2) {
						if (p1.excuteTime == p2.excuteTime)
							return p1.arriveTime - p2.arriveTime;
						return p1.excuteTime - p2.excuteTime;
					}
				});

		while (!minHeap.isEmpty() || index < length) {
			if (!minHeap.isEmpty()) {
				Process cur = minHeap.poll();
				waitTime += curTime - cur.arriveTime;
				// 对于round robin: curTime += Math.min(cur.excuteTime, q);
				curTime += cur.excuteTime;
				while (index < length && arrive[index] <= curTime) {
					minHeap.offer(new Process(arrive[index], execute[index]));
					index++;
				}
				// 对于round robin 如果没执行完， 需要把剩下的部分再加入到queue中去
			} else {
				minHeap.offer(new Process(arrive[index], execute[index]));
				curTime = arrive[index++];
			}
		}
		return (float) waitTime / length;
	}
}
