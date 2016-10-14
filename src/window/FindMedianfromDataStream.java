package window;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value.
 * 
 * Examples: [2,3,4] , the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Design a data structure that supports the following two operations:
 * 
 * void addNum(int num) - Add a integer number from the data stream to the data
 * structure. double findMedian() - Return the median of all elements so far.
 * For example:
 * 
 * add(1) add(2) findMedian() -> 1.5 add(3) findMedian() -> 2
 * 
 * @author jungan
 *
 */
public class FindMedianfromDataStream {

	public static void main(String[] args) {
		MedianFinder mf = new MedianFinder();
		mf.addNum(2);
		System.out.println(mf.findMedian());
		mf.addNum(3);
		System.out.println(mf.findMedian());
	}

	/**
	 * http://www.programcreek.com/2015/01/leetcode-find-median-from-data-stream
	 * -java/
	 */

}

class MedianFinder {

	PriorityQueue<Integer> leftMaxHeap;// lower half
	PriorityQueue<Integer> rightMinHeap;// higher half
	// count 不能是static的
	int count = 0;
	int median = 0;

	MedianFinder() {
		this.leftMaxHeap = new PriorityQueue<Integer>(1,
				Collections.reverseOrder());
		this.rightMinHeap = new PriorityQueue<Integer>();

	}

	// Adds a number into the data structure.
	public void addNum(int num) {
		if (count == 0) {
			median = num;
			count++;
			return;
		}
		if (num > median) {
			rightMinHeap.add(num);
		} else {
			leftMaxHeap.add(num);
		}

		while (leftMaxHeap.size() > rightMinHeap.size()) {
			rightMinHeap.offer(median);
			median = leftMaxHeap.poll();
		}

		while (leftMaxHeap.size() + 1 < rightMinHeap.size()) {
			leftMaxHeap.offer(median);
			median = rightMinHeap.poll();
		}

		count++;
	}

	// Returns the median of current data stream
	public double findMedian() {
		if (count % 2 == 1) {
			return median;
		} else {
			double result = (median + rightMinHeap.peek()) / 2.0;
			return result;
		}
	}
}

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();
