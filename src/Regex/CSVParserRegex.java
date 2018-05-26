package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CSV Parser which can properly handle quote
 * @author ganjun
 *
 */

public class CSVParserRegex {

	public static void main(String[] args) {
		String s = "a1, a2, a3, \"a4,a5\", a6";
		// \s* 0 OR more times whitespace
		Pattern pattern = Pattern.compile("\\s*(\"[^\"]*\"|[^,]*)\\s*");
		Matcher matcher = pattern.matcher(s);
		int count = matcher.groupCount();
		System.out.println(count);
		//System.out.print(matcher.group(0));
		System.out.print(matcher.group(1));
		while (matcher.find()) {
		    System.out.println(matcher.group(1));
		}
	}

}
