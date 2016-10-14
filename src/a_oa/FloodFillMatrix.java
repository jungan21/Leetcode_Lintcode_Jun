package a_oa;

import java.util.Arrays;

public class FloodFillMatrix {

	public static void main(String[] args) {
		int screen[][] = { { 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 0, 0 }, { 1, 0, 0, 1, 1, 0, 1, 1 },
				{ 1, 2, 2, 2, 2, 0, 1, 0 }, { 1, 1, 1, 2, 2, 0, 1, 0 },
				{ 1, 1, 1, 2, 2, 2, 2, 0 }, { 1, 1, 1, 1, 1, 2, 1, 1 },
				{ 1, 1, 1, 1, 1, 2, 2, 1 }, };
		int x = 4, y = 4, newC = 3;
		floodFill(screen, x, y, newC);
		for (int i = 0; i < screen.length; i++) {
			System.out.println(Arrays.toString(screen[i]));
		}
	}

	/**
	 * http://www.geeksforgeeks.org/flood-fill-algorithm-implement-fill-paint/
	 * 
	 * https://scyforce.gitbooks.io/leetcode/content/flood_fill_a_matrix.html
	 */

	// It mainly finds the previous color on (x, y) and
	// calls floodFillUtil()
	public static void floodFill(int screen[][], int x, int y, int newC) {
		int prevC = screen[x][y];
		floodFillUtil(screen, x, y, prevC, newC);
	}

	// A recursive function to replace previous color 'prevC' at '(x, y)'
	// and all surrounding pixels of (x, y) with new color 'newC' and
	public static void floodFillUtil(int screen[][], int x, int y, int prevC,
			int newC) {
		// Base cases
		if (x < 0 || x >= screen.length || y < 0 || y >= screen[x].length
				|| screen[x][y] != prevC)
			return;

		// Replace the color at (x, y)
		screen[x][y] = newC;

		// Recur for north, east, south and west
		floodFillUtil(screen, x + 1, y, prevC, newC);
		floodFillUtil(screen, x - 1, y, prevC, newC);
		floodFillUtil(screen, x, y + 1, prevC, newC);
		floodFillUtil(screen, x, y - 1, prevC, newC);
	}

}
