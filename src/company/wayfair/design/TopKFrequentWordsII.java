package company.wayfair.design;

import java.util.*;

/**
 * Find top k frequent words in realtime data stream.
 * 
 * Implement three methods for Topk Class:
 * 
 * TopK(k). The constructor. add(word). Add a new word. topk(). Get the current
 * top k frequent words.
 * 
 *
 */
public class TopKFrequentWordsII {
	private Map<String, Integer> words = null;
	TreeMap<String, Integer> topk = null;
	private int k;
	// descending
	private Comparator<String> myComparator = new Comparator<String>() {
		public int compare(String left, String right) {
			if (left.equals(right))
				return 0;

			int left_count = words.get(left);
			int right_count = words.get(right);
			if (left_count != right_count) {
				return right_count - left_count;
			}
			return left.compareTo(right);
		}
	};

	public TopKFrequentWordsII(int k) {
		// initialize your data structure here
		this.k = k;
		words = new HashMap<String, Integer>();
		topk = new TreeMap<String, Integer>(myComparator);
	}

	public void add(String word) {
		
		if (words.containsKey(word)) {
			if (topk.containsKey(word)) {
				topk.remove(word);
			}
			words.put(word, words.get(word) + 1);
		} else {
			words.put(word, 1);
		}

		topk.put(word, words.get(word));
		if (topk.size() > k) {
			topk.pollLastEntry();
		}
	}

	public List<String> topk() {
		List<String> results = new ArrayList<String>();

		for (Map.Entry<String, Integer> entry : topk.entrySet()) {
			String str = entry.getKey();
			Integer count = entry.getValue();
			results.add(str);
		}
		return results;
	}

	public static void main(String[] args) {

	}

}
