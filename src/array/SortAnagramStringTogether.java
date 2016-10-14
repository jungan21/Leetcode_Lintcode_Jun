package array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * cc150, 9.2
 * 
 * Write a method to sort an array of strings so that all the anagrams are next
 * to each other.
 * 
 */
public class SortAnagramStringTogether {

	public static void main(String[] args) {
		String[] input = { "ab", "ac", "ba", "ca" };
		sortAnagram(input);
	}

	public static void sortAnagram(String[] input) {

		Arrays.sort(input, new Comparator<String>() {
			public int compare(String a, String b) {
				return sortString(a).compareTo(sortString(b));
			}
		});

		System.out.println(Arrays.toString(input));
	}

	public static String sortString(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}

		char[] charArr = str.toCharArray();
		Arrays.sort(charArr);
		return new String(charArr);

	}

}
