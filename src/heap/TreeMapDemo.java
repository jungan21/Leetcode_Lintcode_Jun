package heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo {

	public static void main(String[] args) {
		String text = "Have a good day. Have a good class. Have a good visit. Have fun";
		Map<String, Integer> hashMap = new HashMap<String, Integer>();
		String[] words = text.split("[ .!?]");
		for (int i = 0; i < words.length; i++) {
			if (words[i].length() > 1) {
				if (hashMap.containsKey(words[i])) {
					hashMap.put(words[i], hashMap.get(words[i]) + 1);
				} else
					hashMap.put(words[i], 1);

			}
		}
		System.out.println(hashMap);

		Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> entry1,
					Map.Entry<String, Integer> entry2) {
				String key1 = entry1.getKey();
				Integer val1 = entry1.getValue();
				String key2 = entry2.getKey();
				Integer val2 = entry2.getValue();
				if (val1 == val2) {
					return key1.compareTo(key2);
				} else {
					return val2 - val1;
				}
			}
		};

		Comparator<String> comparator1 = new Comparator<String>() {
			public int compare(String word1, String word2) {
				Integer count1 = hashMap.get(word1);
				Integer count2 = hashMap.get(word2);
				if (count1 == count2) {
					return word1.compareTo(word2);
				} else {
					return count2 - count1;
				}
			}
		};

		TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(
				comparator1);
		for (int i = 0; i < words.length; i++) {
			if (words[i].length() > 1) {
				if (treeMap.containsKey(words[i])) {
					treeMap.put(words[i], treeMap.get(words[i]) + 1);
				} else
					treeMap.put(words[i], 1);
			}
		}
		System.out.println(treeMap);

		// List<Map.Entry<String, Integer>> arrayList = new ArrayList<>(
		// hashMap.entrySet());
		// Collections.sort(arrayList,
		// new Comparator<Map.Entry<String, Integer>>() {
		// public int compare(Map.Entry<String, Integer> entry1,
		// Map.Entry<String, Integer> entry2) {
		// String key1 = entry1.getKey();
		// Integer val1 = entry1.getValue();
		// String key2 = entry2.getKey();
		// Integer val2 = entry2.getValue();
		// if (val1 == val2) {
		// return key1.compareTo(key2);
		// } else {
		// return val2 - val1;
		// }
		// }
		// });
		//
		// System.out.println(arrayList);
	}

}
