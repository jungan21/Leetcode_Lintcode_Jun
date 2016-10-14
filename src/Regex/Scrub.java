package Regex;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scrub {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner console = new Scanner(System.in);

		while (console.hasNextLine()) {
			System.out.println(removeTag(console.nextLine())); // consume the
																// valid token
		}

	}

	public static String removeTag(String data) {
		StringBuilder regex = new StringBuilder("<script[^>]*>(.*?)</script>");
		int flags = Pattern.MULTILINE | Pattern.DOTALL
				| Pattern.CASE_INSENSITIVE;
		Pattern pattern = Pattern.compile(regex.toString(), flags);
		Matcher matcher = pattern.matcher(data);
		return matcher.replaceAll("");
	}

}
