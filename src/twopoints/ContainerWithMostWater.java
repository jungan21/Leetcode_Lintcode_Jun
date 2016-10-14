package twopoints;

public class ContainerWithMostWater {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 这道题挺类似二分查找法的，这道题是先从两头开始算面积，面积的计算要由短板决定，并维护一个当前最大面积。
	 * 
	 * 然后逐步替换小的短板来计算面积。每一步只替换短板的原因是，短板决定面积，而高板不影响，所以要想有所改变就改变那个有决定性的东西。。
	 * 
	 * http://www.cnblogs.com/springfor/p/3871017.html
	 * 
	 * http://blog.csdn.net/wzy_1988/article/details/17248209
	 * 
	 * 
	 * 
	 * Initially we can assume the result is 0. Then we scan from both sides. If
	 * leftHeight < rightHeight, move left point and find a value that is
	 * greater than leftHeight. Similarily, if leftHeight > rightHeight, move
	 * right point and find a value that is greater than rightHeight.
	 * Additionally, keep tracking the max value.
	 */
	public int maxArea(int[] height) {
		if (height == null || height.length == 0)
			return 0;

		int low = 0, high = height.length - 1;
		int max = 0;
		while (low < high) {
			int area = (high - low) * Math.min(height[low], height[high]);

			max = Math.max(max, area);
			if (height[low] < height[high])
				low++;
			else
				high--;
		}
		return max;
	}

	/**
	 * Jun's
	 */

	public int maxAreaJun(int[] heights) {
		if (heights == null || heights.length == 0) {
			return 0;
		}

		int left = 0;
		int right = heights.length - 1;
		int leftH = heights[left];
		int rightH = heights[right];

		int max = Integer.MIN_VALUE;
		while (left < right) {
			max = Math.max(max, (right - left) * Math.min(leftH, rightH));
			if (leftH < rightH) {
				// 能灌水多少水，是有矮的决定的，所以尽量找高点的，也就是左右两边高度差较小比较好
				// 如果这时候你移动rightH, 无论下一个rightH是高还是低，面积都只会小，宽度变小，高度只可能比leftH高，或低
				left++;
				leftH = heights[left];
			} else {
				right--;
				rightH = heights[right];
			}
		}
		return max;
	}

}
