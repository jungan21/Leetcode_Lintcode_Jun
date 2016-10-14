package subarray;

import java.util.ArrayList;

/**
 * Given an integer array, find a continuous subarray where the sum of numbers
 * is the biggest. Your code should return the index of the first number and the
 * index of the last number. (If their are duplicate answer, return anyone)
 * 
 * 
 * MaximumSubarray.java仅要求求出最大的sum, 而这里要求返回 下标
 *
 */
public class ContinuousSubarraySum {

	public static void main(String[] args) {
		int[] A = { -3, 1, 3, -3, 4 };
		System.out.println(continuousSubarraySum(A));

	}

	public static ArrayList<Integer> continuousSubarraySum(int[] A) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (A == null || A.length == 0) {
			return result;
		}

		int globalMax = Integer.MIN_VALUE;
		int localMax = 0;
		int leftIndex = 0, rightIndex = 0;
		int tempLeftIndex = 0;
		for (int i = 0; i < A.length; i++) {
			localMax = localMax + A[i];
			if (localMax > globalMax) {
				globalMax = localMax;

				leftIndex = tempLeftIndex;
				rightIndex = i;
			}
			if (localMax <= 0) {
				localMax = 0;
				/**
				 * 只能用另外一个变量， 而不能用 leftIndex, 相当于每次local<=0的时候，就重置做窗口位置
				 * 
				 * [7,1,-8,-3,4]， 如果用idLeft = i +1 ==> [4,1]
				 * 
				 */
				tempLeftIndex = i + 1;
			}
		}

		result.add(leftIndex);
		result.add(rightIndex);

		return result;
	}

	public static int continuousSubarraySumResult(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for (int a : A) {
			sum = sum + a;
			if (sum < 0) {
				sum = 0;
			}
			max = Math.max(max, sum);
		}

		return sum;
	}
}
