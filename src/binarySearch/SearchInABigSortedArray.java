package binarySearch;

/**
 * Frequent Question Find the first index of a target number
 * http://www.jiuzhang.com/solutions/search-in-a-big-sorted-array/
 */
// Definition of ArrayReader:
class ArrayReader { // get the number at index, return -1 if index is less

	public int get(int index) {

		return 0;
	}
}

public class SearchInABigSortedArray {

	public static void main(String[] args) {

	}

	/**
	 * @param reader
	 *            : An instance of ArrayReader can read number by index.
	 * @param target
	 *            : An integer
	 * @return : An integer which is the index of the target number
	 */
	public int searchBigSortedArray(ArrayReader reader, int target) {
		// Algorithm:
		// 1. get the index that ArrayReader.get(index) >= target in
		// O(logk)
		// 2. Binary search the target between 0 and index

		// index=1, means the first number element in the array
		// 注意 如果设置为0， 则index = index * 2; 一直为0，死循环
		int index = 1;
		/**
		 * (index-1) is actually the index of the number, e.g. (1-1=0), 0 is the
		 * index of the first number
		 * 
		 * use while loop to find out the first number that >= target. we use
		 * index*2 for saving time
		 */
		while (reader.get(index - 1) < target) {
			index = index * 2;
		}

		// we can use index-1 as the last position/index to do binary search to
		// do first index search
		int start = 0, end = index - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (reader.get(mid) == target) {
				end = mid;
			} else if (reader.get(mid) > target) {
				end = mid;
			} else {
				start = mid;
			}
		}

		if (reader.get(start) == target) {
			return start;
		}

		if (reader.get(end) == target) {
			return end;
		}

		return -1;
	}

}
