package company.walmart;

public class StringsAlmostEquivalent {
    public boolean checkAlmostEquivalent(String word1, String word2) {

        int[] array = new int[256];

        for(char c : word1.toCharArray()){
            array[c - '0']++;
        }

        for(char c : word2.toCharArray()){
            array[c - '0']--;
        }

        for (int a : array){
            if (a > 3){
                return false;
            }
        }
        return true;
    }
}
