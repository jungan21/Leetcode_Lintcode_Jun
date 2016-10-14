package subarray;

import java.util.ArrayList;

/**
 * 
 Given an circular integer array (the next element of the last element is the
 * first element), find a continuous subarray in it, where the sum of numbers is
 * the biggest. Your code should return the index of the first number and the
 * index of the last number.
 * 
 * If duplicate answers exist, return any of them.
 * 
 * 
 *
 */
public class ContinuousSubarraySumII {

	public static void main(String[] args) {
		int[] A = { -101, -33, -44, -55, -67, -78, -101, -33, -44, -55, -67,
				-78, -100, -200, -1000, -22, -100, -200, -1000, -22 };
		System.out.println(continuousSubarraySumII(A));

	}

	public static ArrayList<Integer> continuousSubarraySumII(int[] A) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (A == null || A.length == 0) {
			return result;
		}
		int allSum = 0;
		for (int a : A) {
			allSum += a;
		}

		int globalMin = Integer.MAX_VALUE, localMin = 0;
		int globalMax = Integer.MIN_VALUE, localMax = 0;

		int idLeftMax = 0, idRightMax = 0, leftMax = 0;
		int idLeftMin = 0, idRightMin = 0, leftMin = 0;
		for (int i = 0; i < A.length; i++) {
			localMax = localMax + A[i];
			localMin = localMin + A[i];
			// get max
			if (localMax > globalMax) {
				globalMax = localMax;
				idLeftMax = leftMax;
				idRightMax = i;
			}
			if (localMax <= 0) {
				localMax = 0;
				leftMax = i + 1;
			}

			// get min
			if (localMin < globalMin) {
				globalMin = localMin;
				idLeftMin = leftMin;
				idRightMin = i;
			}

			if (localMin >= 0) {
				localMin = 0;
				leftMin = i + 1;
			}
		}

		int globalMax2 = allSum - globalMin;

		if (globalMax2 >= globalMax && idRightMin < A.length - 1
				&& idLeftMin > 0) {
			result.add(idRightMin + 1);
			result.add(idLeftMin - 1);
		} else {
			result.add(idLeftMax);
			result.add(idRightMax);
		}
		return result;
	}

}
