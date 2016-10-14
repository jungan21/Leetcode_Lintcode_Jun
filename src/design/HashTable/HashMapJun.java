package design.HashTable;

import java.util.ArrayList;

public class HashMapJun<K, V> {

	private static class LinkedListNode<K, V> {
		public K key;
		public V value;
		public LinkedListNode<K, V> next;
		public LinkedListNode<K, V> prev;

		public LinkedListNode(K k, V v) {
			this.key = k;
			this.value = v;
		}
	}

	private ArrayList<LinkedListNode<K, V>> array = null;

	public V put(K key, V value) {
		LinkedListNode<K, V> node = getNodeByKey(key);

		if (node != null) {
			V oldValue = node.value;
			node.value = value;
			return oldValue;
		}
		node = new LinkedListNode<K, V>(key, value);
		int hashCode = hash(key);
		if (array.get(hashCode) != null) {
			node.next = array.get(hashCode);
			node.next.prev = node;
		}
		array.set(hashCode, node);
		return null;
	}

	public V get(K key) {
		if (key == null)
			return null;
		// get node first, then return its values
		LinkedListNode<K, V> node = getNodeByKey(key);
		return node == null ? null : node.value;
	}

	private LinkedListNode<K, V> getNodeByKey(K key) {
		int hashCode = hash(key);
		LinkedListNode<K, V> current = array.get(hashCode);
		while (current != null) {
			if (current.key == key) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	public int hash(K key) {
		// 绝对值
		return (Math.abs(key.hashCode() % array.size()));
	}

}
