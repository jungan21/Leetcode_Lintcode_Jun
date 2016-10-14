package String;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * Given an absolute path for a file (Unix-style), simplify it.
 * 
 * For example:
 * 
 * path = "/home/", => "/home"
 * 
 * path = "/a/./b/../../c/", => "/c"
 *
 * path = "/a/./b/../c/", => "/a/c"和 path = "/a/./b/c/", => "/a/b/c"
 */
public class SimplyPath {

	public static void main(String[] args) {
		// String path = "/a/./b/../../c/";
		String path = "/a/./b/../c/";
		// String path = "/home/";
		System.out.println(simplifyPathStack(path));
	}

	public static String simplifyPath(String path) {
		String result = "/";
		String[] stubs = path.split("/+");
		ArrayList<String> paths = new ArrayList<String>();
		for (String s : stubs) {
			if (s.equals("..")) {
				if (paths.size() > 0) {
					paths.remove(paths.size() - 1);
				}
			} else if (!s.equals(".") && !s.equals("")) {
				paths.add(s);
			}
		}
		for (String s : paths) {
			result += s + "/";
		}
		if (result.length() > 1)
			result = result.substring(0, result.length() - 1);
		return result;
	}

	public static String simplifyPathStack(String path) {
		String result = "/";
		String[] stubs = path.split("/");
		Stack<String> paths = new Stack<String>();
		for (String s : stubs) {
			if (s.equals("..")) {
				if (paths.size() > 0) {
					paths.pop();
				}
			} else if (!s.equals(".") && !s.equals("")) {
				paths.push(s);
			}
		}

		while (!paths.isEmpty()) {
			result = "/" + paths.pop() + result;
		}
		if (result.length() > 1)
			result = result.substring(0, result.length() - 1);
		return result;
	}

}
