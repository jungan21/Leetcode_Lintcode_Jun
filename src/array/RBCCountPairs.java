package array;
import java.util.*;

public class RBCCountPairs {
	
	public static void main(String[] args) {
		int A[] = {1, 2, 1,3,  4, 3, 1,1,1};
		System.out.println(count(A));
	}

	private static int count (int[] A){
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i< A.length; i++) {
			if (map.containsKey(A[i])){
				int temp = map.get(A[i]);
				map.put(A[i], temp+1);
			} else {
				map.put(A[i], 1);
			}
		}
		System.out.println(map);
		
		int count = 0;
		// i.e Cn2 , 组合  n中任意取2个就可以， Cn2 = An2 / 2! . An2 = n! / (n-2)!
		// https://baike.baidu.com/item/%E6%8E%92%E5%88%97%E7%BB%84%E5%90%88/706498
		for (Map.Entry<Integer, Integer> entry : map.entrySet()){
			int temp = entry.getValue();
			count += (temp * (temp-1)) / 2;
			System.out.println(count);
		}
		
		return count;
	}
	
	
}
