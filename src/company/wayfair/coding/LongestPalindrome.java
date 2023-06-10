package company.wayfair.coding;


import java.util.HashSet;
import java.util.Set;

/**
 * 409 https://leetcode.com/problems/longest-palindrome/
 *
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
 *
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 *
 * Example 1:
 *
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 *
 * https://us.jiuzhang.com/problem/longest-palindrome/
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) set.remove(c);
            else set.add(c);
        }

        int remove = set.size();
        //if Input: s = "abccccdd", remove = 2
        //if Input: s = "ccccdd", remove = 0;
        if (remove > 0)
            remove -= 1;
        return s.length() - remove;
    }
}
