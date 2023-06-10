package company.walmart;

public class FirstLastPositionofTarget {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int lastPosition(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}

		int start = 0, end = nums.length - 1;

		while (start + 1 < end) {
			/**
			 * for avoid overflow, we'b better use below method to get the mid
			 * position
			 * 
			 * for (start + end)/2,if start and end are too large, you can get
			 * overflow error
			 * 
			 * we can also use: int mid = start + (end - start) >> 1; (>>1 means
			 * /2)
			 */
			int mid = start + (end - start) / 2;

			if (nums[mid] == target) {
				// since look for last position, in the right half side there
				// might be numbers==target
				start = mid;
			} else if (nums[mid] > target) {
				end = mid;
			} else {
				start = mid;
			}
		}

		/**
		 * since we end condition is (start + 1) < end, after while loop, there
		 * are two cases: 1). start + 1 == end (in most cases) 2). start == end;
		 * 
		 * scine 1) case is more often, we check nums[end] (i.e. right position)
		 * first
		 */
		if (nums[end] == target) {
			return end;
		}

		if (nums[start] == target) {
			return start;
		}

		return -1;
	}

	public static int firstPosition(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}

		int start = 0, end = nums.length - 1;

		while (start + 1 < end) {

			int mid = start + (end - start) / 2;

			if (nums[mid] == target) {
				// since look for first position, we ignore right side, and look for fist occurance in the left side
				// there might be numbers==target, in the left side
				end = mid;
			} else if (nums[mid] > target) {
				end = mid;
			} else {
				start = mid;
			}
		}

		// compared to last position, below if condition sequence is ONLY
		// difference
		if (nums[start] == target) {
			return start;
		}

		if (nums[end] == target) {
			return end;
		}
		return -1;
	}

}
