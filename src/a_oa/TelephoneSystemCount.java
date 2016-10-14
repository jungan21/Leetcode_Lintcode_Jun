package a_oa;

import java.util.HashMap;

/**
 * 电话系统费了一些时间。 电话系统，组pair ID Skill_ID
 * 
 * 1 7
 * 
 * 2 10
 * 
 * 3 69
 * 
 * 1 8
 * 
 * 3 90
 * 
 * 1 70
 *
 * 那么有以下的pairs。 (7,8) (8,70) (69,90) 就是同一个id的连续相邻的两个skill id组合
 * 要统计这一天所有的电话系统pair出现的次数， 输出类似于
 * 
 * pairs count
 * 
 * 7,8 500
 * 
 * 8,70 100
 * 
 * 69,90 300
 * 
 * 我用了两个hashmap， 第一个用来记录最近一次这个id的skillid，比如如果我们在处理第4行之前，我有 Hashmap 1 1 7 2 10 3
 * 69 那么第4行出现Id=1，查map1，就知道我应该组成pair 7,8
 * 
 *
 */
public class TelephoneSystemCount {

	public static void main(String[] args) {
		int[][] input = { { 1, 7 }, { 2, 10 }, { 3, 69 }, { 1, 8 }, { 3, 90 },
				{ 1, 70 } };

		System.out.println(count(input));
	}

	public static HashMap<String, Integer> count(int[][] A) {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		if (A == null || A.length == 0) {
			return result;
		}
		for (int[] a : A) {
			if (!map.containsKey(a[0])) {
				map.put(a[0], a[1]);
			} else {
				int skill_id = map.get(a[0]);
				//由于相邻的，取出来了，就可以覆盖以前的值了
				map.put(a[0], a[1]);
				String pairKey = Integer.valueOf(skill_id) + ","
						+ Integer.valueOf(a[1]);
				if (!result.containsKey(pairKey)) {
					result.put(pairKey, 1);
				} else {
					result.put(pairKey, map.get(pairKey) + 1);
				}
			}
		}
		return result;
	}

}
