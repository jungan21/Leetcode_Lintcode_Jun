package a.design;

import java.util.HashMap;

/**
 * 
 * http://blog.csdn.net/beiyetengqing/article/details/7706086
 *
 */
public class TinyURL {

	public static void main(String[] args) {
		TinyURL tiny = new TinyURL();
		System.out.println(tiny.longToShort("http://www.lintcode.com"));
		System.out.println(tiny.shortToLong("http://tiny.url/000001"));
	}

	public static int GLOBAL_ID = 0;
	private HashMap<Integer, String> id2url = new HashMap<Integer, String>();
	private HashMap<String, Integer> url2id = new HashMap<String, Integer>();

	public String longToShort(String url) {
		if (url2id.containsKey(url)) {
			return "http://tiny.url/" + idToShortKey(url2id.get(url));
		}
		GLOBAL_ID++;
		url2id.put(url, GLOBAL_ID);
		id2url.put(GLOBAL_ID, url);
		return "http://tiny.url/" + idToShortKey(GLOBAL_ID);
	}

	public String shortToLong(String url) {
		String short_key = getShortKey(url);
		int id = shortKeytoID(short_key);
		return id2url.get(id);
	}

	private String getShortKey(String url) {
		return url.substring("http://tiny.url/".length());
	}

	private int toBase62(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		}
		if (c >= 'a' && c <= 'z') {
			return c - 'a' + 10;
		}
		return c - 'A' + 36;
	}

	/**
	 * 对于短址aaae9a，其62进制为[0, 0, 0, 4,61,0] ，则其长地址的ID 为[0, 0, 0, 4,61,0] = 0×62^5+
	 * 0×62^4 + 0×62^3 + 4×62^2 + 61×62^1 + 0×62^0 = 1915810。有了ID，我们自然就可以得到长地址了。
	 */
	private int shortKeytoID(String short_key) {
		int id = 0;
		for (int i = 0; i < short_key.length(); ++i) {
			id = id * 62 + toBase62(short_key.charAt(i));
		}
		return id;
	}

	private String idToShortKey(int id) {
		String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String short_url = "";
		while (id > 0) {
			short_url = chars.charAt(id % 62) + short_url;
			id = id / 62;
		}
		while (short_url.length() < 6) {
			short_url = "0" + short_url;
		}
		return short_url;
	}

}
