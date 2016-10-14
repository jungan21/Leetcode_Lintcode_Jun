package LinkedList;

public class Rehashing {

	public static void main(String[] args) {
		ListNode[] hashTable = new ListNode[3];
		hashTable[0] = null;
		hashTable[1] = null;
		ListNode n1 = new ListNode(29);
		ListNode n2 = new ListNode(5);
		n1.next = n2;
		hashTable[2] = n1;
		rehashing(hashTable);

	}

	public static ListNode[] rehashing(ListNode[] hashTable) {
		if (hashTable == null || hashTable.length == 0) {
			return null;
		}

		int newLen = hashTable.length * 2;
		ListNode[] result = new ListNode[newLen];
		int code = 0;
		for (ListNode node : hashTable) {
			ListNode next = null;
			while (node != null) {
				code = node.val < 0 ? ((node.val % newLen) + newLen) % newLen
						: node.val % newLen;
				// 注意， 重要，如果不切断后面的值，待会，下面result[code] = node;，
				// 就会把node以及后面的一长串都赋值带过去了, 如果不想处理这样的情况，可以每次赋值的时候直接new一个新的node,
				// 像下面的方法一样，简答 方便
				next = node.next;
				node.next = null;

				if (result[code] == null) {
					result[code] = node;
				} else {
					ListNode cur = result[code];
					while (cur.next != null) {
						cur = cur.next;
					}
					cur.next = node;
				}
				node = next;
			}
		}
		return result;
	}

	// method 2: jiuzhang
	public ListNode[] rehashing2(ListNode[] hashTable) {
		if (hashTable == null || hashTable.length == 0) {
			return null;
		}

		int newLen = hashTable.length * 2;
		ListNode[] result = new ListNode[newLen];
		int code = 0;
		for (ListNode node : hashTable) {
			while (node != null) {
				code = node.val < 0 ? ((node.val % newLen) + newLen) % newLen
						: node.val % newLen;

				if (result[code] == null) {
					result[code] = new ListNode(node.val);
				} else {
					ListNode cur = result[code];
					while (cur.next != null) {
						cur = cur.next;
					}
					cur.next = new ListNode(node.val);
				}
				node = node.next;
			}
		}
		return result;
	}
}
