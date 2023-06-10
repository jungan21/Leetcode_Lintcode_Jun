package company.walmart;

import java.net.URI;
import java.net.URISyntaxException;

public class URLProtocol_Host {
    public static void main(String args[]) throws URISyntaxException {
        String url = "https://www.baeldung.com/java";
//        //String domainName =  url.replaceAll("http(s)?://|www\\.|/.*", "");
//        String protocol =  url.substring(0, url.indexOf(":"));
//        String domainName = url.substring(url.indexOf("//"), url.indexOf("/"));
//        System.out.println("protocol: " + protocol);
//        System.out.println("domainName: " + domainName);
        method1(url);
        method2(url);
    }

    public static void method1(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String host = uri.getHost(); // www.baeldung.com
        String domain = host.startsWith("www.") ? host.substring(4) : host;  // baeldung.com
        System.out.println(domain);

    }

    public static void method2(String url) throws URISyntaxException {
        /**
         * n+	Matches any string that contains at least one n
         * n*	Matches any string that contains zero or more occurrences of n
         * n?	Matches any string that contains zero or one occurrences of n
         */
        // String domainName =  url.replaceAll("http(s)?://|www\\.|/.*", "");
        // String url = "https://www.baeldung.com/java";
        String domainName =  url.replaceAll("http(s)?://www\\.|/.*", "");
        System.out.println(domainName);

    }
}
