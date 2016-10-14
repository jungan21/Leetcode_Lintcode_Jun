package String;

public class MostFrequestCharInString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getMax("sassbb"));
		System.out.println(Character.MAX_VALUE);
	}

	public static char getMax(String word) {
		if (word == null || word.isEmpty()) {
			throw new IllegalArgumentException(
					"input word must have non-empty value.");
		}
		char maxchar = ' ';
		int maxcnt = 0;
		// if you are confident that your input will be only ascii, then this
		// array can be size 256.
		int[] charcnt = new int[Character.MAX_VALUE + 1];
		for (int i = word.length() - 1; i >= 0; i--) {
			char ch = word.charAt(i);
			charcnt[ch]++;
			if (charcnt[ch] >= maxcnt) {
				maxcnt = charcnt[ch];
				maxchar = ch;
			}
		}
		return maxchar;
	}
}
