package String;

public class RBCStringBracket {

	public static void main(String[] args) {
		String S = "((";
		//String S = "))((";
		
		System.out.println( get (S));
		System.out.println(get2 (S));
		
	}
	// https://stackoverflow.com/questions/41752447/find-index-k-at-which-the-number-of-opening-and-closing-brackets-is-the-same
	public static int get (String S) {
		char[] arr = S.toCharArray();
		
		int i = 0;
		int j = arr.length -1 ;
		
		while (i <= j) {
	        if (arr[i] != '(') {
	            i++;
	        } else if (arr[j] != ')') {
	            j--;
	        } else {
	            i++;
	            j--;
	        }
	    }
	    return i;
		
	}
	
	// https://www.urfusion.net/codility-brackets-opening-closing-solution/
	public static int get2 (String S) {
		char[] arr = S.toCharArray();
		
		int oCount = 0;
		int cCount =0;
		int count = 0;
		
		for (int i = 0; i < arr.length; i++){
			if(arr[i] == '('){
				oCount++;
			} else if (arr[i] == ')'){
				cCount++;
			}
			
			if (oCount == cCount){
				count = oCount;
			} else {
				count = cCount;
			}
		}		
		return count;
	}
	
}


