package graph_dfs;

import java.util.*;

/**
 * 
 * http://www.programcreek.com/2014/05/leetcode-course-schedule-java/
 *
 * http://www.cnblogs.com/grandyang/p/4484571.html
 */
public class CourseSchedule {

	public static void main(String[] args) {
		// int[][] A = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
		int[][] A = { { 5, 8 }, { 3, 5 }, { 1, 9 }, { 4, 5 }, { 0, 2 },
				{ 1, 9 }, { 7, 8 }, { 4, 9 } };
		// int[][] A = { { 0, 1 }, { 1, 0 } };
		System.out.println(canFinish(10, A));
	}

	/**
	 * Typical BFS， please refer: CourseSchedule2.java
	 */

	/**
	 * DFS: fast
	 * 
	 * http://www.geeksforgeeks.org/topological-sorting/
	 * 
	 * https://www.cs.usfca.edu/~galles/visualization/TopoSortDFS.html
	 * 
	 * http://www.cnblogs.com/yrbbest/p/4493547.html
	 * 
	 * Tarjan's Algorithms (wiki)： DFS based， loop through each node of the
	 * graph in an arbitrary order，initiating a depth-first search that
	 * terminates when it hits any node that has already been visited since the
	 * beginning of the topological sort or the node has no outgoing edges (i.e.
	 * a leaf node). 详细过程见Reference里 NYU的课件
	 * 
	 * 
	 * 
	 * 也需要建立有向图，还是用二维数组来建立，和BFS不同的是，我们像现在需要一个一维数组visit来记录访问状态，大体思路是，先建立好有向图，
	 * 然后从第一个门课开始
	 * ，找其可构成哪门课，暂时将当前课程标记为已访问，然后对新得到的课程调用DFS递归，直到出现新的课程已经访问过了，则返回false
	 * ，没有冲突的话返回true，然后把标记为已访问的课程改为未访问
	 * 
	 * http://www.cnblogs.com/grandyang/p/4484571.html
	 */

	public static boolean canFinish(int numCourses, int[][] prerequisites) {
		if (prerequisites == null) {
			throw new IllegalArgumentException("illegal prerequisites array");
		}
		int len = prerequisites.length;
		if (numCourses == 0 || len == 0) {
			return true;
		}

		// track visited courses
		int[] visited = new int[numCourses];

		/**
		 * build graph, 5预修课，然后才能take , 3和4
		 * 
		 * graph: 2->[0], 5->[3, 4], 8->[5, 7], 9->[1, 1, 4]
		 * 
		 * 和  GraphValidTree.java类似
		 * 
		 */
		HashMap<Integer, ArrayList<Integer>> graphMap = new HashMap<Integer, ArrayList<Integer>>();
		for (int[] a : prerequisites) {
			if (graphMap.containsKey(a[1])) {
				graphMap.get(a[1]).add(a[0]);
			} else {
				ArrayList<Integer> neighborList = new ArrayList<Integer>();
				neighborList.add(a[0]);
				graphMap.put(a[1], neighborList);
			}
		}
		/**
		 * 然后从第一个门课开始，找其可构成哪门课，暂时将当前课程标记为已访问，然后对新得到的课程调用DFS递归，直到出现新的课程已经访问过了，
		 * 则返回false，没有冲突(也就是环)的话返回true，然后把标记为已访问的课程改为未访问。
		 */
		for (int i = 0; i < numCourses; i++) {
			// canFinishDFS(graphMap, visited, i) 一般情况都返回true, 只有有环的时候false
			//
			if (!canFinishDFS(graphMap, visited, i)) {
				return false;
			}
		}
		return true;
	}

	private static boolean canFinishDFS(
			HashMap<Integer, ArrayList<Integer>> map, int[] visited, int i) {
		// -1 means visited already， 表示有环
		if (visited[i] == -1)
			return false;

		if (visited[i] == 1)
			return true;
		// -1 means visited
		visited[i] = -1;
		if (map.containsKey(i)) {
			for (int j : map.get(i)) {
				if (!canFinishDFS(map, visited, j))
					return false;
			}
		}
		visited[i] = 1;
		return true;
	}

	/**
	 * http://www.cnblogs.com/yrbbest/p/4493547.html
	 */
	public boolean canFinish2(int numCourses, int[][] prerequisites) {
		if (numCourses < 0 || prerequisites == null)
			return false;
		if (prerequisites.length == 0)
			return true;

		// build graph, adjacent list
		List<List<Integer>> adjListsGraph = new ArrayList<>();
		for (int i = 0; i < numCourses; i++)
			adjListsGraph.add(new ArrayList<>());
		for (int[] prerequisite : prerequisites)
			adjListsGraph.get(prerequisite[1]).add(prerequisite[0]);

		boolean[] visited = new boolean[numCourses];
		boolean[] onVisitingPath = new boolean[numCourses];

		for (int i = 0; i < numCourses; i++) {
			if (!visited[i]
					&& !canFinish(i, adjListsGraph, visited, onVisitingPath))
				return false;
		}
		return true;
	}
	// 如果 canFinish reutrn false, 如果不能完成， 如果能够完成，则返回true
	private boolean canFinish(int courseNum, List<List<Integer>> adjListsGraph,
			boolean[] visited, boolean[] onVisitingPath) {
		if (visited[courseNum]) {
			return true;
		}

		onVisitingPath[courseNum] = true;
		for (int dependent : adjListsGraph.get(courseNum)) {
			if (onVisitingPath[dependent]
					|| (!visited[dependent] && !canFinish(dependent,
							adjListsGraph, visited, onVisitingPath))) {
				return false;
			}
		}
		// backtracking
		onVisitingPath[courseNum] = false;

		// mark as visited
		visited[courseNum] = true;
		return true;
	}

	// https://www.jiuzhang.com/solution/course-schedule
	//
	public boolean canFinishJun(int numCourses, int[][] prerequisites) {
		if (prerequisites == null) {
			return true;
		}

		int len = prerequisites.length;
		if (numCourses == 0 || len == 0){
			return true;
		}
		// <Key:    Value: >
		Map<Integer, List<Integer>> graphMap = new HashMap<>();
		// track the in degrees for each course (each course is as node in graph):
		int[] inDegrees = new int[numCourses];
		for (int[] a : prerequisites) {
			if (graphMap.containsKey(a[1])){
				graphMap.get(a[1]).add(a[0]);
			} else {
				List<Integer> depList = new ArrayList<>();
				depList.add(a[0]);
				graphMap.put(a[1], depList);
			}
			inDegrees[a[0]]++;
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++){
			if(inDegrees[i] == 0) {
				queue.offer(i);
			}
		}

		int[] result = new int[numCourses];
		int index = 0;
		while(!queue.isEmpty()){
			int course = queue.poll();
			result[index++] = course;
			if(graphMap.containsKey(course)){
				for (int neighbor : graphMap.get(course)){
					inDegrees[neighbor]--;
					if(inDegrees[neighbor] == 0){
						queue.offer(neighbor);
					}
				}
			}
		}

		return index == numCourses;

	}
}
