package binarySearch;

/**
 * 给出一个先上升后下降的整数序列，返回序列中最大的那个数的位置, 相邻不重复
 *
 */
public class FindMaximumInRotatedSortedArray {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 8, 7, 6, 5 };

		System.out.println(findMaxNumerIndex(nums));
	}
	public static int findMaxNumer(int[] A) {
		int start = 0;
		int end = A.length-1;
		
		int target = A[end];
		while (start + 1 < end){
			int mid = start + (end - start) /2;
				if (A[mid] >= target){
					end = mid;
				}else{
					start = mid;
				}
		}
		
		if (A[start] >= target){
			return A[start];
		}
		if (A[end] >= target){
			return A[end]; 
		}
		return 0;
	}

	public static int findMaxNumerIndex(int[] nums) {
		int low = 0;
		int high = nums.length - 1;

		while (low < high) {

			int mid = (low + high) / 2;
			if (nums[mid] > nums[low] && nums[mid] > nums[high]) {
				if ((mid - low) == 1 && (high - mid) == 1) {
					return nums[mid];
				}

				if (nums[mid] > nums[mid - 1]) {
					low = mid;
					high = high - 1;
				} else {
					high = mid - 1;
					low = low + 1;
				}
			}

			if (nums[mid] > nums[low] && nums[mid] < nums[high]) {
				if ((mid - low) == 1 && (high - mid) == 1) {
					return nums[high];
				}
				low = mid;
			}

			if (nums[mid] < nums[low] && nums[mid] > nums[high]) {
				if ((mid - low) == 1 && (high - mid) == 1) {
					return nums[low];
				}
				high = mid - 1;
			}
		}
		return nums[low];
	}

}
