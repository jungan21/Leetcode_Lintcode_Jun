package datastructure;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * Reference: http://jm.taobao.org/2011/01/18/689/
 * 
 * http://www.jiuzhang.com/solutions/lru-cache/
 *
 * http://www.programcreek.com/2013/03/leetcode-lru-cache-java/ nice picture
 * 
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1. set(key, value) - Set or insert the
 * value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new
 * item.
 */

// double linked nodes.

/**
 * The LRU cache is a hash table of keys and double linked nodes. The hash table
 * makes the time of get() to be O(1). The list of double linked nodes make the
 * nodes adding/removal operations O(1).
 * 
 *
 */

/**
 * 假设capacity为3， tail 和 head 为 dummy nodes, 只是为了操作方便，
 * 
 * head->1->3->5->tail，
 * 
 * 假设现在get(3) ==> head->1->5->3->tail， 如果是 set(3,value)==> head->1->5->3->tail
 * 
 * 1. 如果需要移除老的节点，我们从头节点移除。
 * 
 * 2. 如果某个节点被访问（SET/GET），将其移除并挂在双向链表的结尾。
 * 
 * 3. 链表满了后，我们删除头节点。
 * 
 * 4. 最近访问的节点在链尾。最久被访问的节点在链头。
 * 
 *
 */

public class LRUCacheWithLock {
	private final ReadWriteLock readWriteLock;
	private int capacity;
	// map的value是Node
	private HashMap<Integer, Node> map = new HashMap<Integer, Node>();
	// 必须要先new出来一个dummy的值，否则后面要做很多 null 判断
	private Node head = new Node(-1, -1);
	private Node tail = new Node(-1, -1);

	public LRUCacheWithLock(int capacity) {
		this.capacity = capacity;
		tail.prev = head;
		head.next = tail;
		readWriteLock = new ReentrantReadWriteLock(true);
	}

	public int get(int key) {
		readWriteLock.writeLock().lock();
		if (!map.containsKey(key)) {
			return -1;
		}

		// remove current
		Node current = map.get(key);
		current.prev.next = current.next;
		current.next.prev = current.prev;

		// move current to tail
		move_to_tail(current);
		// 也可以直接return current.value
		readWriteLock.writeLock().unlock();
		return current.value;
	}

	public void set(int key, int value) {
		// 注意这里不能写成 map.get(key).value != -1, 也不能用map.containsKey(key)，必须
		// 调用get(key) != -1 因为 上面get()方法里必须要把set的这个node move_to_tail. 因为
		// set操作也算最近被使用了
		if (get(key) != -1) {
			map.get(key).value = value;
			return;
		}
		readWriteLock.writeLock().lock();
		if (map.size() == capacity) {
			// !!! 如果Node节点里没有key属性，map是不会知道least recent的key是哪一个的，所有Node needs
			// key
			// property
			map.remove(head.next.key);
			head.next = head.next.next;
			head.next.prev = head;
		}

		Node insert = new Node(key, value);
		map.put(key, insert);
		move_to_tail(insert);
		readWriteLock.writeLock().unlock();
	}

	private void move_to_tail(Node current) {
		current.prev = tail.prev;
		tail.prev = current;
		current.prev.next = current;
		current.next = tail;
	}
}
