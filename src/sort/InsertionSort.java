package sort;

//http://www.java2novice.com/java-sorting-algorithms/
// http://bigocheatsheet.com/

// Best O(n), Average O(n^2), Worst O(n^2), Space: O (1)
// https://www.cs.cmu.edu/~adamchik/15-121/lectures/Sorting%20Algorithms/sorting.html
public class InsertionSort {

	public static void insertionSort(int array[]) {
		for (int i = 1; i < array.length; i++) {
			// Note: j > 0
			for (int j = i; j > 0; j--) {
				if (array[j] < array[j - 1]) {
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
		int[] input = { 5, 2, 4, 6, 1, 3 };
		insertionSort(input);

	}
}
