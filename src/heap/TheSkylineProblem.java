package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/the-skyline-problem/
 *
 */
public class TheSkylineProblem {

	public static void main(String[] args) {
		// int[][] buildings = { { 1, 3, 4 }, { 3, 4, 4 }, { 2, 6, 2 }, { 8,
		// 11,4 }, { 7, 9, 3 }, { 10, 11, 2 } };

		int[][] buildings = { { 1, 3, 3 }, { 2, 4, 4 }, { 5, 6, 1 } };
		System.out
				.println(new TheSkylineProblem().getSkylineTreeMap(buildings));
	}

	/**
	 * https://www.youtube.com/watch?v=GSBLe8cKu0s
	 * http://www.jiuzhang.com/solutions/building-outline/
	 * 
	 * Time:
	 * 
	 * 最开始 对edgeList排序： O(nlogn)
	 * 
	 * n个元素元素， 每个进出Heap logn O(nlogn) (注意：如果要保证heap
	 * remove(object)操作是logn就必须用TreeMap或HashHeap)
	 * 
	 * ==>总的时间就是 ： O(nlon);
	 * 
	 *
	 */

	class Edge implements Comparator<Edge> {
		int x;
		boolean isStart;
		int height;

		Edge(int x, int height, boolean isStart) {
			this.x = x;
			this.height = height;
			this.isStart = isStart;
		}

		Edge() {

		}

		// 至于为什么这么排序，请参考： https://www.youtube.com/watch?v=GSBLe8cKu0s
		public int compare(Edge a, Edge b) {
			if (a.x != b.x) {
				return a.x - b.x;
			} else {
				// edge case1: 如果两个都是start,先考虑高点
				if (a.isStart && b.isStart) {
					return b.height - a.height;
				}
				// edge case2： 如果两个都是end, 先考虑低点
				if (!a.isStart && !b.isStart) {
					return a.height - b.height;
				}
				// edge case3： 如果a.end, 是 b.start, 那么先考虑b.start的edge
				// 只能是a.isStart,不能写成b.isStart
				// 其实true is 1, false, is 0, 如果要把true放前面也就是start放前面，其实就是逆序
				// 表示如果a.x=b.x的时候
				return a.isStart ? -1 : 1;
			}
		}

	}

