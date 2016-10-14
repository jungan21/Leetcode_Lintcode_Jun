package a.DP;

/**
 * fb asked
 * 
 * @author jungan
 *
 */
public class OneEditDistance {

	public static void main(String[] args) {
		OneEditDistance test = new OneEditDistance();
		System.out.println(test.checkDiffLength("cab", "ab"));
	}

	/**
	 * http://my.oschina.net/u/922297/blog/492952
	 * 
	 * @param s2
	 */
	public boolean isOneEditDistance(String s1, String s2) {
		int len1 = s1.length();
		int len2 = s2.length();

		if (Math.abs(len1 - len2) > 1) {
			return false;
		}

		if (len1 == len2) {
			// only allow one position diff
			checkSameLength(s1, s2);
		} else if (len1 - len2 == 1) {
			return checkDiffLength(s1, s2);
		} else if (len2 - len1 == 1) {
			return checkDiffLength(s2, s1);
		}
		return false;
	}

	// s1 is the longer one
	public boolean checkDiffLength(String s1, String s2) {
		int offset = 0;
		int i = 0;
		// s2 is shorter one
		while (i < s2.length()) {
			if (s2.charAt(i) != s1.charAt(i + offset)) {
				offset++;
				if (offset > 1) {
					return false;
				}
			} else {
				i++;
			}
		}

		return true;
	}

	public boolean checkSameLength(String s1, String s2) {
		int dist = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) == s2.charAt(i)) {
				continue;
			} else {
				dist++;
			}
			if (dist > 1) {
				return false;
			}
		}

		return dist == 1;
	}

	/**
	 * method 2, concise
	 * 
	 * http://mitbbs.ca/mitbbs_article_t.php?board=JobHunting&gid=32761895&start
	 * =32762243&pno=-1
	 */

	public static boolean oneEditApart(String a, String b) {

		String small = a.length() > b.length() ? b : a;
		String large = a.length() > b.length() ? a : b;

		int operation = 0;
		// case 1, two word's length differ by more than one
		if (large.length() - small.length() > 1)
			return false;
		// case 2, length is equal, check every char one by one
		else if (large.length() == small.length()) {
			int i = 0;
			while (i < small.length()) {
				if (small.charAt(i) != large.charAt(i) && ++operation > 1)
					return false;
				i++;
			}
			// case 3, two word's length differ by one, only one differ allow
		} else {
			int i = 0;
			while (i < small.length()) {
				if (small.charAt(i) != large.charAt(i + operation)) {
					if (++operation > 1)
						return false;
				} else
					i++;
			}
		}
		return true;
	}

}
