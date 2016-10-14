package graph_dfs;

import java.util.ArrayList;

public class FindtheMissingNumberII {

	public static void main(String[] args) {
		FindtheMissingNumberII test = new FindtheMissingNumberII();
		System.out.println(test.findMissing2(11, "111098765432"));
	}

	public int findMissing2(int n, String str) {

		ArrayList<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<>();
		DFS(n, str, 0, resultList, list);

		int result = Integer.MAX_VALUE;
		for (ArrayList<Integer> temp : resultList) {
			for (Integer i = 1; i <= n; i++) {
				if (temp.contains(i)) {
					temp.remove(i);
				} else {
					result = Math.min(result, i);
				}
			}
		}
		return result;
	}

	public void DFS(int n, String str, int index,
			ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list) {

		if (index == str.length() && list.size() == n - 1) {
			result.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = index; i < str.length() && i < index + 2; i++) {
			String subStr = str.substring(index, i + 1);
			if (!isValid(subStr, n) || list.contains(Integer.valueOf(subStr))) {
				continue;
			}
			list.add(Integer.valueOf(subStr));
			DFS(n, str, i + 1, result, list);
			list.remove(list.size() - 1);
		}
	}

	public boolean isValid(String str, int n) {
		Integer intVal = Integer.valueOf(str);
		return intVal <= 30 && intVal >= 1 && intVal <= n;
	}
}
