package company.walmart;


// abc ==> a, ab, abc, abcd, b, bc, bcd, c, cd, d,
public class StringAllSubstrings {

    public static void main(String[] args) {
        String str = "abcd";
        SubString(str, str.length());

    }
    // Function to print all substring
    public static void SubString(String str, int n)
    {
        for (int i = 0; i < n; i++)
            for (int j = i+1; j <= n; j++)
                System.out.print(str.substring(i, j) + ", ");
    }

}
