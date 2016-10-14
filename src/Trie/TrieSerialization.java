package Trie;

import java.util.Map;
import java.util.Stack;

public class TrieSerialization {

	public static void main(String[] args) {
		System.out.println(deserialize("<a<b<>c<>d<>>>"));
	}

	public static String serialize(TrieNode root) {
		if (root == null)
			return "";

		StringBuffer sb = new StringBuffer();
		sb.append("<");
		for (Map.Entry<Character, TrieNode> entry : root.subtree.entrySet()) {
			Character key = entry.getKey();
			TrieNode child = entry.getValue();
			// 顺序，类似tree pre order 遍历
			sb.append(key);
			sb.append(serialize(child));
		}
		sb.append(">");
		return sb.toString();
	}

	public static TrieNode deserialize(String data) {
		if (data == null || data.length() == 0)
			return null;

		TrieNode root = new TrieNode();
		TrieNode current = root;
		Stack<TrieNode> path = new Stack<TrieNode>();
		for (Character c : data.toCharArray()) {
			if (c == '<') {
				path.push(current);
			} else if (c == '>') {
				path.pop();
			} else {
				current = new TrieNode();
				path.peek().subtree.put(c, current);
			}
		}
		return root;
	}

}
