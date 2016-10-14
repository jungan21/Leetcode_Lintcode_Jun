package duplicate;

/**
 * Given an array and a value, remove all occurrences of that value in place and
 * return the new length.
 * 
 * The order of elements can be changed, and the elements after the new length
 * don't matter.
 * 
 * Example Given an array [0,4,4,0,0,2,4,4], value=4
 * 
 * return 4 and front four elements of the array is [0,0,0,2]
 *
 */
public class RemoveElement {

	public static void main(String[] args) {
		int[] A = { 0, 4, 4, 0, 4, 4, 4, 0, 2 };
		int elem = 4;
		removeElementJun(A, 4);
	}

	public int removeElement(int[] A, int elem) {
		int i = 0;
		int pointer = A.length - 1;
		while (i <= pointer) {
			if (A[i] == elem) {
				A[i] = A[pointer];
				pointer--;
			} else {
				i++;
			}
		}
		return pointer + 1;
	}

	public static int removeElementJun(int[] A, int elem) {
		if (A == null || A.length == 0) {
			return 0;
		}

		int i = 0;
		int j = A.length - 1;
		/**
		 *  Note: 必须是 i<=j, 如果是i < j, 对于 [0,4,4,0,4,4,4,0,2], 4 就不对
		 *  
		 *  j--, 只有i<=j条件，才能保证，j一直往左move到第一个不等于elem的元素
		 */
		while (i <= j) {
			while (i <= j && A[i] != elem) {
				i++;
			}
			while (i <= j && A[j] == elem) {
				j--;
			}
			if (i <= j) {
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;
				i++;
				j--;
			}
		}
		return j + 1;
	}

}
