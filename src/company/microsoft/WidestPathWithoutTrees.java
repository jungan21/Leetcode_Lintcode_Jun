package company.microsoft;

import java.util.List;
import java.util.Scanner;

public class WidestPathWithoutTrees {
    public static int widestPath(List<Integer> x, List<Integer> y) {
        int maxWidth = 0;
        x.sort(null);
        for (int i = 0; i < x.size() - 1; i++) {
            maxWidth = Math.max(maxWidth, (x.get(i + 1) - x.get(i)));
        }
        return maxWidth;
    }


}
