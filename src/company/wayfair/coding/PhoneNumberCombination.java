package company.wayfair.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * Question: We are interested in getting 7 digit phone number by watching a chess piece (rook) traverse a board with numbers on each board position.
 *
 * Given a 3 by 3 board that looks like this.
 *
 * 1 2 3
 * 4 5 6
 * 7 8 9
 *
 * And assuming that a rook starts on position 4, write a function which provides all of the combinations of 7 digit numbers which start with 4.
 *
 * For example:
 *
 * 456-3214 would be generated when the rook moves
 * { right one, right one, up one, left one, left one, down one }
 *
 * 464-6464 would be generated when the rook moves
 * {right two, left two, right two, left two, right two, left two }
 */
public class PhoneNumberCombination {
    private static final int MAX_PHONE_LENGTH = 7;

    public static List<String> phoneCombinations(int[][] board, int sr, int sc) {
        List<String>  combinations = new ArrayList<>();
        if (isNullOrEmpty(board) || !isSafe(board, sr, sc)) return combinations;


        StringBuilder phone = new StringBuilder(MAX_PHONE_LENGTH);
        phone.append(board[sr][sc]);
        phoneCombinations(board, combinations, phone, sr, sc);
        return combinations;
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    private static void phoneCombinations(int[][] board, List<String> phones, StringBuilder phone, int sr, int sc) {
        if (phone.length() == MAX_PHONE_LENGTH) {
            phones.add(phone.toString());
            return;
        }

        for (int i = 0; i < 4 ; i++) {
            int r = sr + dx[i];
            int c = sc + dy[i];
            if (isSafe(board, r, c)) {
                phone.append(board[r][c]);
                phoneCombinations(board, phones, phone, r, c);
                phone.setLength(phone.length() - 1);
            }
        }
    }

    private static boolean isSafe(int[][] board, int r, int c) {
        return r >= 0 && r < board.length && c >= 0 && c < board[0].length;
    }

    private static boolean isNullOrEmpty(int[][] board) {
        return board == null || board.length == 0 || board[0].length == 0;
    }
}
