package Trie;

import java.util.Map;

/**
 * https://www.youtube.com/watch?v=AXjmTQ8LEoI
 *
 */

public class Trie {
	public TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		TrieNode now = root;
		for (int i = 0; i < word.length(); i++) {
			Character c = word.charAt(i);
			if (!now.subtree.containsKey(c)) {
				now.subtree.put(c, new TrieNode());
			}
			// now 往前移动，移动到指向刚刚插入的这个字符
			now = now.subtree.get(c);
		}
		now.s = word;
		now.hasWord = true;
	}

	// Inserts a word into the trie.- recursive version
	public void insertRecursive(String word) {
		insertRecursiveHelp(root, word, 0);
	}

	public void insertRecursiveHelp(TrieNode now, String word, int index) {
		if (index == word.length()) {
			now.hasWord = true;
			return;
		}
		Character c = word.charAt(index);
		if (!now.subtree.containsKey(c)) {
			now.subtree.put(c, new TrieNode());
		}
		TrieNode node = now.subtree.get(c);

		insertRecursiveHelp(node, word, index + 1);
	}

	// search Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode now = root;
		for (int i = 0; i < word.length(); i++) {
			Character c = word.charAt(i);
			if (!now.subtree.containsKey(c)) {
				return false;
			}
			now = now.subtree.get(c);
		}
		return now.hasWord;
	}

	// search Recursive
	public boolean searchRecursive(String word) {
		return searchRecursiveHelper(word, root, 0);
	}

	public boolean searchRecursiveHelper(String word, TrieNode now, int index) {
		if (index == word.length()) {
			return now.hasWord;
		}

		Character c = word.charAt(index);
		if (!now.subtree.containsKey(c)) {
			return false;
		}
		return searchRecursiveHelper(word, now.subtree.get(c), index + 1);

	}

	/**
	 * advanced Search 模糊查询, 其实就是上面的search的递归实现，里面加了个条件
	 * 
	 * if word bad exist in the Trie, search(".ad") return true;
	 * 
	 * http://www.jiuzhang.com/solutions/add-and-search-word/
	 */
	public boolean search_regularExpression(String word) {
		TrieNode now = root;
		return helper(word, now, 0);
	}

	boolean helper(String word, TrieNode now, int index) {
		if (index == word.length()) {
			return now.hasWord;
		}
		Character c = word.charAt(index);
		if (now.subtree.containsKey(c)) {
			return helper(word, now.subtree.get(c), index + 1);
		} else if (c == '.') {
			boolean result = false;
			for (Map.Entry<Character, TrieNode> child : now.subtree.entrySet()) {
				// if any path is true, set result to be true;
				if (helper(word, child.getValue(), index + 1)) {
					result = true;
				}
			}
			return result;
		} else {
			return false;
		}
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		TrieNode now = root;
		for (int i = 0; i < prefix.length(); i++) {
			Character c = prefix.charAt(i);
			if (!now.subtree.containsKey(c)) {
				return false;
			}
			now = now.subtree.get(c);
		}
		return true;
	}

	// delete word from Trie
	public void delete(String word) {
		deleteHelper(root, word, 0);
	}

	public boolean deleteHelper(TrieNode now, String word, int index) {
		if (index == word.length()) {
			// means Trie dosn't have this word
			if (!now.hasWord) {
				return false;
			}
			// if this word exist, just set hasWord attribute to false;
			now.hasWord = false;
			// check whether this node node has any other mapping, return true,
			// if there is other mappings
			return now.subtree.size() == 0;
		}

		Character c = word.charAt(index);
		if (!now.subtree.containsKey(c)) {
			return false;
		}

		boolean shouldDeleteThisNode = deleteHelper(now.subtree.get(c), word,
				index + 1);

		if (shouldDeleteThisNode) {
			now.subtree.remove(c);
			return now.subtree.size() == 0;
		}
		return false;
	}

}
