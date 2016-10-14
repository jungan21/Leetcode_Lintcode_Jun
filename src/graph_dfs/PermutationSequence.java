package graph_dfs;

import java.util.ArrayList;

public class PermutationSequence {

	public static void main(String[] args) {
		PermutationSequence ps = new PermutationSequence();
		System.out.println(ps.getPermutation(3, 4));
	}
	//http://www.cnblogs.com/grandyang/p/4358678.html
	public String getPermutation(int n, int k) {
		// initialize all numbers
		ArrayList<Integer> numberList = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			numberList.add(i);
		}
		// change k to be index
		k--;
		
		// set factorial of n
		int mod = 1;
		for (int i = 1; i <= n; i++) {
			mod = mod * i;
		}

		String result = "";

		// find sequence
		for (int i = 0; i < n; i++) {
			mod = mod / (n - i);
			// find the right number(curIndex) of
			int curIndex = k / mod;
			// update k
			k = k % mod;

			// get number according to curIndex
			result += numberList.get(curIndex);
			// remove from list
			numberList.remove(curIndex);
		}

		return result.toString();
	}

	/**
	 * Jun's
	 * 
	 * O(n^2) n^2, 思路就是不停的求下一个 Next Permutation,一直求导第k个（小标从1开始计数）
	 */
	public String getPermutation1(int n, int k) {
		NextPermutation np = new NextPermutation();
		int[] num = new int[n];
		// 下面每个元素赋值好后，就算第1个permutation, 所以下面的loop i < k-1
		for (int i = 0; i < n; i++) {
			num[i] = i + 1;
		}
		for (int i = 0; i < k - 1; i++) {
			np.nextPermutation(num);
		}
		String result = "";
		for (int i = 0; i < n; i++) {
			result += num[i];
		}
		return result;
	}
}
