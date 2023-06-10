package company.wayfair.coding;


/**
 * Leetcode: 223. Rectangle Area
 * https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/223-rectangle-area-easy.html
 */
public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int total = (C - A) * (D - B) + (G - E) * (H - F);
        if (C <= E || B >= H || A >= G || D<= F) {
            return total;
        }

        int a = C - E;
        int b = H - B;
        int dup = a * b;
        return total - dup;
    }
}
