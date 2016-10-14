package twopoints;

/**
 * Given an array nums of integers and an int k, partition the array (i.e move
 * the elements in "nums") such that:
 * 
 * All elements < k are moved to the left All elements >= k are moved to the
 * right Return the partitioning index, i.e the first index i nums[i] >= k.
 * 
 * Notice
 * 
 * You should do really partition in array nums instead of just counting the
 * numbers of integers smaller than k.
 * 
 * If all elements in nums are smaller than k, then return nums.length
 *
 * Example If nums = [3,2,2,1] and k=2, a valid answer is 1.
 */
public class PartitionArray {

	public static void main(String[] args) {
		// int[] nums = { 3, 4, 2, 2, 1, 1 };
		// int[] nums = { 2, 0, 2, 2, 1, 2, 2, 1, 2, 0, 0, 0, 1 };
		int[] nums = { 6, 7, 9, 8, 6, 6, 7 };
		new PartitionArray().partitionArrayJiuzhang(nums, 7);
	}

	// 对比: NutsBoltsProblem.java
	/**
	 * 
	 * Note: method 1与 Jiuzhang template的区别：
	 * 
	 * 1. left的位置就是pivot分割位置， 但是pivot并不一定在此位置，method1 运行完后： [6, 6, 8, 9, 7, 7]，
	 * jiuzhang tempalte 方法，会把7放在实际的位置也就是最后返回的结果left,
	 * 当时这种方法的好处是，即使pivot不是数组里的元素，也可以
	 * 
	 * 2. jiuzhang tempalte 方法，会把7放在实际的位置也就是最后返回的结果left, 但缺点是只能用数组里存在的数为pivot
	 * 
	 * method 1: 其实就是QuickSort的第一步，分割，
	 * http://www.code123.cc/docs/leetcode-notes/integer_array/partition_array
	 * .html
	 * 
	 * 最后返回left即可，当nums为空时, left = 0, right =
	 * -1,所以while不会执行，不必单独特殊考虑，所以应返回left而不是right
	 */
	public int partitionArray(int[] nums, int k) {
		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			/**
			 * Note:不能让 nums[left] <= k，
			 * 否则left就会越过第一个等于K的数，（题目要求找到第一个是k的数的index），如果题目改为最后一个等于K的数，就改为
			 * nums[left] <= k， nums[right] > k， 然后返回left-1
			 */
			while (left <= right && nums[left] < k)
				left++;
			/**
			 * 其实就是QuickSort的第一步,选一个pivot,然后分割，为了使得最后的left point能指向第一个分割点，我们要设置
			 * nums[right] >= k， 这样分割完后， 左边全是小于分割点的数字，右边是大于或等于pivot的数字
			 * （注意，在quick sort里， 我们要求nums[right] > k ，即，quick sort
			 * 第一步完后，做半边是小于或等于pivot的数）
			 */
			while (left <= right && nums[right] >= k)
				right--;
			if (left <= right) {
				int temp = nums[left];
				nums[left] = nums[right];
				nums[right] = temp;
				left++;
				right--;
			}
		}
		/**
		 * 最后返回left即可，当nums为空时, left = 0, right =
		 * -1,所以while不会执行，不必单独特殊考虑，所以应返回left而不是right.
		 * 
		 * 
		 * { 7,9, 8, 6, 6, 7 };运行完的结果是=>{6,6, 8 ,9,7,7} left指想8的位置，right指向6的位置
		 * 注意，这种题目只要求返回最后的partition 位置可以，如要要真正实现partition 也就是要output
		 * {6,6,7,7,9,8}即把pivot放到中间的位置是做不到的，
		 * 但是，这个算法的好处是，pivot可以是本数组元素，也可以是不在此数字里的数字
		 */
		// return right +1 也可以
		// 因为 left 控制 <k 的， 出了循环，由于 left++, left 会指向第一个 >=k的位置
		return left;
	}

	/**
	 * !!! 提交不能通过，因为test case,里有用数组里不存在数字作为pivot
	 * 
	 * jiuzhang tempalte 方法，会把7放在实际的位置也就是最后返回的结果left, 但缺点是只能用数组里存在的数为pivot
	 */

	public int partitionArrayJiuzhang(int[] nums, int k) {
		int left = 0;
		int right = nums.length - 1;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == k) {
				int temp = nums[left];
				nums[left] = nums[i];
				nums[i] = temp;
				break;
			}
		}

		int pivot = nums[left];
		while (left < right) {
			// 必须是>=， 因为对于等于的数, 不需要交换，提高效率，否则的话对于 [5, 5,3
			// 5,5]重复元素很多的数字就会退化为O(n^2)复杂度
			while (left < right && nums[right] >= pivot) {
				right--;
			}
			nums[left] = nums[right];

			while (left < right && nums[left] <= pivot) {
				left++;
			}
			nums[right] = nums[left];

		}
		nums[left] = pivot;
		return left;
	}

	/**
	 * method 2 : 用三个指针，类似于SortColors.java 原理, 这题目不需要用这种复杂的方法，sort
	 * corlor题目之所以要用三个指针，因为那题不是简单的要求分割，还要求要求将相同的颜色（数值）要都相邻，
	 * 
	 * 如果题目输入： int[] nums = { 2, 0, 2, 2, 1, 2, 2, 1, 2, 0, 0, 0, 1 };
	 * 
	 * 用这种3个指针方法，我们会得到： [0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2]，
	 * 而用上面两个指针的方法，我们能得到[0, 0, 0, 0, 1, 2, 2, 1, 2, 2, 2, 2, 1]
	 */
	public int partitionArray3(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int pl = 0;
		int pr = nums.length - 1;
		int i = 0;

		while (i <= pr) {
			if (nums[i] > k) {
				swap(nums, i, pr);
				pr--;
			} else if (nums[i] == k) {
				i++;
			} else if (nums[i] < k) {
				swap(nums, i, pl);
				pl++;
				i++;
			}
		}
		return pl;
	}

	public void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

}
