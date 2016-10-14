package a_oa;

import java.util.ArrayList;
import java.util.List;

public class CommonEmailAddressInAllList {

	public static void main(String[] args) {
		List<String> l1 = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		List<String> l3 = new ArrayList<>();

		l1.add("foo@ibm.com");
		l1.add("jun@ibm.com");
		l1.add("gan@ibm.com");

		l2.add("foo@ibm.com");
		l2.add("foo1@ibm.com");
		l2.add("foo2@ibm.com");

		l3.add("foo@ibm.com");
		l3.add("foo5@ibm.com");
		l3.add("foo6@ibm.com");

		List<List<String>> lists = new ArrayList<>();
		lists.add(l1);
		lists.add(l2);
		lists.add(l3);
		System.out.println(getCommonEmai(lists, 0, lists.size() - 1));
	}

	public static List<String> getCommonEmai(List<List<String>> lists,
			int start, int end) {
		if (start >= end) {
			return lists.get(start);
		}
		int mid = start + (end - start) / 2;
		List<String> left = getCommonEmai(lists, start, mid);
		List<String> right = getCommonEmai(lists, mid + 1, end);
		List<String> result = merge(left, right);
		return result;
	}

	public static List<String> merge(List<String> l1, List<String> l2) {
		List<String> result = new ArrayList<>();
		for (String email : l1) {
			if (l2.contains(email) && !result.contains(email)) {
				result.add(email);
			}
		}
		return result;
	}
}
