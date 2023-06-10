package company.walmart;

/**
 * https://leetcode.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/
 *
 * Input: colors = "AAABABB"
 * Output: true
 * Explanation:
 * AAABABB -> AABABB
 * Alice moves first.
 * She removes the second 'A' from the left since that is the only 'A' whose neighbors are both 'A'.
 *
 * Now it's Bob's turn.
 * Bob cannot make a move on his turn since there are no 'B's whose neighbors are both 'B'.
 * Thus, Alice wins, so return true.
 */
public class RemoveColoredPiecesNeighborsSameColor {
    public boolean winnerOfGame(String colors) {
        int countAAA = 0;
        int countBBB = 0;

        for (int i = 1; i + 1 < colors.length(); i++)
            if (colors.charAt(i - 1) == colors.charAt(i) && colors.charAt(i) == colors.charAt(i + 1))
                if (colors.charAt(i) == 'A')
                    countAAA++;
                else
                    countBBB++;

        return countAAA > countBBB;

    }
}
