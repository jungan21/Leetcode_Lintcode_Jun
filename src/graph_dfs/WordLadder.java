package graph_dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

	public static void main(String[] args) {
		
		String start = "hit";
		String end = "cog";

		HashSet<String> set = new HashSet<String>();
		set.add("hot");
		set.add("dot");
		set.add("dog");
		set.add("lot");
		set.add("log");

		System.out.println(new WordLadder().ladderLength(start, end, set));
	}

	/**
	 * http://blog.csdn.net/linhuanmars/article/details/23029973
	 * 
	 * http://www.cnblogs.com/TenosDoIt/p/3443512.html
	 * 
	 * @param dict
	 * 
	 * 
	 *            分析：这种题，肯定是每次改变单词的一个字母，然后逐渐搜索，很多人一开始就想到用dfs，其实像这种求最短路径、
	 *            树最小深度问题bfs最适合，可以参考我的这篇博客bfs（层序遍历）求二叉树的最小深度。本题bfs要注意的问题：
	 * 
	 *            和当前单词相邻的单词是(想象成图)：对当前单词改变一个字母且在字典中存在的单词
	 *            找到一个单词的相邻单词，加入bfs队列后，要从字典中删除，
	 *            因为不删除的话会造成类似于hog->hot->hog的死循环。而删除对求最短路径没有影响
	 *            ，因为我们第一次找到该单词肯定是最短路径
	 *            ，即使后面其他单词也可能转化得到它，路径肯定不会比当前的路径短（如果要输出所有最短路径
	 *            ，则不能立即从字典中删除，具体见下一题） bfs队列中用NULL来标识层与层的间隔，每次碰到层的结尾，遍历深度+1
	 * 
	 *            正因为BFS能够把一层所有可能性都遍历了，所以就保证了一旦找到一个单词equals（end），
	 *            那么return的路径肯定是最短的。
	 * 
	 */

	// http://www.cnblogs.com/springfor/p/3893499.html
	public int ladderLength(String start, String end, Set<String> dict) {
		if (dict == null) {
			return 0;
		}

		if (start.equals(end)) {
			return 1;
		}
		// dict.add(start); 不放没有问题
		// 必须要放里面，否则不对
		dict.add(end);

		/**
		 * dict.add(start); 不放没有问题, dict.add(end);必须要放里面，否则不对，
		 * 因为getNextWords方法里，
		 * 在取下一level的word的时候，如果不在dict就不会加入到下一层，如果在最开始end不在dict里
		 * ，就意味着，和end相等的词永远不可能被加入到下一层去，那么nextWord.equals(end)永远都为false
		 */
		// 是所以定义一个visited HashSet, 是为了提高效率，如果单从功能角度来看，不需要的， 如果不用visited to
		// track，会遇到Time Limit Exceeded
		HashSet<String> visited = new HashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		queue.offer(start);
		visited.add(start);

		// the start string already count for 1
		int length = 1;
		while (!queue.isEmpty()) {
			// 每到一个level，length++, level order traverse
			length++;
			int size = queue.size();
			// level by level 处理，这样最后的length 才是level,也就是实际的高度，
			// LevelOrderTraversal.java
			for (int i = 0; i < size; i++) {
				String word = queue.poll();
				for (String nextWord : getNextWords(word, dict)) {
					if (visited.contains(nextWord)) {
						continue;
					}
					if (nextWord.equals(end)) {
						return length;
					}

					visited.add(nextWord);
					queue.offer(nextWord);
				}
			}
		}
		return 0;
	}

	// get connections with given word.
	// for example, given word = 'hot', dict = {'hot', 'hit', 'hog'}
	// it will return ['hit', 'hog']
	// 和当前单词相邻的单词是(想象成图)：对当前单词改变一个字母且在字典中存在的单词
	/**
	 * Time: (in jiuzhzhang class)
	 * 
	 * 26 * L * L (L是单词average 长度)
	 */
	private ArrayList<String> getNextWords(String word, Set<String> dict) {
		ArrayList<String> nextWords = new ArrayList<String>();
		for (char c = 'a'; c <= 'z'; c++) {
			for (int i = 0; i < word.length(); i++) {
				if (c == word.charAt(i)) {
					continue;
				}
				// O(L)
				String nextWord = replace(word, i, c);
				// O(L)
				if (dict.contains(nextWord)) {
					nextWords.add(nextWord);
				}
			}
		}
		return nextWords;
	}

	// replace character of a string at given index to a given character
	// return a new string
	private String replace(String s, int index, char c) {
		char[] chars = s.toCharArray();
		chars[index] = c;
		return new String(chars);
	}

	/**
	 * 上面getNextWords 和 replace两个方法，可以用下面的方法替代
	 */
	private List<String> getNextLevelWordList(String word, Set<String> dict) {
		List<String> wordList = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			for (char ch = 'a'; ch <= 'z'; ch++) {
				if (word.charAt(i) != ch) {
					// substring(i+1)
					// substring不会越界，如果i越界了，substring只会返回空字符串，所以不用考虑i+1越界问题
					String newString = word.substring(0, i) + ch
							+ word.substring(i + 1);
					if (dict.contains(newString)) {
						wordList.add(newString);
					}
				}
			}
		}
		return wordList;
	}
}
