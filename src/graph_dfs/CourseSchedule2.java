package graph_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * http://www.cnblogs.com/yrbbest/p/4493547.html
 * http://www.cnblogs.com/yrbbest/p/4977294.html
 * 
 * http://www.cnblogs.com/grandyang/p/4504793.html
 * 
 * Time Complexity - O(V + E)，Space Complexity - O(V)
 */
public class CourseSchedule2 {

	public static void main(String[] args) {
		int[][] A = { { 5, 8 }, { 3, 5 }, { 1, 9 }, { 4, 5 }, { 0, 2 },
				{ 1, 9 }, { 7, 8 }, { 4, 9 } };
		System.out.println(Arrays.toString(findOrderBFS(10, A)));
		System.out.println(Arrays.toString(findOrderDFS(10, A)));
		System.out.println(Arrays.toString(findOrder(10, A)));
	}

	/**
	 * Best one: BFS 最快的 leetcode 9ms通过
	 * 
	 * Time Complexity: O(V + E). Space: O(V).
	 * 
	 * 此方法 保证访问每个点 每条边 仅仅一次
	 * 
	 * 要先修完课程5， 才能修3， 4， 5是前驱， 在图中有边:5->3, 5->4
	 * 
	 * graph: 0->[], 1->[], 2->[0], 3->[], 4->[], 5->[3, 4], 6->[], 7->[],
	 * 8->[5, 7], 9->[1, 1, 4]
	 * 
	 * 该方法构建的图与下面方法的方向相反，为每个点 分配一个List, 相当于key是某点A， 其list中的元素B A->[B,
	 * C],其实就是A是B,C的预修课, 这样构建图，我们就不容易找出入度为0的点，所有需要inDegree数组track
	 * 
	 * 
	 * 这里要使用List<List<>>来做adjacencyListsGraph，而不能用List<Set<>>来做，为什么呢？
	 * 因为假如题目给出的prerequisites里面有重复case的话
	 * ，我们下面的代码在计算inDegree的时候并没有区分，但使用List就可以避免这种情况了。速度还可以进一步优化。
	 * 
	 */
	public static int[] findOrderBFS(int numCourses, int[][] prerequisites) {
		if (numCourses < 0 || prerequisites == null)
			return new int[] {};
		List<List<Integer>> adjListsGraph = new ArrayList<>();
		// 这里不用map, 其下标index, 就相当于map里key的左右，直接用list.get(index), 起到和map一样的效果
		for (int i = 0; i < numCourses; i++) {
			adjListsGraph.add(new ArrayList<>());
		}

		int[] inDegrees = new int[numCourses];

		// build map, and 统计每个点的入度
		for (int[] prerequisite : prerequisites) {
			// prerequisite[1] 是预修课，
			adjListsGraph.get(prerequisite[1]).add(prerequisite[0]);
			inDegrees[prerequisite[0]]++;
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (inDegrees[i] == 0)
				queue.offer(i);
		}

		int[] result = new int[numCourses];
		int index = 0;
		while (!queue.isEmpty()) {
			int course = queue.poll();
			result[index++] = course;
			// 该方法每次只访问相邻的边，真正的BFS，下面的方法map不好，
			// 在图中的关系：ccourse->neighbor
			for (int neighbor : adjListsGraph.get(course)) {
				inDegrees[neighbor]--;
				if (inDegrees[neighbor] == 0) {
					queue.offer(neighbor);
				}
			}
		}
		// new int[] {};也行
		return (index == numCourses) ? result : new int[0];
	}

	/**
	 * 
	 * Best one: DFS 最快的 leetcode 8ms通过
	 * 
	 * Time Complexity: O(V + E). Space: O(V).
	 * 
	 * http://www.cnblogs.com/yrbbest/p/4977294.html
	 */

	public static int[] findOrderDFS(int numCourses, int[][] prerequisites) {
		if (numCourses < 0 || prerequisites == null)
			return new int[] {};
		// build graph
		List<List<Integer>> adjListsGraph = new ArrayList<>();
		for (int i = 0; i < numCourses; i++)
			adjListsGraph.add(new ArrayList<>());
		for (int[] prerequisite : prerequisites)
			adjListsGraph.get(prerequisite[1]).add(prerequisite[0]);

		boolean[] visited = new boolean[numCourses];
		boolean[] onVisitingPath = new boolean[numCourses];
		// record result in reversed order
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < numCourses; i++) {
			if (!visited[i]
					&& !canFindOrder(i, adjListsGraph, visited, onVisitingPath,
							stack))
				return new int[0];
		}

