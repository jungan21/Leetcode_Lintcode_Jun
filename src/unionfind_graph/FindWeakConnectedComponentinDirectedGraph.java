package unionfind_graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Find the number Weak Connected Component in the directed graph. Each node in
 * the graph contains a label and a list of its neighbors. (a connected set of a
 * directed graph is a subgraph in which any two vertices are connected by
 * direct edge path.)
 * 
 * Notice: Sort the element in the set in increasing order
 * 
 * 对于无向图，没有强/弱 联通component的概念，只有有向图才有，强/弱连图component 的概念
 * 
 * 对于图中两个点，如果是强联通componeng, A能找到B， B也能找到A， 弱联通component只要求单向的就可以
 * 
 * 注意： BFS，只能在无向图中工作， 有向图，BFS doesn't work!!!
 *
 */
public class FindWeakConnectedComponentinDirectedGraph {

	class DirectedGraphNode {
		int label;
		ArrayList<DirectedGraphNode> neighbors;

		DirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<DirectedGraphNode>();
		}
	};

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
		HashSet<Integer> hashSet = new HashSet<Integer>();
		for (DirectedGraphNode now : nodes) {
			hashSet.add(now.label);
			for (DirectedGraphNode neighbour : now.neighbors) {
				hashSet.add(neighbour.label);
			}
		}
		// init UnionFind map
		UnionFind uf = new UnionFind();
		for (Integer now : hashSet) {
			uf.father.put(now, now);
		}
		// iterator each node and its neighbors, each node and it's neighbors
		// are supposed to be in same 集合
		for (DirectedGraphNode now : nodes) {
			int fnow = uf.find(now.label);
			for (DirectedGraphNode neighbour : now.neighbors) {
				int fneighbour = uf.find(neighbour.label);
				if (fnow != fneighbour) {
					uf.union(now.label, neighbour.label);
				}
			}
		}
		return print(hashSet, uf);
	}

	List<List<Integer>> print(HashSet<Integer> hashSet, UnionFind uf) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		HashMap<Integer, List<Integer>> hashMap = new HashMap<Integer, List<Integer>>();
		for (int i : hashSet) {
			int fa = uf.find(i);
			if (!hashMap.containsKey(fa)) {
				hashMap.put(fa, new ArrayList<Integer>());
			}
			List<Integer> now = hashMap.get(fa);
			now.add(i);
			hashMap.put(fa, now);
		}
		for (List<Integer> now : hashMap.values()) {
			Collections.sort(now);
			result.add(now);
		}
		return result;
	}
}
