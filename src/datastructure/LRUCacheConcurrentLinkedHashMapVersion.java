//package datastructure;
//
//public class LRUCacheConcurrentLinkedHashMapVersion extends
//		LRUCacheLinkedHashMapVersion {
//
//	public LRUCacheConcurrentLinkedHashMapVersion(int capacity) {
//		super(capacity);
//	}
//
//		lock(this)
//		try {
//			return super.get(key);
//		} finally {
//			lock.unlock();
//		}
//	}
//
//}
