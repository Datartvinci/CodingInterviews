package leetcode;

public class NumDistinct {
    public static void main(String[] args) {
        System.out.println(new NumDistinct().numDistinct("babgbag", "bag"));
        ;
    }

    public int numDistinct(String s, String t) {
        return find(s, 0, t, 0);
    }

    private int find(String s, int sIndex, String t, int tIndex) {
        System.out.println("find(" + sIndex + "," + tIndex + ")");
        if (s.length() == 0 && t.length() == 0) {
            return 1;
        }
        if (tIndex == t.length()) {
            return 1;
        }
        int sum = 0;
        for (int i = sIndex; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(tIndex)) {
                sum += find(s, i + 1, t, tIndex + 1);
            }
        }
        return sum;
    }

    public int dp(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            dp[i][t.length()] = 1;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = t.length() - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }
}
