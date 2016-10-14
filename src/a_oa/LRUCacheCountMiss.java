package a_oa;

import java.util.LinkedList;
import java.util.Queue;

public class LRUCacheCountMiss {

	public static void main(String[] args) {

	}

	public int countCacheMiss(int[] array, int size) {
		if (array == null || array.length == 0) {
			return 0;
		}
		Queue<Integer> cache = new LinkedList<Integer>();
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (cache.contains(array[i])) {
				// must be using Integer, when removing/finding any int in List
				cache.remove(new Integer(array[i]));
			} else {
				count++;
				if (size == cache.size())
					// assume 头目
					cache.remove(0);
			}
			// assume Queue's tail is the actual head of the cache list
			cache.add(array[i]);
		}
		return count;
	}
}
