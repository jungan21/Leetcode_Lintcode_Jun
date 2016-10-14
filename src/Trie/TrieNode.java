package Trie;

import java.util.HashMap;

public class TrieNode {
	public HashMap<Character, TrieNode> subtree;
	public boolean hasWord;
	String s;

	public TrieNode() {
		subtree = new HashMap<Character, TrieNode>();
		hasWord = false;
		s = "";
	}

}