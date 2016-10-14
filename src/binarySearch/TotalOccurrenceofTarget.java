package binarySearch;

/**
 * http://www.jiuzhang.com/solutions/total-occurrence-of-target/
 *
 */
public class TotalOccurrenceofTarget {

	public static void main(String[] args) {
		int[] arr = { 100, 156, 189, 298, 299, 300, 1001, 1002, 1003, 1004 };

		System.out.println(totalOccurrence(arr, 1000));
	}

	public static int totalOccurrence(int[] A, int target) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int n = A.length;
		if (A[n - 1] < target || A[0] > target)
			return 0;

		int startPos = helper(A, target, true);
		int endPos = helper(A, target, false);
		
		// Critical, you can't ignore it: Note
		if (startPos== -1 || endPos == -1){
			return 0;
		}
		
		return endPos - startPos + 1;
	}

	private static int helper(int[] A, int target, boolean first) {
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
