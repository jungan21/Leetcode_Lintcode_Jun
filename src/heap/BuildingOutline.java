package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given N buildings in a x-axis，each building is a rectangle and can be
 * represented by a triple (start, end, height)，where start is the start
 * position on x-axis, end is the end position on x-axis and height is the
 * height of the building. Buildings may overlap if you see them from far
 * away，find the outline of them。
 * 
 * An outline can be represented by a triple, (start, end, height), where start
 * is the start position on x-axis of the outline, end is the end position on
 * x-axis and height is the height of the outline.
 * 
 * 
 *
 */

class HeightPoint {
	int x, height;

	public HeightPoint(int x, int height) {
		this.x = x;
		this.height = height;
	}
}

public class BuildingOutline {

	public static void main(String[] args) {

	}

	public List<int[]> getSkyline(int[][] buildings) {
		List<int[]> rst = new ArrayList<int[]>();
		if (buildings == null || buildings.length == 0
				|| buildings[0].length == 0) {
			return rst;
		}

		// Init the list sorted by index && height
		List<HeightPoint> heightPoints = new ArrayList<HeightPoint>();
		for (int i = 0; i < buildings.length; i++) {
			heightPoints
					.add(new HeightPoint(buildings[i][0], -buildings[i][2]));
			heightPoints.add(new HeightPoint(buildings[i][1], buildings[i][2]));
		}
		Collections.sort(heightPoints, new Comparator<HeightPoint>() {
			public int compare(HeightPoint p1, HeightPoint p2) {
				if (p1.x == p2.x) {
					return p1.height - p2.height;
				} else {
					return p1.x - p2.x;
				}
			}
		});

		// Process height points
		// reversed priorityqueue, becase for same pos x, we always want the
		// highest point
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(1000,
				Collections.reverseOrder());
		queue.offer(0);
		int prev = queue.peek();

		for (HeightPoint p : heightPoints) {
			if (p.height < 0) {
				queue.offer(-p.height);
			} else {
				queue.remove(p.height);
			}

			int currPeak = queue.peek();
			if (currPeak != prev) {
				rst.add(new int[] { p.x, currPeak });
				prev = currPeak;
			}
		}

		return rst;
	}

}
