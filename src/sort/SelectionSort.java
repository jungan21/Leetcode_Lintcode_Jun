package sort;


// Space: O(1),  Time: O(n^2) in all cases
public class SelectionSort {
	public static int[] selectionSort(int[] arr) {
		// < arr.length -1
		for (int i = 0; i < arr.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < arr.length; j++){
				if (arr[j] < arr[index])
					index = j;
			}
			int smallerNumber = arr[index];
			arr[index] = arr[i];
			arr[i] = smallerNumber;
			
//			for (int m : arr){
//				System.out.print(m + ",");
			// }
			//			System.out.println();
		}
		
		return arr;
	}

	public static void main(String a[]) {
		int[] arr = { 5, 1, 12, -5, 16, 2, 12, 14 };
		for (int i : selectionSort(arr)) {
			System.out.print(i);
			System.out.print(", ");
		}
	}
}
