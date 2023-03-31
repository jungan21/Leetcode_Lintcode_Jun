package company.microsoft;

import java.util.List;

//https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
public class MaxLengthConcatenatedStringUniqueCharacters {

    public static void main(String[] args){
        System.out.println(0);

    }

    private int result = 0;
    public int maxLength(List<String> arr) {
        if (arr == null || arr.size() == 0){
            return 0;
        }
        dfs("", arr, 0);
        return result;
    }

    public void dfs(String path, List<String> arr, int pos){

        if (!isUniqueChars(path)){
            return;
        } else {
            result = Math.max(path.length(), result);
        }

        for (int i = pos; i < arr.size(); i++){
            dfs(path+arr.get(i), arr, i+1);
        }

    }

    private boolean isUniqueChars(String s) {
        int[] charCount = new int[256];
        for (char c : s.toCharArray()) {
            charCount[c]++;

            if(charCount[c] > 1){
                return false;
            }
        }
        return true;
    }
}
