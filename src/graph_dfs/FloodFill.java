package graph_dfs;

import java.util.Arrays;

/**
 * Leetcode 733: https://leetcode.com/problems/flood-fill/
 *
 * Lintcode: 1062 https://us.jiuzhang.com/problems/info/1062
 */
public class FloodFill {
    int originalColor = 0;
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int[][] visited = new int[image.length][image[0].length];
        originalColor = image[sr][sc];
        helper (image, sr, sc, color, visited);

        return image;
    }

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    public void helper(int[][] image, int row, int col, int color, int[][] visited){
        if (row < 0 || col > image[row].length || visited[row][col] == 1){
            return;
        }
        visited[row][col] = 1;
        image[row][col] = color;

        for (int i = 0; i < 4; i++){
            int mx = row + dx[i];
            int my = col + dy[i];
            if (mx >=0 && mx < image.length && my>=0 && my < image[mx].length &&  image[mx][my] == originalColor){
                helper(image, mx, my, color, visited);
            }
        }
    }

}
