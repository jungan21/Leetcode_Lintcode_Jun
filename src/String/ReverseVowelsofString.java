package String;

// Given s = "leetcode", return "leotcede".
public class ReverseVowelsofString {

	public static void main(String[] args) {
		System.out.println(reverseVowels("leetcode"));
	}

	// method 1 suggested
	public static String reverseVowels(String s) {
		String vowStr = "aeiouAEIOU";
		// 装换成 array 方便很多
		char[] arr = s.toCharArray();
		int i = 0;
		int j = arr.length - 1;

		while (i < j) {
			// 或者 vowStr.contains(String.valueOf(arr[i]))
			while (vowStr.indexOf(arr[i]) == -1 && i < j) {
				i++;
			}
			while (vowStr.indexOf(arr[j]) == -1 && i < j) {
				j--;
			}
			char t = arr[i];
			arr[i] = arr[j];
			arr[j] = t;

			i++;
			j--;
		}

		return new String(arr);
	}
}
