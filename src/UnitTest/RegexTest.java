package UnitTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String data = "Ticket #5377-12593785 New Ticket added to Queue";
		//System.out.println(removeTag(data));
		System.out.println(2147483585 > Integer.MAX_VALUE);
	}

	public static String removeTag(String data) {
	  	 StringBuilder regex = new StringBuilder("^(.{8})w+:(.*)");
		 final Pattern RT_EXP = Pattern.compile(regex.toString());
		 Matcher matcher = RT_EXP.matcher(data);
		// matcher.find(5);
		// System.out.println(matcher);
		 return matcher.replaceAll("");
	}
}
