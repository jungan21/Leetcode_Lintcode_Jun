package HackerRank;

import java.util.HashSet;
import java.util.Scanner;

public class FirstRepeatWord {
	// http://www.blogs8.cn/posts/EPPU1f7
	// he;had a. a -good, son had
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.println(firstRepeatedWord(str));
	}

	public static String firstRepeatedWord(String str) {
		// dash和dot 都是regular expression中的，出现会有歧义，因此需要加上 转意符\\
		// 注意空格也可能是分隔符，所以,号前面的空格不能少
		String[] words = str.split("[ ,;:\\-\\.]");
		HashSet<String> set = new HashSet<>();
		int resultIndex = -1;
		for (int i = words.length - 1; i >= 0; i--) {
			if (words[i] == null || words[i].length() == 0) {
				continue;
			}
			if (!set.contains(words[i])) {
				set.add(words[i]);
			} else {
				resultIndex = i;
			}
		}
		return words[resultIndex];
	}
}
