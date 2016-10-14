package binarySearch;

/**
 * 有n段木头，长度各异。现在希望通过这n段木头得到more than k段木材。这K段长度要一样，并且尽可能长（我们需要找出这个满足条件的长度），
 * http://www.lintcode.com/en/problem/wood-cut/
 *
 * find the largest length that can cut more than k pieces of wood.
 * 
 * @author jungan
 *
 */
public class WoodCut {

	public static void main(String[] args) {
		int[] woods = { 232, 124, 456 };
		// we need to get K pieces wood
		int k = 7;
		System.out.println(woodCut(woods, k));
	}

	/**
	 * int[] L: Given n pieces of wood with length L[i], : k:we need to get K
	 * 段木头. 返回： The maximum length of the small pieces.
	 * 
	 * ===>
	 * 
	 * last/Biggest length that can get >= k pieeces
	 */
	public static int woodCut(int[] L, int k) {
		int max = 0;
		for (int l : L) {
			max = Math.max(max, l);
		}

		// find the largest length that can cut more than k (>=k) pieces of
		// wood.
		int start = 1, end = max;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			// (>=k)
			if (count(L, mid) >= k) {
				start = mid;
			} else {
				end = mid;
			}
		}
		// 先算end, 因为是>=k 的最后一个数
		if (count(L, end) >= k) {
			return end;
		}
		// 注意：如果上面 end不符合条件，不能直接返回start
		if (count(L, start) >= k) {
			return start;
		}
		// 如果都不满足条件，返回0
		return 0;
	}

	// to calculate how many piece we can get based on the length of each piece
	// = length variable;
	private static int count(int[] L, int unitLength) {
		int sum = 0;
		for (int l : L) {
			sum += l / unitLength;
		}
		return sum;
	}

}
