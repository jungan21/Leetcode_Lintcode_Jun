package math_bit;

import java.util.ArrayList;
import java.util.List;

/**
 * Given 2*n + 2 numbers, every numbers occurs twice except two, find them.
 * 
 * Example Given [1,2,2,3,4,4,5,3] return 1 and 5
 * 
 */

public class SingleNumberIII {

	public static void main(String[] args) {
		// int[] A = { 1, 2, 2, 3, 4, 4, 5, 3 };
		// return 1 and 5
		// int[] A = { 1, 2, 1, 3, 2, 5, 6, 5, 4, 6 };
		int[] A = { 1, 1, 2, 3 };
		System.out.println(singleNumberIII(A));
	}

	// http://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/72983
	// https://lefttree.gitbooks.io/leetcode-categories/content/BitManipulation/singleNumber3.html
	// http://bookshadow.com/weblog/2015/08/17/leetcode-single-number-iii/
	public static List<Integer> singleNumberIII(int[] A) {
		int aXORb = 0;
		for (int i = 0; i < A.length; i++) {
			aXORb ^= A[i];
		}

		// 利用lastBit可将数组的数分为两组，一组是相应位为0，另一组是相应位为1.
		// 1 和 5， int lastBit = 4 - (4 & (4 - 1));
		// int lastBit = aXORb - (aXORb & (aXORb - 1));
		// get the last 1 bit of axorb,
		/**
		 * jiuzhang: a = 1(0001);, b =5(0101), a^b=4 (0100)
		 * 
		 * a^b & -a^b可以得到a和b两个数, 从右往左数第一个不相同的数位的数字，即：a^b & -a^b = 0100 i.e.4，
		 * a在该位上是0， b在该位上是1， 现在可以利用这个数字0010 (4)， 再去和数组里所有的数字求XOR (^), 就可以把a, b找到
		 * 
		 * 如 a = 3 (0011), b=4 (0100) a^b = 0111, a^b & -a^b, 可以找到从右往左数，第一个a,b
		 * bit不同的数， 也就是0001
		 * 
		 * 
		 * -k，这是位运算，我们知道一个数加一个负号是把这个数的二进制取反+1，如-10的二进制就是-1010=0101+1=0110
		 * 
		 */
		int lastBit = aXORb & -aXORb;
		/**
		 * http://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/
		 * 72983
		 * 
		 * 最低位的1提取出来，由于在这一二进制位上x1和x2必然相异，即x1,
		 * x2中相应位一个为0，另一个为1，所以我们可以利用这个最低位的1将x1和x2分开
		 */
		int group0 = 0, group1 = 0;
		for (int num : A) {
			// 位运算的优先级很低，所有要确保 & 先算，加括号
			if ((lastBit & num) == 0) {
				group0 ^= num;
			} else {
				group1 ^= num;
			}
		}

		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(group0);
		result.add(group1);
		return result;
	}
	
	/**
	 * follow up http://zhedahht.blog.163.com/blog/static/25411174201283084246412/
	 */

}
