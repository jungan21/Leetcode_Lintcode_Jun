package company.snowflake;

import java.util.ArrayList;
import java.util.List;

class Solution {


    public static void main(String[] args) {

        System.out.println(reformat("a0b1c2"));

    }

    public static String reformat(String s) {
        List<Character> digits = new ArrayList<>();
        List<Character> characters = new ArrayList<>();
        for (char c : s.toCharArray()){
            if(Character.isDigit(c)){
                digits.add(c);
            } else if (Character.isLetter(c)) {
                characters.add(c);
            }
        }

        int sizeDiff = digits.size() - characters.size();
        if ( Math.abs(sizeDiff) >=2 ){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        boolean digit = sizeDiff >=0 ? true : false;
        for (int i = 0; i < s.length(); i++){
            if(digit) {
                sb.append(digits.remove(0));
            }else {
                sb.append(characters.remove(0));
            }
            digit =! digit;
        }

        return sb.toString();
    }
}

