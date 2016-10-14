package math_bit;

import java.util.Arrays;

public class CountandSay {

	public static void main(String[] args) {
		System.out.println(countAndSay(4));
		int[] A = {1,2};
		int[] B = Arrays.copyOf(A, A.length +1);
		B[B.length-1] = 3;
		System.out.println(Arrays.toString(B));
		
	}

	public static String countAndSay(int n) {
		String preString = "1";
		if (n == 1) {
			return preString;
		}
		// 必须大于1， 因为==1的时候上面以及考虑过了，只有n=2或以上开始计算一直推到1
		while (n > 1) {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < preString.length();) {
				int count = 1;
				int j = i + 1;
				while (j < preString.length()
						&& preString.charAt(i) == preString.charAt(j)) {
					count++;
					j++;
				}
				sb.append(String.valueOf(count)
						+ String.valueOf(preString.charAt(i)));
				i = i + count;
			}
			preString = sb.toString();
			n--;
		}

		return preString;
	}

	public static String countAndSayRecursive(int n) {
		String result = "";
		if (n <= 0) {
			return result;
		}
		if (n == 1) {
			result = result + "1";
			return result;
		}
		// divide
		result = countAndSayRecursive(n - 1);

		// conquer
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.length();) {
			int count = 1;
			int j = i + 1;
			while (j < result.length() && result.charAt(j) == result.charAt(i)) {
				count++;
				j++;
			}
			sb = sb.append(String.valueOf(count)).append(result.charAt(i));
			if (j == result.length()) {
				break;
			}
			i = i + count;
		}
		result = sb.toString();
		return result;
	}

}
