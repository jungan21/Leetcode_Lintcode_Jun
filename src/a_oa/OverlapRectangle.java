package a_oa;

/**
 * 给两个长方形的topLeft和bottomRight坐标, 判断这两个长方形是否重叠
 * 
 * Rectangle Overlap。这题和leetcode
 * 算相交面积的区别：它帮你定义好两个类，一个叫Point，一个叫Rectangle，Rectangle包括左上右下两个Point, Point包括x,
 * y的值， 这种细节并不影响程序，总之一句判断直接通过了全部20多个case.
 *
 */
public class OverlapRectangle {

	public static void main(String[] args) {

	}

	// Returns true if two rectangles (l1, r1) and (l2, r2) overlap
	boolean isOverlap(Point l1, Point r1, Point l2, Point r2) {
		// If one rectangle is on left side of other
		if (l1.x > r2.x || l2.x > r1.x)
			return false;

		// If one rectangle is above other
		if (l1.y < r2.y || l2.y < r1.y)
			return false;

		return true;
	}

}
