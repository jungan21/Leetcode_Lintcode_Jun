package duplicate;

import java.util.Arrays;

// crack code: 1.3
public class RemoveDuplicateCharacters {

	public static void main(String[] args) {
		// System.out.println(removeDuplicates("bacca"));
		String s = "adcadb";
		removeDuplicatesJun(s.toCharArray());
	}

	// without extra memory
	public static void removeDuplicates(char[] str) {
		if (str == null)
			return;
		int len = str.length;
		if (len < 2)
			return;
		int tail = 1; // number of unique char in the array.
		// start at 2nd char and go till the end of the array.
		for (int i = 1; i < len; ++i) {
			int j;
			// for every char in outer loop check if that char is already seen.
			// char in [0,tail) are all unique.
			for (j = 0; j < tail; j++) {
				if (str[i] == str[j])
					break; // break if we find duplicate., after break, j++
							// won't be executed
			}
			// if j reachs tail..we did not break, which implies this char at
			// pos i
			// is not a duplicate. So we need to add it our "unique char list"
			// we add it to the end, that is at pos tail.
			// if j!= tail, that means above inner for loop was breaked, which
			// means there are duplicaet
			if (j == tail) {
				str[tail] = str[i]; // add
				++tail; // increment tail...[0,tail) is still "unique char list"
			}
		}
		str[tail] = '#'; // add a 0 at the end to mark the end of the unique
							// char.
		System.out.println(str);
	}

	// with extra buffer
	public static void removeDuplicatesJunNew(char[] str) {
		if (str == null)
			return;
		int len = str.length;
		if (len < 2)
			return;

		int[] hit = new int[256];

		hit[str[0]]++;
		int tail = 1;

		for (int i = 1; i < len; i++) {
			if (hit[str[i]] == 0) {
				str[tail] = str[i];
				tail++;
				hit[str[i]]++;
			}
		}
		// before #, all unique
		str[tail] = '#';

		System.out.println(str);
	}

	public static void removeDuplicatesJun(char[] str) {
		if (str == null)
			return;
		int len = str.length;
		if (len < 2)
			return;

		int pre = 0;
		int cur = 1;
		while (cur < len) {
			int runner = 0;
			while (runner < cur && str[runner] != str[cur]) {
				runner++;
			}
			if (runner == cur) {
				pre++;
				cur++;
			} else {
				pre++;
				str[pre] = '#';
				cur++;
			}
		}
		System.out.println(new String(str).replace("#", ""));
	}

	// with extra buffer
	public static void removeDuplicates2(char[] str) {
		if (str == null)
			return;
		int len = str.length;
		if (len < 2)
			return;

		boolean[] hit = new boolean[256];

		hit[str[0]] = true;
		int tail = 1;

		for (int i = 1; i < len; i++) {
			if (!hit[str[i]]) {
				str[tail] = str[i];
				tail++;
				hit[str[i]] = true;
			}
		}
		// before #, all unique
		str[tail] = '#';

		System.out.println(str);
	}

	// this method ONLY return unique characters in the string
	public static char[] getUniqueCharacter(String str) {
		char[] array = str.toCharArray();
		int len = array.length;
		if (str == null || array.length < 2) {
			return str.toCharArray();
		}

		Arrays.sort(array);

		int j = 0;
		int i = 1;
		while (i < len) {
			if (array[i] == array[i - 1]) {
				i++;
			} else {
				j++;
				array[j] = array[i];
				i++;
			}
		}

		char[] result = Arrays.copyOf(array, j + 1);
		return result;
	}
}
