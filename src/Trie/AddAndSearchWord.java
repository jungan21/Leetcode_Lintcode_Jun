package Trie;

/**
 * Design a data structure that supports the following two operations:
 * addWord(word) and search(word)
 * 
 * search(word) can search a literal word or a regular expression string
 * containing only letters a-z or ..
 * 
 * A . means it can represent any one letter.
 * 
 * Notice
 * 
 * You may assume that all words are consist of lowercase letters a-z.
 *
 *
 * Example addWord("bad")
 * 
 * addWord("dad")
 * 
 * addWord("mad")
 * 
 * search("pad") // return false
 * 
 * search("bad") // return true
 * 
 * search(".ad") // return true
 * 
 * search("b..") // return true
 */
public class AddAndSearchWord {

	public static Trie trie = new Trie();

	public void addWord(String word) {
		trie.insert(word);
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		// 核心点
		return trie.search_regularExpression(word);
	}

	public static void main(String[] args) {
		AddAndSearchWord dic = new AddAndSearchWord();
		dic.addWord("a");
		System.out.println(dic.search("."));
	}

}
