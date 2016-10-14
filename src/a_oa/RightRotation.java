package a_oa;

/**
 * Assume you have a method isSubstring which checks if one word is a substring
 * of another Giventwostrings,s1ands2,writecodetocheckifs2isarotationofs1using
 * only one call to isSubstring (i e , “waterbottle” is a rotation of
 * “erbottlewat”)
 * 
 * 判断string1和string2是否互为right rotation。比如：abcd和cdab。CC150原题， 一行代码即可解决。
 * 
 *
 */
public class RightRotation {

	public static void main(String[] args) {
		String a = "abcd";
		String b = "cdab";
		System.out.println(isRotation(a, b));

	}

	// amazon OA: 判断string1和string2是否互为right rotation。比如：abcd和cdab。CC150原题
	// s2: waterbottle s1: erbottlewat
	public static int isRotation(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return -1;
		}

		if (s1.length() != s2.length()) {
			return -1;
		}
		String s1s1 = s1 + s1;
		return s1s1.contains(s2) ? 1 : -1;
		// return s1s1.indexOf(s2) == -1 ? -1 : 1;

	}
}
