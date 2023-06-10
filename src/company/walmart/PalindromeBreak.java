package company.walmart;

import java.util.*;

/**
 * https://leetcode.com/problems/break-a-palindrome/
 *
 * Input: palindrome = "abccba"
 * Output: "aaccba"
 * Explanation: There are many ways to make "abccba" not a palindrome, such as "zbccba", "aaccba", and "abacba".
 * Of all the ways, "aaccba" is the lexicographically smallest.
 */
public class PalindromeBreak {
    public String breakPalindrome(String palindrome) {
        if (palindrome.length() == 1) return "";
        for (int i = 0; i < palindrome.length() / 2; i++) {
            if (palindrome.charAt(i) != 'a') {
                return palindrome.substring(0, i) + 'a' +
                        palindrome.substring(i + 1);
            }
        }
        // If all the characters are 'a' replace the last character with 'b'
        return palindrome.substring(0, palindrome.length() - 1) + 'b';
    }

    //Jun
    public String breakPalindrome_method2(String palindrome) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < palindrome.length(); i++){
            for (char c = 'a'; c <= 'z'; c++){
                if (palindrome.charAt(i) != c){
                    String newString = palindrome.substring(0, i) + c + palindrome.substring(i+1);
                    if (!isPalindrome(newString)){
                        list.add(newString);
                    }
                }

            }
        }
        // (a, b) -> b.compareTo(a) (字母倒序)， then list.get(list.size()-1);
        Collections.sort(list);

        return list.size() > 0 ? list.get(0) : "";
    }

    private static boolean isPalindrome(String string){
        int i = 0;
        int j = string.length() -1;
        while (i < j){
            if (string.charAt(i) == string.charAt(j)){
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
