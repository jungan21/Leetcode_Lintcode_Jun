package heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a list of words and an integer k, return the top k frequent words in
 * the list.
 * 
 * Notice
 * 
 * You should order the words by the frequency of them in the return list, the
 * most frequent one comes first. If two words has the same frequency, the one
 * with lower alphabetical order come first.
 * 
 * 考点：
 * 
 * 1. Map 迭代
 *
 * 2. compare two string
 * 
 */
public class TopKFrequentWords {

	public static void main(String[] args) {
		String[] words = { "yes", "lint", "code", "yes", "code", "baby", "you",
				"baby", "chrome", "safari", "lint", "code", "body", "lint",
				"code" };
		new TopKFrequentWords().topKFrequentWords(words, 3);

	}

	class Pair {
		String word;
		Integer count;

		public Pair(String word, Integer count) {
			this.word = word;
			this.count = count;
		}
	}

	/**
	 * method 2 Best one 面试的话必须用minHeap
	 *
	 * 
	 * /** Note: how to compare two String:
	 * 
	 * return value > 0 ==> a.word > b.word;
	 * 
	 * return value == 0 ==> a.word == b.word;
	 * 
	 * return value < 0 ==> a.word < b.word;
	 */
	public String[] topKFrequentWords(String[] words, int k) {
		String[] result = new String[k];
		if (words == null || words.length == 0 || k < 1) {
			return result;
		}
		Comparator<Pair> comparator = new Comparator<Pair>() {
			public int compare(Pair a, Pair b) {
				if (a.count == b.count) {
					// 字母逆序排列
					return b.word.compareTo(a.word);
				}
				// frequency升序排列
				return a.count - b.count;
			}
		};

		HashMap<String, Integer> map = new HashMap<>();
		PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>(k, comparator);

		for (String word : words) {
			if (map.containsKey(word)) {
				map.put(word, map.get(word) + 1);
			} else {
				map.put(word, 1);
			}
		}
//
//		for (Map.Entry<String, Integer> entry : map.entrySet()) {
//			Pair pair = new Pair(entry.getKey(), entry.getValue());
//			minHeap.offer(pair);
//			// 由于是minheap,每次poll出来都是最小的，剩下的都是 frequency大的
//			if (minHeap.size() > k) {
//				minHeap.poll();
//			}
//		}
		// 也可以按上面的方法写，不过效率没有这种好
		// Do it in O(nlogk) time and O(n) extra space.

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			Pair peek = minHeap.peek();
			Pair newPair = new Pair(entry.getKey(), entry.getValue());
			if (minHeap.size() < k) {
				minHeap.offer(newPair);
			} else {
				if (comparator.compare(newPair, peek) > 0) {
					minHeap.poll();
					minHeap.add(newPair);
				}
			}
		}
		// 因为是minheap,每一次poll出来的是 frequncy 最小的
		for (int i = k - 1; i >= 0; i--) {
			result[i] = minHeap.poll().word;
		}
		return result;
	}

	public String[] topKFrequentWords2(String[] words, int k) {
		// Note: you can't set String = null,otherwise, you will get nullpoint
		String[] result = new String[k];
		if (words == null || words.length == 0 || k < 1) {
			return result;
		}

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>(k,
				new Comparator<Pair>() {
					public int compare(Pair a, Pair b) {
						if (a.count == b.count) {
							/**
							 * Note: how to compare two String:
							 * 
							 * return value > 0 ==> a.word > b.word;
							 * 
							 * return value == 0 ==> a.word == b.word;
							 * 
							 * return value < 0 ==> a.word < b.word;
							 */
							// 字母顺序排列
							return a.word.compareTo(b.word);
						} else {
							// 因为frequency 大的要放在前面，所以是根据frequency逆序
							return b.count - a.count;
						}

					}
				});

		for (int i = 0; i < words.length; i++) {
			if (map.containsKey(words[i])) {
				map.put(words[i], map.get(words[i]) + 1);
			} else {
				map.put(words[i], 1);
			}
		}
		// Note: Map.Entry
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			// key is word, value is the count
			Pair pair = new Pair(entry.getKey(), entry.getValue());
			maxHeap.offer(pair);
		}
		// if two words has the same frequency, the one with lower alphabetical
		// order come first.
		for (int i = 0; i < k; i++) {
			result[i] = maxHeap.poll().word;
		}
		return result;
	}

}
