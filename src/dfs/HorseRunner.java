package dfs;

import java.util.Formatter;

/**
 * Created by Datartvinci on 2017/3/26.
 * 计算出马在棋盘的欧拉回路
 */
public class HorseRunner {

    private static int m, n;
    private static int[] fx = new int[]{1, 1, 2, 2, -1, -1, -2, -2};
    private static int[] fy = new int[]{2, -2, 1, -1, 2, -2, 1, -1};
    private static int[][] board;
    private static long start;
    public static void main(String[] args) {
        m = 5;
        n = 6;
        board = new int[m + 1][n + 1];
        start = System.currentTimeMillis();
        run(4, 4, 1);

    }

    /**
     * @param x   当前行动的x坐标
     * @param y   当前行动的y坐标
     * @param seq 当前是第几步
     */
    private static void run(int x, int y, int seq) {
        board[x][y] = seq;
        //output the result if reaching
        if (seq == m * n) {
            output();
            System.out.println("cost time:" + (System.currentTimeMillis() - start));
            System.exit(0);
//            start=System.currentTimeMillis();
        }
        //then try 8 directors and recurse if cannot reach
        for (int i = 0; i < 8; ++i) {
            int nextX = x + fx[i];
            int nextY = y + fy[i];
            //check whether next step is validate
            if (nextX < 1 || nextY < 1 || nextX > m || nextY > n || board[nextX][nextY] != 0) {
                continue;
            }
            run(nextX, nextY, seq + 1);
            board[nextX][nextY] = 0;
        }

    }

    private static void output() {
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(new Formatter().format("%2d ", board[i][j]));
            }
            System.out.println();
        }
    }
}
