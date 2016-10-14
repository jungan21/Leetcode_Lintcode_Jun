package math_bit;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Bit操作的题, 给一个整数+1,不能用加号. 其实很简单,扫一下整数的每个bit(从低位到高位), 对它做&操作, 如果结果是0, 证明此位是0,
 * 那么我们在这位+1, 如果是1, 那么我们清空此位.
 * 
 */
public class AddBinaryNumberBy1 {

	public static int add(int n) {
		for (int i = 0; i < 32; i++) {
			if (((1 << i) & n) == 0) { // until we find the first 1 bit
				n = n | (1 << i);
				break;
			} else
				n = n & ~(1 << i); // clean bits
		}
		return n;
	}

	public static int addJun(int n) {
		for (int i = 0; i < 32; i++) {
			if ((n >> i & 1) == 0) { // until we find the first 1 bit
				n = n | (1 << i);
				// n = n + (1 << i);
				break;
			} else {
				// 相当于n的第i位为1时， 将0001的第i位0变为1， 然后取反 与现在的n求与
				n = n & ~(1 << i); // clean bits
				System.out.println(n);
			}
		}
		return n;
	}

	public static void main(String[] args) {
		// for (int i = -500; i < 500; i++) {
		System.out.println(add(5));
		System.out.println(addJun(5));
		Set set = new HashSet();
		
		// }
	}
}
