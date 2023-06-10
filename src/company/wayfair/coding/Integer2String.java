package company.wayfair.coding;

public class Integer2String {

    private static String convertIntegerToString(int a) {

        int c;
        char m;
        StringBuilder ans = new StringBuilder();


        // Char to Int:   int intvalue = '7' - '0'
        // Int to Char:  char  charValue  = 7 + '0';

        /**
         * When ASCII encoding is used, that becomes:
         *
         * '1' - '0' = 49 - 48 = 1
         * '2' - '0' = 50 - 48 = 2
         * When ASCII encoding is used, the integer value of '0' is 48.
         *
         * '0' + 1 = 49 = '1'
         * '0' + 2 = 50 = '2'
         */


        while (a > 0) {
            c = a % 10;
            a = a / 10;
            m = (char) ('0' + c);
            ans.append(m);
        }
        return ans.reverse().toString();
    }

    public static void main(String args[]){
        System.out.println(convertIntegerToString(256));
    }

}
