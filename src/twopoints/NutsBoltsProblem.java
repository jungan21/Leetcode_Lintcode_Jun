package twopoints;

/**
 * Given a set of n nuts of different sizes and n bolts of different sizes.
 * There is a one-one mapping between nuts and bolts. Comparison of a nut to
 * another nut or a bolt to another bolt is not allowed. It means nut can only
 * be compared with bolt and bolt can only be compared with nut to see which one
 * is bigger/smaller.
 * 
 * We will give you a compare function to compare nut with bolt.
 * 
 * Example Given nuts = ['ab','bc','dd','gg'], bolts = ['AB','GG', 'DD', 'BC'].
 * 
 * Your code should find the matching bolts and nuts.
 * 
 * one of the possible return:
 * 
 * nuts = ['ab','bc','dd','gg'], bolts = ['AB','BC','DD','GG'].
 * 
 * we will tell you the match compare function. If we give you another compare
 * function.
 * 
 * the possible return is the following:
 * 
 * nuts = ['ab','bc','dd','gg'], bolts = ['BC','AA','DD','GG'].
 * 
 * So you must use the compare function that we give to do the sorting.
 * 
 * The order of the nuts or bolts does not matter. You just need to find the
 * matching bolt for each nut.
 * 
 *
 */

class NBComparator {
	public int cmp(String a, String b) {
		if (a.compareToIgnoreCase(b) > 0) {
			return 1;
		} else if (a.compareToIgnoreCase(b) == 0) {
			return 0;
		} else {
			return -1;
		}
	}
}

public class NutsBoltsProblem {

	public static void main(String[] args) {
		String[] nuts = { "ab", "bc", "dd", "gg" };
		String[] bolts = { "BC", "AB", "GG", "DD" };
		NBComparator compare = new NBComparator();
		// new NutsBoltsProblem().sortNutsAndBolts(nuts, bolts, compare);
		new NutsBoltsProblem().partition(bolts, "DD", compare, 0, 3);
	}

	/**
	 * http://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/73103
	 * 
	 * 
	 */

	/**
	 * 1st, we pick a “nut” from nuts pile.
	 * 
	 * 2nd we divide bolts into two parts according to the “nut”;
	 * 
	 * 3rd we find the matching bolt
	 * 
	 * 4th then we divide our nuts into two parts
	 * 
	 * 5th using recursion to sort our bolts and nuts! Done.
	 * 
	 */
	// 算法的平均复杂度是 O(n Log(n) )，最坏复杂度是O(n^2).
	public void sortNutsAndBolts(String[] nuts, String[] bolts,
			NBComparator compare) {
		if (nuts == null || bolts == null)
			return;
		if (nuts.length != bolts.length)
			return;

		qsort(nuts, bolts, compare, 0, nuts.length - 1);
	}

	private void qsort(String[] nuts, String[] bolts, NBComparator compare,
			int l, int u) {
		// u = nuts.length-1
		if (l >= u)
			return;
		// find the partition index for nuts with bolts[l]
		int part_inx = partition(nuts, bolts[l], compare, l, u);
		// partition bolts with nuts[part_inx]
		partition(bolts, nuts[part_inx], compare, l, u);
		// qsort recursively

		// after above step, [gg, dd, bc, ab] [DD,GG, BC,Ab]
		// bc<->BC, part_index is 2, 这一对已经配好，然后，左右两边继续qsort,配对 左边 (l ~ part_inx01) 右边  (part_inx+1 ~ u) 
		qsort(nuts, bolts, compare, l, part_inx - 1);
		qsort(nuts, bolts, compare, part_inx + 1, u);
		System.out.println("test");

	}

	// 首先使用 nuts 中的某一个元素作为基准对 bolts 进行 partition 操作，随后将 bolts 中得到的基准元素作为基准对 nuts
	/**
	 * 进行 partition 操作。
	 * 
	 */
	private int partition(String[] str, String pivot, NBComparator compare,
			int l, int u) {

		// 先找到pivot的位置，把pivot交换到最左边位置
		for (int i = l; i <= u; i++) {
			if (compare.cmp(str[i], pivot) == 0
					|| compare.cmp(pivot, str[i]) == 0) {
				swap(str, i, l);
				break;
			}
		}
		// 上面for循环后， 最左边已经是pivot的值了，把该值保存下来
		String now = str[l];
		int left = l;
		int right = u;
		// 逆序
		while (left < right) {
			while (left < right
					&& (compare.cmp(str[right], pivot) == -1 || compare.cmp(
							pivot, str[right]) == 1)) {
				right--;
			}
			str[left] = str[right];

			while (left < right
					&& (compare.cmp(str[left], pivot) == 1 || compare.cmp(
							pivot, str[left]) == -1)) {
				left++;
			}
			str[right] = str[left];
		}
		str[left] = now;

		return left;
	}

	private void swap(String[] str, int l, int r) {
		String temp = str[l];
		str[l] = str[r];
		str[r] = temp;
	}

	/**
	 * Wrong method!!!
	 * 
	 * from PartitionArray.java 逆序partition,
	 * partition完后，比pivot大的在左边，比pivot小的在右边,
	 * 注意此方法不行，因为这个方法正能保证返回的0到left位置的是比pivot大的， left+1到末尾的是比pivot小的，
	 * 但是不能保证pivot就在分割的位置，而我们这道题是要求运行完后pivot正好在分割的位置
	 */
	private int partitionWrong(String[] str, String pivot,
			NBComparator compare, int l, int u) {

		int left = l;
		int right = u;
		while (left <= right) {
			while (left <= right
					&& (compare.cmp(str[right], pivot) == -1 || compare.cmp(
							pivot, str[right]) == 1)) {
				right--;
			}

			while (left <= right
					&& (compare.cmp(str[left], pivot) == 1 || compare.cmp(
							pivot, str[left]) == -1)) {
				left++;
			}
			if (left <= right) {
				String temp = str[left];
				str[left] = str[right];
				str[right] = temp;
				left++;
				right--;
			}
		}

		return left;
	}
}
