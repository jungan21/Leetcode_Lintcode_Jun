package twopoints;

import java.util.HashMap;
import java.util.Map;

/**
 * Two Sum 3
 * 
 * Design and implement a TwoSum class. It should support the following
 * operations: add and find.
 * 
 * add - Add the number to an internal data structure. find - Find if there
 * exists any pair of numbers which sum is equal to the value.
 * 
 * For example,
 * 
 * add(1); add(3); add(5); find(4) -> true; find(7) -> false
 * 
 *
 */

// http://www.programcreek.com/2014/03/two-sum-iii-data-structure-design-java/
public class TwoSumIII {

	public static void main(String[] args) {

		TwoSumIII twoSum3 = new TwoSumIII();
		twoSum3.add(1);
		twoSum3.add(3);
		twoSum3.add(5);
		System.out.println(twoSum3.find(7));

	}

	private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	public void add(int number) {
		if (map.containsKey(number)) {
			map.put(number, map.get(number) + 1);
		} else {
			map.put(number, 1);
		}
	}

	// please try use O(1) and O(n)
	public boolean find(int value) {
		for (Integer i : map.keySet()) {
			int target = value - i;
			if (target == i && map.get(i) > 1) {
				return true;
			} else if (target != i && map.containsKey(target)) {
				return true;
			}
		}
		return false;
	}

	public boolean find2(int value) {
		for (Integer i : map.keySet()) {
			int target = value - i;
			if (map.containsKey(target)) {
				if (i == target && map.get(target) < 2) {
					continue;
				}
				return true;
			}
		}
		return false;
	}

}
