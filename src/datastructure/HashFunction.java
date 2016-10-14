package datastructure;

public class HashFunction {

	public static void main(String[] args) {
		String key = "abcd";
		System.out.println(hashCode(key.toCharArray(), 100));
	}

	/**
	 * http://www.jiuzhang.com/qa/234/
	 * 
	 * 时间复杂度 O(n)O(n)O(n), 空间复杂度 O(1)O(1)O(1).
	 * 
	 * hashcode("abc") = (a * 33 ^2 + b * 33 + c) % M
	 *
	 * = (33 * (33 * a + b) + c) % M
	 * 
	 * = (33 * (33 * (33 * 0 + a) + b) + c ) % M
	 * 
	 * 九章的答案是 hashcode("abc") = (33 * (33 * (33 * 0 + a) % M + b) % M + c ) % M
	 * 
	 */
	public static int hashCode(char[] key, int HASH_SIZE) {
		if (key == null || key.length == 0) {
			return -1;
		}
		// sum必须是long, 最后return 结果的时候，再转为int
		long sum = 0;
		for (int i = 0; i < key.length; i++) {
			// 这样一边乘，一边取mod,就能保证不溢出
			sum = sum * 33 + key[i];
			sum = sum % HASH_SIZE;
			System.out.println(sum);
		}
		return (int) sum;
	}

}
