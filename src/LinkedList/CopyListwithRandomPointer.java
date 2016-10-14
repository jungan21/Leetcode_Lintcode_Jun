package LinkedList;

import java.util.HashMap;

/**
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 * 
 */

class RandomListNode {
	int label;
	RandomListNode next, random;

	RandomListNode(int x) {
		this.label = x;
	}
}

public class CopyListwithRandomPointer {

	/**
	 * method 1: time: O(n) space: O(n); jiuzhang:
	 * http://www.jiuzhang.com/solutions/copy-list-with-random-pointer/
	 * 
	 * @return
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null) {
			return null;
		}

		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode dummy = new RandomListNode(0);
		RandomListNode pre = dummy;
		RandomListNode newNode = null;
		while (head != null) {
			// map.containsKey method
			if (map.containsKey(head)) {
				newNode = map.get(head);
			} else {
				newNode = new RandomListNode(head.label);
				map.put(head, newNode);
			}
			pre.next = newNode;

			if (head.random != null) {
				if (map.containsKey(head.random)) {
					newNode.random = map.get(head.random);
				} else {
					newNode.random = new RandomListNode(head.random.label);
					map.put(head.random, newNode.random);
				}
			}

			pre = newNode;
			head = head.next;
		}

		return dummy.next;
	}

	/**
	 * method 2: time: O(n) space: O(1); jiuzhang:
	 * http://www.jiuzhang.com/solutions/copy-list-with-random-pointer/
	 * 
	 * 最好的解释： http://www.cnblogs.com/TenosDoIt/p/3387000.html
	 * 
	 * 第一遍扫的时候巧妙运用next指针， 开始数组是1->2->3->4 。 然后扫描过程中 先建立copy节点
	 * 1->1`->2->2`->3->3`->4->4`, 然后第二遍copy的时候去建立边的copy， 拆分节点,
	 * 一边扫描一边拆成两个链表，这里用到两个dummy node。第一个链表变回 1->2->3 , 然后第二变成 1`->2`->3`
	 */
	// entry point
	public RandomListNode copyRandomList1(RandomListNode head) {
		if (head == null) {
			return null;
		}
		/**
		 * Note: copyNext 和 copyRandom 都不用返回任何值
		 */
		// 1->2->3 变成 1->1`->2->2`->3->3`
		copyNext(head);
		// 1->1`->2->2`->3->3`, 即，将1的random copy给1`的random
		copyRandom(head);
		// split to two list, 返回，newly copied one
		return splitList(head);
	}

	/**
	 * copy every node, i.e., duplicate every node, and insert it to the list
	 * 
	 * 1->2->3 变成 1->1`->2->2`->3->3`
	 * 
	 * Best explanation nice picture to illustrate:
	 * http://www.cnblogs.com/TenosDoIt/p/3387000.html
	 */
	private void copyNext(RandomListNode head) {
		while (head != null) {
			RandomListNode newNode = new RandomListNode(head.label);
			/**
			 * 注意这里虽然对新节点的random赋值了，但是其值是不对的, 假设1.random=3,
			 * 下面的random赋值仅仅表示1'.random=3, 而实际上我们要求1'.random =3' 所以
			 * 需要copyRandom方法进一步处理
			 * 
			 * 1->1'->2->2'->3->3'
			 * 
			 * 其实省略掉下面的newNode.random = head.random; 也可以， 只不过要将copyRandom
			 * 里的if条件语句稍微改一下
			 * 
			 */
			// newNode.random = head.random;
			newNode.next = head.next;
			// insert newly copied node to original list
			head.next = newNode;
			head = head.next.next;
		}
	}

	// copy random pointers for all newly created nodes
	private void copyRandom(RandomListNode head) {
		while (head != null) {
			if (head.random != null) {
				// 构建新节点random指针：new1->random = old1->random->next
				// head.next 就是1' ，假设1原来指向2，现在我们要1'指向 2', ( 2' 也就是1.random.next)
				head.next.random = head.random.next;
			}
			head = head.next.next;
		}
	}

	/**
	 * break the list to two
	 * 
	 * 恢复原始链表以及构建新链表：例如old1->next = old1->next->next, new1->next =
	 * new1->next->next
	 */
	private RandomListNode splitList(RandomListNode head) {
		RandomListNode newHead = head.next;
		while (head != null) {
			RandomListNode temp = head.next;
			head.next = temp.next;
			head = head.next;
			if (temp.next != null) {
				temp.next = temp.next.next;
			}
		}
		return newHead;
	}

	/**
	 * 上面splitList方法简化版 Jun
	 */
	private RandomListNode splitList2(RandomListNode head) {
		RandomListNode newHead = head.next;
		RandomListNode cur = newHead;
		while (cur.next != null) {
			cur.next = cur.next.next;
			cur = cur.next;
		}
		return newHead;
	}

}
