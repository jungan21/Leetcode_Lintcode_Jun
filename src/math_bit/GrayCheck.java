package math_bit;

public class GrayCheck {

	public static void main(String[] args) {
		// System.out.println(isGrayCode2(80, 90));
		byte term1 = (byte) 0x0a;
		byte term2 = (byte) 0x80;
		int out;
		out = grayCheck(term1, term2);
		System.out.println(out);
	}

	public static int grayCheck(byte term1, byte term2) {
		byte x = (byte) (term1 ^ term2);

		// 先对两个数字求^,然后再统计这个^后的结果有多少位1即可
		int count = 0;
		while (x != 0) {
			x = (byte) (x & (x - 1));
			count++;
		}
		return count == 1 ? 1 : 0;
	}

}
