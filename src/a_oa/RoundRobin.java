package a_oa;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 一个处理器要处理一堆request，一次只能处理一条，每次执行一个任务最多执行时间q，接着执行等待着的下一个任务。若前一个任务没执行完则放到队尾，
 * 等待下一次执行
 * 
 * 假设只要有任务开始以后cpu是不会空闲的，也就是说cpu开始后如果空闲了就说明没有任务了，另外Robin Round最后返回值是float,
 * 求平均等待时间
 * 
 * 
 * http://blog.csdn.net/lycorislqy/article/details/49172941
 * 
 * round robin 用 priority queue写的，没有啥问题啊= =Process 类也没有写static。。
 * 
 * 给一个int[] arrival time, int[] Execution time, int q. 例子： 【0，1，4】 【5，2，3】 q=3.
 * 输出的是average wait time 2.3333333
 * 
 * 给出几个Process的request time和duration求average waiting time。
 *
 *
 * 示例输入： request time: [0,1,3,9] duration: [2,1,7,5] q=3 average waiting time :
 * 0.5
 * 
 * request time: [0,2,4,5] duration: [7,4,1,4] q=3 average waiting time : 7
 * 
 * 
 * round robin补充一点我的题里写的是可以假设只要有任务开始以后cpu是不会空闲的，也就是说cpu开始后如果空闲了就说明没有任务了
 * 
 * input ｛0，1，4｝｛5，2，3｝3，在第三秒的时候p1执行了三秒，还剩两秒，然后p2p3
 * 到达。Amazon给的testcase解释是把p1放在p3
 * 前面，因为p1先进的queue，然而我看youtube上一些视频，是把p3在p1前面。两种情况都可以？不知道标准是什么。。。
 * 
 * 
 * 
 */

class Process {
	int arriveTime;
	int excuteTime;

	Process(int arr, int exc) {
		this.arriveTime = arr;
		this.excuteTime = exc;
	}
}

public class RoundRobin {

	public static void main(String[] args) {
		int[] arrive = { 0, 1, 4 };
		int[] execute = { 5, 2, 3 };
		int q = 3;
		System.out.println(roundRobin(arrive, execute, q));
		System.out.println(roundRobinJun(arrive, execute, q));
	}

	public static float roundRobinJun(int[] arrive, int[] execute, int q) {
		if (arrive == null || execute == null
				|| arrive.length != execute.length) {
			return 0;
		}
		int nextProIndex = 0;
		int curTime = 0;
		int waitTime = 0;
		Queue<Process> queue = new LinkedList<>();
		while (!queue.isEmpty() || nextProIndex < arrive.length) {
			if (!queue.isEmpty()) {
				Process cur = queue.poll();
				waitTime += curTime - cur.arriveTime;
				curTime += Math.min(cur.excuteTime, q);
				while (nextProIndex < arrive.length
						&& arrive[nextProIndex] <= curTime) {
					queue.offer(new Process(arrive[nextProIndex],
							execute[nextProIndex]));
					nextProIndex++;
				}
				// 表示当前的这个 prcess在指定的时间段q内，没有执行完， 这种情况，这个剩余的部分的arrive time
				// 就是curTime, 执行时间就是剩下的一小部分
				if (cur.excuteTime > q) {
					queue.offer(new Process(curTime, cur.excuteTime - q));
				}
			} else {
				// 1. 初始情况
				// 2. 之前的process都已经run完，新的process的arrival
				// time还没有到。所以只需要更新curTime，将curTime设置为下一个process的arrivtime,不需要更新waitTime。
				Process p = new Process(arrive[nextProIndex],
						execute[nextProIndex]);
				queue.offer(p);
				curTime = arrive[nextProIndex++];
			}
		}
		return (float) waitTime / arrive.length;
	}

	// calculate waiting time: https://www.youtube.com/watch?v=KDjTNvuYC8Y
	// execution sequence: https://www.youtube.com/watch?v=aWlQYllBZDs

	// Assume arrive is sorted.
	// https://wenyuanjiao.gitbooks.io/algorithm_practice/content/round_robin.html
	public static float roundRobin(int[] arrive, int[] execute, int q) {
		if (arrive == null || execute == null
				|| arrive.length != execute.length) {
			return 0;
		}

		LinkedList<Process> queue = new LinkedList<>();
		int curTime = 0;
		int waitTime = 0;
		int nextProIdx = 0;
		while (!queue.isEmpty() || nextProIdx < arrive.length) {
			if (!queue.isEmpty()) {
				Process cur = queue.poll();
				waitTime += curTime - cur.arriveTime;
				// Note
				curTime += Math.min(cur.excuteTime, q);
				for (int i = nextProIdx; i < arrive.length; i++) {
					if (arrive[i] <= curTime) {
						queue.offer(new Process(arrive[i], execute[i]));
						// Note
						nextProIdx = i + 1;
					} else {
						break;
					}
				}
				if (cur.excuteTime > q) {
					queue.offer(new Process(curTime, cur.excuteTime - q));
				}
			} else {
				queue.offer(new Process(arrive[nextProIdx], execute[nextProIdx]));
				curTime = arrive[nextProIdx++];
			}
		}
		System.out.println(curTime);
		System.out.println(waitTime);
		return (float) waitTime / arrive.length;
	}
}
