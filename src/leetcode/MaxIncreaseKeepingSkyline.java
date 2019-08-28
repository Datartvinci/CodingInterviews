package leetcode;

/**
 * 寻找矩阵x和y轴各自最大值，并取较小的那个覆盖
 */
public class MaxIncreaseKeepingSkyline {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[] xMax = new int[grid[0].length];
        int[] yMax = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                xMax[i] = Math.max(grid[i][j], xMax[i]);
                yMax[j] = Math.max(grid[i][j], yMax[j]);
            }
        }
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                sum += Math.min(xMax[i], yMax[j]) - grid[i][j];
            }
        }
        return sum;
    }
}