		int[] res = new int[numCourses];
		for (int i = 0; i < numCourses; i++)
			res[i] = stack.pop();
		return res;
	}

	private static boolean canFindOrder(int course,
			List<List<Integer>> adjListsGraph, boolean[] visited,
			boolean[] onVisitingPath, Stack<Integer> stack) {
		if (visited[course])
			return true;
		onVisitingPath[course] = true;
		for (int dependent : adjListsGraph.get(course)) {
			// onVisitingPath meas cycle
			if (onVisitingPath[dependent]
					|| !canFindOrder(dependent, adjListsGraph, visited,
							onVisitingPath, stack)) {
				return false;
			}
		}
		onVisitingPath[course] = false;
		visited[course] = true;
		stack.push(course);
		return true;
	}

	/**
	 * OLD BFS 版本, 不推荐 用了简单的数据结构: O(VE), space: O(V) 该算法效率偏低：
	 * 
	 * 因为while loop visit 每个点1次， 然后对于while loop里的for
	 * loop,表示每访问一个点的时候，把所有的边都要过一遍，所有时间是 O(V*E),
	 * 
	 * 要想提高效率，必须使用把输入图List of Edges的表达方式转变为Adjacency Lists的表达方式
	 */
	public static int[] findOrder(int numCourses, int[][] A) {
		if (A == null) {
			throw new IllegalArgumentException("illegal A array");
		}
		int len = A.length;
		int[] result = new int[numCourses];
		// 表示入度，预修课程list为空，直接返回输入的
		if (A == null | A.length == 0) {
			for (int i = 0; i < numCourses; i++) {
				result[i] = i;
			}
			return result;
		}

		// 统计每一门课程的入度，即预修课程
		int[] inDegree = new int[numCourses];
		for (int i = 0; i < len; i++) {
			inDegree[A[i][0]]++;
		}
		// indegree 为0的课程
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < numCourses; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}

		int courseCounter = 0;
		int index = 0;

		// 由于我们用的是BFS， 找出每一个level,的最大的吞吐量，即哪个level的节点最多
		int maxThroghput = 0;
		int throughputCounter = 0;

		while (!queue.isEmpty()) {
			int c = queue.poll();
			result[index++] = c;
			courseCounter++;
			throughputCounter = 0;
			// 每次需要变量全部 edge
			for (int i = 0; i < len; i++) {
				if (A[i][1] == c) {
					throughputCounter++;
					if (throughputCounter > maxThroghput) {
						maxThroghput = throughputCounter;
					}
					inDegree[A[i][0]]--;
					if (inDegree[A[i][0]] == 0) {
						queue.add(A[i][0]);
					}
				}
			}
		}
		System.out.println("maxThroghput: " + maxThroghput);
		return courseCounter == numCourses ? result : new int[0];
	}

	/**
	 * Jun's Adjacent List 133ms
	 * 
	 * 
	 * 方式存储图， 这里用HashSet,而不用ArrayList,是因为 预修课程的输入里有可能有重复，譬如 1 <- 9,1 <-
	 * 9即1的预修课是9， 可能会出现2次
	 * 
	 * <key value>: key: 课程， value：该课程的所有的预修课程列表， key 到 value
	 * list里的每个点就相当于一条edge, 之所有这样build graph, 因为可以根据， value
	 * 的size==0来判断key的node入度是否为0， 否则，如果方向，反过来，则需要额外的inDegree数组来track 每个点
	 * inDegree
	 * 
	 * 由于 while loop, 里的 for loop
	 * 
	 * Time Complexity - O(V*E)，Space Complexity - O(V)
	 * 
	 * graph map: <1, [9]>, <3, [5]>, <4, [5,9]>, <5, [8]>, <7, [8]> note:
	 * 表示4的，预修课是5和9
	 * 
	 */

	public static int[] findOrderJun(int numCourses, int[][] A) {
		int[] result = new int[numCourses];
		// build Adjacent List 表示的图结构
		// http://www.cnblogs.com/yrbbest/p/4977294.html
		// 不一定要用map, 因为课程编号是0ton, 可以直接映射到List的下标，所有可以直接用 List<Set<Integer>>
		// adjListsGraph,注意这里只能用Set因为有重复输入
		HashMap<Integer, HashSet<Integer>> graph = new HashMap<Integer, HashSet<Integer>>();
		for (int i = 0; i < A.length; i++) {
			HashSet<Integer> set = null;
			if (graph.containsKey(A[i][0])) {
				graph.get(A[i][0]).add(A[i][1]);
			} else {
				set = new HashSet<>();
				set.add(A[i][1]);
				graph.put(A[i][0], set);
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		// no indegree
		for (int i = 0; i < numCourses; i++) {
			if (!graph.containsKey(i)) {
				queue.offer(i);
			}
		}

		int courseCounter = 0;
		int index = 0;
		// O(V+E)
		// while loop visit each node
		while (!queue.isEmpty()) {
			Integer courseNo = queue.poll();
			result[index++] = courseNo;
			courseCounter++;
			// for loop visit each edge
			List<Integer> keysToBeRmoved = new ArrayList<>();
			for (Map.Entry<Integer, HashSet<Integer>> entry : graph.entrySet()) {
				Integer course = entry.getKey();
				HashSet<Integer> preSet = entry.getValue();
				if (preSet.contains(courseNo)) {
					// 如果courseno定义成int,就会remove
					// 第index位置的数字，只有定义为Integer,时候，才会remove这个数字
					preSet.remove(courseNo);
				}
				if (preSet.size() == 0) {
					queue.offer(course);
					keysToBeRmoved.add(course);
				}
			}
			// 对于预修课程集合为0的entry, 必须从map里移除掉，不让，上面的preSet.size()==0的条件会被多次满足
			for (Integer key : keysToBeRmoved) {
				graph.remove(key);
			}
		}
		return courseCounter == numCourses ? result : new int[0];
	}

}
