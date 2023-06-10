package array;

import java.util.Arrays;
import java.util.Comparator;


/**
 * Leetcode 593: https://leetcode.com/problems/valid-square/
 * Lintcode: 1149 https://us.jiuzhang.com/problems/info/1149
 * solution: https://us.jiuzhang.com/problems/info/1149
 */
public class ValidSquare {
    public double dist(int[] p1, int[] p2) {
        return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
    }
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p = {p1, p2, p3, p4};

        /**
         * 我们将这 4 个点按照 x 轴坐标第一关键字，y 轴坐标第二关键字进行升序排序。
         * 假设排完序之后的 4 个点依次为 p0, p1, p2, p3，那么我们可以发现正方形的四条边依次为 p0p1，p1p3，p3p2 和 p2p0，
         * 对角线为 p0p3 和 p1p2。下图给出了所有的 3 种排完序后的情
         */
        Comparator<int[]> comparator = new Comparator<int[]>(){
            public int compare(int[] p1, int[] p2) {
                if (p1[0] == p2[0]){
                    return p1[1] - p2[1];
                } else {
                    return p1[0] - p2[0];
                }
            }
        };
        Arrays.sort(p, comparator);

        return dist(p[0], p[1]) != 0
                && dist(p[0], p[1]) == dist(p[1], p[3])
                && dist(p[1], p[3]) == dist(p[3], p[2])
                && dist(p[3], p[2]) == dist(p[2], p[0])
                && dist(p[0], p[3]) == dist(p[1], p[2]);
    }
}
