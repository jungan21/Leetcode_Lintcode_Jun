package a_oa;

/**
 * 
 * 找最小连续空间 sample: {1,1,1,0,0,0,0,1,0,0,1,1},1代表内存被使用, 0代表空
 * 闲,然后找出至少为2的最小连续空间的index,此例应该返回8
 *
 */
public class MinimumContinousSpace {

	public static void main(String[] args) {
		int[] input = { 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1 };
		System.out.println(findIndex(input));
	}

	public static int findIndex(int[] A) {
		int minLen = Integer.MAX_VALUE;
		int resultIndex = 0;
		int j = 0;
		for (int i = 0; i < A.length;) {
			if (A[i] == 0) {
				j = i;
				while (j < A.length && A[j] == 0) {
					j++;
				}
				// after while loop A[j] == 1
				int length = j - i;
				if (length >= 2) {
					minLen = Math.min(minLen, length);
					resultIndex = i;
					if (length == 2) {
						return resultIndex;
					}
				}
				i = j;
			} else {
				i++;
			}
		}
		return minLen == Integer.MAX_VALUE ? -1 : resultIndex;
	}

}
