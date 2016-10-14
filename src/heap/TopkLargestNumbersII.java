package heap;

/**
 * Implement a data structure, provide two interfaces:

 add(number). Add a new number in the data structure.
 topk(). Return the top k largest numbers in this data structure. k is given when we create the data structure
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 这道题只能用 minHeap, 题目要求topK
 * 大的所有的数字，因为这题目要求类似数据流，用户一直操作heap,如果用maxHeap,那么每次poll() 操作活动max值后，max就从heap里丢失了
 * 只有用minHeap,才能通过堆顶元素去忽略掉很小的数字从而不影响整体的结果,,如果用maxHeap,原来的一些大的元素都被poll出去了
 * 如果这题目没有后续的操作，而是一次性给你一堆数，求结果，可以把所有数放入maxheap,然后一个一个poll();,
 * 但是由于这题目是不知道将来什么数字会进来
 */
public class TopkLargestNumbersII {
	private PriorityQueue<Integer> minHeap;
	private int k = 0;

	/**
	 * 内部维护一个大小为k的最小堆
	 * 
	 * 如果size < k则直接将num加入到minHeap
	 * 
	 * 如果size >= k,则比较num与minHeap中的最小值，若小于最小值则忽略，大于最小值则删除最小值，并把num加入到minHeap
	 * 
	 * 每次返回minHeap数组，返回时先反向排序
	 */
	public TopkLargestNumbersII(int k) {
		this.minHeap = new PriorityQueue<Integer>();
		this.k = k;
	}

	public void add(int num) {
		// 如果size < k则直接将num加入到minHeap,这所以,这么写，每次进行一组操作，在最开始，这个k是固定住的
		if (minHeap.size() < k) {
			minHeap.offer(num);
		} else {
			// 如果size >= k,则比较num与minHeap中的最小值，
			// 若<=最小值则忽略，大于最小值则删除最小值，并把num加入到minHeap
			if (num <= this.minHeap.peek()) {
				// ignore this num
			} else {
				minHeap.poll();
				minHeap.offer(num);
			}
		}
	}

	public List<Integer> topk() {
		List<Integer> list = new ArrayList<Integer>();
		for (int a : minHeap) {
			list.add(a);
		}
		// Note: Collections工具类的运用
		Collections.sort(list, Collections.reverseOrder());
		return list;
	}

	public static void main(String[] args) {
		TopkLargestNumbersII driver = new TopkLargestNumbersII(3);
		driver.add(3);
		driver.add(10);
		driver.topk();
		driver.add(1000);
		driver.add(-99);
		driver.topk();
		driver.add(4);
		driver.topk();
		driver.add(100);
		driver.topk();
	}

}
