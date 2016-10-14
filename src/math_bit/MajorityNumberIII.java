package math_bit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array of integers and a number k, the majority number is the number
 * that occurs more than 1/k of the size of the array.
 * 
 * Find it.
 * 
 * Notice
 * 
 * There is only one majority number in the array.
 *
 * Example Given [3,1,2,3,2,3,3,4,4,4] and k=3, return 3.
 * 
 * Challenge O(n) time and O(k) extra space
 */
public class MajorityNumberIII {

	public static void main(String[] args) {
		int[] nums = { 3, 1, 2, 3, 2, 3, 3, 4, 4, 4 };
		ArrayList<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(2);
		list.add(5);
		list.add(1);
		int k = 3;
		System.out.println(majorityNumber2(list, k));
	}

	/**
	 * http://wdxtub.com/interview/14520595473216.html
	 * 
	 * @return
	 */
	public static int majorityNumber(ArrayList<Integer> nums, int k) {
		HashMap<Integer, Integer> counters = new HashMap<Integer, Integer>();
		for (Integer i : nums) {
			if (!counters.containsKey(i)) {
				counters.put(i, 1);
			} else {
				counters.put(i, counters.get(i) + 1);
			}
			/**
			 * 在MajorityNumberII里面为了找大于1/3的， 我们设置两个candidate1，candidate2两个变量,
			 * 找大于1/2的时候设置一个变量，那么现在找大于1/k的，设置k-1个变量，这些变量存入map,设置为map的key
			 * 
			 * 变量，这里我们找大于1/k的，设置k-1个变量
			 * 
			 * 当 size >=k的时候，表示新加入的这个数字 不等于之前k-1 个candidates，写成 ==k也可以
			 * 思路就和之前MajorityNumberII一样，这时候，就要抵消
			 */
			if (counters.size() >= k) {
				removeKey(counters);
			}
		}

		// recalculate counters
		// map里的计数清0， 相当于MajorityNumberII里，map里最后只剩下k-1个key,
		// 然后看这k-1个key，哪个count最大
		for (Integer key : counters.keySet()) {
			counters.put(key, 0);
		}

		int maxCounter = 0, maxKey = 0;
		for (Integer num : nums) {
			if (counters.containsKey(num)) {
				int count = counters.get(num) + 1;
				counters.put(num, count);
				if (count > maxCounter) {
					maxCounter = count;
					maxKey = num;
				}
			}
		}
		return maxKey;
	}

	/**
	 * 这一步骤就类似于：MajorityNumberII里面的 count1--; count2--;
	 * 表示当新加入的数字不等于map里的已经存在的k-1个key的时候，其计数就要减去1，
	 * 当减到0的时候就从map里移除，也就是腾出空间给其他candidate数字重新计数
	 */
	private static void removeKey(HashMap<Integer, Integer> counters) {
		List<Integer> removeList = new ArrayList<>();
		for (Integer key : counters.keySet()) {
			counters.put(key, counters.get(key) - 1);
			if (counters.get(key) == 0) {
				removeList.add(key);
			}
		}
		for (Integer key : removeList) {
			counters.remove(key);
		}
	}

	// method 2 Jun
	public static int majorityNumber2(ArrayList<Integer> nums, int k) {

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (Integer num : nums) {
			if (map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
			} else {
				/**
				 * 要保证map里最多只有k-1个keys(candidate1, candidate2....candidatek-1),
				 * 所有只有map.size() < k-1才往里面直接加入，表示直到把现有的
				 * k-1个candidates抵消到少于k-1个candidates时，才选新的candiate
				 * 
				 * 当size >=k-1的时候，新加入的数字如果不等于任何一个key, 就需要对现有的key的value 逐个减1
				 */
				if (map.size() < k - 1) {
					map.put(num, 1);
				} else {
					// 相当于MajorityNumberII里面的 num !=candidate1 && num !=
					// canadidate2, 并且之前k-1个candidates已经选好了的都情况，这时候count1--,
					// count2--, 其实就是抵消的过程，经过几轮运行抵消后，总会有的count减为0，
					// 这时候，再加入新的candidate
					removeKey(map);
				}
			}
		}

		for (Integer key : map.keySet()) {
			map.put(key, 0);
		}

		int maxCount = 0;
		int maxNum = 0;
		for (Integer num : nums) {
			if (map.containsKey(num)) {
				int count = map.get(num) + 1;
				map.put(num, count);
				if (count > maxCount) {
					maxCount = count;
					maxNum = num;
				}
			}
		}
		return maxNum;
	}
}
