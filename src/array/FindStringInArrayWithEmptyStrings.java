package array;

/**
 * cc150 9.5
 * 
 * Given a sorted array of strings which is interspersed with empty strings,
 * write a meth- od to nd the location of a given string. Example: nd “ball” in
 * [“at”, “”, “”, “”, “ball”, “”, “”, “car”, “”, “”, “dad”, “”, “”] will return
 * 4 Example: nd “ballcar” in [“at”, “”, “”, “”, “”, “ball”, “car”, “”, “”,
 * “dad”, “”, “”] will return -1
 * 
 * 
 * Example: nd “ball” in [“at”, “”, “”, “”, “ball”, “”, “”, “car”, “”, “”,
 * “dad”, “”, “”] will return 4
 * 
 * Example: nd “ballcar” in [“at”, “”, “”, “”, “”, “ball”, “car”, “”, “”, “dad”,
 * “”, “”] will return -1
 *
 */
public class FindStringInArrayWithEmptyStrings {

	public static void main(String[] args) {
		String[] strs = { "at", "", "", "", "ball", "", "", "car", "", "",
				"dad", "", "" };
		System.out.println(find(strs, "dad"));
	}

	public static int find(String[] strs, String target) {

		if (target == null || strs == null) {
			return -1;
		}

		if (target.equals("")) {
			for (int i = 0; i < strs.length; i++) {
				if (strs[i].endsWith("")) {
					return i;
				}
			}
			return -1;
		}
		return helper1(strs, target, 0, strs.length - 1);
	}

	// binary search
	public static int helper1(String[] strs, String target, int start, int end) {

		while (start + 1 < end) {
			while (start + 1 < end && strs[start].equals("")) {
				start++;
			}

			while (start + 1 < end && strs[end].equals("")) {
				end--;
			}
			if (strs[end].equals("") && strs[start].equals("")) {
				return -1;
			}

			int mid = start + (end - start) / 2;
			if (strs[mid] == "") {
				int j = mid;
				while (strs[j] == "" && j < strs.length) {
					j++;
				}

				if (j == strs.length) {
					j = mid;
					while (strs[j] == "" && j >= 0) {
						j--;
					}
					if(j <0){
						return -1;
					}else {
						mid = j;
					}
					
				} else {
					mid = j;
				}
			}

			int r = strs[mid].compareTo(target);
			if (r > 0) {
				end = mid;
			} else if (r < 0) {
				start = mid;
			} else {
				return mid;
			}

		}

		return -1;
	}

	// binary search
	public static int helper(String[] strs, String target, int start, int end) {

		while (start + 1 < end) {
			while (start + 1 < end && strs[end].equals("")) {
				end--;
			}
			if (strs[end].equals("") && strs[start].equals("")) {
				return -1;
			}

			int mid = start + (end - start) / 2;
			/**
			 * 由于我们上面处理的时候从end--,开始处理，后面保证有一个数字不为"", 如果mid--, 有可能start, "", "",
			 * end，这样就会死循环
			 * 
			 * 只能往后走mid++,
			 */
			while (strs[mid].equals("")) {
				mid++;
			}
			int r = strs[mid].compareTo(target);
			if (r > 0) {
				end = mid;
			} else if (r < 0) {
				start = mid;
			} else {
				return mid;
			}
		}

		return -1;
	}
}
