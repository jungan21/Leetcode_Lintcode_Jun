package HackerRank;

//http://problemasdeprogramacao.blogspot.ca/2016/06/hacker-rank-super-reduced-string.html
public class SuperReducedString {

	/**
	 * String s = "aaabccddd";
	 * 
	 * String s =
	 * "oagciicgaoyjmahhamjymmwjnnjwmmvpxhpphxpvlikappakilyygvkkvgyymlpfddfplmhiodvvdoihfxpkggkpxfuevvuuvveu"
	 * ;
	 * 
	 * String s = "baab"
	 * 
	 */
	public static void main(String[] args) {
		// Scanner sc = new Scanner(System.in);
		// String str = sc.next();
		// String result = reduceRecursive(str, 0);
		// if (result == null || result.length() == 0) {
		// System.out.println("Empty String");
		// } else {
		// System.out.println(result);
		// }
		String s = "oagciicgaoyjmahhamjymmwjnnjwmmvpxhpphxpvlikappakilyygvkkvgyymlpfddfplmhiodvvdoihfxpkggkpxfuevvuuvveu";
		System.out.println(reduce(s));

	}

	// method 1: recursive
	public static String reduceRecursive(String str, int index) {
		if (index >= str.length() - 1) {
			return str;
		}

		String result = "";
		if (str.charAt(index) == str.charAt(index + 1)) {
			str = str.substring(0, index) + str.substring(index + 2);
			result = reduceRecursive(str, 0);
		} else {
			result = reduceRecursive(str, index + 1);
		}
		return result;
	}

	// method 2: non recursive
	public static String reduce(String str) {
		int i = 0;
		while (i < str.length() - 1) {
			if (str.charAt(i) == str.charAt(i + 1)) {
				str = str.substring(0, i) + str.substring(i + 2);
				i = 0;
			} else {
				i++;
			}
		}
		return str;
	}
}
