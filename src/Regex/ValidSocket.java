package Regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidSocket {

	private static final int IPV4_MAX_OCTET_VALUE = 255;
	
	//^	The beginning of a line
	//$	The end of a line
	// \d	A digit: [0-9]  extra \ 转意字符
	// {1，3} number appear at least 1 time, but no more than 3 times 
	// X{n,m}	X, at least n but not more than m times
	// => (\\d{1,3})
	// by default . is used to match any character. \. to make sure it matches "."
	static final String IPV4_REGEX = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";
	static Pattern pattern = Pattern.compile(IPV4_REGEX);
	// https://commons.apache.org/proper/commons-validator/apidocs/src-html/org/apache/commons/validator/routines/InetAddressValidator.html#line.86

	public static void main(String[] args) {

		System.out.println(new ValidSocket().isValidInet4Address("1.1.2.3"));

	}
	
	//https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html

	public boolean isValidInet4Address(String inet4Address) {
		// verify that address conforms to generic IPv4 format
		String[] groups = match(inet4Address);

		if (groups == null) {
			return false;
		}

		// verify that address subgroups are legal
		for (String ipSegment : groups) {
			if (ipSegment == null || ipSegment.length() == 0) {
				return false;
			}

			int iIpSegment = 0;

			try {
				iIpSegment = Integer.parseInt(ipSegment);
			} catch (NumberFormatException e) {
				return false;
			}

			if (iIpSegment > IPV4_MAX_OCTET_VALUE) {
				return false;
			}

			if (ipSegment.length() > 1 && ipSegment.startsWith("0")) {
				return false;
			}

		}

		return true;
	}

	public String[] match(String value) {
		if (value == null) {
			return null;
		}
		
		Matcher matcher = pattern.matcher(value);
		if (matcher.matches()) {
			int count = matcher.groupCount();
			String[] groups = new String[count];
			for (int j = 0; j < count; j++) {
				groups[j] = matcher.group(j + 1);
			}
			return groups;
		}
		return null;
	}

}
