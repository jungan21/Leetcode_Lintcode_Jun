package duplicate;

/**
 * https://www.hrwhisper.me/leetcode-remove-duplicate-letters/
 * 
 * http://blog.csdn.net/sbitswc/article/details/50420035
 * http://www.cnblogs.com/grandyang/p/5085379.html
 *
 */
public class RemoveDuplicateLetter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new RemoveDuplicateLetter()
				.removeDuplicateLettersNew("cbacdcbc"));
	}

	public String removeDuplicateLettersNew(String s) {
		if (s == null) {
			return null;
		}
		int[] hash = new int[26];

		for (int i = 0; i < s.length(); i++) {
			hash[s.charAt(i) - 'a']++;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			if (hash[i] != 0) {
				sb.append((char) (i + 97) );
			}
		}
		return sb.toString();
	}

	public String removeDuplicateLetters(String s) {
		if (s == null) {
			return null;
		}
		char[] array = s.toCharArray();
		char[] result_array = new char[Character.MAX_VALUE + 1];
		int[] countArray = new int[Character.MAX_VALUE + 1];
		char ch = ' ';

		for (int i = 0; i < array.length; i++) {
			ch = array[i];
			if (countArray[ch] < 1) {
				countArray[ch] = (countArray[ch] + 1);
				result_array[(int) ch] = ch;
			}
		}
		System.out.println(result_array.length);
		return new String(result_array);
	}

	public String removeDuplicateLetters2(String s) {
		if (s == null) {
			return null;
		}
		char[] array = s.toCharArray();
		char[] result_array = new char[26];
		int[] countArray = new int[Character.MAX_VALUE + 1];
		char ch = ' ';

		for (int i = 0, j = 0; i < array.length && j < 26; i++) {
			ch = array[i];
			if (countArray[ch] < 1) {
				countArray[ch] = (countArray[ch] + 1);
				result_array[j] = ch;
				j++;
			}
		}

		String tem_result = new String(result_array);
		char[] tem_result_array = tem_result.trim().toCharArray();
		System.out.println(tem_result_array.length);
		insertSort(tem_result_array, tem_result_array.length);

		return new String(tem_result_array);
	}

	public static void insertSort(char[] array, int n) {
		int i = 0, j = 0;
		char temp = ' ';
		// 比较n-1轮
		for (i = 1; i < n; i++) {
			temp = array[i];// 取出一个未排序的元素
			// 在已经排序好的序列中找出需要插入的位置
			for (j = i - 1; j >= 0 && temp < array[j]; j--) {
				array[j + 1] = array[j];// 元素向后移动
			}
			System.out.println(j);
			array[j + 1] = temp;// 插入元素到数列
		}
	}

}
