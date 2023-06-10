package company.wayfair.coding;


/**
 *
 * Leetcode 415: https://leetcode.com/problems/add-strings/
 * The ASCII table is arranged so that the value of the character '9' is nine greater than the value of '0'; the value of the character '8' is eight greater than the value of '0'; and so on.
 *
 * So you can get the int value of a decimal digit char by subtracting '0'.
 *
 * char x = '9';
 * int y = x - '0'; // gives the int value 9
 */
public class AddTwoStringNumbers {
    public static String addStrings(String num1, String num2) {

        StringBuilder sb = new StringBuilder();
        int carry = 0;

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        while(i >= 0 || j >= 0){
            if(i>=0)
                // num1.charAt(i--) - '0', 只能是 - '0', + '0'不工作
                carry += num1.charAt(i--) - '0';
            if(j>=0)
                carry += num2.charAt(j--) - '0';

            sb.append(carry % 10);
            carry /= 10;
        }

        if (carry !=0){
            sb.append(carry);
        }
        return sb.reverse().toString();

    }

    public static void main(String[] args) {
        int[] arr = {-13, -5, 6, -7, 5, -3, 11 };

        addStrings("123", "11");

    }
}
