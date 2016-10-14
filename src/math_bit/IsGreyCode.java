package math_bit;

public class IsGreyCode {

	public static void main(String[] args) {
		// System.out.println(isGrayCode2(80, 90));
		int a = 25;
		byte x = (byte) a;
		System.out.println(count1Byte(4));
	}

	public static boolean isGrayCode(byte a, byte b) {
		byte x = (byte) (a ^ b);

		// 先对两个数字求^,然后再统计这个^后的结果有多少位1即可
		int count = 0;
		while (x != 0) {
			x = (byte) (x & (x - 1));
			count++;
		}
		return count == 1 ? true : false;
	}

	// http://zhedahht.blog.163.com/blog/static/2541117420073118945734/
	// 上面的isGrayCode里就用到这种方法
	/**
	 * 
	 * http://zhedahht.blog.163.com/blog/static/2541117420073118945734/
	 * 思路：如果一个整数不为0，那么这个整数至少有一位是1。
	 * 如果我们把这个整数减去1，那么原来处在整数最右边的1就会变成0，并且在原来1后面的所有的0都会变成1.
	 * 
	 * 举个例子：一个二进制数1100，从右边数起的第三位是处于最右边的一个1。减去1后，第三位变成0，它后面的两位0变成1，而前面的1保持不变，
	 * 因此得到结果是1011。 我们发现减1的结果是把从最右边一个1开始的所有位都取反了。这个时候如果我们再把原来的整数和减去1之后的结果做与运算，
	 * 从原来整数最右边一个1那一位开始所有位都会变成0 。如1100&1011=1000。
	 * 
	 * 总之：也就是说，把一个整数减去1，再和原整数做与运算，会把该整数最右边一个1变成0。那么一个整数的二进制有多少个1，就可以进行多少次这样的操作。
	 */
	private static int count1Byte(int x) {
		int count = 0;
		while (x != 0) {
			x = (byte) (x & (x - 1));
			count++;
		}
		return count;
	}

	/**
	 * jun's
	 */
	public static boolean isGrayCode2(int a, int b) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if (((a >> i & 1) ^ (b >> i & 1)) == 1) {
				count++;
			}
		}
		return count == 1 ? true : false;
	}

}
