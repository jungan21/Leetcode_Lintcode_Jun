package binarySearch;

/**
 * Given a sorted array of n integers, find the starting and ending position of
 * a given target value.
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 *
 */
public class SearchForARange {

	public int[] searchRange(int[] A, int target) {

		if (A == null || A.length == 0) {
			// note, you can't just: return {-1, -1};
			return new int[] { -1, -1 };
		}

		int[] result = new int[2];

		int startPos = helper(A, target, true);
		int endPos = helper(A, target, false);

		result[0] = startPos;
		result[1] = endPos;
		return result;
	}

	// boolean is to check whether look for first index or last index
	private int helper(int[] A, int target, boolean first) {
		int start = 0;
		int end = A.length - 1;

		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (A[mid] == target) {
				if (first) {
					end = mid;
				} else {
					start = mid;
				}

			} else if (A[mid] > target) {
				end = mid;
			} else {
				start = mid;
			}
		}

		if (first) {
			if (A[start] == target) {
				return start;
			}
			if (A[end] == target) {
				return end;
			}
		} else {
			if (A[end] == target) {
				return end;
			}
			if (A[start] == target) {
				return start;
			}
		}

		return -1;
	}
}
