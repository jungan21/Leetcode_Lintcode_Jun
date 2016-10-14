package String;

/**
 * Assume you have a method isSubstring which checks if one word is a substring
 * of another Giventwostrings,s1ands2,writecodetocheckifs2isarotationofs1using
 * only one call to isSubstring (i e , “waterbottle” is a rotation of
 * “erbottlewat”)
 * 
 *
 */
public class S1IsRotationOfS2 {

	public static void main(String[] args) {
		String a = "testjun";
		String b = "tjuu";
		System.out.println(a.indexOf(b));

	}

	// amazon OA: 判断string1和string2是否互为right rotation。比如：abcd和cdab。CC150原题
	// s2: waterbottle s1: erbottlewat
	public static boolean isRotation(String s1, String s2) {
		if (s1 == null && s2 == null){
			return false;
		} else if (s1 == null){
			return false;
		} else if (s2 == null ){
			return false;
		}
		
		int len1 = s1.length();
		int len2 = s2.length();
		if (len1 != len2) {
			return false;
		}
		String s1s1 = s1 + s1;
		return s1s1.indexOf(s2) == -1 ? false : true;

	}
}
