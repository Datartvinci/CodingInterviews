package leetcode;

import org.junit.Assert;

/**
 * Created by Datartvinci on 2017/5/24.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 */
public class RegularExpressionMatching {
    public static void main(String[] args) {
        Assert.assertTrue(isMatch("aa", ".*"));
        Assert.assertTrue(!isMatch("aa", "a"));
        Assert.assertTrue(!isMatch("a", "a."));
        Assert.assertTrue(!isMatch("a", "a*"));
    }

    public static boolean isMatch(String s, String p) {
        boolean result = true;
        int index = 0;
        int i=0;
        for (;;) {
            if(s.charAt(i)==p.charAt(index)){
                index++;
                i++;
            }else if(p.charAt(index)=='.'){
                continue;
            }else if(p.charAt(index)=='*'){

            }else {
                result=false;
                break;
            }
        }
        return result;
    }
}
