package leetcode;

/**
 * Created by Datartvinci on 2017/11/19.
 */
public class WildCardMatch {
    public static void main(String[] args) {
        System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("aa","aa"));
        System.out.println(isMatch("aaa","aa"));
        System.out.println(isMatch("aa","*"));
        System.out.println(isMatch("aa","a*"));
        System.out.println(isMatch("ab","?*"));
        System.out.println(isMatch("aab","c*a*b*"));
        System.out.println(isMatch("aaaa","***a"));
//        isMatch("aa","a") → false
//        isMatch("aa","aa") → true
//        isMatch("aaa","aa") → false
//        isMatch("aa", "*") → true
//        isMatch("aa", "a*") → true
//        isMatch("ab", "?*") → true
//        isMatch("aab", "c*a*b") → false
    }
    public static boolean isMatch(String s, String p) {
        boolean[][] dp=new boolean[s.length()+1][p.length()+1];
        dp[0][0]=true;
        for(int i=1;i<=p.length();i++){
            if(p.charAt(i-1)=='*')
            {
                dp[0][i]=true;
            }else {
                break;
            }
        }
        for(int j=1;j<=p.length();j++){
            for(int i=1;i<=s.length();i++){
                if(p.charAt(j-1)=='*'){
                    dp[i][j]=dp[i-1][j]||dp[i][j-1];
                }else{
                    dp[i][j]=dp[i-1][j-1]&&(p.charAt(j-1)==s.charAt(i-1)||p.charAt(j-1)=='?');
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
