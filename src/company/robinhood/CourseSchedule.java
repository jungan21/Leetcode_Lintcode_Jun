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
        int[][] A = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
        int[] results2 = new CourseSchedule().findOrderJun(4, A);
    }

    // jiuzhang:: https://www.jiuzhang.com/solution/course-schedule
    public int[] findOrderJun(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graphMap = new HashMap<>();
        // int[] inDegrees = new int[numCourses];  
        Map<Integer, Integer> indegreesMap = new HashMap<>(); // track the in degrees for each course 

        for (int[] a : prerequisites) {
            // a[1]->a[0] 先take a[1], 以a[1] 构建图 类似loadfactor服务调用链{A->B|C} 图：A=[B, C]
            if (graphMap.containsKey(a[1])) {  
                graphMap.get(a[1]).add(a[0]);
            } else {
                List<Integer> depList = new ArrayList<>();
                depList.add(a[0]);
                graphMap.put(a[1], depList);
            }
            //inDegrees[a[0]]++;
           // 依赖关系：a[1]-> a[0] 计算a[0]的入度. 类似loadfactor服务调用链 {A->B|C}  A=[B, C] 计算B, C的入度
           indegreesMap.put(a[0], indegreesMap.getOrDefault(a[0], 0) + 1);
        }  // System.out.println("indegreesMap" + indegreesMap);

        Queue<Integer> queue = new LinkedList<>();   
        for (int i = 0; i < numCourses; i++){   // find Ingree == 0
            if(!indegreesMap.containsKey(i)) {  // if (inDegrees[i] == 0) 
                queue.offer(i);
            }
        }

        int[] result = new int[numCourses];  int index = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[index++] = course; // 等于  result[index] = course, index++ 两句，顺序不能乱

            if (graphMap.containsKey(course)) {
                for (int neighbor : graphMap.get(course)) {
                    int updatedInDegree = indegreesMap.get(neighbor) - 1; // inDegrees[neighbor]--;
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
