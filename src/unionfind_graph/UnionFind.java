package unionfind_graph;

import java.util.HashMap;

public class UnionFind {

	HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();

	/**
	 * O(n),如果用compressed_find就是O(1)
	 * 
	 * 用非递归的方式好， 否则如果路径过程，容易stack over flow
	 */
	int find(int x) {
		int parent = father.get(x);
		while (parent != father.get(parent)) {
			parent = father.get(parent);
		}
		return parent;
	}

	// O(n),如果用compressed_find就是O(1)
	void union(int x, int y) {
		int fa_x = find(x);
		int fa_y = find(y);
		// 只有不属于一个集合的时候，才需要合并，否则直接skip
		if (fa_x != fa_y)
			father.put(fa_x, fa_y);
	}

	/**
	 * A->B->C->D->E->F
	 * 
	 * 压缩后在hashmap里是这样的
	 * 
	 * A->F, B->F, C->F, D->F, E->F, F->F
	 */
	int compressed_find(int x) {
		// 第一次遍历先找到这条路径条路径的最后的root,也就是所有点的root i.e F
		int parent = father.get(x);
		while (parent != father.get(parent)) {
			parent = father.get(parent);
		}

		/**
		 * 上面一次loop 完了 parent = F
		 * 
		 * 第二次遍历，把这了路径上的每个点摘下来，都直接指向最后的root,放入hashmap存下来
		 * 
		 * 相当于把一个本来很长的链状结构，变成类似于每条路径都很短的网状结构
		 */
		int fa = father.get(x);
		while (fa != father.get(fa)) {
			// 当前节点的下一个点，先取出来保存到temp, 否则下面处理后，就会丢失了，驾驶cur是A，temp就是B
			int temp = father.get(fa);
			// 遍历第二遍，把这了路径上的每个点摘下来，直接指向最后的root，也就是第一遍遍历时找到的parent
			father.put(fa, parent);
			fa = temp;
		}
		return parent;

	}

}
