package company.wayfair.coding;

// Leetcode 205 https://leetcode.com/problems/isomorphic-strings/
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        for(int i=0; i < s.length(); i++){
            if(m1[s.charAt(i)] != m2[t.charAt(i)]) return false;

            m1[s.charAt(i)]++;
            m2[t.charAt(i)]++;
        }

        return true;
    }
}
