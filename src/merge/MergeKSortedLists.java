package merge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list.
 * 
 * Analyze and describe its complexity.
 *
 */
public class MergeKSortedLists {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(2);
		ListNode n11 = new ListNode(4);
		n1.next = n11;
		ListNode n2 = new ListNode(1);
		ListNode n22 = new ListNode(3);
		n2.next = n22;
		List<ListNode> lists = new ArrayList<ListNode>();
		lists.add(n1);
		lists.add(n2);

		mergeKLists(lists);

	}

	/**
	 * jiuzhang: http://www.jiuzhang.com/solutions/merge-k-sorted-lists/
	 * 
	 * Divide and Conquer: 由于用到了二分，所以时间复杂度是 NlogK，空间是logk
	 * 
	 * 假设K个链表，一共有N个node ==> NlogK
	 * 
	 * priority queue: 时间复杂度是 nlogk，空间是k (k就是也就是k个list)
	 * 
	 */
	public static ListNode mergeKLists(List<ListNode> lists) {
		if (lists.size() == 0) {
			return null;
		}

		// 0 is index of first ListNode in lists
		return mergeHelper(lists, 0, lists.size() - 1);
	}

	private static ListNode mergeHelper(List<ListNode> lists, int start, int end) {
		// Note: 这一步不能省
		if (start == end) {
			return lists.get(start);
		}

		int mid = start + (end - start) / 2;

		// divide - recursive call
		ListNode left = mergeHelper(lists, start, mid);
		ListNode right = mergeHelper(lists, mid + 1, end);

		// conquer
		return merge2List(left, right);
	}

	/**
	 * jiuzhang: http://www.jiuzhang.com/solutions/merge-k-sorted-lists/
	 * 
	 * Heap
	 * 
	 * 假设K个链表，一共有N个node ==> NlogK
	 * 
	 */

	public static ListNode mergeKListsHeap(List<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}

		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;

		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(
				lists.size(), new Comparator<ListNode>() {
					public int compare(ListNode a, ListNode b) {
						if (b == null) {
							return 1;
						} else if (a == null) {
							return -1;
						}
						return a.val - b.val;
					}
				});

		for (ListNode node : lists) {
			// ignore null ListNode
			if (node != null) {
				minHeap.offer(node);
			}
		}

		while (!minHeap.isEmpty()) {
			ListNode node = minHeap.poll();
			cur.next = node;
			cur = node;
			// ignore null ListNode
			if (node.next != null) {
				minHeap.offer(node.next);
			}
		}
		return dummy.next;
	}

	/**
	 * 
	 * * jiuzhang merge 2 by 2 (real one)
	 * http://www.jiuzhang.com/solutions/merge-k-sorted-lists/
	 * 
	 * 假设K个链表，一共有N个node ==> NlogK
	 * 
	 * 算法： 1，2，3，4， 5， 6，。。。 让， 1和2合并(结果temp1)，3和4合并(结果temp2),
	 * 然后temp1和temp2合并，画出来就像树形结构，高度最多为logk (k为链表个数), 而每个node最多参与logk次合并
	 * 
	 */

	public ListNode mergeKLists2By2(List<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}

		while (lists.size() > 1) {
			// extra space
			List<ListNode> new_lists = new ArrayList<ListNode>();
			// i = i +2;
			for (int i = 0; i < lists.size() - 1; i += 2) {
				ListNode merged_list = merge2List(lists.get(i),
						lists.get(i + 1));
				new_lists.add(merged_list);
			}
			// 如果有奇数个链表，剩下的一个一定是原来List里的最后一个，那么这样lists.size()>1,继续while
			// loop,把最后这两个合并（之前合并好的，加上最后这一个）
			if (lists.size() % 2 == 1) {
				new_lists.add(lists.get(lists.size() - 1));
			}
			lists = new_lists;
		}

		return lists.get(0);
	}

	/**
	 * Juns' 不推荐 效率最低
	 * 
	 * O(nk) 假设K个链表，一共有N个node, 每个node要参与K-1次合并过程
	 * 
	 * 假设第一个链表有很多Node, 其余都是1个node, 那么这种算法就会有
	 * O(N(k-1))=O(Nk),因为机会每个node都会参与者K-1次合并
	 * 
	 */
	public static ListNode mergeKListsJun(List<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}
		if (lists.size() == 1) {
			return lists.get(0);
		}
		ListNode mergedList = null;
		while (lists.size() > 1) {
			mergedList = merge2List(lists.remove(0), lists.remove(0));
			lists.add(0, mergedList);
		}
		return mergedList;
	}

	public static ListNode merge2List(ListNode head1, ListNode head2) {
		if (head1 == null && head2 == null) {
			return null;
		} else if (head1 == null) {
			return head2;
		} else if (head2 == null) {
			return head1;
		}

		ListNode newDummyHead = new ListNode(0);
		ListNode cur = newDummyHead;
		while (head1 != null && head2 != null) {
			if (head1.val < head2.val) {
				cur.next = head1;
				head1 = head1.next;
			} else {
				cur.next = head2;
				head2 = head2.next;
			}
			cur = cur.next;
		}

		if (head1 != null) {
			cur.next = head1;
		}
		if (head2 != null) {
			cur.next = head2;
		}
		return newDummyHead.next;
	}

}
