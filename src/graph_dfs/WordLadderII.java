package graph_dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Best: https://segmentfault.com/a/1190000006079776
 * 
 * 分析： 第一感觉是dfs + backtracking，就是先用bfs求出beginWord到endWord的最短距离，然后dfs +
 * backtracking找到不同的路径，等于这个最短距离的留下，大于的pass，果断TLE。
 * 
 * 简单思考了下，除了可能出现环的情况会TLE外，还可以通过剪枝的方法优化
 * ——用HashMap记录每个单词和beginWord之间的距离，当dfs遍历到某个单词已经比最短距离长的时候，就不用再遍历下去了。提交，又TLE。
 * 
 * 仔细又一想，这种剪枝等于没剪枝，想想HashMap是怎么求出来的？是在BFS的时候层序更新的，搜索到endWord的时候就停止了。
 * 所以道理上不存在比到endWord更长的一条路
 * 。问题不是出在走了更长的路，而是因为所有的路线都是从beginWword辐射出去的，dfs搜索的话有太多的岔路。
 * 所以一个比较自然的想法是，从endWord往回找
 * ，就可以避免许多岔路。进一步的剪枝是依据HashMap里记录的距离，规定dfs的方向只往里beginWord距离更小的方向遍历。
 * 
 */
public class WordLadderII {

	public static void main(String[] args) {
		String start = "hit";
		String end = "cog";

		HashSet<String> set = new HashSet<String>();
		set.add("hot");
		set.add("dot");
		set.add("dog");
		set.add("lot");
		set.add("log");
		System.out.println(new WordLadderII().findLadders(start, end, set));
	}

	// http://huangyawu.com/leetcode-word-ladder-ii-java/
	// http://javabypatel.blogspot.ca/2015/10/word-ladder-doublets-word-links-word-golf.html
	/**
	 * BFS: bfs构图的过程中，每个点进入队列一次，时间是O(n);
	 * 
	 * dfs找路线的时候最坏情况是O(n^2)(因为n个点最多 C(n,2), n(n-1)/2个边),平均时间复杂度是O(n).
	 * 
	 * 九章令狐老师： O(答案个数  * 构造每个答案的时间 + 单词个数 * 图的边数)
	 */
	public List<List<String>> findLadders(String start, String end,
			Set<String> dict) {
		List<List<String>> result = new ArrayList<List<String>>();

		/**
		 * BFS时记录每个单词的前驱, key是该单词， value 是该单词所有的前驱节点
		 * 
		 * map<key是dict里的每个词， value是，list里记录是由哪些词能够变成这个key, 也就是dict里的词>
		 * 
		 * 简单来说key:dict里单词， value: key的所有可能的前驱节点集合 (要成为真正的前驱，必须满足下面条件)
		 * 
		 * 从start 出发到 next 距离 加1,等于 start 出发到crt的距离，i.e start
		 * ->.....->next->crt,
		 * 
		 * {hit=[hot], cog=[dog, log], hot=[hit, dot, lot], lot=[hot, dot, log],
		 * dog=[dot, log, cog], log=[lot, dog, cog], dot=[hot, lot, dog]}
		 */
		Map<String, List<String>> map = new HashMap<String, List<String>>();

		/**
		 * distance map <String, Integer>记录有start变成各种数需要的次数 {hit=0, cog=4,
		 * lot=2, hot=1, dog=3, log=3, dot=2}
		 * 
		 * 简单来说key:dict里单词， value: 由start
		 * 转换到key所代表的单词，需要的最短装换的步骤，在DFS时候，利用这个distance map也可以避免环
		 */
		Map<String, Integer> distance = new HashMap<String, Integer>();
		// start 可以不用放在end里, 当时build graph,放里面更合理一点
		dict.add(start);
		dict.add(end);
		// BFS 就是填充上面两个map， 从start 到 end
		BFS(map, distance, start, end, dict);

		List<String> path = new ArrayList<String>();
		// DFS 就是对上面BFS填充的两个map结果进行 DFS，找出路径， 注意从end往start找路径，
		// 因为我们build的map,保存时每个节点的前驱集合，从后往前找方便
		/**
		 * 1）在替换String的某一位的字符时，先转换成char数组再操作；
		 * 2）如果按照正常的方法从start找end，然后根据这个来构造路径，代价会比较高
		 * ，因为保存前驱结点容易，而保存后驱结点则比较困难。所以我们在DFS搜索时反过来先从end找start
		 * ，最后再根据生成的前驱结点映射从start往end构造路径，这样算法效率会有明显提高。
		 */
		// System.out.println(map);
		// System.out.println(distance);
		// 从end 往start 找
		DFS(result, path, end, start, distance, map);

		return result;
	}

