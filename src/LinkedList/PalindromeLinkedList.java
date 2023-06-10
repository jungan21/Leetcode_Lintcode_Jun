package LinkedList;

//http://www.programcreek.com/2014/05/leetcode-reverse-linked-list-java/
public class PalindromeLinkedList {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(0);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(0);
		ListNode n6 = new ListNode(1);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		System.out.println(isPalindrome(n1));
	}

	/**
	 * The time is O(n) and space is O(1).
	 * 
	 * break list to two parts: first half and second half, then compare first
	 * half with reversed second half
	 * 
	 * 1. use slow, fast point to find the mid, the get the second half
	 * 
	 * 2. 关于数组的长度的奇偶数问题， 我们不需要考虑,偶数问题很好理解，两边长度一样，一个一个比较即可， 对于奇数，不用当心，
	 * 因为下面的判断条件是： while (head != null && reversedSecondHalf != null)，
	 * 即两个都不为空的时候，才比较 对于下面的奇数例子， 最后比较的就是first and reversed second half的前两个数1->2
	 * 
	 * 奇数： 1->2->3->2->1, first half: 1->2->3 reversed second half: 1->2
	 * 
	 * 偶数： 1->2->3->3->2->1, first half: 1->2->3 reversed second half: 1->2->3
	 *
	 */
	public static boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		ListNode slow = head;
		ListNode fast = head;

		/**
		 * while (fast != null && fast.next != null) 结束，slow指向第2个middle (偶数情况下 有2个middle ndoes)
		 * while (fast.next != null && fast.next.next != null) 结束，slow指向第1个middle (偶数情况下 有2个middle ndoes)
		 */
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		// get the second half of the list
		ListNode secondHead = slow.next;
		slow.next = null; // split linked list into 2 from the middle

		// reverse second half list
		ListNode reversedSecondHalf = reverse(secondHead);

		// compare first half WITH reversed second half
		while (head != null && reversedSecondHalf != null) {
			if (head.val != reversedSecondHalf.val) {
				return false;
			}
			head = head.next;
			reversedSecondHalf = reversedSecondHalf.next;
		}
		return true;
	}

	// jiuzhang template
	public static ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode pre = null;
		ListNode cur = head;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}

}
