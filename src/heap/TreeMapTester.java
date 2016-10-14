package heap;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapTester {

	public static void main(String[] args) {
		TreeMap<Integer, Integer> map = new TreeMap<>(
				Collections.reverseOrder());

		map.put(8, 1);
		map.put(6, 4);
		map.put(9, 8);
		System.out.println(map.lastKey());
		System.out.println(map.firstKey());
		System.out.println(map.lastEntry().getKey() + ","
				+ map.lastEntry().getValue());
		System.out.println("=============");
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "," + entry.getValue());
		}

		HashSet<Integer> set = new HashSet<Integer>();
	}

}
