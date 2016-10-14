package twopoints;

/**
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * For example, given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 */
public class TrappingRainWater {

	public static void main(String[] args) {
		// int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		int[] height = { 3, 0, 1, 4, 0, 1, 2 };

		System.out.println(trapRainWater(height));
	}

	/**
	 * O(N) Time and O(1) Space with Two Pointers
	 * 
	 * http://www.sigmainfy.com/blog/leetcode-trapping-rain-water.html
	 * 
	 * For each bar we get the highest bar on its left and right side. Then the
	 * water for this bar to hold only depends on these two bounds which is
	 * min(leftboud, rightboud) – its own height.
	 * 
	 */
	public static int trap(int[] height) {
		int result = 0;

		if (height == null || height.length < 2)
			return result;

		int left[] = new int[height.length];
		int right[] = new int[height.length];

		// scan from left to right
		int max = height[0];
		left[0] = height[0];
		for (int i = 1; i < height.length; i++) {
			// 相当于 对 left[i] 与 max 同时赋值
			left[i] = max = Math.max(max, height[i]);
		}

		// scan from right to left
		max = height[height.length - 1];
		right[height.length - 1] = height[height.length - 1];
		for (int i = height.length - 2; i >= 0; i--) {
			right[i] = max = Math.max(max, height[i]);
		}

		// calculate totoal
		for (int i = 0; i < height.length; i++) {
			result = result + Math.min(left[i], right[i]) - height[i];
		}

		return result;
	}

	/**
	 * Best one: jiuzhang two points 老师课堂上提到的
	 * 
	 * 其思路就是， 左右两个指针，从最两端开始，从矮的那个板开始灌水往里面灌水 即，从外往内灌水
	 * 
	 * http://www.cnblogs.com/grandyang/p/4402392.html Solution 3
	 * 
	 * O(n)
	 */
	public static int trapRainWater(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int result = 0;
		int left = 0;
		int right = height.length - 1;

		if (left >= right) {
			return result;
		}
		// 初始化成 最左边和最右边的木板高度，灌水是从两边往内部灌水， 并且从低的木板出往里灌，这样 由于另外一端高，就可以保证水不会流出
		int leftH = height[left];
		int rightH = height[right];
		// 下面的条件写成 while (left < right)也可以
		while (left < right) {
			// 左右两个指针，从两边开始，从矮的那个开始灌水
			if (leftH < rightH) {
				left++;
				if (leftH > height[left]) {
					result = result + (leftH - height[left]);
				} else {
					leftH = height[left];
				}
			} else {
				right--;
				if (rightH > height[right]) {
					result = result + (rightH - height[right]);
					// 下一个木板比我高， 所有不能灌水，但是必须更新 此时右边的rightH 的值
				} else {
					rightH = height[right];
				}
			}
		}
		return result;
	}

	/**
	 * jiuzhang two points
	 */
	public static int trapRainWater1(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int start = 0;
		int end = height.length - 1;
		int smaller;
		int area = 0;
		while (start < end) {
			if (height[start] < height[end]) {
				smaller = height[start];
				while (start < end && height[start] <= smaller) {
					area += smaller - height[start];
					start++;
				}
			} else {
				smaller = height[end];
				while (start < end && height[end] <= smaller) {
					area += smaller - height[end];
					end--;
				}
			}
		}
		return area;
	}

	/**
	 * two
	 * points:http://www.sigmainfy.com/blog/leetcode-trapping-rain-water.html
	 * 
	 * key fact is that we only need constant memory to keep the maximum height
	 * among [0..i] and [j..n-1], and if lMax < rMax, it means for the bar i,
	 * the left bound must be lMax, and the right bound does not affect how much
	 * rain bar i can hold! So we directly calculate the amount of water and add
	 * it to the results, the similar reasoning applies to the right side j.
	 * 
	 */
	public static int trap1(int[] A) {

		int i = 0, j = A.length - 1;
		int lMax = 0;
		int rMax = 0;

		int ret = 0;

		while (i <= j) {
			lMax = Math.max(lMax, A[i]); // max within [0..i]
			rMax = Math.max(rMax, A[j]); // max within [j..n-1]

			if (lMax < rMax) {
				ret += lMax - A[i++];
			} else {
				ret += rMax - A[j--];
			}
		}
		return ret;
	}

}
