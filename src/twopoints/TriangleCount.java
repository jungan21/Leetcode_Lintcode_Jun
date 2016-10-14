package twopoints;

import java.util.Arrays;

public class TriangleCount {

	public static void main(String[] args) {
		int[] A = { 4, 4, 4, 4 };
		new TriangleCount().triangleCount(A);
	}

	/**
	 * best one: jiuzhang
	 */
	public int triangleCount(int S[]) {
		// write your code here
		int left = 0, right = S.length - 1;
		int ans = 0;
		Arrays.sort(S);
		/**
		 * brute force
		 * 
		 * for (i from 0 to n) for (j from 0 to i) fro (k from 0 to j)
		 * 
		 * 把上面brute force 方式 n^3 的时间优化成下面的东西
		 */
		for (int i = 0; i < S.length; i++) {
			left = 0;
			right = i - 1;
			while (left < right) {
				if (S[left] + S[right] > S[i]) {
					ans = ans + (right - left);
					right--;
				} else {
					left++;
				}
			}
		}
		return ans;
	}

	/**
	 * http://www.chenguanghe.com/lintcode-triangle-count/
	 * 
	 * O(n^2) + O(nlogn) = O(n^2)
	 */
	public int triangleCountJun(int[] S) {
		if (S == null || S.length == 0) {
			return 0;
		}
		int left = 0, right = S.length - 1;
		int count = 0;
		Arrays.sort(S);
		// K指向最后一个元素固定，让left, right 移动
		// 注意： k >0,因为 下面right 的值为 k-1
		for (int k = S.length - 1; k > 0; k--) {
			left = 0;
			// right始终指向k指向的前面一个元素
			right = k - 1;
			// left has to be less than right, otherwise, overlap each other
			while (left < right) {
				/**
				 * e.g 3, 4, 5, 6
				 * 
				 * 假设，left指向 3, right指向 5, k 指向6. 如果 S[left] + S[right] > S[k],
				 * 那么left到right 中间的元素 也都满足（即假设left往右移） S[left+1] + S[right] >
				 * S[k]
				 */
				if (S[left] + S[right] > S[k]) {
					count = count + (right - left);
					right--;
				} else {
					left++;
				}
			}
		}
		return count;
	}

}