	public List<List<Integer>> getSkyline(int[][] buildings) {

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (buildings == null || buildings.length == 0
				|| buildings[0].length == 0) {
			return result;
		}

		List<Edge> edgeList = new ArrayList<Edge>();
		for (int[] building : buildings) {
			edgeList.add(new Edge(building[0], building[2], true));
			edgeList.add(new Edge(building[1], building[2], false));
		}
		// 注意comparator的用法， Edge implements Comparator
		Collections.sort(edgeList, new Edge());

		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(
				buildings.length * 2, Collections.reverseOrder());

		// 堆中先加入一个零点高度，帮助我们在只有最矮的建筑物时选择最低值
		maxHeap.offer(0);
		int preMaxHeight = 0;
		for (Edge edge : edgeList) {
			if (edge.isStart) {
				maxHeap.offer(edge.height);
			} else if (!edge.isStart) {
				maxHeap.remove(edge.height);
			}

			int curMaxHeight = maxHeap.peek();
			if (preMaxHeight != curMaxHeight) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(edge.x);
				list.add(curMaxHeight);
				result.add(list);
				preMaxHeight = curMaxHeight;
			}
		}
		// return result;
		return result;
	}

	// this output is for Lintcode Building outline problem
	List<List<Integer>> output(List<List<Integer>> result) {
		List<List<Integer>> finalResult = new ArrayList<List<Integer>>();
		if (result.size() > 0) {
			int pre = result.get(0).get(0);
			int height = result.get(0).get(1);
			for (int i = 1; i < result.size(); i++) {
				ArrayList<Integer> now = new ArrayList<Integer>();
				int id = result.get(i).get(0);
				if (height > 0) {
					now.add(pre);
					now.add(id);
					now.add(height);
					finalResult.add(now);
				}
				pre = id;
				height = result.get(i).get(1);
			}
		}
		return finalResult;
	}

	/**
	 * remove(object) from PriorityQueue takes O(n)time, for reducing this part
	 * to O(logn) we can use TreeMap
	 * 
	 */
	public List<List<Integer>> getSkylineTreeMap(int[][] buildings) {

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (buildings == null || buildings.length == 0
				|| buildings[0].length == 0) {
			return result;
		}

		List<Edge> edgeList = new ArrayList<Edge>();
		for (int[] building : buildings) {
			edgeList.add(new Edge(building[0], building[2], true));
			edgeList.add(new Edge(building[1], building[2], false));
		}
		// 注意comparator的用法， Edge implements Comparator
		Collections.sort(edgeList, new Edge());

		TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();

		// 0 is actually val, 1 is the count;
		treeMap.put(0, 1);
		int preMaxHeight = 0;
		for (Edge edge : edgeList) {
			if (edge.isStart) {
				if (treeMap.containsKey(edge.height)) {
					treeMap.put(edge.height, treeMap.get(edge.height) + 1);
				} else {
					treeMap.put(edge.height, 1);
				}
			} else if (!edge.isStart) {
				if (treeMap.get(edge.height) > 1) {
					treeMap.put(edge.height, treeMap.get(edge.height) - 1);
				} else {
					treeMap.remove(edge.height);
				}
			}
			// TreeMap is sorted according to the natural ordering of its
			// keys,即默认升序，lastKey就是key最大的那个也就是当前TreeMap里height最大的那个
			int curMaxHeight = treeMap.lastKey();
			if (preMaxHeight != curMaxHeight) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(edge.x);
				list.add(curMaxHeight);
				result.add(list);
				preMaxHeight = curMaxHeight;
			}
		}
		// return result;
		return result;
	}

	/**
	 * method 2
	 *
	 */

	class Height {
		int index;
		int height;

		Height(int index, int height) {
			this.index = index;
			this.height = height;
		}
	}

	/**
	 * http://codechen.blogspot.ca/2015/06/leetcode-skyline-problem.html?_sm_au_
	 * =isVmHvFmFs40TWRt
	 * 
	 * http://www.cnblogs.com/easonliu/p/4531020.html
	 * 
	 * (1) 自建一个名为Height的数据结构，保存一个building的index和height。约定，
	 * 当height为负数时表示这个高度为height的building起始于index
	 * ；height为正时表示这个高度为height的building终止于index。
	 * 
	 * (2) 对building数组进行处理，每一行[ Li, Ri, Hi
	 * ]，根据Height的定义，转换为两个Height的对象，即，Height(Li, -Hi) 和 Height(Ri, Hi)。
	 * 将这两个对象存入heights这个List中。
	 * 
	 * (3) 写个Comparator对heights进行升序排序，首先按照index的大小排序，若index相等，则按height大小排序，
	 * 以保证一栋建筑物的起始节点一定在终止节点之前。
	 * 
	 * (4)
	 * 将heights转换为结果。使用PriorityQueue对高度值进行暂存。遍历heights，遇到高度为负值的对象时，表示建筑物的起始节点
	 * ，此时应将这个高度加入PriorityQueue
	 * 。遇到高度为正值的对象时，表示建筑物的终止节点，此时应将这个高度从PriorityQueue中除去。
	 * 且在遍历的过程中检查，当前的PriorityQueue的peek
	 * ()是否与上一个iteration的peek()值（prev）相同，若否，则应在结果中加入[当前对象的index,
	 * 当前PriorityQueue的peek()]，并更新prev的值。
	 * 
	 * 思路是照抄这个链接的：http://www.cnblogs.com/easonliu/p/4531020.html
	 * 
	 * 根据x坐标值排序，然后遍历求拐点。求拐点的时候用一个最大化heap来保存当前的楼顶高度，遇到左边节点，就在heap中插入高度信息，
	 * 遇到右边节点就从heap中删除高度。分别用pre与cur来表示之前的高度与当前的高度，当cur !=
	 * pre的时候说明出现了拐点。在从heap中删除元素时要注意
	 * ，我使用priority_queue来实现，priority_queue并不提供删除的操作
	 * ，所以又用了别外一个unordered_map来标记要删除的元素
	 * 。在从heap中pop的时候先看有没有被标记过，如果标记过，就一直pop直到空或都找到没被标记过的值
	 * 。别外在排序的时候要注意，如果两个节点的x坐标相同
	 * ，我们就要考虑节点的其它属性来排序以避免出现冗余的答案。且体的规则就是如果都是左节点，就按y坐标从大到小排
	 * ，如果都是右节点，按y坐标从小到大排，一个左节点一个右节点，就让左节点在前。
	 * 
	 */

	public List<List<Integer>> getSkyline1(int[][] buildings) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (buildings == null || buildings.length == 0
				|| buildings[0].length == 0) {
			return result;
		}

		List<Height> heights = new ArrayList<Height>();
		for (int[] building : buildings) {
			heights.add(new Height(building[0], -building[2]));
			heights.add(new Height(building[1], building[2]));
		}
		Collections.sort(heights, new Comparator<Height>() {
			public int compare(Height h1, Height h2) {
				return h1.index != h2.index ? h1.index - h2.index : h1.height
						- h2.height;
			}
		});

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(1000,
				Collections.reverseOrder());
		// https://www.youtube.com/watch?v=GSBLe8cKu0s
		// http://www.jiuzhang.com/solutions/building-outline/
		pq.offer(0);
		int prev = 0;
		for (Height h : heights) {
			if (h.height < 0) {
				pq.offer(-h.height);
			} else {
				pq.remove(h.height);
			}
			int cur = pq.peek();
			if (cur != prev) {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(h.index);
				list.add(cur);
				result.add(list);
				prev = cur;
			}
		}

		return result;
	}

}
