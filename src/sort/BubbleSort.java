package sort;

//http://www.java2novice.com/java-sorting-algorithms/
// http://bigocheatsheet.com/

// Best O(n), Average O(n^2), Worst O(n^2), Space: O (1)
// https://www.cs.cmu.edu/~adamchik/15-121/lectures/Sorting%20Algorithms/sorting.html
public class BubbleSort {

	/*
	 * In bubble sort, we basically traverse the array from first to
	 * array_length - 1 position and compare the element with the next one.
	 * Element is swapped with the next element if the next element is greater.
	 * 
	 * Bubble sort steps are as follows.
	 * 
	 * 1. Compare array[0] & array[1] 2. If array[0] > array [1] swap it. 3.
	 * Compare array[1] & array[2] 4. If array[1] > array[2] swap it. ... 5.
	 * Compare array[n-1] & array[n] 6. if [n-1] > array[n] then swap it.
	 * 
	 * After this step we will have largest element at the last index.
	 * 
	 * Repeat the same steps for array[1] to array[n-1]
	 */
	// each step, the largest number sink to the last
	public static void bubble_srt(int array[]) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 1; j < array.length; j++) {
				if (array[j - 1] > array[j]) {
					int temp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = temp;
				}
			}
			printNumbers(array);
		}
	}

	private static void printNumbers(int[] input) {
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i] + ", ");
		}
		System.out.println("\n");
	}

	public static void main(String[] args) {
		int[] input = { 5, 1, 12, -5, 16 };
		bubble_srt(input);

	}
}
