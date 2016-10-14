package a_oa;

import java.util.Arrays;

/* 一個已經預設好的class */
class Container {
	public double first;
	public double second;

	public Container(double first, double second) {
		this.first = first;
		this.second = second;
	}
}

/**
 * 現在給某個容量(double capacity), 還有一個array(double[] weights), 和int numOfContainers
 * 
 * 主要是要在array中選出兩個weights總和小於等於capacity但最接近capacity 然後指定到一個Container
 * object並且return
 * 
 * first和second的順序並不影響，numOfContainer在java裡好像也是沒有用的,因為double[]本身就自帶length資訊
 * 
 * public Container findOptimalWeights(double capacity, double[] weights, int
 * numOfContainers)
 * 
 * 最後用了最簡單的方法兩個 for loop找總和最接近capacity的pair 總共8個test cases直接就過了
 *
 */
public class FindOptimalWeights {

	public static void main(String[] args) {
		double[] A = { 1, 2, 3, 4, 6 };
		findOptimalWeights(11, A, 1);
		findOptimalWeightsJun(11, A, 1);
	}

	/**
	 * 
	 * capacity： 相当于tow sum closest 里的target
	 * 
	 * weights： array, 相当于 two sum里的数字数组
	 */
	public static Container findOptimalWeights(double capacity,
			double[] weights, int numOfContainers) {
		double lessThanCapacityMax = 0.0;

		int firstIndex = 0;
		int secondIndex = weights.length - 1;

		int i = 0, j = weights.length - 1;
		Arrays.sort(weights);
		while (i < j) {
			double sum = weights[i] + weights[j];

			if (sum > lessThanCapacityMax && sum <= capacity) {
				lessThanCapacityMax = sum;
				firstIndex = i;
				secondIndex = j;
			}

			if (sum > capacity) {
				j--;
			} else {
				i++;
			}
		}

		System.out
				.println("The two numbers for which sum is closest to target are "
						+ weights[firstIndex] + " and " + weights[secondIndex]);

		return new Container(weights[firstIndex], weights[secondIndex]);
	}

	public static Container findOptimalWeightsJun(double capacity,
			double[] weights, int numOfContainers) {

		int firstIndex = 0;
		int secondIndex = weights.length - 1;

		double minDiff = Integer.MAX_VALUE;
		double diff = 0;
		double sum = 0;

		int i = 0, j = weights.length - 1;
		Arrays.sort(weights);
		while (i < j) {
			sum = weights[i] + weights[j];
			diff = Math.abs(capacity - sum);

			if (diff < minDiff) {
				minDiff = diff;
				firstIndex = i;
				secondIndex = j;
			}

			if (sum > capacity) {
				j--;
			} else {
				i++;
			}
		}

		System.out
				.println("The two numbers for which sum is closest to target are "
						+ weights[firstIndex] + " and " + weights[secondIndex]);

		return new Container(weights[firstIndex], weights[secondIndex]);
	}

}
