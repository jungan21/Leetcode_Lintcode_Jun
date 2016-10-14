package HackerRank;

import java.util.Scanner;

public class AlgorithmicCrush {

	/**
	 * https://www.hackerrank.com/challenges/crush
	 * 
	 * http://srikantpadala.com/blog/hackerrank-solutions/crush
	 * 
	 * https://www.youtube.com/watch?v=6kn8QJJKhS4
	 * 
	 * https://gist.github.com/mbuff24/5d24ffcf06c7ba7f82ae
	 * 
	 * http://codereview.stackexchange.com/questions/95755/algorithmic-crush-
	 * problem-hitting-timeout-errors/95812#95812
	 * 
	 */

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String[] times = sc.nextLine().split(" ");
		int n = Integer.valueOf(times[0]);
		int m = Integer.valueOf(times[1]);
		Op[] ops = new Op[m];
		for (int i = 0; i < m; i++) {
			String[] ops_prop = sc.nextLine().split(" ");
			ops[i] = new Op(Integer.valueOf(ops_prop[0]),
					Integer.valueOf(ops_prop[1]), Integer.valueOf(ops_prop[2]));
		}
		System.out.println(crush(n, m, ops));
	}

	public static long crush(int n, int m, Op[] ops) {
		// 之所以n+1,因为如果右边范围正好是最后一个数字，下一个数字要减去
		long[] curPreSum = new long[n + 1];
		for (int i = 0; i < curPreSum.length; i++) {
			curPreSum[i] = 0;
		}
		/**
		 * 参考上面视频，build prefix sum 数组 这是关键 把复杂度降低了， 因为用了prefix sum,就不用循环每个赋值了
		 */
		for (int i = 0; i < m; i++) {
			// [i,j] 只在i出加上，然后在j+1位置减去
			// 这样，prefixSum[i] = prefixSum[0]+ prefixSum[1]+...+prefixSum[i]
			curPreSum[ops[i].a - 1] += ops[i].k;
			curPreSum[ops[i].b] -= ops[i].k;
		}

		long max = curPreSum[0];
		long sum = max;
		for (int i = 1; i < n; i++) {
			sum += curPreSum[i];
			max = Math.max(max, sum);
		}
		return max;
	}

}

class Op {
	int a;
	int b;
	int k;

	public Op(int a, int b, int k) {
		this.a = a;
		this.b = b;
		this.k = k;
	}
}