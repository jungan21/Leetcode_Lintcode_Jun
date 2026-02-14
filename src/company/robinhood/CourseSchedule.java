package company.robinhoods;

import sun.security.krb5.internal.crypto.HmacSha1Aes128CksumType;

import java.util.*;

/**
 * http://www.programcreek.com/2014/05/leetcode-course-schedule-java/
 * <p>
 * http://www.cnblogs.com/grandyang/p/4484571.html
 */
public class CourseSchedule {

    public static void main(String[] args) {
        // int[][] A = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
        int[][] A = {{5, 8}, {3, 5}, {1, 9}, {4, 5}, {0, 2},
                {1, 9}, {7, 8}, {4, 9}};
        // int[][] A = { { 0, 1 }, { 1, 0 } };

        int[] results1 = new CourseSchedule().findOrder(10, A);
        int[] results2 = new CourseSchedule().findOrderJun(10, A);

        for (int r1: results1) {
            System.out.println(r1);
        }
        System.out.println("=====================");
        for (int r2: results2) {
            System.out.println(r2);
        }
    }


    // https://www.jiuzhang.com/solution/course-schedule
    // Jun最优就解
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return true;
        }

        int len = prerequisites.length;
        if (numCourses == 0 || len == 0) {
            return true;
        }
        Map<Integer, List<Integer>> graphMap = new HashMap<>();
        // track the in degrees for each course (each course is as node in graph):
        int[] inDegrees = new int[numCourses];
        for (int[] a : prerequisites) {
            // [1,0]    a[0] 需要先完成，才能完成a[1], 所以以a[1] 构建链接表图
            if (graphMap.containsKey(a[1])) {
                graphMap.get(a[1]).add(a[0]);
            } else {
                List<Integer> depList = new ArrayList<>();
                depList.add(a[0]);
                graphMap.put(a[1], depList);
            }
            inDegrees[a[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int[] result = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[index++] = course;
            if (graphMap.containsKey(course)) {
                for (int neighbor : graphMap.get(course)) {
                    inDegrees[neighbor]--;
                    if (inDegrees[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return index == numCourses;

    }


    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> graphMap = new HashMap<>();
        // track the in degrees for each course (each course is as node in graph):
        int[] inDegrees = new int[numCourses];
        for (int[] a : prerequisites) {
            // [1,0]    a[0] 需要先完成，才能完成a[1], 所以以a[1] 构建链接表图
            if (graphMap.containsKey(a[1])) {
                graphMap.get(a[1]).add(a[0]);
            } else {
                List<Integer> depList = new ArrayList<>();
                depList.add(a[0]);
                graphMap.put(a[1], depList);
            }
            inDegrees[a[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int[] result = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[index++] = course; // 等于  result[index] = course, index++ 两句，顺序不能乱

            if (graphMap.containsKey(course)) {
                for (int neighbor : graphMap.get(course)) {
                    inDegrees[neighbor]--;
                    if (inDegrees[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return index == numCourses ? result : new int[0];

    }

    public int[] findOrderJun(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> graphMap = new HashMap<>();
        // track the in degrees for each course (each course is as node in graph):
        // int[] inDegrees = new int[numCourses];
        Map<Integer, Integer> indegreesMap = new HashMap<>();

        for (int[] a : prerequisites) {
            // [1,0]    a[0] 需要先完成，才能完成a[1], 所以以a[1] 构建链接表图
            if (graphMap.containsKey(a[1])) {
                graphMap.get(a[1]).add(a[0]);
            } else {
                List<Integer> depList = new ArrayList<>();
                depList.add(a[0]);
                graphMap.put(a[1], depList);
            }
            //inDegrees[a[0]]++;
            // a[1] finish, then can take a[0]
           indegreesMap.put(a[0], indegreesMap.getOrDefault(a[0], 0) + 1);
//            if(indegreesMap.containsKey(a[0])){
//                indegreesMap.put(a[0], indegreesMap.get(a[0]) + 1);
//            } else {
//                indegreesMap.put(a[0], 0);
//            }

        }

        System.out.println("indegreesMap" + indegreesMap);

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++){
            if(!indegreesMap.containsKey(i)) {
                queue.offer(i);
            }
        }


       // System.out.println("queue" + queue);
        int[] result = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[index++] = course; // 等于  result[index] = course, index++ 两句，顺序不能乱

            if (graphMap.containsKey(course)) {
                for (int neighbor : graphMap.get(course)) {
                    int updatedInDegree = indegreesMap.get(neighbor) - 1;
                    indegreesMap.put(neighbor, updatedInDegree);

                    if (updatedInDegree == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return index == numCourses ? result : new int[0];
    }
}