	// build graph ,key 是dict里的词，value是那些词经过一个字母装换可以变成这个词，相当于入度
	void BFS(Map<String, List<String>> map, Map<String, Integer> distance,
			String start, String end, Set<String> dict) {
		Queue<String> queue = new LinkedList<String>();
		queue.offer(start);
		// start --> start 需要0步装换
		distance.put(start, 0);
		// init 前驱map,为dict里的每个词去找前驱节点集合
		for (String word : dict) {
			map.put(word, new ArrayList<String>());
		}

		while (!queue.isEmpty()) {
			// crt从start 开始
			String crt = queue.poll();

			List<String> nextList = expand(crt, dict);
			// 有expand方法出来的nextList里的word肯定都在dict里，也就是说一定在map里
			for (String next : nextList) {
				// 这里之所有不用去重，因为下面的queue.offer(next)放在了if条件里， 有了去重的效果了
				map.get(next).add(crt);
				// !!!! 只有没处理过得才往distance map里面加, 为字典里的每个单词计算start 到该单词的距离，
				if (!distance.containsKey(next)) {
					distance.put(next, distance.get(crt) + 1);
					// 一定要放在if里，否则死循环， 相当于去重的作用
					queue.offer(next);
				}
			}
		}
	}

	/**
	 * 之所以要倒过来从end 往start找，是因为我们build graph map的结构决定的，这样找起来方便
	 * 
	 * 
	 * DFS 的methd2 https://segmentfault.com/a/1190000006079776
	 * 
	 */
	void DFS(List<List<String>> result, List<String> path, String crt,
			String start, Map<String, Integer> distance,
			Map<String, List<String>> map) {
		// 以每个crt做DFS
		path.add(crt);
		if (crt.equals(start)) {
			// 因为我们是从end逆向往前找start, 所以在放到结果集中，要reverse
			Collections.reverse(path);
			result.add(new ArrayList<String>(path));
			// 放入结果集合后，要还原到原来的从end-->start的顺序
			Collections.reverse(path);
			// 注意：这里不能return,否则不对,因为 如果这里return了，程序就返回到else里面的 for(String
			// next...),而我们希望return 到 path.remove(path.size() - 1);开始删除接回朔的过程
		} else {
			// 查看该结点的所有前驱集合,在图中，next-->crt (next 是crt的前驱)
			// 如果不加上distance.get(crt) == distance.get(next) + 1, 会stack over
			// flow
			for (String next : map.get(crt)) {
				/**
				 * 因为map里的前驱list里保存的仅仅是表示这个list里单词改变一个单词就可以变成crt,
				 * 这并不表示，list里所有单词，如果从start出发，逐个改变一个单词的距离也差一 但实际distance
				 * map才保存了，每个单词从start 出发需要改变几次才能成为这个单词
				 * 
				 * 我们需要找这样的next,从start 出发到 next 距离 加1,等于 start 出发到crt的距离
				 * 
				 * start ->.....->next->crt,
				 */

				if (distance.containsKey(next)
						&& distance.get(crt) == distance.get(next) + 1) {
					DFS(result, path, next, start, distance, map);
				}

			}
		}
		System.out.println(path);
		path.remove(path.size() - 1);

	}

	List<String> expand(String crt, Set<String> dict) {
		List<String> expansion = new ArrayList<String>();
		for (int i = 0; i < crt.length(); i++) {
			for (char ch = 'a'; ch <= 'z'; ch++) {
				if (ch != crt.charAt(i)) {
					// 和 wordladder一样，就是把第i个元素换成ch
					String expanded = crt.substring(0, i) + ch
							+ crt.substring(i + 1);
					if (dict.contains(expanded)) {
						expansion.add(expanded);
					}
				}
			}
		}

		return expansion;
	}

}
