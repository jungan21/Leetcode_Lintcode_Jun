package a.design;

import java.util.ArrayList;
import java.util.List;

class Person {
	boolean isHost;

	public Person(boolean isHost) {
		this.isHost = isHost;
	}

	public boolean know(Person p) {
		if (p.isHost) {
			return true;
		}
		return false;
	}

}

public class FindHostPerson {

	public static void main(String[] args) {
		Person p1 = new Person(false);
		Person p2 = new Person(false);
		Person p3 = new Person(false);
		Person p4 = new Person(false);
		Person p5 = new Person(true);
		Person p6 = new Person(false);
		Person p7 = new Person(false);
		List<Person> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p7);

		System.out.println(findHostDivideConquer(list).isHost);
	}

	/**
	 * logn check two by two
	 */
	public static Person findHost(List<Person> list) {
		if (list == null || list.size() == 0) {
			return null;
		}

		while (list.size() > 1) {
			List<Person> temp = new ArrayList<>();
			for (int i = 0; i < list.size() - 1; i = i + 2) {
				Person p1 = list.get(i);
				Person p2 = list.get(i + 1);
				boolean know = p1.know(p2);
				if (know) {
					// p1认识p2, p2可能是host
					temp.add(p2);
				} else {
					// p1不认识p2, p1可能是host
					temp.add(p1);
				}
			}
			if (list.size() % 2 == 1) {
				temp.add(list.get(list.size() - 1));
			}
			list = temp;
		}
		return list.get(0);
	}

	/**
	 * divide and conquer
	 */
	public static Person findHostDivideConquer(List<Person> lists) {
		if (lists.size() == 0) {
			return null;
		}

		// 0 is index of first ListNode in lists
		return mergeHelper(lists, 0, lists.size() - 1);
	}

	private static Person mergeHelper(List<Person> lists, int start, int end) {
		// Note: 这一步不能省
		if (start == end) {
			return lists.get(start);
		}

		int mid = start + (end - start) / 2;

		// divide - recursive call
		Person left = mergeHelper(lists, start, mid);
		Person right = mergeHelper(lists, mid + 1, end);

		// conquer
		return merge(left, right);
	}

	public static Person merge(Person p1, Person p2) {
		if (p1.know(p2)) {
			return p2;
		} else {
			return p1;
		}
	}

}
