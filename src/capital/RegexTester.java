package capital;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data = "Hello ${name}hello#{name}";
		System.out.println(removeTag(data));
	}

	public static String removeTag(String data) {
	  	 StringBuilder regex = new StringBuilder("[\\$#%]\\{([^}]+)}");
		 final Pattern RT_EXP = Pattern.compile(regex.toString());
		 Matcher matcher = RT_EXP.matcher(data);
		 matcher.find(5);
		// System.out.println(matcher);
		 return matcher.replaceAll("");
	}

}
