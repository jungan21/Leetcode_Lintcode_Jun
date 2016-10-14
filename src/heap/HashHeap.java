package heap;

import java.util.ArrayList;
import java.util.HashMap;

class Node {
	public Integer id;
	public Integer num;

	Node(Node now) {
		id = now.id;
		num = now.num;
	}

	Node(Integer first, Integer second) {

		this.id = first;
		this.num = second;
	}
}

/**
 * 
 * HashHeap 之所以能把PriorityQueue,的remove(object)操作时间从O(n)
 * 降低到O(logn)是因为，对于PriorityQueue,要删除一个元素，先要遍历所有节点找到待删除的点，这一步耗费O(n),而HashHeap,
 * 里面维护了一个HashMap, 里面存着每个Heap里每个点对应的位置，要删除的时候，找到待删除的元素的时间就是O(1)
 * 
 * HashHeap has the property of PriorityQueue. Can be min/max heapList: defined
 * by mode property
 * 
 * Node class for duplicate values
 * 
 * Delete value in O(logn) (default priority queue has O(n) time)
 * 
 * 
 * http://www.importnew.com/20169.html
 * 
 */

public class HashHeap {
	ArrayList<Integer> heapList;
	String mode;
	int size_t;
	HashMap<Integer, Node> hashMap;

	public HashHeap(String mod) {
		// TODO Auto-generated constructor stub
		heapList = new ArrayList<Integer>();
		mode = mod;
		hashMap = new HashMap<Integer, Node>();
		size_t = 0;
	}

	int peak() {
		return heapList.get(0);
	}

	int size() {
		return size_t;
	}

	Boolean empty() {
		return (heapList.size() == 0);
	}

	int parent(int id) {
		if (id == 0) {
			return -1;
		}
		// parent's index
		return (id - 1) / 2;
	}

	int lson(int id) {
		return id * 2 + 1;
	}

	int rson(int id) {
		return id * 2 + 2;
	}

	boolean comparesmall(int a, int b) {
		if (mode == "min") {
			if (a <= b) {
				return true;
			} else {
				return false;
			}
		} else {
			if (a <= b) {
				return false;
			} else {
				return true;
			}
		}
	}

	void swap(int idA, int idB) {
		int valA = heapList.get(idA);
		int valB = heapList.get(idB);

		int numA = hashMap.get(valA).num;
		int numB = hashMap.get(valB).num;
		hashMap.put(valB, new Node(idA, numB));
		hashMap.put(valA, new Node(idB, numA));
		heapList.set(idA, valB);
		heapList.set(idB, valA);
	}

	Integer poll() {
		size_t--;
		Integer now = heapList.get(0);
		Node hashnow = hashMap.get(now);
		if (hashnow.num == 1) {
			swap(0, heapList.size() - 1);
			hashMap.remove(now);
			heapList.remove(heapList.size() - 1);
			if (heapList.size() > 0) {
				// 删除了顶端元素， 并把最后一个元素swap到顶端，这时候要siftdown新的顶端元素
				siftdown(0);
			}
		} else {
			hashMap.put(now, new Node(0, hashnow.num - 1));
		}
		return now;
	}

	// 相当于priorityqueue的offer()
	void add(int now) {
		size_t++;
		if (hashMap.containsKey(now)) {
			Node hashnow = hashMap.get(now);
			hashMap.put(now, new Node(hashnow.id, hashnow.num + 1));

		} else {
			heapList.add(now);
			hashMap.put(now, new Node(heapList.size() - 1, 1));
		}
		siftup(heapList.size() - 1);
	}

	// 删除指定元素
	/**
	 * remove(Object o)可以分为2种情况：1. 删除的是最后一个元素。直接删除即可，不需要调整。2.
	 * 删除的不是最后一个元素，从删除点开始以最后一个元素为参照调用一次siftDown()
	 * 
	 * http://blog.csdn.net/cdnight/article/details/11650983
	 * 
	 * 操作原理是：当删除节点的数值时，原来的位置就会出现一个孔 填充这个孔的方法就是，把最后的叶子的值赋给该孔，最后把该叶子删除
	 * 
	 * 
	 * 这个delete时间复杂度为O(logn): O(1)的时间找到元素，O(logn)时间调整heap(即：sitfup/sitfdown)
	 * 
	 * 如果是Java自带的PriorityQueue, O(n)时间找找到元素，
	 * O(logn)时间调整heap(即：sitfup/sitfdown),所以是O(n+logn)大约等于O(n)
	 */
	void delete(int now) {
		size_t--;
		;
		Node hashnow = hashMap.get(now);
		int id = hashnow.id;
		int num = hashnow.num;
		if (hashnow.num == 1) {

			swap(id, heapList.size() - 1);
			hashMap.remove(now);
			heapList.remove(heapList.size() - 1);
			if (id < heapList.size()) {
				/**
				 * poll 操作是把最后一个叶子节点换到堆顶，变成新的堆顶，所有只要siftdown即可
				 * 
				 * add 操作是把新的节点变成最后一个叶子节点，所以只要siftup即可，
				 * 
				 * 但是，这里的delete操作，可能是中间的任何一个元素，所以即需要siftup,又要siftdown
				 */
				siftup(id);
				siftdown(id);
			}
		} else {
			hashMap.put(now, new Node(id, num - 1));
		}
	}

	void siftup(int id) {
		// root 的id是0， root节点的parent是-1
		while (parent(id) > -1) {
			int parentId = parent(id);
			if (comparesmall(heapList.get(parentId), heapList.get(id)) == true) {
				break;
			} else {
				swap(id, parentId);
			}
			id = parentId;
		}
	}

	void siftdown(int id) {
		while (lson(id) < heapList.size()) {
			int leftId = lson(id);
			int rightId = rson(id);
			int son;
			// first check which child is smaller,
			if (rightId >= heapList.size()
					|| (comparesmall(heapList.get(leftId),
							heapList.get(rightId)) == true)) {
				son = leftId;
			} else {
				son = rightId;
			}
			// then compare this id with the smaller child
			if (comparesmall(heapList.get(id), heapList.get(son)) == true) {
				break;
			} else {
				swap(id, son);
			}
			id = son;
		}
	}
}
