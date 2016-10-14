package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Document {
	public int id;
	public String content;
}

public class InvertedIndex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
		// Write your code here
		Map<String, List<Integer>> results = new HashMap<String, List<Integer>>();
		for (Document doc : docs) {
			int id = doc.id;
			String content = doc.content;
			String[] words = content.split(" ");
			for (String word : words) {
				insert(results, word, id);
			}
		}
		return results;
	}

	public void insert(Map<String, List<Integer>> rt, String tmp, int id) {
		if (tmp.equals("") || tmp == null)
			return;
		if (!rt.containsKey(tmp))
			rt.put(tmp, new ArrayList<Integer>());

		int n = rt.get(tmp).size();
		if (n == 0 || rt.get(tmp).get(n - 1) != id)
			rt.get(tmp).add(id);
	}

}
