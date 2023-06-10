package company.walmart;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * http://wdxtub.com/interview/14520604446223.html
 *
 * LinkedHashMap 效果和HashMap和 LinkedList一样
 * 
 * http://wiki.jikexueyuan.com/project/java-collection/linkedhashmap.html
 */
public class LRUCacheLinkedHashMapVersion {

	public static void main(String[] args) {
		LRUCacheLinkedHashMapVersion test = new LRUCacheLinkedHashMapVersion(2);
		test.set(2, 1);
		test.set(1, 1);
		test.get(2);
		test.set(4, 1);
		test.get(1);
		test.get(2);

	}

	private Map<Integer, Integer> map;
	private int capacity;

	public LRUCacheLinkedHashMapVersion(int capacity) {
		this.capacity = capacity;
		// map = new LinkedHashMap<Integer, Integer>(capacity + 1);

		map = new ConcurrentHashMap<Integer, Integer>(capacity + 1);
	}

	public int get(int key) {
		Integer val = map.get(key);
		if (val == null)
			return -1;
		map.remove(key);
		map.put(key, val);
		return val;
	}

	/**
	 * Jun's map左端是Old数据，右边是新数据, linkedHashMap里，每加入一个，数据就被放在右边
	 * set(2,1),set(1,1), ==> [2=1, 1=1]
	 * 
	 * 由于我们自己实现的get,先把get的数据remove,再加入一份， get(2) ==> [1=1, 2=1]
	 * 
	 * set(4,1)时，由于map.size=capacity, 所有先remove掉[1=1]entry, 然后再加入[4=1]
	 */
	public void set(int key, int value) {
		if (map.containsKey(key)) {
			map.remove(key);
			map.put(key, value);
		} else {
			if (map.size() < capacity) {
				map.put(key, value);
			} else {
				// keySet().iterator().next(),返回的其实就是keySet的里的第一条数据，也就是最老的数据
				map.remove(map.keySet().iterator().next());
				map.put(key, value);
			}
		}
	}

	public void set1(int key, int value) {
		map.remove(key);
		map.put(key, value);
		if (map.size() > capacity)
			map.remove(map.entrySet().iterator().next().getKey());
	}

}
