package leetcode;

/**
 * For example,
 * Given board =
 * <p>
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */
public class WordSearch {
    public static void main(String[] args) {
        char[][] board=new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(new WordSearch().exist(board,"ABCCED"));
        System.out.println(new WordSearch().exist(board,"SEE"));
        System.out.println(new WordSearch().exist(board,"ABCB"));
    }
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || word.length() == 0) {
            return false;
        }
        int[][] visited = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (search(visited, board, word, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean search(int[][] visited, char[][] board, String word, int i, int j) {
        if (word.length() == 0) {
            return true;
        }
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[i].length - 1) {
            return false;
        }
        if (visited[i][j] != 0) {
            return false;
        }
        if (word.charAt(0) != board[i][j]) {
            return false;
        }
        visited[i][j] = 1;
        if (search(visited, board, word.substring(1), i + 1, j) ||
                search(visited, board, word.substring(1), i, j + 1) ||
                search(visited, board, word.substring(1), i - 1, j) ||
                search(visited, board, word.substring(1), i, j - 1)) {
            return true;
        }
        visited[i][j] = 0;
        return false;
    }

}
